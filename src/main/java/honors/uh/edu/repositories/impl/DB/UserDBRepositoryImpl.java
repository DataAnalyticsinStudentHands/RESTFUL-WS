package honors.uh.edu.repositories.impl.DB;

import com.google.inject.Singleton;

import honors.uh.edu.infrastructure.DBConnectionHelper;
import honors.uh.edu.pojo.NullUser;
import honors.uh.edu.pojo.User;
import honors.uh.edu.repositories.contract.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserDBRepositoryImpl extends GenericDBRepository<User> implements UserRepository {
   
    

    public List<User> getAll() {    	
		
		String sql = "SELECT * FROM users as u "
				+ "GROUP BY u.id "
				+ "ORDER BY u.firstName, u.lastName";
		List<User> list = new ArrayList<User>();
		Connection c = null;

		try {
			c = DBConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return list;
    }
    
    public User getById(int id) {
    	
		String sql = "SELECT * FROM users as u "
				+ "WHERE u.id = ? " + "GROUP BY u.id"; 		
		
		User user = new NullUser();
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = processRow(rs);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);    			
		} finally {
			DBConnectionHelper.close(c);    			
		} 
    
}
    
    public List<User> getByName(String name) {
    	
    	String sql = "SELECT * FROM users as u "
				+ "WHERE UPPER(CONCAT(u.firstName, ' ', u.lastName)) LIKE ? "
				+ "GROUP BY u.id " + "ORDER BY u.firstName, u.lastName";
		List<User> list = new ArrayList<User>();
		Connection c = null;
		
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + name.toUpperCase() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return list;
	}

    @Override
	public User create(User user) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DBConnectionHelper.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO users (firstName, lastName, city, homePhone, cellPhone, email, picture) VALUES (?, ?, ?, ?, ?, ?, ?)",
					new String[] { "ID" });
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());					
			ps.setString(3, user.getCity());
			ps.setString(4, user.getHomePhone());
			ps.setString(5, user.getCellPhone());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getPicture());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must be returned to the client.
			int id = rs.getInt(1);
			user.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return user;
	}

    @Override
	public User update(User user) {
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE users SET firstName=?, lastName=?, city=?, homePhone=?, cellPhone=?, email=?, picture=? WHERE id=?");
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());			
			ps.setString(3, user.getCity());
			ps.setString(4, user.getHomePhone());
			ps.setString(5, user.getCellPhone());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getPicture());
			ps.setInt(8, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return user;
	}

    @Override
	public void remove(int id) {
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM users WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();            
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
	}

    @Override
    public int getNumberOfUsers() {
    	
    	Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT COUNT(*) FROM users");
			//ResultSet rs = ps.executeQuery("SELECT COUNT(*) FROM " + tableName);
			ResultSet rs = ps.executeQuery();
			// get the number of rows from the result set
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}         
    }
    
    public User save(User user) {
		return user.getId() > 0 ? update(user) : create(user);
	}

    protected User processRow(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));				
		user.setCity(rs.getString("city"));	
		user.setHomePhone(rs.getString("homePhone"));
		user.setCellPhone(rs.getString("cellPhone"));
		user.setEmail(rs.getString("email"));
		user.setPicture(rs.getString("picture"));

		return user;
	}  	
}
