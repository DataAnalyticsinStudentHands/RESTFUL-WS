package dash.security;

import org.springframework.security.acls.domain.DefaultPermissionFactory;

/**
 * This is a helper class which is needed to activate the customPermissions
 * defined in CustomPermission.java
 * 
 * @author Tyler.swensen@gmail.com
 *
 */

public class CustomPermissionFactory extends DefaultPermissionFactory {
	 public CustomPermissionFactory() {
	 super();
	 registerPublicPermissions(CustomPermission.class);
	 }
	}
