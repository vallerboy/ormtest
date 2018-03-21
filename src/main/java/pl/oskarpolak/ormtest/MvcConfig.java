//package pl.oskarpolak.ormtest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import pl.oskarpolak.ormtest.models.handlers.LoginHandler;
//
//@Configuration
//public class MvcConfig extends WebMvcConfigurerAdapter {
//
//     final LoginHandler loginHandler;
//
//    @Autowired
//    public MvcConfig(LoginHandler loginHandler) {
//        this.loginHandler = loginHandler;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginHandler);
//    }
//}
