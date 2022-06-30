package org.saphka.finance.exception;

import lombok.Getter;
import org.saphka.finance.model.ErrorCode;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode code;

    public BaseException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

}
