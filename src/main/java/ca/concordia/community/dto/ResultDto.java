package ca.concordia.community.dto;

import lombok.Data;

/**
 * Created by Hanford Wu on 2020-07-24 11:10 p.m.
 */
@Data
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T data;


    public static ResultDto errorOf(Integer code, String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static <T> ResultDto okOf(T t){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("success");
        resultDto.setData(t);
        return resultDto;
    }
}
