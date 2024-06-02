package org.example.StatePattern;

import org.example.CSVFileRW;

public class ExitState implements State{
    private Context context;
   public ExitState(Context context){
       this.context = context;
   }


    @Override
    public void handleInput() {
       System.out.println("bye <3");
    }
}
