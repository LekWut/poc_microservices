package base.backingbean;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.spring.security.AppUser;
import base.utils.AppUtil;
import base.utils.JSFUtil;
//import caat.stat.auth.domain.AuthMProgram;

@Component("bkSessionBean")
@Scope("session")
public class BKSessionBean extends BackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKSessionBean.class);
	private AppUser user;
	private String viewLocale = "th";
	private Map<String, Object> imageMap = new HashMap<String, Object>();
	/* Message code */
	private String redirectInfoMessageCode;
	private String redirectErrorMessageCode;
	// private AuthMProgram authorizedProgram;

	@PostConstruct
	public void postConstruct() {
		log.debug("PostConstruct");
	}

	public StreamedContent getBlobImage() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				/*
				 * So, we're rendering the view. Return a stub StreamedContent so that it will
				 * generate right URL.
				 */
				return new DefaultStreamedContent();
			} else {
				String blobUuid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
						.get("blobUuid");
				Object dataObj = getImageMap().get(blobUuid);
				if (dataObj == null) {
					log.error("data is null");
					return new DefaultStreamedContent();
				} else if (dataObj instanceof byte[]) {
					byte[] data = (byte[]) dataObj;
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
					return new DefaultStreamedContent(bis);
				} else if (dataObj instanceof UploadedFile) {
					UploadedFile data = (UploadedFile) dataObj;
					return new DefaultStreamedContent(data.getInputstream());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new DefaultStreamedContent();
	}

	public void action_toggleLanguage() {
		viewLocale = viewLocale.equalsIgnoreCase("en") ? "th" : "en";
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(viewLocale));
	}

	public String getWebsiteUrl() {
		return JSFUtil.getWebsiteURL();
	}

	public boolean isAuthenticated() {
		return AppUtil.isAuthenticated();
	}

	public String getViewLocale() {
		return viewLocale;
	}

	public void setViewLocale(String viewLocale) {
		this.viewLocale = viewLocale;
	}

	public Map<String, Object> getImageMap() {
		return imageMap;
	}

	public void setImageMap(Map<String, Object> imageMap) {
		this.imageMap = imageMap;
	}

	public String getRedirectInfoMessageCode() {
		return redirectInfoMessageCode;
	}

	public void setRedirectInfoMessageCode(String redirectInfoMessageCode) {
		this.redirectInfoMessageCode = redirectInfoMessageCode;
	}

	public String getRedirectErrorMessageCode() {
		return redirectErrorMessageCode;
	}

	public void setRedirectErrorMessageCode(String redirectErrorMessageCode) {
		this.redirectErrorMessageCode = redirectErrorMessageCode;
	}

	public AppUser getUser() {
		if (user == null) {
			user = AppUtil.getCurrentUser();
		}
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public static boolean isSameDomainNameWithHost(String url) {
		try {
			return AppUtil.getDomainNameExWww(url)
					.equalsIgnoreCase(AppUtil.getDomainNameExWww(JSFUtil.getWebsiteURL()));
		} catch (Exception ex) {
			return true;
		}
	}

	// public AuthMProgram getAuthorizedProgram() {
	// return authorizedProgram;
	// }

	// public void setAuthorizedProgram(AuthMProgram authorizedProgram) {
	// this.authorizedProgram = authorizedProgram;
	// }

}
