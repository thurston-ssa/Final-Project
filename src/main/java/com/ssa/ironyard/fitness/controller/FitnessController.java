package com.ssa.ironyard.fitness.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;


@RestController
@RequestMapping(value="/fitness")
public class FitnessController {

    Logger LOGGER = LogManager.getLogger(FitnessController.class);

    @RequestMapping(value = "")
    public View homeView() {
        return new InternalResourceView("index.html");
    }
}
