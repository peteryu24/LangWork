package gmx.fwd.utils;

public class CustomException extends RuntimeException {

	//private static final long serialVersionUID = 1L;

	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(String msg, Throwable e) {
		super(msg, e);
	}
}
