package base.backingbean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.enumtype.CrudMode;
import base.spring.security.AppUser;
import base.utils.AppUtil;
import base.utils.JSFUtil;

public abstract class BaseBackingBean extends BackingBean {
	private static final Logger log = LogManager.getLogger(BaseBackingBean.class);
	private static final long serialVersionUID = 1L;
	protected CrudMode mode = null;
	private String currentDateStr;
	protected int rowsPerPage = 5;
	protected int rowsPerPage2 = 5;
	protected int rowsPerPage3 = 5;
	protected int rowsPerPage4 = 5;
	protected int rowsPerPage5 = 5;
	protected int rowsPerPage6 = 5;
	protected String currentPageReportTemplate = "Showing {startRecord}-{endRecord} of {totalRecords}";
	protected String paginatorTemplate = "{CurrentPageReport} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}";
	protected String shortPaginatorTemplate = "{FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink}";
	protected String rowsPerPageTemplate5 = "5,10,15,20";
	protected String rowsPerPageTemplate10 = "10,20,30,40";
	protected List<FacesMessage> jsfMessageList = new ArrayList<FacesMessage>();

	public BaseBackingBean() {

	}

	protected static Object getFacadeBean(String facadeName) {
		return AppUtil.getSpringBean(facadeName);
	}

	protected void addInfoMessage(String msgCode) {
		AppUtil.addMessage(msgCode, new String[] { "" }, "1");
	}

	protected void addInfoMessage(String msgCode, String[] msgParams) {
		AppUtil.addMessage(msgCode, msgParams, "1");
	}
	
	protected void addInfoMessageForComponent(String componentId, String msgCode) {
		AppUtil.addMessageForComponent(componentId, msgCode, new String[] { "" }, "1");
	}

	protected void addInfoMessageForComponent(String componentId, String msgCode, String[] msgParams) {
		AppUtil.addMessageForComponent(componentId, msgCode, msgParams, "1");
	}

	protected void addErrorMessage(String msgCode) {
		AppUtil.addMessage(msgCode, new String[] { "" }, "2");
	}

	protected void addErrorMessage(String msgCode, String[] msgParams) {
		AppUtil.addMessage(msgCode, msgParams, "2");
	}
	
	protected void addErrorMessageForComponent(String componentId, String msgCode) {
		AppUtil.addMessageForComponent(componentId, msgCode, new String[] { "" }, "2");
	}

	protected void addErrorMessageForComponent(String componentId, String msgCode, String[] msgParams) {
		AppUtil.addMessageForComponent(componentId, msgCode, msgParams, "2");
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

	public AppUser getCurrentUser() {
		return AppUtil.getCurrentUser();
	}

	protected void addRedirectInfoMessage(String msgCode) {
		AppUtil.addRedirectInfoMessage(msgCode);
	}

	protected void addRedirectErrorMessage(String msgCode) {
		AppUtil.addRedirectErrorMessage(msgCode);
	}

	public String getCurrentDateStr() {
		if (currentDateStr == null) {
			Calendar c = Calendar.getInstance(Locale.US);
			c.setTime(new Date());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			currentDateStr = simpleDateFormat.format(c.getTime());
		}
		return currentDateStr;
	}

	public CrudMode getMode() {
		return mode;
	}

	public void setMode(CrudMode mode) {
		this.mode = mode;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getPaginatorTemplate() {
		return paginatorTemplate;
	}

	public void setPaginatorTemplate(String paginatorTemplate) {
		this.paginatorTemplate = paginatorTemplate;
	}

	public String getCurrentPageReportTemplate() {
		return currentPageReportTemplate;
	}

	public void setCurrentPageReportTemplate(String currentPageReportTemplate) {
		this.currentPageReportTemplate = currentPageReportTemplate;
	}

	public String getRowsPerPageTemplate5() {
		return rowsPerPageTemplate5;
	}

	public void setRowsPerPageTemplate5(String rowsPerPageTemplate5) {
		this.rowsPerPageTemplate5 = rowsPerPageTemplate5;
	}

	public String getRowsPerPageTemplate10() {
		return rowsPerPageTemplate10;
	}

	public void setRowsPerPageTemplate10(String rowsPerPageTemplate10) {
		this.rowsPerPageTemplate10 = rowsPerPageTemplate10;
	}

	public void setCurrentDateStr(String currentDateStr) {
		this.currentDateStr = currentDateStr;
	}

	public List<FacesMessage> getJsfMessageList() {
		return jsfMessageList;
	}

	public void setJsfMessageList(List<FacesMessage> jsfMessageList) {
		this.jsfMessageList = jsfMessageList;
	}

	public void addFilterInfoMessage(String msgCode, String[] msgParams) {
		if (msgParams == null) {
			msgParams = new String[] {};
		}
		String msg = AppUtil.getPreparedMessage(msgCode, msgParams);
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		jsfMessageList.add(fm);
	}

	public void addFilterErrorMessage(String msgCode, String[] msgParams) {
		if (msgParams == null) {
			msgParams = new String[] {};
		}
		String msg = AppUtil.getPreparedMessage(msgCode, msgParams);
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
		jsfMessageList.add(fm);
	}

	public void listener_filterPtabel() {
		for (FacesMessage fm : jsfMessageList) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(ctx.getViewRoot().getId(), fm);
		}
		jsfMessageList = new ArrayList<FacesMessage>();
	}

	public boolean hasFilterMessage() {
		boolean isHasMessage = false;
		if (JSFUtil.hasMessages() || (jsfMessageList != null && jsfMessageList.size() > 0)) {
			isHasMessage = true;
		}
		return isHasMessage;
	}

	public String getShortPaginatorTemplate() {
		return shortPaginatorTemplate;
	}

	public void setShortPaginatorTemplate(String shortPaginatorTemplate) {
		this.shortPaginatorTemplate = shortPaginatorTemplate;
	}

	public int getRowsPerPage2() {
		return rowsPerPage2;
	}

	public void setRowsPerPage2(int rowsPerPage2) {
		this.rowsPerPage2 = rowsPerPage2;
	}

	public int getRowsPerPage3() {
		return rowsPerPage3;
	}

	public void setRowsPerPage3(int rowsPerPage3) {
		this.rowsPerPage3 = rowsPerPage3;
	}

	public int getRowsPerPage4() {
		return rowsPerPage4;
	}

	public void setRowsPerPage4(int rowsPerPage4) {
		this.rowsPerPage4 = rowsPerPage4;
	}

	public int getRowsPerPage5() {
		return rowsPerPage5;
	}

	public void setRowsPerPage5(int rowsPerPage5) {
		this.rowsPerPage5 = rowsPerPage5;
	}

	public int getRowsPerPage6() {
		return rowsPerPage6;
	}

	public void setRowsPerPage6(int rowsPerPage6) {
		this.rowsPerPage6 = rowsPerPage6;
	}

}
