package ca.concordia.community.controller;

import ca.concordia.community.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Hanford Wu on 2020-07-26 3:44 p.m.
 */
@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDto upload(){
        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl("/images/logos/editormd-logo-320x320.png");
        return fileDto;
    }
}
