package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class csvFile_RW {
    private Rows rows = new Rows();

    public void readFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(",");
                Row row = new Row();
                for (String val : splits) {
                    Cells cell = new Cells(val);
                    cell.checkIfDate();
                    row.cellsList.add(cell);
                }
                rows.rowsList.add(row);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFile(int printConsole , int index , int AD) {
        rows.sort(index, AD);
        if (printConsole == 1) {
            rows.cPrint();
            System.out.println("Sorted By : " + rows.rowsList.get(0).cellsList.get(index));
        }
        else if (printConsole == 2) {
            System.out.println("Enter the Filepath :");
            String path = new Scanner(System.in).nextLine();
            try {
                FileWriter file = new FileWriter(path);
                BufferedWriter buffer = new BufferedWriter(file);
                for (Row row : rows.rowsList) {
                    for (Cells cell : row.cellsList) {
                        buffer.write(cell.getValue() + ",");
                    }
                    buffer.newLine();
                }
                buffer.write("Sorted by : " + rows.rowsList.get(0).cellsList.get(index));
                buffer.close();
                System.out.println("File Saved Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getHeader(){
        System.out.print("[ ");
        for (int i = 0 ; i <rows.rowsList.get(0).cellsList.size();i++){
            System.out.print(rows.rowsList.get(0).cellsList.get(i).getValue()+ "    ");
        }
        System.out.println(" ]");
    }


}