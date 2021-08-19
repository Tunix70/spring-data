package com.springCrudV2.demo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Weather {
    private Main main;

    public Weather(Main main) {
        this.main = main;
    }
}
