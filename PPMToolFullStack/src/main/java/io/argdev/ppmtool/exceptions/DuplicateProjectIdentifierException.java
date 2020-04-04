package io.argdev.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateProjectIdentifierException extends RuntimeException {
    public DuplicateProjectIdentifierException(String projectIdentifier) {
        super("Project with Identifier: " + projectIdentifier + " already exists!");
    }
}
