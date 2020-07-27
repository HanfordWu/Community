package ca.concordia.community.dto;

import lombok.Data;

/**
 * Created by Hanford Wu on 2020-07-26 6:22 p.m.
 */
@Data
public class QuestionQueryDto {
    private String search;
    private Integer page;
    private Integer size;
}
