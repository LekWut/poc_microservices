package base.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JSFUtil {
	private static final Logger log = LogManager.getLogger(JSFUtil.class);

	/**
	 * Convenience method for setting Session variables.
	 * 
	 * @param key
	 *            object key
	 * @param object
	 *            value to store
	 */
	public static void storeOnSession(String key, Object object) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, Object> sessionState = ctx.getExternalContext().getSessionMap();
		sessionState.put(key, object);
	}

	/**
	 * Convenience method for getting Session variables.
	 * 
	 * @param key
	 *            object key
	 */
	public static Object getFromSession(String key) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, Object> sessionState = ctx.getExternalContext().getSessionMap();
		return sessionState.get(key);
	}

	public static void addFacesErrorMessage(String msg) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (msg != null) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			ctx.addMessage(null, fm);
		}
	}

	public static void addFacesInfoMessage(String msg) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (msg != null) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			ctx.addMessage(null, fm);
		}
	}

	public static void addFacesErrorMessageForComponent(String componentId, String msg) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (msg != null && msg.length() > 0) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg);
			ctx.addMessage(componentId, fm);
		}
	}

	public static void addFacesInfoMessageForComponent(String componentId, String msg) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (msg != null && msg.length() > 0) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg);
			ctx.addMessage(componentId, fm);
		}
	}

	public static String getRootViewComponentId() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		return ctx.getViewRoot().getId();
	}

	public static boolean hasMessages() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (ctx.getMessages().hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasGlobalMessages() {
		return !FacesContext.getCurrentInstance().getMessageList(null).isEmpty();
	}

	/**
	 * Internal method to pull out the correct local message bundle
	 */
	public static ResourceBundle getLocalMessageBundle() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot uiRoot = ctx.getViewRoot();
		Locale locale = uiRoot.getLocale();
		ClassLoader ldr = Thread.currentThread().getContextClassLoader();
		return ResourceBundle.getBundle(ctx.getApplication().getMessageBundle(), locale, ldr);
	}

	/**
	 * Internal method to pull out the message bundle For example fileName =
	 * view.resources.JSFMessages
	 */
	public static ResourceBundle getLocalMessageBundleByFileName(String fileName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Locale locale = Locale.US;
		if (ctx != null) {
			UIViewRoot uiRoot = ctx.getViewRoot();
			locale = uiRoot.getLocale();
		}
		ClassLoader ldr = Thread.currentThread().getContextClassLoader();
		ResourceBundle rb = ResourceBundle.getBundle(fileName, locale, ldr);
		return rb;
	}

	public static String getRequestParameter(String name) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> requestState = ctx.getExternalContext().getRequestParameterMap();
		return requestState.get(name);
	}

	public static void performNavigation(String outcome) {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler nh = context.getApplication().getNavigationHandler();
		nh.handleNavigation(context, null, outcome);
	}

	public static String getWebsiteURL() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String strPort = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		return request.getScheme() + "://" + request.getServerName() + strPort + request.getContextPath();
	}

	public static void redirectToURL(String redirectUrl) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public static void removeFromSession(String key) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, Object> sessionState = ctx.getExternalContext().getSessionMap();
		Set<String> keySet = sessionState.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String keyString = (String) it.next();
			if (keyString.trim().equals(key)) {
				sessionState.remove(keyString);
			}
		}
	}
	
	public static String getFullRequestURL() {
		HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		StringBuffer requestURL = origRequest.getRequestURL();
		String queryString = origRequest.getQueryString();
		if (queryString == null || queryString.trim().length() == 0) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}

	public static int jsfMessagesSize() {
		return FacesContext.getCurrentInstance().getMessageList().size();
	}

}
