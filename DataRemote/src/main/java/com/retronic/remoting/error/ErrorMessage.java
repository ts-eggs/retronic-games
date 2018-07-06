package com.retronic.remoting.error;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class ErrorMessage {
    private String code;
    private String message;
    private String translationKey;
    private String additionalInfo;

    @JsonRawValue
    private Object additionalJson;

    public ErrorMessage(String code, String message, String translationKey) {
        this.code = code;
        this.message = message;
        this.translationKey = translationKey;
    }

    public ErrorMessage() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public Object getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Object getAdditionalJson() {
        return additionalJson;
    }

    public void setAdditionalJson(Object additionalJson) {
        this.additionalJson = additionalJson;
    }
}
