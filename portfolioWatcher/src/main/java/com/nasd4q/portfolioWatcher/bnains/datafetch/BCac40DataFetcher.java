package com.nasd4q.portfolioWatcher.bnains.datafetch;

import com.nasd4q.portfolioWatcher.databundles.Stock;
import com.nasd4q.portfolioWatcher.operations.dependencies.Cac40DataFetcher;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BCac40DataFetcher implements Cac40DataFetcher {


    /**
     * @implNote returns Stocks of class Stock.Immutable
     */
    @Override
    public Collection<Stock> fetchMembers(LocalDate date) {

        //1. obtention du document html contenant les infos
        Document document = this.getCompoCac40DocFromBnains(date);

        //2. extraction des éléments pertinents
        Element table = document.select("table").get(0);
        Elements rows = table.select("tr");

        //TODO : see if this need to be logged
        //log du titre de la table - permettant checker date
        System.out.println(rows.get(0).text());

        //3. extraction des stocks, puis on enlève les valeurs null de la liste
        List<Stock> stocks = rows.stream().map(this::StockFromRow).collect(Collectors.toList());
        while (stocks.remove(null));

        //TODO : see if this need to be logged
        //log des stocks - membres du cac40 à la date donnée, et du nombre de membres
        stocks.forEach(s->System.out.println(s.toString()));
        System.out.println(stocks.size());

        //4. return results
        return stocks;
    }

    /**
     * @param row telle que fournie par https://www.bnains.org/archives/histocac/compocac.php
     *            lors de la demande des membres du cac40 à une date donnée
     * @return l'action (implementation BStock) correspondante, null si échec
     */
    private Stock.Immutable StockFromRow(Element row) {
        if (row == null)
            return null;

        Elements tds = row.select("td");

        if (tds==null || tds.size()!=3)
            return null;
        return Stock.from(tds.get(0).text(),tds.get(1).text(),tds.get(2).text());
    }

    /**
     * @return le document html obtenu sur www.bnains.org pour la composition
     * du CAC 40 à la date donnée, ou bien null
     */
    private Document getCompoCac40DocFromBnains(LocalDate date) {
        Connection connection = Jsoup
                .connect("https://www.bnains.org/archives/histocac/compocac.php")
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .method(Connection.Method.POST)
                .data("date_input", date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Connection.Response response = null;
        Document document = null;
        try {
            response = connection.execute();
            document = response.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
