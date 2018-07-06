package com.retronic.remoting.error;

public enum Error implements IError {

    RESOURCE_NOT_FOUND {
        @Override
        public String getMessage() {
            return "Resource not found.";
        }
    },
    MISSING_RESOURCE_ID {
        @Override
        public String getMessage() {
            return "Resource ID missing.";
        }
    },
    UPLOAD_ERROR {
        @Override
        public String getMessage() {
            return "Upload error.";
        }
    },
    RESPONSE_TO_BIG {
        @Override
        public String getMessage() {
            return "Response contains too many Elements. Please apply filters.";
        }
    },
    VALIDATION_ERROR {
        @Override
        public String getMessage() {
            return "Validation error.";
        }
    },
    BEGINDATE_AFTER_ENDDATE {
        @Override
        public String getMessage() {
            return "The parameter dateFrom must be before dateTo.";
        }
    },
    INVALID_DATE_FORMAT {
        @Override
        public String getMessage() {
            return "Invalid date format. Format must be 'M/d/yyyy hh:mm:ss aa'";
        }
    },
    INVALID_DATE_FORMAT_WITHOUT_AM_PM {
        @Override
        public String getMessage() {
            return "Invalid date format. Format must be 'M/d/yyyy HH:mm:ss'";
        }
    },
    CCSERVER_GENERICERROR {
        @Override
        public String getMessage() {
            return "Service not available. Please try again in 5 minutes.";
        }
    },
    METER_ATTACHED_TO_ONLY_ONE_NODE_ERROR{
        @Override
        public String getMessage() {
            return "The meter is attached to only one node.";
        }
    }
}
