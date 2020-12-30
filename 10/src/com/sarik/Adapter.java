package com.sarik;

import java.util.ArrayList;
import java.util.List;

public class Adapter {
    private int value;
    private Adapter connectedAdapter1joltForward;
    private Adapter connectedAdapter1joltBackward;
    private Adapter connectedAdapter3joltForward;
    private Adapter connectedAdapter3joltBackward;
    public List<Adapter> connectedTo = new ArrayList<>();
    List<Integer> countForwardList =  new ArrayList<>();

    public Adapter(int value) {
        this.value = value;
    }

    public int getCountPosiblePathForward(){
        Integer result = 0;
        Integer forwardValue = 0;
        for(Adapter adapter : this.connectedTo){
            if(adapter.getValue() > this.value){
                forwardValue = adapter.getCountPosiblePathForward();
                if(forwardValue > 0){
                    result += forwardValue;
                }else{
                    result++;
                }
            }
        }
        return (result);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Adapter getConnectedAdapter1joltForward() {
        return connectedAdapter1joltForward;
    }

    public void setConnectedAdapter1joltForward(Adapter connectedAdapter1joltForward) {
        this.connectedAdapter1joltForward = connectedAdapter1joltForward;
    }

    public Adapter getConnectedAdapter1joltBackward() {
        return connectedAdapter1joltBackward;
    }

    public void setConnectedAdapter1joltBackward(Adapter connectedAdapter1joltBackward) {
        this.connectedAdapter1joltBackward = connectedAdapter1joltBackward;
    }

    public Adapter getConnectedAdapter3joltForward() {
        return connectedAdapter3joltForward;
    }

    public void setConnectedAdapter3joltForward(Adapter connectedAdapter3joltForward) {
        this.connectedAdapter3joltForward = connectedAdapter3joltForward;
    }

    public Adapter getConnectedAdapter3joltBackward() {
        return connectedAdapter3joltBackward;
    }

    public void setConnectedAdapter3joltBackward(Adapter connectedAdapter3joltBackward) {
        this.connectedAdapter3joltBackward = connectedAdapter3joltBackward;
    }

    public List<Integer> getCountForwardList() {
        return countForwardList;
    }

    public void setCountForwardList(List<Integer> countForwardList) {
        this.countForwardList = countForwardList;
    }

    @Override
    public String toString() {
        return "Adapter{" +
                "value=" + value +
                '}';
    }
}
