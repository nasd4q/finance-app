package com.nasd4q.portfolioWatcher.abcbourse.datafetch;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
class _QuoteFetcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("classpath:ABCBourseJS/setDateAndClickDownload.js")
    private Resource jsSetDateAndClick;
    private File downloadFolder = new File("src/main/resources/ABCBourseSeleniumDownloads");

    private static final String ABC_BOURSE_DOWNLOAD_URL =
            "https://www.abcbourse.com/download/historiques.aspx";

    private static WebDriver driver = null;

    void ensureDriverIsInitialized() throws IOException {
        if (driver != null)
            return;

        String path = downloadFolder.getCanonicalPath();
        logger.info(path + " is the path for selenium downloads");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("browser.download.folderList",2);
        firefoxOptions.addPreference("browser.download.dir", path);
        firefoxOptions.addPreference("browser.download.manager.showWhenStarting",false);
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk","text/plain");

        driver = new FirefoxDriver(firefoxOptions);
    }

    public void downloadQuotes(Collection<LocalDate> datesOriginal, final int millisToWait, boolean close)
            throws InterruptedException, IOException {
        ensureDriverIsInitialized();

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.addAll(datesOriginal);
        Collections.shuffle(dates);

        driver.navigate().to(ABC_BOURSE_DOWNLOAD_URL);
        logger.info("navigating");

        Thread.sleep(3000);

        dates.stream().forEach(date-> {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor)driver).executeScript(getJsSetDateAndClickFor(date));
                logger.info("Executing js for date " + date.getDayOfWeek().toString() +
                        " " + date.toString());
            } else {
                throw new IllegalStateException("This driver does not support JavaScript!");
            }

            try {
                Thread.sleep(millisToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(5000);
        if (close) {
            logger.info("closing");
            driver.close();
        }
    }

    public Collection<LocalDate> getMissingDates(int year) {
        Set<LocalDate> dates = new HashSet<>();
        LocalDate now = LocalDate.now();
        for (LocalDate d = LocalDate.of(year,1,1);
             d.compareTo(LocalDate.of(year+1,1,1))<0;
             d = d.plus(Period.of(0,0,1))) {
            if (!(d.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    d.getDayOfWeek() == DayOfWeek.SUNDAY ||
                    d.compareTo(now)>0)) {
                dates.add(d);
            }
        }
        File dwnldFolder = downloadFolder;
        for (File f : dwnldFolder.listFiles()) {
            final LocalDate dateForWhichExistsFile = parseDateFromFileName(f.getName());
            dates.remove(dateForWhichExistsFile);
        }
        return dates;
    }

    String getJsSetDateAndClickFor(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        String s = null;
        try (Reader reader = new InputStreamReader(jsSetDateAndClick.getInputStream(), UTF_8)) {
            s = FileCopyUtils.copyToString(reader);
        } catch ( IOException e) {
            throw new UncheckedIOException(e);
        }
        return String.format(s, year, month, day);
    }

    private LocalDate parseDateFromFileName(String name) {
        if (name.length()<17) {
            return null;
        }
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(name.substring(9, 9 + 8), DateTimeFormatter.BASIC_ISO_DATE);
        } catch (DateTimeParseException e) {
            logger.info("parseDateFromFileName() failed for name :" + name);
        }
        return parsedDate;
    }
}
