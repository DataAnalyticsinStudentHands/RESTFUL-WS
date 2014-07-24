package dash.security;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

public class CustomPermission extends BasePermission {
	 
	private static final long serialVersionUID = -8963735762723530908L;
	
	public static final Permission MEMBER   = new CustomPermission(1<<6,'B');
	 public static final Permission MANAGER    = new CustomPermission(1<<7,'M');
	 
	 protected CustomPermission(int mask) {
	   super(mask);
	 }
	 
	 protected CustomPermission(int mask, char code) {
	   super(mask, code);
	 }
	}