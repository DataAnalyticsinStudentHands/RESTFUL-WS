package dash.security;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;

import dash.pojo.User;

/**
 * Handles the authorities table by adding and removing roles for the UserResource.
 * 
 * Config the data source in webSecurityConfig.xml where this bean is declared.
 * 
 * @author Tyler.swensen@gmail.com
 *
 */
public class UserLoginController  extends JdbcDaoSupport {


	private InsertAuthority insertAuthority;
	private InsertLogin insertLogin;
	private ResetPassword resetPassword;

	// Instantiates the inner classes. Inheirited from grandparent class
	// DaoSupport.
	@Override
	protected void initDao() throws Exception {
		insertLogin = new InsertLogin(getDataSource());
		insertAuthority = new InsertAuthority(getDataSource());
		resetPassword= new ResetPassword(getDataSource());

	}

	public void create(User user, String authority) {
		insertLogin.insert(user);
		insertAuthority.insert(user, authority);
	}
	
	public void passwordReset(User user){
		resetPassword.reset(user);
	}

	/********* Inner Classes  ************/
	protected class InsertAuthority extends SqlUpdate {
		protected InsertAuthority(DataSource ds) {
			super(ds, "INSERT INTO authorities (username, authority) VALUES (?, ?)");
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		protected void insert(User user, String authority) {
			Object[] objs = new Object[] { user.getUsername(), authority };
			super.update(objs);
		}

	}
	
	protected class InsertLogin extends SqlUpdate {
		protected InsertLogin(DataSource ds) {
			super(ds, "INSERT INTO login VALUES (?, ?, ?, ?)");
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.TINYINT));
			declareParameter(new SqlParameter(Types.INTEGER));
			compile();
		}

		protected void insert(User user) {
			Object[] objs = new Object[] { user.getUsername(), user.getPassword(), 1, user.getId()  };
			super.update(objs);
		}

	}
	
	protected class ResetPassword extends SqlUpdate {
		protected ResetPassword(DataSource ds) {
			super(ds, "UPDATE `login` SET `password` = ? WHERE `login`.`id` = ? ;");
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			compile();
		}
		
		protected void reset(User user){
			Object[] objs = new Object[] {user.getPassword(), user.getId()};
			super.update(objs);
		}
	}
	
}
