package org.example;
import org.example.StatePattern.*;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        new FilePathState(context).handleInput();
    }
}