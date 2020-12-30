package com.sarik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int preambleSize = 25;
        System.out.println(first_part(preambleSize));
        second_part(first_part(preambleSize));
    }

    private static long first_part(int preambleSize) throws FileNotFoundException {
        File myObj = new File("D:\\WORK\\Advent_of_code_2020\\09\\material\\input.txt");
        Scanner myReader = new Scanner(myObj);

        Map<Long, Integer> last5possibleMapValue = new HashMap<>();
        List<PaperClip> readedPaperClip = new ArrayList<>();
        PaperClip currentPaperClip = new PaperClip();

        while (myReader.hasNextLine()) {
            Long data = Long.parseLong(myReader.nextLine());
            currentPaperClip = new PaperClip();
            currentPaperClip.setValue(data);
            readedPaperClip.add(currentPaperClip);
            if(readedPaperClip.size() > preambleSize) {
                if (last5possibleMapValue.containsKey(currentPaperClip.getValue())) {
                    removePossibleValue(readedPaperClip.get(0), last5possibleMapValue);
                    readedPaperClip.remove(0);
                } else {
                    myReader.close();
                    return currentPaperClip.getValue();
                }
            }
            addLastNPossibleValue(0, currentPaperClip, readedPaperClip, last5possibleMapValue);
        }
        myReader.close();
        return currentPaperClip.getValue();
    }

    private static void addLastNPossibleValue(int startIndex, PaperClip currentPaperClip, List<PaperClip> readedPaperClip, Map<Long, Integer> last5possibleMapValue){
        PaperClip targetPaperClip;
        long targetValue;
        for (Integer prevIndex = startIndex; prevIndex < readedPaperClip.size() - 1; prevIndex++) {
            targetPaperClip = readedPaperClip.get(prevIndex);
            targetValue = targetPaperClip.getValue() + currentPaperClip.getValue();
            if (last5possibleMapValue.containsKey(targetValue)) {
                last5possibleMapValue.replace(targetValue, last5possibleMapValue.get(targetValue) + 1);
            } else {
                last5possibleMapValue.put(targetValue, 1);
            }
            targetPaperClip.getPossiblePairResult().add(targetValue);
        }
    }

    private static void removePossibleValue(PaperClip targetPaperClip, Map<Long, Integer> last5possibleMapValue){
        for (Long targetValueToRemove : targetPaperClip.getPossiblePairResult()) {
            if (last5possibleMapValue.get(targetValueToRemove) > 1) {
                last5possibleMapValue.replace(targetValueToRemove, last5possibleMapValue.get(targetValueToRemove) - 1);
            } else {
                last5possibleMapValue.remove(targetValueToRemove);
            }
        }
    }

    private static void second_part(long targetValue) throws FileNotFoundException {
        File myObj = new File("D:\\WORK\\Advent_of_code_2020\\09\\material\\input.txt");
        Scanner myReader = new Scanner(myObj);

        List<PossibleValueSet> possibleResultList = new ArrayList<>();
        PossibleValueSet currentPossibleValueSet;
        int minimumSumSize = 2;
        int indexInPossibleList;
        List<Integer> removeIndexList;

        while (myReader.hasNextLine()) {
            Long data = Long.parseLong(myReader.nextLine());
            indexInPossibleList = 0;
            removeIndexList = new ArrayList<>();
            for(PossibleValueSet possibleResult : possibleResultList){
                if(possibleResult.getSumValue() + data == targetValue && possibleResult.getIncludeList().size() > minimumSumSize){
                    long min = possibleResult.getIncludeList().get(0);
                    long max = possibleResult.getIncludeList().get(0);
                    for (Long continueValue : possibleResult.getIncludeList()){
                        if(min > continueValue){
                             min = continueValue;
                        }
                        if(max < continueValue){
                            max = continueValue;
                        }
                    }
                    System.out.println(max+min);
                }else if(possibleResult.getSumValue() + data > targetValue){
                    removeIndexList.add(indexInPossibleList);
                }else{
                    possibleResult.setSumValue(possibleResult.getSumValue() + data);
                    possibleResult.getIncludeList().add(data);
                }
                indexInPossibleList++;
            }
            currentPossibleValueSet = new PossibleValueSet();
            currentPossibleValueSet.setSumValue(data);
            currentPossibleValueSet.getIncludeList().add(data);
            possibleResultList.add(currentPossibleValueSet);
            for (Integer removeIndex : removeIndexList){
                possibleResultList.remove(removeIndex);
            }

        }
    }
}

