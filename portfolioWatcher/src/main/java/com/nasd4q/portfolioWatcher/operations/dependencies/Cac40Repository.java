package com.nasd4q.portfolioWatcher.operations.dependencies;

import com.nasd4q.portfolioWatcher.datatypes.Cac40Member;

import java.util.Collection;

public interface Cac40Repository {
    void addAll(Collection<Cac40Member> cac40Members);
    Collection<Cac40Member> findAll();
}
