package ca.concordia.community.exception;

/**
 * Created by Hanford Wu on 2020-07-24 9:19 p.m.
 */
public class CustomizeException extends RuntimeException{

    private final String message;
    private final int code;
    public CustomizeException(int code, String message){
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
