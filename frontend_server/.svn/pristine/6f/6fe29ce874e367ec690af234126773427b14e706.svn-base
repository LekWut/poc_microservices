package base.test.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.backingbean.BaseBackingBean;
import base.test.domain.TestUser;
import base.test.facade.SecurityTestingFacade;
import base.utils.ExceptionUtil;

@Component("bkSecurityTesting")
@Scope("view")
public class BKSecurityTesting extends BaseBackingBean {

	private static final Logger log = LogManager.getLogger(BKSecurityTesting.class);
	private String username;
	private String userType;
	private String text;
	private String sampleText;
	private final String FACADE = "securityTestingFacade";
	private List<TestUser> userList = new ArrayList<TestUser>();

	@PostConstruct
	public void postConstruct() {
		log.debug("PostConstruct");
		try {
			sampleText = "<script type='text/javascript'>alert('xss');</script>";
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_sqlInjection() {
		log.debug("action_sqlInjection");
		try {
			SecurityTestingFacade facade = (SecurityTestingFacade) getFacadeBean(FACADE);
			facade.sqlInjection(this);
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
		return;
	}

	public void action_preventSqlInjection() {
		log.debug("action_preventSqlInjection");
		try {
			SecurityTestingFacade facade = (SecurityTestingFacade) getFacadeBean(FACADE);
			facade.preventSqlInjection(this);
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
		return;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<TestUser> getUserList() {
		return userList;
	}

	public void setUserList(List<TestUser> userList) {
		this.userList = userList;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSampleText() {
		return sampleText;
	}

	public void setSampleText(String sampleText) {
		this.sampleText = sampleText;
	}

}
