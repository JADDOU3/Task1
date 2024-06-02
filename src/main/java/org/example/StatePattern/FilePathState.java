package org.example.StatePattern;
import org.example.*;
import java.util.Scanner;

public class FilePathState implements State{
private Context context;

public FilePathState(Context context){
    this.context = context;
}

    @Override
    public void handleInput() {
    Scanner scanner = context.getScanner();
    System.out.println("Enter The File Path :");
    String filePath = scanner.nextLine();
    if(filePath.toLowerCase().trim().equals("exit")) {
        context.setCurrentState(new ExitState(context));

    }
    else{
    CSVFileRW.setFilePath(filePath);
    CSVFileRW.readFile();
    context.setCurrentState(new ChooseColumnState(context));
    }
        context.handleInput();

    }
}
