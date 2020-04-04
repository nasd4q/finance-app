package com.nasd4q.portfolioWatcher.service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * extraire tous les fichiers interessants depuis le dossier data from www-bnains-org
 */
public class bnainFileSelector {
    private static File bNainDirectory = new File("" +
            "/Users/apple/dev_projects/financeApp/data from www-bnains-org");

    private static Collection<File>  relevantFiles = null;

    /**
     * @return une collection des fichiers dont le nom commence par une date dans dossier bnains
     */
    static Collection<File>  getRelevantFiles() {
        if (relevantFiles == null) {
            relevantFiles = FileUtils.listFiles(bNainDirectory, null, true)
                    .parallelStream()
                    .filter((File f) -> f != null &&
                            dateFromString(f.getName()) != 0
                    )
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return relevantFiles;
    }

    /**
     * @param name String de la forme "yyyyMMdd" + ...
     * @return yyyyMMdd as int ou 0
     */
    static int dateFromString(String name) {
        if (name == null || name.length() < 8)
            return 0;
        int res = 0;
        try {
            res = Integer.parseInt(name.substring(0,8));
        } catch (NumberFormatException e) {
            return 0;
        }
        return res;
    }
}
