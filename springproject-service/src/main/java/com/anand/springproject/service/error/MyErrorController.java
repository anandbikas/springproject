package com.anand.springproject.service.error;

import com.anand.springproject.core.exception.UnexpectedException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * https://www.baeldung.com/spring-boot-custom-error-page
 * https://mkyong.com/spring-boot/spring-rest-error-handling-example/
 */
//@RestController
public class MyErrorController implements ErrorController {

//1.     @RequestMapping("/error")
//    public String handleError() {
//        return "<h1>Something went wrong!</h1>";
//    }



//2.     @RequestMapping("/error")
//    @ResponseBody
//    public String handleError(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//
//        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//                        + "<div>Exception Message: <b>%s</b></div><body></html>",
//                statusCode, exception==null? "N/A": exception.getMessage());
//    }


//3.   @RequestMapping("/error")
//    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//
//        /**
//         * custom handler only for 500 series
//         */
//        if( Integer.toString(statusCode).startsWith("5")){
//            return new ModelAndView("error");
//        }
//        Object e = request.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION");
//        if(e == null){
//            throw new UnexpectedException("Unknown exception");
//        }
//        throw (Exception) e;
//    }

    //https://stackoverflow.com/questions/62436379/how-to-replace-errorcontroller-deprecated-function-on-spring-boot
}