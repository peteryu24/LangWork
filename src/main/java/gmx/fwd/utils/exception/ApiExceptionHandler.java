package gmx.fwd.utils.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gmx.fwd.utils.GmxResult;

@ControllerAdvice
public class ApiExceptionHandler {

	@Autowired
	private GmxResult gmxResult;

	@ExceptionHandler(LangWorkItemNotFoundException.class)
	public GmxResult handleException(LangWorkItemNotFoundException infe) {
		ApiErrorResponse response = new ApiErrorResponse("error-0001", "No book is found with mgrSeq: " + infe.getMgrSeq());
		return gmxResult.resultError("No LangWork item is found", response);
	}
}
