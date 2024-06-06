package org.example.StateController;

import org.example.CSVFileRW;

import java.util.Scanner;

public class ChooseSortingOrderState implements State{
    private Context context;

    ChooseSortingOrderState(Context context){
        this.context = context;
    }
    @Override
    public void handleInput() {
        Scanner scanner = context.getScanner();
        System.out.println("How To Sort : \n 1.Ascending        2.Descending");
        String sortingOrder = scanner.next();
        if(sortingOrder.toLowerCase().trim().equals("exit"))
            context.setCurrentState(new ExitState(context));
        else if(sortingOrder.toLowerCase().trim().equals("back")){
            context.setCurrentState(new ChooseColumnState(context));
        }
        else if(!sortingOrder.equals("1") && !sortingOrder.equals("2")){
            System.err.println("invalid input");
        }
        else {
            CSVFileRW.setSortingOrder(Integer.parseInt(sortingOrder));
            CSVFileRW.sort();
            context.setCurrentState(new WhereToPrintState(context));
        }
        context.handleInput();
    }

}
