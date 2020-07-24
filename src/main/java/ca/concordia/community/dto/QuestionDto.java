package ca.concordia.community.dto;

import ca.concordia.community.model.TUser;
import lombok.Data;

/**
 * Created by Hanford Wu on 2020-07-22 12:29 a.m.
 */
@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private TUser user;

}
