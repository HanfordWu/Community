package ca.concordia.community.controller;

import ca.concordia.community.dto.AccessTokenDto;
import ca.concordia.community.dto.GithubUser;
import ca.concordia.community.mapper.UserMapper;
import ca.concordia.community.model.User;
import ca.concordia.community.model.UserExample;
import ca.concordia.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hanford Wu on 2020-07-19 4:35 p.m.
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
//inject from property file
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletRequest request, HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);


        if (githubUser != null) {

            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(Integer.toString(githubUser.getId()));
            List<User> user = userMapper.selectByExample(userExample);




            User tUser = new User();
            if ( user.size() == 0){

                tUser.setAccountId(String.valueOf(githubUser.getId()));
                tUser.setGmtCreate(System.currentTimeMillis());
                tUser.setName(githubUser.getName());
                tUser.setGmtModified(tUser.getGmtCreate());
                tUser.setToken(UUID.randomUUID().toString());
                tUser.setAvatarUrl(githubUser.getAvatar_url());
                userMapper.insert(tUser);
            }

            response.addCookie(new Cookie("token", tUser.getToken()));
            request.getSession().setAttribute("user", user.get(0));

        } else {


        }
        return "redirect:/";

    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");

        return "redirect:/";
    }


}
