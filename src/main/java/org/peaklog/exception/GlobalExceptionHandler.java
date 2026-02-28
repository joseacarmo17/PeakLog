package org.peaklog.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiError> handleServiceException(
            ServiceException ex,
            HttpServletRequest request) {

        ErrorCode errorCode = ex.getErrorCode();

        ApiError apiError = new ApiError(
                errorCode.getStatus().value(),
                errorCode.getStatus().getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(apiError, errorCode.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ApiError apiError = new ApiError(
                500,
                "Internal Server Error",
                "Ha ocurrido un error inesperado",
                request.getRequestURI()
        );

        return ResponseEntity.status(500).body(apiError);
    }
}