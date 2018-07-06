package com.retronic.remoting.error;

public interface IError {
    String name();

    default String getMessage() {
        return this.name();
    }

    default String getTranslationPath() {
        return "rest.common." + this.name().toLowerCase();
    }
}
