package Command;

import Formes.Carre;

import java.util.ArrayList;
import java.util.Scanner;

public class DrawingApp {

    private ArrayList<Carre> listeCarrees;

    public void run() throws CommandeException {
        Scanner scanner = new Scanner(System.in);
        DrawingTUI drawingTUI = new DrawingTUI();

        while(true){
            // RECUPERATION PROCHAINE COMMANDE
            Commande c = drawingTUI.nextCommand(scanner.nextLine());
            // EXECUTION
            c.execute();
            // AFFICHAGE
            // c.print();
        }
    }

    public static void main( String[] args ) throws CommandeException {
        DrawingApp drawingApp = new DrawingApp();
        drawingApp.run();
    }


}
