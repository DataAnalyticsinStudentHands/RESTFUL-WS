package honors.uh.edu.repositories.impl.DB;

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

import com.google.inject.Singleton;

@Singleton
public class UserDBRepositoryImpl extends GenericDBRepository<User> implements
UserRepository {

	@Override
	public List<User> getAll() {

		final String sql = "SELECT * FROM users as u "
				+ "GROUP BY u.id "
				+ "ORDER BY u.firstName, u.lastName";
		final List<User> list = new ArrayList<User>();
		Connection c = null;

		try {
			c = DBConnectionHelper.getConnection();
			final Statement s = c.createStatement();
			final ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return list;
	}

	@Override
	public User getById(final int id) {

		final String sql = "SELECT * FROM users as u "
				+ "WHERE u.id = ? " + "GROUP BY u.id";

		User user = new NullUser();
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			final PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = processRow(rs);
			}
			return user;
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}

	}

	@Override
	public List<User> getByName(final String name) {

		final String sql = "SELECT * FROM users as u "
				+ "WHERE UPPER(CONCAT(u.firstName, ' ', u.lastName)) LIKE ? "
				+ "GROUP BY u.id " + "ORDER BY u.firstName, u.lastName";
		final List<User> list = new ArrayList<User>();
		Connection c = null;

		try {
			c = DBConnectionHelper.getConnection();
			final PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + name.toUpperCase() + "%");
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return list;
	}

	@Override
	public User create(final User user) {
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
			final ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			// Update the id in the returned object. This is important as this
			// value must be returned to the client.
			final int id = rs.getInt(1);
			user.setId(id);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return user;
	}

	@Override
	public User update(final User user) {
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			final PreparedStatement ps = c
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
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
		return user;
	}

	@Override
	public void remove(final int id) {
		Connection c = null;
		try {
			c = DBConnectionHelper.getConnection();
			final PreparedStatement ps = c
					.prepareStatement("DELETE FROM users WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (final Exception e) {
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
			final PreparedStatement ps = c
					.prepareStatement("SELECT COUNT(*) FROM users");
			//ResultSet rs = ps.executeQuery("SELECT COUNT(*) FROM " + tableName);
			final ResultSet rs = ps.executeQuery();
			// get the number of rows from the result set
			rs.next();
			return rs.getInt(1);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnectionHelper.close(c);
		}
	}

	public User save(final User user) {
		return user.getId() > 0 ? update(user) : create(user);
	}

	protected User processRow(final ResultSet rs) throws SQLException {
		final User user = new User();
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
