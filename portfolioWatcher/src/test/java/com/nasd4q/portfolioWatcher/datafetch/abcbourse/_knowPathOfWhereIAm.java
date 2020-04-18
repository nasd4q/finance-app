package com.nasd4q.portfolioWatcher.datafetch.abcbourse;

import org.junit.jupiter.api.Test;

import java.io.File;

class _knowPathOfWhereIAm {

    @Test
    public void showPath() {
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(new File(loader.getResource("application.properties")
            .getFile()).getParentFile().getParentFile().getParentFile());
    }
}
