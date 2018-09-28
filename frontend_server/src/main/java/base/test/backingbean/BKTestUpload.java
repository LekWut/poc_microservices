package base.test.backingbean;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import base.backingbean.BaseBackingBean;

@Component("bkTestUpload")
@Scope("view")
public class BKTestUpload extends BaseBackingBean {
	private static final Logger log = LogManager.getLogger(BKTestUpload.class);
	private UploadedFile file;

	@PostConstruct
	public void initial() {
		log.debug("::: inital ");
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (event.getFile() != null) {
			log.debug("::: event.getFile().getFileName() = " + event.getFile().getFileName());
			addInfoMessage("::: Succesful " + event.getFile().getFileName() + " is uploaded.");
		} else {
			log.debug("::: No file ");
		}
	}

	public void action_upload() {
		log.debug("::: file.getFileName() = " + file.getFileName());
		addInfoMessage("::: Succesful " + file.getFileName() + " is uploaded.");
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
