package com.sarik;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        first_part();
        second_part();
    }

    private static void first_part() throws FileNotFoundException {
        File myObj = new File("D:\\WORK\\Advent_of_code_2020\\08\\material\\input.txt");
        Scanner myReader = new Scanner(myObj);
        List<String[]> row_list = new ArrayList();
        int acc = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            row_list.add(data.split(" "));
        }
        int current_index = 0;
        List passed_index = new ArrayList();
        while(!passed_index.contains(current_index)){
            String[] current_row = row_list.get(current_index);
            passed_index.add(current_index);
            if("jmp".equalsIgnoreCase(current_row[0])){
                current_index += Integer.parseInt(current_row[1]);
            }else{
                current_index += 1;
                if("acc".equalsIgnoreCase(current_row[0])){
                    acc += Integer.parseInt(current_row[1]);
                }else if("nop".equalsIgnoreCase(current_row[0])){

                }
            }
        }
        myReader.close();
        System.out.println(acc);
    }

    private static void second_part() throws FileNotFoundException {
        File myObj = new File("D:\\WORK\\Advent_of_code_2020\\08\\material\\input.txt");
        Scanner myReader = new Scanner(myObj);
        List<CommandObj> commandLists = new ArrayList();
        CommandObj lastCommand = new CommandObj();
        int index = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lastCommand = new CommandObj();
            List<String> command = Arrays.asList(data.split(" "));
            lastCommand.setCommand(command.get(0));
            lastCommand.setValue(Integer.parseInt(command.get(1)));
            lastCommand.setIndex(index);
            commandLists.add(lastCommand);
            index++;
        }

        System.out.println(" ");

        CommandObj targetCommand = new CommandObj();
        for (CommandObj currentCommand : commandLists) {
            if (currentCommand.getCommand().equalsIgnoreCase("jmp")) {
                if (currentCommand.getIndex() + 1 < commandLists.size()) {
                    targetCommand = commandLists.get(currentCommand.getIndex() + currentCommand.getValue());
                    targetCommand.getReferenceBy().add(currentCommand);
                    currentCommand.getForwardTo().add(targetCommand);
                }
            } else {
                if (currentCommand.getIndex() + 1 < commandLists.size()) {
                    targetCommand = commandLists.get(currentCommand.getIndex() + 1);
                    targetCommand.getReferenceBy().add(currentCommand);
                    currentCommand.getForwardTo().add(targetCommand);
                }
            }
        }

        List<Integer> listLast = new ArrayList<>();
        findAllSuccessPath(listLast, lastCommand);

        System.out.println(listLast);


        List<Integer> passedIndex = new ArrayList<>();

        int current_index = 0;
        int acc = 0;
        Boolean isFoundResult = false;
        while (true) {
            if(passedIndex.contains(current_index)){
                break;
            }
            passedIndex.add(current_index);
            CommandObj current_row = commandLists.get(current_index );
            if(!isFoundResult) {
                if (current_row.getCommand().equalsIgnoreCase("jmp")) {
                    if (listLast.contains(current_row.getIndex() + 1)) {
                        current_row.setCommand("nop");
                        System.out.println(" change> " + current_row.getIndex() + " " + current_row.getValue());
                        isFoundResult = true;
                    }
                }
                if (current_row.getCommand().equalsIgnoreCase("nop")) {
                    if (listLast.contains(current_row.getIndex() + current_row.getValue())) {
                        System.out.println(" change> " + current_row.getIndex() + current_row.getValue());
                        current_row.setCommand("jmp");
                        isFoundResult = true;
                    }
                }
            }
            if ("jmp".equalsIgnoreCase(current_row.getCommand())) {
                current_index += current_row.getValue();
            } else {
                if ("acc".equalsIgnoreCase(current_row.getCommand())) {
                    acc += current_row.getValue();
                }
                current_index += 1;
            }
            if(commandLists.size() <= current_index){
                break;
            }
        }

        System.out.println(" passIndex > " + passedIndex);

        System.out.println(acc);
    }

    private static void findAllSuccessPath(List<Integer> listLast,  CommandObj lastCommand){
        if(lastCommand.getReferenceBy().size() == 0) {
            if(!listLast.contains(lastCommand.getIndex())) {
                listLast.add(lastCommand.getIndex());
            }
        }else {
            for(CommandObj commandObj1 : lastCommand.getReferenceBy()) {
                findAllSuccessPath(listLast , commandObj1);
            }
        }
    }



}


