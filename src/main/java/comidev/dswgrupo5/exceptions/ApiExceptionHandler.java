package comidev.dswgrupo5.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import comidev.dswgrupo5.exceptions.badRequest.BadRequestException;
import comidev.dswgrupo5.exceptions.conflict.ConflictException;
import comidev.dswgrupo5.exceptions.forbidden.ForbiddenException;
import comidev.dswgrupo5.exceptions.notFound.NotFoundException;
import comidev.dswgrupo5.exceptions.unauthorized.UnauthorizedException;

@ControllerAdvice
public class ApiExceptionHandler {
    /*
     * Capturamos el ERROR, y mandamos el ERROR en HTTP al Frontend o consumidor.
     */

    @ResponseStatus(HttpStatus.NOT_FOUND) // Devuelve este tipo de HTTP
    @ExceptionHandler({ NotFoundException.class }) // Captura estas Excepciones
    @ResponseBody // El Body :v
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ ForbiddenException.class })
    @ResponseBody
    public ErrorMessage forbbidenRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ ConflictException.class })
    @ResponseBody
    public ErrorMessage conflict(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            UnauthorizedException.class,
    // org.springframework.security.access.AccessDeniedException.class
    })
    public void unauthorized(Exception exception) {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage(exception, request.getRequestURI());
    }

}
