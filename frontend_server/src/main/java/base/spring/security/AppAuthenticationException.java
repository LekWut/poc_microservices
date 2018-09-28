package base.spring.security;

import org.springframework.security.core.AuthenticationException;

public class AppAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = -7066629303324918080L;
	private String[] messageParams;

	public AppAuthenticationException(String msg) {
		super(msg);
	}

	public AppAuthenticationException(String msgCode, String[] messageParams) {
		super(msgCode);
		this.messageParams = messageParams;
	}

	public String[] getMessageParams() {
		return messageParams;
	}

	public void setMessageParams(String[] messageParams) {
		this.messageParams = messageParams;
	}

}
