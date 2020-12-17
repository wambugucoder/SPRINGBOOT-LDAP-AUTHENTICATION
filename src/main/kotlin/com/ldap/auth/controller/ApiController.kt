package com.ldap.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {


    @GetMapping("/hello")
    fun getHello():String{
        return "Hello LDAP-AUTH"
    }
}