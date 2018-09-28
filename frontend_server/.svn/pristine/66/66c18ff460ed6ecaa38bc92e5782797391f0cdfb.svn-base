package base.test.backingbean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//import com.dca.base.facade.TestFacade;

import base.backingbean.BKSessionBean;
import base.backingbean.BaseBackingBean;
import base.utils.AppUtil;
import base.utils.ExceptionUtil;
import base.utils.JSFUtil;
//import base.utils.JasperReportUtil;

@Component("bkTest")
@Scope("view")
public class BKTest extends BaseBackingBean {

	private static final Logger log = LogManager.getLogger(BKTest.class);
	private boolean doAction = false;
	private Integer count = 0;
	private final String FACADE = "testFacade";
	private String text;
	private ScheduleModel eventModel;
	private Date birthDate;
	private String fileUrl;
//	private List<TestName> testNames = new ArrayList<TestName>();
	private String test;
	private StreamedContent blobImage;

	@PostConstruct
	public void postConstruct() {
		log.debug("PostConstruct");
		try {
			eventModel = new DefaultScheduleModel();
			DefaultScheduleEvent event = new DefaultScheduleEvent("Birthday Party", new Date(), new Date(), true);
			event.setStyleClass("myclass");
			eventModel.addEvent(event);
			BKSessionBean bk = (BKSessionBean) AppUtil.getManagedBean("bkSessionBean", BKSessionBean.class);
			InputStream pictureIs = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/loading.gif");
			byte[] data = IOUtils.toByteArray(pictureIs);
			log.debug("picture size = "+data.length);
			bk.getImageMap().put("blob001", data);
//			testNames.add(new TestName("1", "AS", "SE"));
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        log.info("oldValue = " + oldValue);
        log.info("newValue = " + newValue);
        if (JSFUtil.hasMessages()) {
        	return;
        }
    }
	
	public String action_saveTest() {
		try {
			if (AppUtil.isBlankOrNull(test)) {
				addErrorMessage("test");
			}
			if (JSFUtil.hasMessages()) {
				return null;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String action_count() {
		log.debug("action_count");
		try {
			Thread.sleep(2000);
			if (doAction == true) {
				count++;
				log.info("Reject = " + count);
				return null;
			} else {
				doAction = true;
			}
			// do something
			Thread.sleep(3000);
			doAction = false;
			count++;
			log.debug("Accept = " + count);
		} catch (Exception ex) {
			doAction = false;
			ExceptionUtil.addMessage(ex);
		}
		return null;
	}

	public void action_test() {
		log.debug("action_test");
		try {
//			TestFacade testFacade = (TestFacade) getFacadeBean(FACADE);
//			List<TestName> list = testFacade.fetchAllTestName(this);
//			for (TestName item : list) {
//				log.debug(">>>>" + item.getFirstName());
//			}
			Thread.sleep(3000);
			JSFUtil.addFacesErrorMessage("" + new Date());
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_save() {
		log.debug("action_save");
		try {
//			TestFacade testFacade = (TestFacade) getFacadeBean(FACADE);
//			testFacade.validateSave(this);
//			if (JSFUtil.hasMessages()) {
//				return;
//			}
//			testFacade.prepareSave(this);
//			testFacade.save(this);
			addInfoMessage("info.saveComplete");
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
		return;
	}

	public void action_print() {
		try {
			String reportName = "/WEB-INF/reports/ReportTest1.jasper";
			HashMap<String, String> map = new HashMap<String, String>();
//			List<TestName> reportList = new ArrayList<TestName>();
//			TestName report = new TestName();
//			report.setUuid("001");
//			report.setFirstName("A");
//			report.setLastName("AAA");
//			report.getSubList().add(report);
//			reportList.add(report);
//			report = new TestName();
//			report.setUuid("002");
//			report.setFirstName("B");
//			report.setLastName("BBB");
//			report.getSubList().add(report);
//			reportList.add(report);
			List<String> list = new ArrayList<String>();
			list.add("1");
			list.add("2");
//			JasperReportUtil.generatePdfReport(reportName, list, map, null, "ReportTest1");
			log.debug("finish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void action_testCalendar() {
		try {
			log.debug("Start");
			log.debug("::: birthDate = " + birthDate);
			log.debug("finish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void action_checkUrl() {
		try {
			HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			log.debug(origRequest.getRequestURL());
			log.debug(origRequest.getRequestURI());
			log.debug(origRequest.getServletPath());
			log.debug(origRequest.getQueryString());
			FacesContext.getCurrentInstance().getExternalContext()
					.setResponseStatus(HttpServletResponse.SC_UNAUTHORIZED);
			FacesContext.getCurrentInstance().responseComplete();
			return;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public void action_testFileServlet() {
		try {
//			InputStream pictureIs = FacesContext.getCurrentInstance().getExternalContext()
//					.getResourceAsStream("/resources/images/calendar.pdf");
//			byte[] data = IOUtils.toByteArray(pictureIs);
//			Blob blobData = new SerialBlob(data);
//			fileUrl = AppUtil.getBlobUrlDownload("file1", blobData);
//			fileUrl = AppUtil.getBlobUrlPreview("file1", blobData);
			String filePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/calendar.pdf");
			fileUrl = AppUtil.getFileUrlPreview(filePath);  
//			fileUrl = AppUtil.getFileUrlDownload(filePath);
			System.out.println("fileUrl=" + fileUrl);
			//RequestContext.getCurrentInstance().execute("alert('test');");
			 RequestContext.getCurrentInstance().execute("$('#tabViewID\\\\:testFormTab6\\\\:downloadPDF')[0].click();");  
			return;
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}
	
	public void action_genImage() {
		try {
			//Graphic Text
            BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImg.createGraphics();
            g2.drawString("This is a text", 0, 10);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImg, "png", os);		
            blobImage = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()));
		} catch (Exception ex) {
			ExceptionUtil.addMessage(ex);
		}
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

//	public List<TestName> getTestNames() {
//		return testNames;
//	}

//	public void setTestNames(List<TestName> testNames) {
//		this.testNames = testNames;
//	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public StreamedContent getBlobImage() {
		return blobImage;
	}

	public void setBlobImage(StreamedContent blobImage) {
		this.blobImage = blobImage;
	}

}
