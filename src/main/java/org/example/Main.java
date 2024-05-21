package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        csvFile_RW reader = new csvFile_RW();
        Scanner in = new Scanner(System.in);
        int x = 1,index = 0,AD=0;
        String back = "back" , exit = "exit" ,temp;
        boolean run = true;
        while(run){
            switch (x){
                case 1: System.out.println("Enter the file path :");
                temp = in.nextLine();
                if(temp.equals(exit))
                    x = 10;
                else{
                    reader.readFile(temp);
                    x++;
                }
                break;
                case 2: System.out.println("Enter the index of the column starting from 0 :");
                    reader.getHeader();
                    temp = in.nextLine();
                    if(temp.equals(exit))
                        x = 10;
                    else if(temp.equals(back)){
                        x--;
                    }
                    else{
                        index = Integer.parseInt(temp);
                        x++;
                    }
                    break;
                case 3: System.out.println("1.Ascending      2.Descending");
                    temp = in.nextLine();
                    if(temp.equals(exit))
                        x = 10;
                    else if(temp.equals(back)){
                        x--;
                    }
                    else{
                        if(Integer.parseInt(temp) == 1 || Integer.parseInt(temp) == 2) {
                            AD = Integer.parseInt(temp);
                            x++;
                        }
                        else{
                            System.out.println("invalid input");
                        }
                    }
                    break;
                case 4: System.out.println("File sorted successfully \n 1.Print in Console      2.Print in a New File");
                    temp = in.nextLine();
                    if(temp.equals(exit))
                        x = 10;
                    else if(temp.equals(back)){
                        x--;
                    }
                    else{
                        if(Integer.parseInt(temp) == 1 || Integer.parseInt(temp) == 2) {
                            reader.writeFile(Integer.parseInt(temp), index , AD);
                            x = 10;
                        }
                        else{
                            System.out.println("invalid input");
                        }
                    }
                    break;
                case 10: run = false;
                     break;
            }

        }


    }
}