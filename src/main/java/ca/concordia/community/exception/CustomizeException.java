package ca.concordia.community.exception;

/**
 * Created by Hanford Wu on 2020-07-24 9:19 p.m.
 */
public class CustomizeException extends RuntimeException{

    private final String message;
    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
