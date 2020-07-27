package ca.concordia.community.dto;

import ca.concordia.community.model.User;
import lombok.Data;

/**
 * Created by Hanford Wu on 2020-07-26 11:23 a.m.
 */
@Data
public class NotificationDto {
    private Integer id  ;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private String type;
}
