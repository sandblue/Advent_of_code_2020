package com.sarik;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private int result = 1;
    private List<Adapter> containList = new ArrayList<>();

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Adapter> getContainList() {
        return containList;
    }

    public void setContainList(List<Adapter> containList) {
        this.containList = containList;
    }
}
