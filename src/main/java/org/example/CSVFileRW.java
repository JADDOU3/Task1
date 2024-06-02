package org.example;

import org.example.Cells.*;
import org.example.Cells.Row;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileRW {
    private static String filePath;
    private static int columnIndex,whereToPrint,sortingOrder;
    private static FileReader file;
    private static List<Row> list = new ArrayList<>();


    public static void setFilePath(String filePath) {
        CSVFileRW.filePath = filePath;
    }

    public static void setColumnIndex(int columnIndex) {
        CSVFileRW.columnIndex = columnIndex;
    }

    public static void setWhereToPrint(int whereToPrint){
        CSVFileRW.whereToPrint = whereToPrint;
    }

    public static void setSortingOrder(int sortingOrder){
        CSVFileRW.sortingOrder = sortingOrder;
    }

    public static int getRowSize(){
        return list.get(0).cellList.size();
    }


    private static boolean isValidBigDecimal(String a){
        try {
            new BigDecimal(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void readFile(){
        try {
            file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(",");
                Row row = new Row();
                for (String val : splits) {
                    Cell cell;
                    if(val.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                        cell = new DateCell(val);
                    }
                    else if(val.matches("\\d{2}:\\d{2}:\\d{2}")){
                        cell = new TimeCell(val);
                    }
                    else if(isValidBigDecimal(val)){
                        cell = new BigDecimalCell(val);
                    }
                    else{
                        cell = new StringCell(val);
                    }
                    row.cellList.add(cell);
                }
                list.add(row);
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void sort() {
        if (sortingOrder == 1) {
            for (int i = 1; i < list.size() - 1; i++) {
                for (int j = 1; j < list.size() - i; j++) {
                    if (list.get(j).cellList.get(columnIndex).compare(list.get(j + 1).cellList.get(columnIndex)) > 0) {
                        Row temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
        else {
            for (int i = 1; i < list.size() - 1; i++) {
                for (int j = 1; j < list.size() - i; j++) {
                    if (list.get(j).cellList.get(columnIndex).compare(list.get(j + 1).cellList.get(columnIndex)) < 0) {
                        Row temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
    }

    public static void cPrint (){
        for(Row row: list){
            System.out.print("{ ");
            for(Cell cell : row.cellList){
                System.out.print("[ " + cell.getValue() + " ]");
            }
            System.out.println(" }");
        }
        System.out.print("Sorted by : " + list.get(0).cellList.get(columnIndex).getValue());
    }

    public static void fPrint(){
        System.out.println("Enter the Filepath :");
        String path = new Scanner(System.in).nextLine();
        try {
            FileWriter file = new FileWriter(path);
            BufferedWriter buffer = new BufferedWriter(file);
            for (Row row : list) {
                for (Cell cell : row.cellList) {
                    buffer.write(cell.getValue() + ",");
                }
                buffer.newLine();
            }
            buffer.write("Sorted by : " + list.get(0).cellList.get(columnIndex).getValue());
            buffer.close();
            System.out.println("File Saved Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wrtieFile(){
        if(whereToPrint == 1)
            fPrint();
        else
            cPrint();
    }

    public static void getHeader(){
            System.out.print("{ ");
            for(int i = 0 ; i < list.get(0).cellList.size();i++){
                System.out.print("[ "+list.get(0).cellList.get(i).getValue()+" ] ");
            }
            System.out.println(" }");

    }
}

