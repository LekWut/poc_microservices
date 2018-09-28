package com.poc.frontend.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.poc.frontend.dto.UserDto;
import com.poc.frontend.facade.TestUserFacade;

import base.backingbean.BaseBackingBean;
import base.enumtype.CrudMode;
import base.utils.ExceptionUtil;

@Component("bkTestUser")
@Scope("view")
public class BKTestUser extends BaseBackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKTestUser.class);
	@Autowired
	private TestUserFacade testUserFacade;
	private List<UserDto> userList = new ArrayList<UserDto>();
	private UserDto userDto = new UserDto();
	private boolean showModal;

	@PostConstruct
	public void postConstruct() {
		log.debug("postConstruct");
		try {
			testUserFacade.initial(this);
		} catch (HttpClientErrorException httpEx) {
			log.debug("httpEx==>"+httpEx.getStatusCode());
			if (httpEx.getStatusCode()==HttpStatus.FORBIDDEN) {
				log.debug("httpEx.getStatusCode()==HttpStatus.FORBIDDEN==>"+(httpEx.getStatusCode()==HttpStatus.FORBIDDEN));
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();
				HttpServletResponse hp = (HttpServletResponse) ec.getResponse();
				hp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				ExceptionUtil.addMessage(httpEx);
			}
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_openUserModal() {
		log.debug("action_openUserModal");
		try {
			userDto = new UserDto();
			userDto.setItemMode(CrudMode.CREATE);
			showModal = true;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_closeUserModal() {
		log.debug("action_closeUserModal");
		try {
			showModal = false;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_saveNewUser() {
		log.debug("action_saveNewUser");
		try {
			if (this.userDto.getItemMode() == CrudMode.CREATE) {
				testUserFacade.saveNewUser(this);
			} else {
				testUserFacade.updateUser(this);
			}
			testUserFacade.findAllUser(this);
			showModal = false;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_delete(UserDto userDto) {
		log.debug("action_delete");
		try {
			this.userDto = userDto;
			testUserFacade.deleteUser(this);
			testUserFacade.findAllUser(this);
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_openEditUserModal(UserDto userDto) {
		log.debug("action_openEditUserModal");
		try {
			this.userDto = userDto.clone();
			this.userDto.setItemMode(CrudMode.UPDATE);
			log.debug("userDto.getId()=" + this.userDto.getId());
			showModal = true;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public boolean isShowModal() {
		return showModal;
	}

	public void setShowModal(boolean showModal) {
		this.showModal = showModal;
	}

	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

}
