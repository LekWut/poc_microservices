package base.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.backingbean.BKSessionBean;
import base.spring.security.AppUser;
import base.utils.AppUtil;
//import caat.stat.common.facade.AuthTLogFacade;

/**
 * Application Lifecycle Listener implementation class SessionCounter
 * 
 */
public class SessionInvalidate implements HttpSessionListener {
	private static final Logger log = LogManager.getLogger(SessionInvalidate.class);

	/**
	 * Default constructor.
	 */
	public SessionInvalidate() {
		// do nothing
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				System.out.print("\n");
				log.debug("sessionDestroyed");
			}
			HttpSession httpsession = event.getSession();
			AppUser user = AppUtil.getCurrentUser();
			String method = "";
			if (user != null) {
				log.debug(user.getUsername() + " already logout!");
				method = "logout";
			} else {
				Object sessionBeanObj = httpsession.getAttribute("bkSessionBean");
				if (sessionBeanObj != null) {
					BKSessionBean bkSessionBean = (BKSessionBean) sessionBeanObj;
					user = bkSessionBean.getUser();
					if (user != null) {
						log.debug(user.getUsername() + " already destroyed!");
						method = "destroyed";
					}
				}
			}
			/* save log */
//			if (user != null) {
//				ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(httpsession
//						.getServletContext());
//				AuthTLogFacade authTLogFacade = (AuthTLogFacade) ctx.getBean("authTLogFacade");
//				HttpServletRequest request = null;
//				try {
//					request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//				} catch (Exception ex) {
//				}
//				authTLogFacade.saveLogout(user, httpsession, request, method);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		if (log.isDebugEnabled()) {
			System.out.print("\n");
			log.debug("sessionCreated");
		}
	}

}
