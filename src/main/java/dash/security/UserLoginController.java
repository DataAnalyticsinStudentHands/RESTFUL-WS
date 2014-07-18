package dash.security;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;

import dash.pojo.User;

/*
 * Handles the authorities table by adding and removing roles
 * 
 * Config the data source in webSecurityConfig.xml where this bean is declared.
 */
public class UserLoginController  extends JdbcDaoSupport {


	private InsertAuthority insertAuthority;
	private InsertLogin insertLogin;

	// Instantiates the inner classes. Inheirited from grandparent class
	// DaoSupport.
	@Override
	protected void initDao() throws Exception {
		insertLogin = new InsertLogin(getDataSource());
		insertAuthority = new InsertAuthority(getDataSource());

	}

	public void create(User user, String authority) {
		insertLogin.insert(user);
		insertAuthority.insert(user, authority);
	}

	/********* Inner Classes  ************/
	protected class InsertAuthority extends SqlUpdate {
		protected InsertAuthority(DataSource ds) {
			super(ds, "INSERT INTO authorities VALUES (?, ?)");
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
	
}
