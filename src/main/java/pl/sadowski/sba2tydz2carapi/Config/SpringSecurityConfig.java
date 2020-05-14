package pl.sadowski.sba2tydz2carapi.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Sebek").password("{noop}Sebek").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.httpBasic();
       http.authorizeRequests()
               .antMatchers("/cars/**").hasRole("ADMIN")
               .anyRequest().permitAll();
       http.formLogin();
    }



}
