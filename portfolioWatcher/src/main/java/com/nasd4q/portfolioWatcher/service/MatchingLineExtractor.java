package com.nasd4q.portfolioWatcher.service;

import com.nasd4q.portfolioWatcher.storage.LineDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * extraction de toutes les lignes contenant certains mots-clés
 * dans des fichiers textes dont le non commence par la date sous forme yyyyMMdd...
 */
class MatchingLineExtractor {

    private static Logger logger = LoggerFactory.getLogger(MatchingLineExtractor.class);

    /**
     * extract lines which contains every keywords in any of a collection of text files
     * @param keywords case-insensitive keywords
     * @param files text files where data is stored as lines
     *              and with names starting with date (in format yyyyMMdd)
     * @return all lines containing every keywords, sorted by date
     */
    Map<String, String> getLines(List<String> keywords, Collection<File> files) {

        Map<String, String> list = files.parallelStream()
                .map(f -> getLines(keywords, f))
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        (LineDate l)->l.getDate() + " " + LocalDateTime.now().getNano(),
                        (LineDate l2)-> l2.getLine()));
        return list;
    }

    /**
     * extract lines which contains every keywords in a text file
     * @param keywords case-insensitive keywords
     * @param file text file where data is stored as lines
     *             and with name starting with date (in format yyyyMMdd)
     * @return all lines containing every keywords
     */
    private List<LineDate> getLines(List<String> keywords, File file) {
        Scanner scanner = null;
        ArrayList<LineDate> matchingLines = new ArrayList<>();

        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException e) {
            return matchingLines;
        }

        //TODO handle this dependency...
        final int date = bnainFileSelector.dateFromString(file.getName());

        //now read the file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(keywords.stream().allMatch(c->line.toLowerCase().contains(c.toLowerCase()))) {
                matchingLines.add(new LineDate(line, date));
            }
        }
        scanner.close();
        return matchingLines;
    }
}
