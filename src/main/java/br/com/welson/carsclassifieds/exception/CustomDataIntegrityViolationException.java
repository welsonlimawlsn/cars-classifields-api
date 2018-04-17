package br.com.welson.carsclassifieds.exception;

public class CustomDataIntegrityViolationException extends ErrorDetails {


    public static final class Builder {
        private CustomDataIntegrityViolationException customDataIntegrityViolationException;

        private Builder() {
            customDataIntegrityViolationException = new CustomDataIntegrityViolationException();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder title(String title) {
            customDataIntegrityViolationException.setTitle(title);
            return this;
        }

        public Builder status(int status) {
            customDataIntegrityViolationException.setStatus(status);
            return this;
        }

        public Builder detail(String detail) {
            customDataIntegrityViolationException.setDetail(detail);
            return this;
        }

        public Builder timestamp(long timestamp) {
            customDataIntegrityViolationException.setTimestamp(timestamp);
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            customDataIntegrityViolationException.setDeveloperMessage(developerMessage);
            return this;
        }

        public CustomDataIntegrityViolationException build() {
            return customDataIntegrityViolationException;
        }
    }
}
