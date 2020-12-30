package com.sarik;

import java.util.ArrayList;
import java.util.List;

public class PaperClip {

    private Long value;
    private List<Long> possiblePairResult  = new ArrayList<>();

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public List<Long> getPossiblePairResult() {
        return possiblePairResult;
    }

    public void setPossiblePairResult(List<Long> possiblePairResult) {
        this.possiblePairResult = possiblePairResult;
    }
}
