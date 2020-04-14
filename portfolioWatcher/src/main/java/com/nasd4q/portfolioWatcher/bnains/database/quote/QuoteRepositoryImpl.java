package com.nasd4q.portfolioWatcher.bnains.database.quote;

import com.nasd4q.portfolioWatcher.databundles.Asset;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class QuoteRepositoryImpl implements QuoteRepository {

    private _BnainsQuoteRepository repository;

    @Autowired
    public QuoteRepositoryImpl(_BnainsQuoteRepository repository) {
        this.repository = repository;
    }

    public void loadDataFromBnains(){
        System.out.println("debut");

        Resource resource = new ClassPathResource("data from www-bnains-org");

        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stack<File> filesToLookInto = new Stack<>();
        filesToLookInto.push(file);
        File currentFile = null;
        while (!filesToLookInto.empty()) {
            currentFile = filesToLookInto.pop();
            System.out.println("Examining " + currentFile.getName());
            if (currentFile.isDirectory()) {
                System.out.println();
                System.out.println("FOLDER");
                System.out.println();

                File[] filesArray = currentFile.listFiles();
                Arrays.sort(filesArray, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        return o2.getName().compareTo(o1.getName());
                    }
                });
                filesToLookInto.addAll(List.of(filesArray));
            } else {
                System.out.println("FILE");
                if (currentFile.getName().matches("^[0-9]{8}[\\s\\S]*"))
                {
                    System.out.println("Adding to repository : " + currentFile.getName());
                    repository.saveAll(fileToQuotes(currentFile));
                }
            }
        }
    }

    @Override
    public double getValue(Asset asset, LocalDateTime datetime) {
        //TODO
        return 0;
    }

    private List<_BnainsQuote> fileToQuotes(File file) {
        //filename : src/main/resources/data from www-bnains-org/1994/19940104.TXT
        String filename = file.getName();
        LocalDate date = LocalDate.parse(filename.substring(
                filename.lastIndexOf('/')+1,
                filename.lastIndexOf('.')),
                DateTimeFormatter.BASIC_ISO_DATE);

        return fileToList(file).stream()
                .map(s->_BnainsQuote.of(s, date ))
                .collect(Collectors.toList());
    }

    private List<String> fileToList(File file) {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(file))) {
            if (s.hasNext()) {
                String lineOne = s.nextLine();
                String notGoodline = "CODE	LIBELLE	PREMIER	PLUS HAUT	PLUS BAS	DERNIER	VOLUME";
                String[] lineOneSplit = lineOne.split("\\s+");
                String[] notGoodSplit = notGoodline.split("\\s+");
                int score = 0;
                int i;
                for (i=0; i<lineOneSplit.length && i<9;i++) {
                    if (lineOneSplit[i].equalsIgnoreCase(notGoodSplit[i]))
                        score++;
                }

                if (score<i)
                    result.add(lineOne);
            }

            while (s.hasNext()) {
                result.add(s.nextLine());
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }
}
