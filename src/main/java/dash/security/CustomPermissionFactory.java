package dash.security;

import org.springframework.security.acls.domain.DefaultPermissionFactory;

public class CustomPermissionFactory extends DefaultPermissionFactory {
	 public CustomPermissionFactory() {
	 super();
	 registerPublicPermissions(CustomPermission.class);
	 }
	}
