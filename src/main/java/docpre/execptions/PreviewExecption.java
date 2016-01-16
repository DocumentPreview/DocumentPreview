package docpre.execptions;

public class PreviewExecption extends Exception {
	private static final long serialVersionUID = 1L;

	public PreviewExecption() {
		super();
	}

	public PreviewExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PreviewExecption(String message, Throwable cause) {
		super(message, cause);
	}

	public PreviewExecption(String message) {
		super(message);
	}

	public PreviewExecption(Throwable cause) {
		super(cause);
	}

	
	
}
