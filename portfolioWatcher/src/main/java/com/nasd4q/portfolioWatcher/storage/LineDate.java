package com.nasd4q.portfolioWatcher.storage;

public final class LineDate {
    private final String line;
    private final int date; //13 février 1997 -> 19970213

    public LineDate(String line, int date) {
        this.line = line;
        this.date = date;
    }

    public String getLine() {
        return line;
    }

    public int getDate() {
        return date;
    }

    @Override
    public String toString() {
        return line + " * date = " + date;
    }
}

