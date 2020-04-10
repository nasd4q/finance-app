package com.nasd4q.portfolioWatcher.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbConfigResolver {

    @Value("${myconfigs.db.url}") private String url;
    @Value("${myconfigs.db.user}") private String user;
    @Value("${myconfigs.db.password}") private String password;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}