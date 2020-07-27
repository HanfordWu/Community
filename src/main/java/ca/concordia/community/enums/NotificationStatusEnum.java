package ca.concordia.community.enums;

/**
 * Created by Hanford Wu on 2020-07-26 10:03 a.m.
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
