package com.mediscreen.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {
    if (response.status() == 404) {
      return new NonexistentException("The patient doesn't exist.");
    }
    if (response.status() == 403) {
      return new NonexistentException(
          "There are several patients with this family name, try the request with his patient id: curl http://.../assess?patientId={id}");
    }
    return defaultErrorDecoder.decode(methodKey, response);
  }

}