package klio;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

public class Application {

    private static Application _instance = null;

    private int _gameState = 0; // initializing

    /**
     * Private application constructor for singleton instance.
     */
    private Application() {
        _gameState = 1; // running
    }

    public static Application getInstance() {
        if (_instance == null) {
            _instance = new Application();
        }

        return _instance;
    }

    /**
     * Main application loop.
     * 
     * @return Non zero on error.
     */
    public int Run() {

        SimpleStringParser parser = new SimpleStringParser();
        PrintStream out = System.out;
        String prompt = "> ";
        Scanner keyboard = new Scanner(System.in);
        
        while (_gameState == 1)
        {
            out.print(prompt);
            
            String command = keyboard.nextLine();
            parser.parse(command);
            
            out.print("{ ");            
            Iterator<String> itr = parser.command.iterator();
            for (String s = null; itr.hasNext();) {
                s = itr.next();
                out.print(s);
                if (itr.hasNext()) {
                    out.print(", ");
                }
            }
            out.print(" }");
            out.println();            
            
            String joinedCommand = parser.stitchCommand();
            
            out.print("\"");
            out.print(joinedCommand);
            out.print("\"");
            out.println();
            
            if (joinedCommand.equals("exit") || joinedCommand.equals("quit")) {
                keyboard.close();
                return 0; // Clean exit.
            }
        }

        keyboard.close();
        return -1; // Clean exit happens within loop.
    }
}
