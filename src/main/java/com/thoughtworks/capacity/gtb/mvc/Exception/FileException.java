package com.thoughtworks.capacity.gtb.mvc.Exception;

import lombok.Getter;

@Getter
public class FileException extends RuntimeException {
  
  public FileException(String message){
    super(message);
  }
  public static FileException notFound() {
    return new FileException("zip_is_not_found");
  }
}
