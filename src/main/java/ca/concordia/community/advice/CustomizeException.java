package ca.concordia.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hanford Wu on 2020-07-24 8:37 p.m.
 */
@ControllerAdvice
public class CustomizeException {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Model model, HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
            model.addAttribute("message", "It's too hot!");
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
