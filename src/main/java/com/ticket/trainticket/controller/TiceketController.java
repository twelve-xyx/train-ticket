package com.ticket.trainticket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/ticket")
public class TiceketController {


    @GetMapping(value = "/select")
    public String checkCompany() {
        return null;
    }
}

