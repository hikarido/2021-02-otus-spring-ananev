package ru.otus.homework2.core;

public class CantGetAccessToResource extends RuntimeException{
    public static class Builder{
        private Throwable cause = null;
        private String resourceName = "";
        private final String message;

        public Builder(String message){
            this.message = message;
        }

        public Builder withCause(Throwable value){
            cause = value;
            return this;
        }

        public Builder withResourceName(String value){
            resourceName = value;
            return this;
        }

        public CantGetAccessToResource build(){
            return new CantGetAccessToResource(this);
        }
    }

    private CantGetAccessToResource(Builder builder){
        super(builder.message + builder.resourceName, builder.cause);
    }
}
