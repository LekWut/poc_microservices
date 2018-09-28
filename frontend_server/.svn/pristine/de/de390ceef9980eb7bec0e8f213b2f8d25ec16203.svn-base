package base.backingbean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.spring.security.AppUser;
import base.utils.AppUtil;
import base.utils.JSFUtil;

@Component("bkRequestBean")
@Scope("request")
public class BKRequestBean extends BackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKRequestBean.class);

	@PostConstruct
	public void postConstruct() {

	}

	public boolean isHasMessages() {
		return JSFUtil.hasMessages();
	}

	public boolean isHasGlobalMessages() {
		return JSFUtil.hasGlobalMessages();
	}

	public int jsfGlobalMessagesSize() {
		return FacesContext.getCurrentInstance().getMessageList(null).size();
	}

	public void action_closeMessagePopup() {

	}

	public boolean isAuthenticated() {
		return AppUtil.isAuthenticated();
	}

	public String getLoginUsername() {
		AppUser appUser = AppUtil.getCurrentUser();
		if (appUser != null) {
			return appUser.getUsername();
		}
		return null;
	}

	public String getMessagePopupTitle() {
		boolean hasError = AppUtil.hasErrorLevelMessage();
		if (hasError) {
			return AppUtil.getAppLabelByKey("base.errorPopup.title");
		} else {
			return AppUtil.getAppLabelByKey("base.infoPopup.title");
		}
	}

	public String getWebsiteURL() {
		return JSFUtil.getWebsiteURL();
	}

}
