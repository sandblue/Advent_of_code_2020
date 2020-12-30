package com.sarik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        first_part();
        second_part();
    }

    private static void first_part() throws FileNotFoundException {
        File myObj = new File("D:\\WORK\\Advent_of_code_2020\\10\\material\\input.txt");
        Scanner myReader = new Scanner(myObj);
        Map<Integer , Adapter> adaptersPrev =  new HashMap<Integer, Adapter>();
        Adapter adapter0 = new Adapter(0);
        adaptersPrev.put(0, adapter0);
        Integer current_val;
        Adapter currentAdapter;
        Integer max = 0;
        Counter oneJolt = new Counter();
        Counter threeJolt = new Counter();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            current_val = Integer.parseInt(data);
            currentAdapter = new Adapter(current_val);

            if(current_val > max){
                max = current_val;
            }

            checkAdapterForward(adaptersPrev, current_val, currentAdapter, oneJolt, threeJolt);
            checkAdapterBackward(adaptersPrev, current_val, currentAdapter, oneJolt, threeJolt);

            adaptersPrev.put(current_val, currentAdapter);
        }
        myReader.close();

        currentAdapter = new Adapter(max + 3);

        checkAdapterForward(adaptersPrev, max + 3, currentAdapter, oneJolt, threeJolt);
        checkAdapterBackward(adaptersPrev, max + 3, currentAdapter, oneJolt, threeJolt);

        System.out.println(oneJolt.getCount());
        System.out.println(threeJolt.getCount());
        System.out.println(oneJolt.getCount() * threeJolt.getCount());
    }

    private static void checkAdapterForward(Map<Integer, Adapter> adaptersPrev, int current_val, Adapter currentAdapter, Counter oneJolt, Counter threeJolt) {
        Adapter targetAdapter;
        if (adaptersPrev.containsKey(current_val - 1)) {
            targetAdapter = adaptersPrev.get(current_val - 1);
            if (null == targetAdapter.getConnectedAdapter1joltForward()) {
                targetAdapter.setConnectedAdapter1joltForward(currentAdapter);
                currentAdapter.setConnectedAdapter1joltBackward(targetAdapter);
                if(null != targetAdapter.getConnectedAdapter3joltForward()){
                    Adapter target3joltForward = targetAdapter.getConnectedAdapter3joltForward();
                    target3joltForward.setConnectedAdapter3joltBackward(null);
                    targetAdapter.setConnectedAdapter3joltForward(null);
                    threeJolt.setCount(threeJolt.getCount() - 1);
                }
                oneJolt.setCount(oneJolt.getCount() + 1);
            }
        }else if(adaptersPrev.containsKey(current_val-3)){
            targetAdapter = adaptersPrev.get(current_val-3);
            if(null == targetAdapter.getConnectedAdapter3joltForward()){
                targetAdapter.setConnectedAdapter3joltForward(currentAdapter);
                currentAdapter.setConnectedAdapter3joltBackward(targetAdapter);
                threeJolt.setCount(threeJolt.getCount() + 1);
            }
        }
    }

    private static void checkAdapterBackward(Map<Integer, Adapter> adaptersPrev, int current_val, Adapter currentAdapter, Counter oneJolt, Counter threeJolt){
        Adapter targetAdapter;
        if (adaptersPrev.containsKey(current_val + 1)) {
            targetAdapter = adaptersPrev.get(current_val + 1);
            if (null == targetAdapter.getConnectedAdapter1joltBackward()) {
                targetAdapter.setConnectedAdapter1joltBackward(currentAdapter);
                currentAdapter.setConnectedAdapter1joltForward(targetAdapter);
                if(null != targetAdapter.getConnectedAdapter3joltBackward()){
                    Adapter target3joltBackward = targetAdapter.getConnectedAdapter3joltBackward();
                    target3joltBackward.setConnectedAdapter3joltForward(null);
                    targetAdapter.setConnectedAdapter3joltBackward(null);
                    threeJolt.setCount(threeJolt.getCount() - 1);
                }
                oneJolt.setCount(oneJolt.getCount() + 1);
            }
        }else if(adaptersPrev.containsKey(current_val+3)){
            targetAdapter = adaptersPrev.get(current_val+3);
            if(null == targetAdapter.getConnectedAdapter3joltBackward()){
                targetAdapter.setConnectedAdapter3joltBackward(null);
                currentAdapter.setConnectedAdapter3joltForward(null);
                threeJolt.setCount(threeJolt.getCount() + 1);
            }
        }
    }

    private static void second_part() throws FileNotFoundException {
        File myObj = new File("D:\\WORK\\Advent_of_code_2020\\10\\material\\input.txt");
        Scanner myReader = new Scanner(myObj);
        Map<Integer , Adapter> adaptersPrev =  new HashMap<Integer, Adapter>();

        /* default adapter */
        Adapter adapter0 = new Adapter(0);
        adaptersPrev.put(0, adapter0);

        Integer current_val;
        Adapter currentAdapter;
        Adapter targetAdapter;

        Integer max = 0;
        Map<Integer, List<Integer>> connectedJoltMap = new HashMap<>();
        List<Integer> focusDifJoltList;
        List<Integer> inverseFocusDifJoltList;
        List<Integer> accpetedDifJolt = Arrays.asList(1,2,3);


        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            current_val = Integer.parseInt(data);
            currentAdapter = new Adapter(current_val);

            if(current_val > max){
                max = current_val;
            }

            for(Integer difJolt : accpetedDifJolt){
                if(!connectedJoltMap.containsKey(difJolt)){
                    connectedJoltMap.put(difJolt, new ArrayList<>());
                }
                if(!connectedJoltMap.containsKey(-difJolt)){
                    connectedJoltMap.put(-difJolt, new ArrayList<>());
                }

                focusDifJoltList = connectedJoltMap.get(difJolt);

                if(adaptersPrev.containsKey(current_val + difJolt)){
                    if(!focusDifJoltList.contains(current_val)){
                        currentAdapter.getCountForwardList().add(current_val + difJolt);
                    }
                }
                if(adaptersPrev.containsKey(current_val - difJolt)){
                    targetAdapter = adaptersPrev.get(current_val - difJolt);
                    targetAdapter.getCountForwardList().add(current_val);
                }
            }

            adaptersPrev.put(current_val, currentAdapter);
        }
        myReader.close();

        current_val = max + 3;
        currentAdapter = new Adapter(current_val);

        adaptersPrev.put(current_val, currentAdapter);

        for(Integer difJolt : accpetedDifJolt){
            if(!connectedJoltMap.containsKey(difJolt)){
                connectedJoltMap.put(difJolt, new ArrayList<>());
            }
            if(!connectedJoltMap.containsKey(-difJolt)){
                connectedJoltMap.put(-difJolt, new ArrayList<>());
            }

            focusDifJoltList = connectedJoltMap.get(difJolt);

            if(adaptersPrev.containsKey(current_val + difJolt)){
                if(!focusDifJoltList.contains(current_val)){
                    currentAdapter.getCountForwardList().add(current_val + difJolt);
                }
            }
            if(adaptersPrev.containsKey(current_val - difJolt)){
                targetAdapter = adaptersPrev.get(current_val - difJolt);
                targetAdapter.getCountForwardList().add(current_val);
            }
        }

        // print all forward list
        for (Map.Entry<Integer, Adapter> adapterEntry : adaptersPrev.entrySet()){
            System.out.println(   adapterEntry.getKey() + " : " + adapterEntry.getValue().getCountForwardList() );
        }

        Map<Integer , Long> mapResult = new HashMap<>();
        for (Map.Entry<Integer, Adapter> adapterEntry : adaptersPrev.entrySet()){

            if(!mapResult.containsKey(adapterEntry.getKey())){
                Long value = 0L;
                for(Integer subAdapterValue : adapterEntry.getValue().getCountForwardList()){
                    value += Long.valueOf(getSubValue(adaptersPrev, subAdapterValue, mapResult));
                }
                mapResult.put(adapterEntry.getKey(), Long.valueOf(value));
            }

        }

        System.out.println( mapResult.get(0) );
    }

    private static Long getSubValue(Map<Integer , Adapter> adaptersPrev, Integer index, Map<Integer , Long> mapResult){
        if(!mapResult.containsKey(index)){
            Long value = 0L;
            for(Integer subAdapterValue : adaptersPrev.get(index).getCountForwardList()){
                value += Long.valueOf(getSubValue(adaptersPrev, subAdapterValue, mapResult));
            }
            if(adaptersPrev.get(index).getCountForwardList().size() < 1){
                value = 1L;
            }
            mapResult.put(index, Long.valueOf(value));
            return Long.valueOf(value);
        }else {
            return mapResult.get(index);
        }
    }

}
