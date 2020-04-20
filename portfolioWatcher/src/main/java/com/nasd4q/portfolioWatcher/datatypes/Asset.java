package com.nasd4q.portfolioWatcher.datatypes;

public interface Asset {
    Long getIdentifier();

    static Asset of(final Long id) {
        return new Asset() {
            @Override
            public Long getIdentifier() {
                return id;
            }
        };
    }
}
