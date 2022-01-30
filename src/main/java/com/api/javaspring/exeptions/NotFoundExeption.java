package com.api.javaspring.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExeption extends RuntimeException {

  public NotFoundExeption(Integer id) {
    super(String.format("Client with ID %s not found!", id));
  }
}
