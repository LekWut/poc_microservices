package base.backingbean;

import java.net.URLDecoder;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.utils.AppUtil;
import base.utils.ExceptionUtil;

@Component("bkViewBean")
@Scope("view")
public class BKViewBean extends BackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKViewBean.class);
	private String loadViewBeanId = "loadViewBeanId";
	private final String FACADE = "viewBeanFacade";
//	private AuthMProgram currentProgram;

	@PostConstruct
	public void postConstruct() {
		try {
			HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			String reqServletPath = origRequest.getServletPath();
			String reqQueryString = null;
			if (!AppUtil.isBlankOrNull(origRequest.getQueryString())) {
				reqQueryString = URLDecoder.decode(origRequest.getQueryString(), "UTF-8");
			}
//			 log.debug("reqServletPath::" + reqServletPath);
//			 log.debug("reqQueryString::" + reqQueryString);
			Object sessionBeanObj = AppUtil.getManagedBean("bkSessionBean", BKSessionBean.class);
			if (sessionBeanObj != null) {
				BKSessionBean bkSessionBean = (BKSessionBean) sessionBeanObj;
				if (bkSessionBean.getUser() == null) {
					bkSessionBean.setUser(AppUtil.getCurrentUser());
				}
				prepareTempSessionVar(bkSessionBean);
				prepareJSFMessage(bkSessionBean);	
				
//				List<CaatMenuItem> allMenuList = bkSessionBean.getAllMenuList();
//				for (CaatMenuItem item : allMenuList) {
//					if (item.getProgram() != null) {
//						checkProgramByRequestUrl(item.getProgram(), reqServletPath, reqQueryString);
//						if (currentProgram != null) {
//							break;
//						}
//					}
//				}
//				if (currentProgram == null) {
//					checkProgramByRequestUrl(bkSessionBean.getAuthorizedProgram(), reqServletPath, reqQueryString);
//				}
//				bkSessionBean.setAuthorizedProgram(null);
			}
			
//			if (currentProgram == null) {
//				if ((reqServletPath.startsWith("/pages/secured/") || reqServletPath.startsWith("/mobilePages/secured/")) 
//					&& !reqServletPath.startsWith("/pages/secured/home.xhtml")
//					&& !reqServletPath.startsWith("/pages/secured/auth/authChangePasswordPage.xhtml")) {
//					JSFUtil.redirectToURL(JSFUtil.getWebsiteURL() + "/help/pageUnauthorized.xhtml");
//					return;
//				} else {
//					ViewBeanFacade viewBeanFacade = (ViewBeanFacade) AppUtil.getSpringBean(FACADE);
//					List<AuthMProgram> programList = viewBeanFacade.findProgramByServletPath(reqServletPath);
//					for (AuthMProgram program : programList) {
//						checkProgramByRequestUrl(program, reqServletPath, reqQueryString);
//						if (currentProgram != null) {
//							break;
//						}
//					}
//				}
//			}
			
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}


	private void prepareJSFMessage(BKSessionBean bkSessionBean) {
		if (!AppUtil.isBlankOrNull(bkSessionBean.getRedirectErrorMessageCode())) {
			AppUtil.addErrorMessage(bkSessionBean.getRedirectErrorMessageCode());
		}
		if (!AppUtil.isBlankOrNull(bkSessionBean.getRedirectInfoMessageCode())) {
			AppUtil.addInfoMessage(bkSessionBean.getRedirectInfoMessageCode());
		}
		bkSessionBean.setRedirectErrorMessageCode(null);
		bkSessionBean.setRedirectInfoMessageCode(null);
	}

	private void prepareTempSessionVar(BKSessionBean bkSessionBean) {
		/* ลบค่าจาก Session */
		bkSessionBean.setImageMap(new HashMap<String, Object>());
	}

//	private void checkProgramByRequestUrl(AuthMProgram checkProgram, String reqServletPath, String reqQueryString) {
//		if (checkProgram == null) {
//			return;
//		}
//		String authQueryString = null;
//		String authServletPath = null;
//		int index = checkProgram.getUrl().indexOf("?");
//		if (index >= 0) {
//			authQueryString = checkProgram.getUrl().substring(index + 1);
//			authServletPath = checkProgram.getUrl().substring(0, index);
//		} else {
//			authServletPath = checkProgram.getUrl().trim();
//		}
//		 log.debug("checkProgramUrl::" + checkProgram.getUrl());
//		 log.debug("authServletPath::" + authServletPath);
//		 log.debug("authQueryString::" + authQueryString);
//		if (authServletPath.equals(reqServletPath)) {
//			if (AppUtil.isBlankOrNull(authQueryString)) {
//				currentProgram = checkProgram;
//				return;
//			} else {
//				if (authQueryString.equals(reqQueryString) || reqQueryString.startsWith(authQueryString + "&")) {
//					currentProgram = checkProgram;
//					return;
//				}
//			}
//		}
//		return;
//	}

	public String getDisplayCurrentProgramName() {
//		if (currentProgram != null) {
//			return currentProgram.getProgramNameByLocal();
//		} else {
			return null;
//		}
	}

//	public String getDisplayCurrentProgramCode() {
//		if (currentProgram != null) {
//			return currentProgram.getProgramCode();
//		} else {
//			return null;
//		}
//	}

//	public AuthMProgram getCurrentProgram() {
//		return currentProgram;
//	}

//	public void setCurrentProgram(AuthMProgram currentProgram) {
//		this.currentProgram = currentProgram;
//	}

	public String getLoadViewBeanId() {
		return loadViewBeanId;
	}
	
	public String getLoadViewBean() {
		return "";
	}

}
