package base.spring.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//import caat.stat.auth.dao.AuthMUserDao;
//import caat.stat.auth.domain.AuthMUser;
//import caat.stat.auth.domain.AuthMUserRole;
//import caat.stat.base.utils.CaatUtil;

@Service("commonUserDetailsService")
public class CommonUserDetailsService implements UserDetailsService {
	private static final Logger log = LogManager.getLogger(CommonUserDetailsService.class);
//	@Autowired
//	private AuthMUserDao authMUserDao;

	//@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername");
		try {
			/*
			AuthMUser authMUser = authMUserDao.findByLoginId(username);
			if (authMUser == null) {
				throw new UsernameNotFoundException(
						CaatUtil.getPreparedMessage("error.login.user.notFound", new String[] { username }));
			}
			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
			for (AuthMUserRole userRole : authMUser.getAuthMUserRoleList()) {
				setAuths.add(new SimpleGrantedAuthority(userRole.getRoleShortName().trim()));
			}
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(setAuths);
			CaatUser user = new CaatUser(authMUser.getLoginId(), authMUser.getPassword(), true, true, true, true, authorities);
			return user;
			*/
		} catch (Exception ex) {
			new UsernameNotFoundException("Exception in CommonUserDetailsService#loadUserByUsername.");
			ex.printStackTrace();
		}
		return null;
	}

}