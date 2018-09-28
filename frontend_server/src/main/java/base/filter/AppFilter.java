package base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

//import com.dca.base.utils.DcaUtil;
//import eu.bitwalker.useragentutils.UserAgent;

/**
 * Servlet Filter implementation class AppFilter
 */
public class AppFilter implements Filter {
	private static Logger log = LogManager.getLogger(AppFilter.class);
//	private String IS_HTTPS_LOGIN;
//	private String HTTPS_PORT;
//	private String HTTP_PORT;
	private String invalidSessionUrl = "/pages/public/login.xhtml";
	private static final String FACES_REQUEST_HEADER = "faces-request";

	/**
	 * Default constructor.
	 */
	public AppFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		log.debug("init");
//		IS_HTTPS_LOGIN = DcaUtil.getAppConfigByKey("IS_HTTPS_LOGIN");
//		HTTPS_PORT = DcaUtil.getAppConfigByKey("HTTPS_PORT");
//		HTTP_PORT = DcaUtil.getAppConfigByKey("HTTP_PORT");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
//		if (log.isDebugEnabled()) {
//			System.out.print("\n");
//		}
		/* Redirect to HTTPS */
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
//		if ("true".equalsIgnoreCase(IS_HTTPS_LOGIN)) {
//			if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
//				log.debug("doFilter-->Method=GET");
//				if ("/pages/public/common/login.xhtml".equalsIgnoreCase(httpRequest.getServletPath())) {
//					if ("HTTP".equalsIgnoreCase(httpRequest.getScheme())) {
//						String redirectUrl = "https://" + httpRequest.getServerName();
//						if (!"443".equalsIgnoreCase(HTTPS_PORT)) {
//							redirectUrl += ":" + HTTPS_PORT;
//						}
//						redirectUrl += httpRequest.getRequestURI();
//						if (httpRequest.getQueryString() != null && httpRequest.getQueryString().trim().length() > 0) {
//							redirectUrl += "?" + httpRequest.getQueryString().trim();
//						}
//						httpResponse.sendRedirect(redirectUrl);
//						return;
//					}
//				} else {
//					if ("HTTPS".equalsIgnoreCase(httpRequest.getScheme())) {
//						UserAgent userAgent = UserAgent.parseUserAgentString(httpRequest.getHeader("User-Agent"));
//						if ("Internet Explorer".equalsIgnoreCase(userAgent.getBrowser().getGroup().getName())) {
//							String redirectUrl = "http://" + httpRequest.getServerName();
//							if (!"80".equalsIgnoreCase(HTTP_PORT)) {
//								redirectUrl += ":" + HTTP_PORT;
//							}
//							redirectUrl += httpRequest.getRequestURI();
//							if (httpRequest.getQueryString() != null
//									&& httpRequest.getQueryString().trim().length() > 0) {
//								redirectUrl += "?" + httpRequest.getQueryString().trim();
//							}
//							httpResponse.sendRedirect(redirectUrl);
//							return;
//						}
//					}
//				}
//			}
//		}

		/* ถ้า JSF Ajax request ไม่ได้ login จะ response ข้อมูล ajaxRedirectXml ออกมา */
//		log.debug(FACES_REQUEST_HEADER + " = " + httpRequest.getHeader(FACES_REQUEST_HEADER));
		boolean ajaxRedirect = "partial/ajax".equals(httpRequest.getHeader(FACES_REQUEST_HEADER));
		HttpSession httpSession = httpRequest.getSession(false);
		boolean isLogin = false;
		SecurityContext sci = null;
		if (httpSession != null) {
			sci = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		}
		if (sci != null) {
			Authentication authentication = sci.getAuthentication();
			if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
				isLogin = authentication.isAuthenticated();
			}
		}
		String contextPath = httpRequest.getContextPath();
		String redirectUrl = contextPath + invalidSessionUrl;
		if (ajaxRedirect) {
			if ((httpRequest.getServletPath().toLowerCase().startsWith("/pages/secured/") 
					|| httpRequest.getServletPath().toLowerCase().startsWith("/mobilepages/secured/"))
					&& !isLogin) {
				log.debug("Session expired due to ajax request, redirecting to " + redirectUrl);
				String ajaxRedirectXml = createAjaxRedirectXml(redirectUrl);
				log.debug("Ajax partial response to redirect: " + ajaxRedirectXml);
				httpResponse.setContentType("text/xml");
				httpResponse.getWriter().write(ajaxRedirectXml);
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();
				return;
			}
		}

		/* Pass the request along the filter chain */
		chain.doFilter(request, response);
	}

	private String createAjaxRedirectXml(String redirectUrl) {
		return new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<partial-response><redirect url=\"").append(redirectUrl)
				.append("\"></redirect></partial-response>").toString();
	}

}
