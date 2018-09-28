package com.poc.frontend.backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.poc.frontend.dto.SuspensionDto;
import com.poc.frontend.facade.TestSuspensionFacade;

import base.backingbean.BaseBackingBean;
import base.enumtype.CrudMode;
import base.utils.ExceptionUtil;

@Component("bkTestSuspension")
@Scope("view")
public class BKTestSuspension extends BaseBackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKTestSuspension.class);
	@Autowired
	private TestSuspensionFacade testSuspensionFacade;
	private List<SuspensionDto> SuspensionList = new ArrayList<SuspensionDto>();
	private SuspensionDto suspensionDto = new SuspensionDto();
	private boolean showModal;

	@PostConstruct
	public void postConstruct() {
		log.debug("postConstruct");
		try {
			testSuspensionFacade.initial(this);
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_openSuspensionModal() {
		log.debug("action_openSuspensionModal");
		try {
			suspensionDto = new SuspensionDto();
			suspensionDto.setItemMode(CrudMode.CREATE);
			showModal = true;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_closeSuspensionModal() {
		log.debug("action_closeSuspensionModal");
		try {
			showModal = false;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_saveNewSuspension() {
		log.debug("action_saveNewSuspension");
		try {
			if (this.suspensionDto.getItemMode() == CrudMode.CREATE) {
				testSuspensionFacade.saveNewSuspension(this);
			} else {
				testSuspensionFacade.updateSuspension(this);
			}
			testSuspensionFacade.findAllSuspension(this);
			showModal = false;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_delete(SuspensionDto suspensionDto) {
		log.debug("action_delete");
		try {
			this.suspensionDto = suspensionDto;
			testSuspensionFacade.deleteSuspension(this);
			testSuspensionFacade.findAllSuspension(this);
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_openEditSuspensionModal(SuspensionDto suspensionDto) {
		log.debug("action_openEditSuspensionModal");
		try {
			this.suspensionDto = suspensionDto.clone();
			this.suspensionDto.setItemMode(CrudMode.UPDATE);
			log.debug("SuspensionDto.getId()=" + this.suspensionDto.getId());
			showModal = true;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public boolean isShowModal() {
		return showModal;
	}

	public void setShowModal(boolean showModal) {
		this.showModal = showModal;
	}

	public List<SuspensionDto> getSuspensionList() {
		return SuspensionList;
	}

	public void setSuspensionList(List<SuspensionDto> suspensionList) {
		SuspensionList = suspensionList;
	}

	public SuspensionDto getSuspensionDto() {
		return suspensionDto;
	}

	public void setSuspensionDto(SuspensionDto suspensionDto) {
		this.suspensionDto = suspensionDto;
	}

}
