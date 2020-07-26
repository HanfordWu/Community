package ca.concordia.community.enums;

/**
 * Created by Hanford Wu on 2020-07-24 11:22 p.m.
 */
public enum  CommentTypeEnum {
    QUESTION_TYPE_ENUM(1),
    COMMENT_TYPE_ENUM(2);



    private Integer type;
    CommentTypeEnum(Integer type){
        this.type = type;
    }


    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer type){
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if (commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }
}
