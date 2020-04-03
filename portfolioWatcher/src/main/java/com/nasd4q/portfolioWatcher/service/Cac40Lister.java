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
    /**
     * @param date
     * @return la liste des actions faisant partie du cac40 à la date donnée
     * @throws IOException
     */
    //TODO gérer mieux cette IOException
    public List<Stock> getList(LocalDate date) throws IOException {

        //1. requête POST avec un form data contenant un champ : date_input
        Connection.Response response =
                Jsoup.connect("https://www.bnains.org/archives/histocac/compocac.php")
                        .userAgent("Mozilla/5.0")
                        .timeout(10 * 1000)
                        .method(Connection.Method.POST)
                        .data("date_input",  date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .execute();

        //2. extraction des éléments pertinents
        Document document = response.parse();
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
     *
     * @param row telle que fournie par https://www.bnains.org/archives/histocac/compocac.php
     *            lors de la demande des membres du cac40 à une date donnée
     * @return l'action (Stock) correspondante, null si échec
     */
    private Stock StockFromRow(Element row) {
        if (row == null)
            return null;

        Elements tds = row.select("td");

        if (tds==null || tds.size()!=3)
            return null;
        return new Stock(tds.get(0).text(),tds.get(1).text(),tds.get(2).text());
    }
}
