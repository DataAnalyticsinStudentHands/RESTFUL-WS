package honors.uh.edu.security;

import honors.uh.edu.pojo.User;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;

/*
 * Handles the authorities table by adding and removing roles
 * 
 * Config the data source in webSecurityConfig.xml where this bean is declared.
 */
public class AuthoritiesController  extends JdbcDaoSupport {


	private InsertAuthority insertAuthority;

	// Instantiates the inner classes. Inheirited from grandparent class
	// DaoSupport.
	@Override
	protected void initDao() throws Exception {
		insertAuthority = new InsertAuthority(getDataSource());

	}

	public void create(User user, String authority) {
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
}
