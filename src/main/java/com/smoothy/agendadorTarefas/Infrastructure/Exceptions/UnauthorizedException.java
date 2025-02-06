package com.smoothy.agendadorTarefas.Infrastructure.Exceptions;


public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable throwable) {
    super(message , throwable);
  }
}
