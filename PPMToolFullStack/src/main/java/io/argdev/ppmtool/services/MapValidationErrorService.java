package io.argdev.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationError(BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldErrors().stream().collect(
                    Collectors.toMap(FieldError::getField, fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Null"))
            ), HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
