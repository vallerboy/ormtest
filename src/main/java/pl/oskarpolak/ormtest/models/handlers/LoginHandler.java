package pl.oskarpolak.ormtest.models.handlers;

import groovy.util.logging.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.oskarpolak.ormtest.models.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandler implements HandlerInterceptor {

    final
    UserService userService;

    @Autowired
    public LoginHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("Time: " + (httpServletRequest.getSession().getLastAccessedTime() - httpServletRequest.getSession().getCreationTime()));

        String requestURI = httpServletRequest.getRequestURI();
        if(userService.getBadLoginCounter() >= 5) {
            return false;
        }

        if(!userService.isLogin()
                && !(requestURI.equals("/login") || requestURI.equals("/register"))){
         httpServletResponse.sendRedirect("/login");
         return false;
        }



        System.out.println("Zapytanie poszło!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
