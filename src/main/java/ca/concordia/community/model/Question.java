package ca.concordia.community.model;

import lombok.Data;
import sun.rmi.runtime.Log;

/**
 * Created by Hanford Wu on 2020-07-21 5:21 p.m.
 */
@Data
public class Question {
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
}
