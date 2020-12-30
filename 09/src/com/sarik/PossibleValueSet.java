package com.sarik;

import java.util.ArrayList;
import java.util.List;

public class PossibleValueSet {
    private Long sumValue = 0L;
    private List<Long> includeList = new ArrayList<>();

    public Long getSumValue() {
        return sumValue;
    }

    public void setSumValue(Long sumValue) {
        this.sumValue = sumValue;
    }

    public List<Long> getIncludeList() {
        return includeList;
    }

    public void setIncludeList(List<Long> includeList) {
        this.includeList = includeList;
    }
}
