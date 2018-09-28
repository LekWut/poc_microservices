package base.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//import caat.stat.base.utils.CaatUtil;
//import caat.stat.auth.dao.AuthMRoleMenuDao;
//import caat.stat.auth.domain.AuthMRoleMenu;


@Service("commonAuthenticationSuccessHandler")
public class CommonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger log = LogManager.getLogger(CommonAuthenticationSuccessHandler.class);
//	@Autowired
//	private AuthMRoleMenuDao authMRoleMenuDao;
	public static final String HOME_PAGE = "/pages/secured/home.xhtml";

	//@Transactional(readOnly = true)
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		log.debug("onAuthenticationSuccess");
		try {
			/* initialization logic after login */
			
//			CaatUser user = CaatUtil.getCurrentUser();
//			log.debug("username=" + user.getUsername());
//			if (user.getAuthMUser().getAuthMUserRoleList() != null && user.getAuthMUser().getAuthMUserRoleList().size() > 0) {
//				List<AuthMRoleMenu> roleMenuList = authMRoleMenuDao.findMenuLevelByRoleName(user.getAuthMUser().getAuthMUserRoleList());
//				user.setAuthMRoleMenuList(roleMenuList);
//			}
			
			Object sessionBeanObj = request.getSession().getAttribute("bkSessionBean");
			if (sessionBeanObj != null) {
				request.getSession().removeAttribute("bkSessionBean");
			}
			/* save log */
//			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
//					.getServletContext());
//			AuthTLogFacade authTLogFacade = (AuthTLogFacade) ctx.getBean("authTLogFacade");
//			authTLogFacade.saveLogin(user, request.getSession(), request);			
			/* redirect */
			SavedRequest savedReq = new HttpSessionRequestCache().getRequest(request, response);
			String redirectUrl = request.getContextPath() + HOME_PAGE;
			if (savedReq != null) {
				redirectUrl = savedReq.getRedirectUrl();
			}
			response.sendRedirect(redirectUrl);
			log.debug("After login redirectUrl = " + redirectUrl);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
