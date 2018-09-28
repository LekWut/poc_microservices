package base.utils;

import org.primefaces.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;

import base.dto.ExceptionMessage;
import base.dto.ExceptionMessageDto;

//import javax.mail.AuthenticationFailedException;

//import org.hibernate.StaleObjectStateException;
//import org.hibernate.exception.ConstraintViolationException;
//import org.hibernate.exception.GenericJDBCException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.jdbc.UncategorizedSQLException;
//import org.springframework.mail.MailAuthenticationException;
//import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
//import org.springframework.transaction.CannotCreateTransactionException;

public class ExceptionUtil {

	public static void addMessage(Exception ex) {
		boolean printStackTrace = true;

		/* ให้ Catch Exception Class เรียงลำดับไปหา Super Class */
		if (ex instanceof FacadeException) {
			AppUtil.addErrorMessage(ex.getMessage());
			printStackTrace = false;
//		} else if (ex instanceof UncategorizedSQLException) {
//			AppUtil.addErrorMessage("error.cannotOpenConnection");
//		} else if (ex instanceof GenericJDBCException) {
//			AppUtil.addErrorMessage("error.exception", new String[] { ex.getCause().getMessage() });
//		} else if (ex instanceof CannotCreateTransactionException) {
//			AppUtil.addErrorMessage("error.cannotOpenConnection");
//		} else if (ex instanceof StaleObjectStateException) {
//			AppUtil.addErrorMessage("error.update.version");
//		} else if (ex instanceof HibernateOptimisticLockingFailureException) {
//			AppUtil.addErrorMessage("error.update.version");
//		} else if (ex instanceof DataIntegrityViolationException) {
//			AppUtil.addErrorMessage("error.exception.dataIntegrityViolationException");
//		} else if (ex instanceof ConstraintViolationException) {
//			AppUtil.addErrorMessage("error.exception.constraintViolationException");
//		} else if (ex instanceof AuthenticationFailedException) {
//			AppUtil.addErrorMessage("error.exception.AuthenticationFailedException");
//		} else if (ex instanceof MailAuthenticationException) {
//			AppUtil.addErrorMessage("error.exception.AuthenticationFailedException");
		} else if (ex instanceof HttpClientErrorException) {
			HttpClientErrorException cex = (HttpClientErrorException) ex;
			if (cex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				String body = cex.getResponseBodyAsString();
				try {
					JSONObject json = new JSONObject(body);
					if (json.has("hasMessage")) {
						ObjectMapper mapper = new ObjectMapper();
						ExceptionMessageDto messageDto = mapper.readValue(json.toString(), ExceptionMessageDto.class);
						for (ExceptionMessage m : messageDto.getMessageList()) {
							AppUtil.addErrorMessage(m.getMessageEn());
						}
					}
				} catch (Exception e) {
					ex.printStackTrace();
					AppUtil.addErrorMessage("error.exception", new String[] { cex.toString() });
				}
			} else {
				AppUtil.addErrorMessage("error.exception", new String[] { cex.toString() });
			}
		} else if (ex instanceof HttpServerErrorException) {
			HttpServerErrorException sex = (HttpServerErrorException) ex;
			if (sex.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				String body = sex.getResponseBodyAsString();
				try {
					JSONObject json = new JSONObject(body);
					if (json.has("hasMessage")) {
						ObjectMapper mapper = new ObjectMapper();
						ExceptionMessageDto messageDto = mapper.readValue(json.toString(), ExceptionMessageDto.class);
						for (ExceptionMessage m : messageDto.getMessageList()) {
							AppUtil.addErrorMessage(m.getMessageEn());
						}
					}
				} catch (Exception e) {
					ex.printStackTrace();
					AppUtil.addErrorMessage("error.exception", new String[] { sex.toString() });
				}
			} else {
				AppUtil.addErrorMessage("error.exception", new String[] { sex.toString() });
			}
		} else {
			AppUtil.addErrorMessage("error.exception", new String[] { ex.toString() });
		}

		/* printStackTrace */
		if (printStackTrace) {
			ex.printStackTrace();
		}
	}
}
