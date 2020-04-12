package com.nasd4q.portfolioWatcher.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class _DbConfigResolver {

    @Value("${spring.datasource.url}") private String url;
    @Value("${spring.datasource.username}") private String user;
    @Value("${spring.datasource.password}") private String password;

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