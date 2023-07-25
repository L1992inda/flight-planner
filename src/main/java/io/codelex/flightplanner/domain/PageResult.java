package io.codelex.flightplanner.domain;

import java.util.ArrayList;
import java.util.List;

public class PageResult<T> {
    private long page;
    private long totalItems;
    private List<T> items = new ArrayList<>();

    public PageResult(long page, long totalItems, List<T> items) {
        this.page = page;
        this.totalItems = totalItems;
        this.items = items;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
