package com.poc.frontend.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.poc.frontend.dto.CheckgradeDto;
import com.poc.frontend.dto.SuspensionDto;
import com.poc.frontend.facade.TestCheckgradeFacade;

import base.backingbean.BaseBackingBean;
import base.utils.ExceptionUtil;

@Component("bkTestCheckgrade")
@Scope("view")
public class BKTestCheckgrade extends BaseBackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKTestCheckgrade.class);
	@Autowired
	private TestCheckgradeFacade testCheckgradeFacade;
	private String studentCode;
	private boolean isStdRole;
	private List<SuspensionDto> suspensionList = new ArrayList<SuspensionDto>();
	private List<CheckgradeDto> checkgradeList = new ArrayList<CheckgradeDto>();

	@PostConstruct
	public void postConstruct() {
		log.debug("postConstruct");
		try {
			isStdRole = false;
			log.debug("getRole()::"+getCurrentUser().getAuthMUser().getRole());
			if ("STD".equals(getCurrentUser().getAuthMUser().getRole())) {
				isStdRole = true;
				studentCode = getCurrentUser().getAuthMUser().getEmail();
				action_checkgrade();
			}
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_checkgrade() {
		log.debug("::action_checkgrade::"+studentCode);
		try {
			testCheckgradeFacade.checkgradeStudentCode(this);
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}
	
	public void action_clear() {
		log.debug("action_clear");
		try {
			studentCode = null;
			suspensionList = null;
			checkgradeList = null;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public List<SuspensionDto> getSuspensionList() {
		return suspensionList;
	}

	public void setSuspensionList(List<SuspensionDto> suspensionList) {
		this.suspensionList = suspensionList;
	}

	public List<CheckgradeDto> getCheckgradeList() {
		return checkgradeList;
	}

	public void setCheckgradeList(List<CheckgradeDto> checkgradeList) {
		this.checkgradeList = checkgradeList;
	}

	public boolean isStdRole() {
		return isStdRole;
	}

	public void setStdRole(boolean isStdRole) {
		this.isStdRole = isStdRole;
	}


}
