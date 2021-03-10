package ru.otus.homework2.core;

public class ThereIsNoSuchResource extends RuntimeException{
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

        public ThereIsNoSuchResource build(){
            return new ThereIsNoSuchResource(this);
        }
    }

    private ThereIsNoSuchResource(Builder builder){
        super(builder.message + builder.resourceName, builder.cause);
    }
}
