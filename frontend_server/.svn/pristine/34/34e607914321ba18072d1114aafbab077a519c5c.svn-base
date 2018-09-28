package base.utils;

public class FacadeException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorDesc;
	private String[] errorDescs;

	public FacadeException() {
		super();
	}

	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FacadeException(String message) {
		super(message);
	}

	public FacadeException(Throwable cause) {
		super(cause);
	}

	public FacadeException(String message, String errorDesc) {
		super(message);
		this.errorDesc = errorDesc;
	}

	public FacadeException(String message, String[] errorDesc) {
		super(message);
		this.setErrorDescs(errorDesc);
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public void setErrorDescs(String[] errorDescs) {
		this.errorDescs = errorDescs;
	}

	public String[] getErrorDescs() {
		return errorDescs;
	}

}
