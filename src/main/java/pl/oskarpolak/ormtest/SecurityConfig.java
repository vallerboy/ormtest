package pl.oskarpolak.ormtest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.oskarpolak.ormtest.models.UserDetails;
import pl.oskarpolak.ormtest.models.handlers.SuccessLoginHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserDetails userDetails;
    final SuccessLoginHandler successLoginHandler;

    @Autowired
    public SecurityConfig(UserDetails userDetails, SuccessLoginHandler successLoginHandler) {
        this.userDetails = userDetails;
        this.successLoginHandler = successLoginHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register")
                .permitAll()
                .antMatchers("/")
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("login")
                .passwordParameter("password")
                .successHandler(successLoginHandler);
               // .loginPage("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        ShaPasswordEncoder encoder  = new ShaPasswordEncoder();
        auth.userDetailsService(userDetails).passwordEncoder(encoder);


        //  auth.inMemoryAuthentication().withUser("oskar").password("oskar1").roles("USER");
    }
}
