package com.youxiunanren.yxnr.rs.core;

public class ValidationResult {
    private ValidationResult(){}

    private ECode code;
    private String message;

    public ECode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private void setCode(ECode code){
        this.code = code;
    }

    private void setMessage(String message){
        this.message = message;
    }

    public static ValidationResultBuilder code(ECode code) {
        ValidationResultBuilder builder = ValidationResultBuilder.newInstance();
        builder.code(code);
        return builder;
    }

    public static ValidationResultBuilder message(String message) {
        ValidationResultBuilder builder = ok(message);
        return builder;
    }

    public boolean isOK(){
        return ECode.OK.equals(this.code);
    }

    public static ValidationResultBuilder ok(){
        return code(ECode.OK);
    }

    public static ValidationResultBuilder ok(String message){
        ValidationResultBuilder builder = ok();
        builder.message(message);
        return builder;
    }

    public static class ValidationResultBuilder {
        private ValidationResult bundle;

        private ValidationResultBuilder(){
            this.bundle = new ValidationResult();
        }

        public static ValidationResultBuilder newInstance(){
            return new ValidationResultBuilder();
        }

        public ValidationResultBuilder code(ECode code){
            this.bundle.setCode(code);
            return this;
        }

        public ValidationResultBuilder message(String message){
            this.bundle.setMessage(message);
            return this;
        }

        public ValidationResult build(){
            return this.bundle;
        }
    }

    public static enum ECode {
        OK(200);

        private final int code;

        ECode(int code) {
            this.code = code;
        }
    }
}