package com.nasd4q.portfolioWatcher.database.bnains.quote;

import com.nasd4q.portfolioWatcher.database.bnains.quote.dependencies.AssetRepository;
import com.nasd4q.portfolioWatcher.datatypes.Asset;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Component
/**
 * Repository class for managing quote data obtained from www.bnains.org
 * Public methods : void loadDataIntoDB(), double getValue(Asset asset, LocalDateTime datetime)
 */
public class BQuoteRepository implements QuoteRepository {

    private static final File B_DATA_FOLDER =
            new File("src/main/resources/data from www-bnains-org");



    /* ******************************************************
     *******        Dependencies + constructor        *******
     ****************************************************** */

    private final _BQuoteRepository bQuoteRepository;

    private final AssetRepository assetRepository;

    @Autowired
    public BQuoteRepository(_BQuoteRepository bQuoteRepository, AssetRepository assetRepository) {
        this.bQuoteRepository = bQuoteRepository;
        this.assetRepository = assetRepository;
    }


    /* ******************************************************
     *******                Class API                 *******
     ****************************************************** */

    /**
     * Examines all (recursive) files present inside B_DATA_FOLDER.
     * If file name starts with at least 8 digits, then tries to parse
     * the file into a bunch of quotes (of class _BQuote) and saves them all to DB,
     * parsing file name as quote date.
     */
    public void loadDataIntoDB(){
        System.out.println("debut");

        Stack<File> filesToLookInto = new Stack<>();
        filesToLookInto.push(B_DATA_FOLDER);
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
                    bQuoteRepository.saveAll(fileToQuotes(currentFile));
                }
            }
        }
    }

    @Override
    public Double getValue(Asset asset, LocalDateTime datetime) {
        //TODO
        //assetRepository.getAssetAvatars(asset);
        //bQuoteRepository.getLatestQuote   ????
        return 0.0;
    }

    void associateAssetId() {
        //TODO
    }

    /* ******************************************************
     *******        private "helper" functions        *******
     ****************************************************** */

    private List<_BQuote> fileToQuotes(File file) {
        //filename : src/main/resources/data from www-bnains-org/1994/19940104.TXT
        String filename = file.getName();
        LocalDate date = LocalDate.parse(filename.substring(
                filename.lastIndexOf('/')+1,
                filename.lastIndexOf('.')),
                DateTimeFormatter.BASIC_ISO_DATE);

        return fileToList(file).stream()
                .map(s-> _BQuote.of(s, date ))
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
