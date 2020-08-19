package com.application.covid.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreatingConfiguration {

    @Value("${message.great}")
    private String great;

    @Value("${message.first.welcome}")
    private String welcome;

    public String getGreat() {
        return great;
    }
    public void setGreat(String great) {
        this.great = great;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
}
