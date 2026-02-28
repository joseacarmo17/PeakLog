package org.peaklog.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "El usuario ya existe"),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Usuario no encontrado"),
  INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"),
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "El token ha expirado"),
  TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Token inválido"),
  ACCESS_DENIED(HttpStatus.FORBIDDEN, "No tienes permisos para realizar esta acción"),
  INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");

  private final HttpStatus status;
  private final String message;

  ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
