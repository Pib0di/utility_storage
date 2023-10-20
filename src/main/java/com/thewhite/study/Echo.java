package com.thewhite.study;

import java.util.ArrayList;
import java.util.List;

/**
 * Nothing useful. Just return passed string
 */
public class Echo {

    private final List<String> history;

    public Echo() {
        this.history = new ArrayList<>();
    }

    public Echo(List<String> history) {
        assert history != null;
        this.history = history;
    }

    public String echo(String s) {
        history.add(s);
        return s;
    }

}
