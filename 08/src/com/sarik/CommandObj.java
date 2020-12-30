package com.sarik;

import java.util.ArrayList;
import java.util.List;

public class CommandObj {
    private String command;
    private Integer value;
    private Integer index;
    private List<CommandObj> referenceBy = new ArrayList<>();
    private List<CommandObj> forwardTo = new ArrayList<>();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<CommandObj> getReferenceBy() {
        return referenceBy;
    }

    public void setReferenceBy(List<CommandObj> referenceBy) {
        this.referenceBy = referenceBy;
    }

    public List<CommandObj> getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(List<CommandObj> forwardTo) {
        this.forwardTo = forwardTo;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "CommandObj{" +
                "index=" + index +
                '}';
    }
}
