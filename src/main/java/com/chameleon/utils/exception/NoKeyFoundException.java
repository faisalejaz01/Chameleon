package com.chameleon.utils.exception;

import com.chameleon.AutomationException;

public class NoKeyFoundException extends AutomationException {
    /**
     *
     */
    private static final long serialVersionUID = 1861535540217015795L;

    public NoKeyFoundException(String message) {
        super(message);
    }
}
