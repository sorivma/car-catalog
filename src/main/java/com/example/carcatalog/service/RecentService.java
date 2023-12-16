package com.example.carcatalog.service;

import java.util.List;

public interface RecentService<T> {
    List<T> getRecent(String username);
    void addRecent(T recent, String username);
}
