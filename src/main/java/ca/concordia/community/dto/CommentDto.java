package ca.concordia.community.dto;

import lombok.Data;

/**
 * Created by Hanford Wu on 2020-07-24 10:46 p.m.
 */
@Data
public class CommentDto {
    private Integer parentId;
    private String content;
    private Integer type;
}
