package base.backingbean;

import java.io.ByteArrayInputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.utils.AppUtil;

@Component("bkServeImage")
@Scope("request")
public class BKServeImage extends BackingBean {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(BKServeImage.class);

	private StreamedContent blobImage;

	/**
	 * <p:graphicImage value="#{bkServeImage.blobImage}" cache="false">
	 * 		<f:param name="blobUuid" value="blob001"></f:param>
	 * </p:graphicImage>
	 * ต้องเรียกใช้งาน component แบบนี้ 
	 * ถ้าใส่  stream="false" จะเป็น Data URI ให้ value binding ไปที่ StreamedConten ใน  Backing bean ได้เลย
	 * ไม่ต้องใช้ bkServeImage
	 */
	public StreamedContent getBlobImage() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				/*
				 * So, we're rendering the view. Return a stub StreamedContent
				 * so that it will generate right URL.
				 */
				return new DefaultStreamedContent();
			} else {
				String blobUuid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
						.get("blobUuid");
				log.debug("blobUuid=" + blobUuid);
				BKSessionBean bkSessionBean = (BKSessionBean) AppUtil.getManagedBean("bkSessionBean",
						BKSessionBean.class);
				Object dataObj = bkSessionBean.getImageMap().get(blobUuid);
				if (dataObj == null) {
					log.error("data is null");
					return new DefaultStreamedContent();
				} else if (dataObj instanceof byte[]) {
					byte[] data = (byte[]) dataObj;
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
					blobImage = new DefaultStreamedContent(bis);
					return blobImage;
				} else if (dataObj instanceof UploadedFile) {
					UploadedFile data = (UploadedFile) dataObj;
					blobImage = new DefaultStreamedContent(data.getInputstream());
					return blobImage;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new DefaultStreamedContent();
	}

	public void setBlobImage(StreamedContent blobImage) {
		this.blobImage = blobImage;
	}

}
