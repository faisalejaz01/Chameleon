package com.chameleon.utils.exception;

import com.chameleon.AutomationException;

public class DataProviderInputFileNotFound extends AutomationException {
    private static final long serialVersionUID = 8734638785785664287L;

    public DataProviderInputFileNotFound(String message) {
        super(message);
    }

    public DataProviderInputFileNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
