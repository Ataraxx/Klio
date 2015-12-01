package klio;

public class Klio {

    public static void main(String[] args) {

        int errCode = Application.getInstance().Run();

        switch (errCode) {
        case -1: 
            System.err.println("Error on exit. Main loop terminated abnormally.");
            break;
        case 0:
        default: // No errors. Clean exit. Make 7, Up yours.
        }
    }

}
