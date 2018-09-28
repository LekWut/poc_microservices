package base.spring.security;

import java.util.Collection;
//import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.poc.frontend.dto.UserDto;

import base.test.domain.TestUser;
//import caat.stat.auth.domain.AuthMRoleMenu;

public class AppUser extends User {

	private UserDto authMUser;
	private String currentToken;
//	private List<AuthMRoleMenu> authMRoleMenuList;

	public AppUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public UserDto getAuthMUser() {
		return authMUser;
	}

	public void setAuthMUser(UserDto authMUser) {
		this.authMUser = authMUser;
	}

	public String getCurrentToken() {
		return currentToken;
	}

	public void setCurrentToken(String currentToken) {
		this.currentToken = currentToken;
	}

//	public List<AuthMRoleMenu> getAuthMRoleMenuList() {
//		return authMRoleMenuList;
//	}

//	public void setAuthMRoleMenuList(List<AuthMRoleMenu> authMRoleMenuList) {
//		this.authMRoleMenuList = authMRoleMenuList;
//	}

}
