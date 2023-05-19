package com.nnk.springboot.security;

import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Classe permettant la configuration de la securité de l'application, la gestion des rôles/des permissions et l'authentification.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean permettant d'initialiser l'AuthenticationProvider avec l'encoder BCryptPasswordEncoder et le UserDetailsService de notre appli
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    /**
     * Methode permettant de configurer les accès en fonction du rôle et de l'authentification
     * Toutes les url relative a nos entités sont accessibles par le rôle ADMIN
     * Toutes les url sauf celle de l'entité user sont accessibles par le rôle USER
     * Seul le form loggin est accessible sans authentification
     * Lors d'un Logout, les cookies sont supprimés, la session invalidée et l'authentification remise à zéro
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bidList/**", "rating/**", "ruleName/**", "curvePoint/**", "trade/**")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/bidList/list", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/app/error");
    }
}
