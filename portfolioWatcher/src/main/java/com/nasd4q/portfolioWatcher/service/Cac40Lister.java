package com.nasd4q.portfolioWatcher.service;

import com.nasd4q.portfolioWatcher.model.Stock;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Cac40Lister {
    public List<Stock> getList(LocalDate date) throws IOException {

        Connection.Response response =
                Jsoup.connect("https://www.bnains.org/archives/histocac/compocac.php")
                        .userAgent("Mozilla/5.0")
                        .timeout(10 * 1000)
                        .method(Connection.Method.POST)
                        .data("date_input",  date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .execute();

        //parse the document from response
        Document document = response.parse();
        Element table = document.select("table").get(0);
        Elements rows = table.select("tr");

        System.out.println(rows.get(0).text());

        List<Stock> stocks = rows.stream().map(this::StockFromRow).collect(Collectors.toList());

        while (stocks.remove(null));

        stocks.forEach(s->System.out.println(s.toString()));

        System.out.println(stocks.size());

        return stocks;
    }

    private Stock StockFromRow(Element row) {
        if (row == null)
            return null;

        Elements tds = row.select("td");

        if (tds==null || tds.size()!=3)
            return null;
        return new Stock(tds.get(0).text(),tds.get(1).text(),tds.get(2).text());
    }
}
