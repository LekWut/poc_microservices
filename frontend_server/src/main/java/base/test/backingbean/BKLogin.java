package base.test.backingbean;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.backingbean.BaseBackingBean;
import base.spring.security.AppAuthenticationException;
import base.spring.security.CommonAuthenticationSuccessHandler;
import base.utils.AppUtil;
import base.utils.ExceptionUtil;
import base.utils.JSFUtil;

@Component("bkLogin")
@Scope("request")
public class BKLogin extends BaseBackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKLogin.class);
	private String username;
	private String password;

	@PostConstruct
	public void postConstruct() {
		log.debug("postConstruct");
		try {
			if (AppUtil.isAuthenticated()) {
				JSFUtil.redirectToURL(JSFUtil.getWebsiteURL() + CommonAuthenticationSuccessHandler.HOME_PAGE);
				return;
			}
			if (!AppUtil.isBlankOrNull(JSFUtil.getRequestParameter("error"))) {
				Object lastExceptionObj = JSFUtil.getFromSession("SPRING_SECURITY_LAST_EXCEPTION");
				if (lastExceptionObj != null) {
					Exception lastException = (Exception) lastExceptionObj;
					if (lastException instanceof AppAuthenticationException) {
						AppAuthenticationException appEx = (AppAuthenticationException) lastException;
						AppUtil.addErrorMessage(appEx.getMessage(),
								appEx.getMessageParams() == null ? new String[] { "" } : appEx.getMessageParams());
					} else {
						JSFUtil.addFacesErrorMessage(lastException.getMessage());
					}
				} else {
					JSFUtil.addFacesErrorMessage("Unknown exception");
				}
				// BadCredentialsException
				// InternalAuthenticationServiceException
			}
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
