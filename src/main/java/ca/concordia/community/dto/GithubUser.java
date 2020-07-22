package ca.concordia.community.dto;

import lombok.Data;

/**
 * Created by Hanford Wu on 2020-07-19 5:31 p.m.
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
