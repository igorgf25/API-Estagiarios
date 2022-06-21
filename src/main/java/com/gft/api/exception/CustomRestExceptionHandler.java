package com.gft.api.exception;

import com.gft.api.model.dto.error.ApiErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler( { EntityNotFoundException.class } )
    public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){

        String error = "Recurso não encontrado";

        ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

    }

    @ExceptionHandler( { EntityAlreadyExist.class } )
    public ResponseEntity<ApiErrorDTO> handleEntityAlreadyExist(EntityAlreadyExist ex, WebRequest request){

        String error = "Recurso já cadastrado";

        ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

    }

    @ExceptionHandler( { IllegalArgumentException.class } )
    public ResponseEntity<ApiErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){

        String error = "Parametro inválido";

        ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

    }

    @ExceptionHandler( { InvalidDataException.class } )
    public ResponseEntity<ApiErrorDTO> handleDataIntegrityViolationException(InvalidDataException ex, WebRequest request){

        String error = "Parametro inválido, por favor preencha todas as informações necessarias";

        ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());

    }
}
