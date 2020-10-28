package in.myapp.main.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception Handler class for catching exceptions raised in application.
 * 
 * @author PranaySK
 */

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR_CODE = "ERROR_CODE";
	private static final String ERROR = "ERROR";
	private static final String MESSAGE = "MESSAGE";
	private static final String PATH = "PATH";

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(SUCCESS, false);
		responseBody.put(ERROR_CODE, "APIIVDT");
		responseBody.put(ERROR, ex.getParameterName() + " parameter is missing");
		responseBody.put(MESSAGE, ex.getMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(SUCCESS, false);
		responseBody.put(ERROR_CODE, "APIISE");
		responseBody.put(ERROR, ex.getLocalizedMessage());
		responseBody.put(MESSAGE, ex.getMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Returning HTTP 400 - Bad Request...");
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(SUCCESS, false);
		responseBody.put(ERROR_CODE, "APITMM");
		responseBody.put(PATH, getPath(request));
		responseBody.put(ERROR, ex.getLocalizedMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Returning HTTP 400 - Bad Request...");
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(SUCCESS, false);
		responseBody.put(ERROR_CODE, "APITMM");
		responseBody.put(PATH, getPath(request));
		responseBody.put(ERROR, ex.getLocalizedMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(SUCCESS, false);
		responseBody.put(ERROR_CODE, "APITMM");
		responseBody.put(PATH, getPath(request));
		responseBody.put(ERROR, ex.getLocalizedMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(responseBody, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public final ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex,
			WebRequest request) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(SUCCESS, false);
		responseBody.put(ERROR_CODE, "APINDF");
		responseBody.put(PATH, getPath(request));
		responseBody.put(ERROR, ex.getMessage());
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(responseBody, HttpStatus.NOT_ACCEPTABLE);
	}

	private static String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURL().toString();
	}

}