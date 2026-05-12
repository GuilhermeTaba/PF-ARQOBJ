package com.example.PF.common;

import com.example.PF.common.ErrorDTO;
import com.example.PF.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventoAlreadyExists.class)
    public ResponseEntity<ErrorDTO> handleEventoAlreadyExists(
            EventoAlreadyExists ex,
            HttpServletRequest request
    ) {
        return buildError(HttpStatus.CONFLICT, "EVENTO_ALREADY_EXISTS", ex, request);
    }

    @ExceptionHandler(EventoNotFound.class)
    public ResponseEntity<ErrorDTO> handleEventoNotFound(
            EventoNotFound ex,
            HttpServletRequest request
    ) {
        return buildError(HttpStatus.NOT_FOUND, "EVENTO_NOT_FOUND", ex, request);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExists(
            UserAlreadyExists ex,
            HttpServletRequest request
    ) {
        return buildError(HttpStatus.CONFLICT, "USER_ALREADY_EXISTS", ex, request);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorDTO> handleUserNotFound(
            UserNotFound ex,
            HttpServletRequest request
    ) {
        return buildError(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", ex, request);
    }

    @ExceptionHandler(UsuarioNotAdmin.class)
    public ResponseEntity<ErrorDTO> handleUsuarioNotAdmin(
            UsuarioNotAdmin ex,
            HttpServletRequest request
    ) {
        return buildError(HttpStatus.FORBIDDEN, "USER_NOT_ADMIN", ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex, request);
    }

    private ResponseEntity<ErrorDTO> buildError(
            HttpStatus status,
            String codigoErro,
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorDTO error = new ErrorDTO();
        error.setMensagem(ex.getMessage());
        error.setCodigoHttp(status.value());
        error.setData(LocalDateTime.now());
        error.setPath(request.getRequestURI());
        error.setCodigoErro(codigoErro);

        return ResponseEntity.status(status).body(error);
    }
}