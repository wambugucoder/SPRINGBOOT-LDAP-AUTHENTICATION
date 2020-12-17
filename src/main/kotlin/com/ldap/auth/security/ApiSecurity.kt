package com.ldap.auth.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class ApiSecurity: WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        if (auth != null) {
            auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8399/dc=springframework,dc=org")
                .and()
                .passwordCompare()
               // .passwordEncoder(BCryptPasswordEncoder())
                .passwordAttribute("userPassword")
        }
    }

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
        }
    }

}