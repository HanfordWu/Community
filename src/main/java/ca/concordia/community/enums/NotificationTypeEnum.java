package ca.concordia.community.enums;

/**
 * Created by Hanford Wu on 2020-07-26 10:03 a.m.
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1, "Question's New Reply"),
    REPLY_COMMENT(2, "Comment's New Reply");
    private int type;
    private String name;

    NotificationTypeEnum(int type,
                         String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }



    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType() == type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
