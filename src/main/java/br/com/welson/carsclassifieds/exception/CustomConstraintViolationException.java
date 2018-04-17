package br.com.welson.carsclassifieds.exception;

public class CustomConstraintViolationException extends ErrorDetails {


    public static final class Builder {
        private CustomConstraintViolationException customConstraintViolationException;

        private Builder() {
            customConstraintViolationException = new CustomConstraintViolationException();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder title(String title) {
            customConstraintViolationException.setTitle(title);
            return this;
        }

        public Builder status(int status) {
            customConstraintViolationException.setStatus(status);
            return this;
        }

        public Builder detail(String detail) {
            customConstraintViolationException.setDetail(detail);
            return this;
        }

        public Builder timestamp(long timestamp) {
            customConstraintViolationException.setTimestamp(timestamp);
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            customConstraintViolationException.setDeveloperMessage(developerMessage);
            return this;
        }

        public CustomConstraintViolationException build() {
            return customConstraintViolationException;
        }
    }
}
