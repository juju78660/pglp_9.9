package Command;

import DAO.CarreDAO;
import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;

import java.sql.SQLException;
import java.util.Scanner;

public class DrawingApp {
    private DrawingTUI drawingTUI;

    public void run() throws CommandeException, FormeDejaExistenteException, SQLException, FormeInexistanteException, NomDejaUtiliseException {
        Scanner scanner = new Scanner(System.in);
        drawingTUI = drawingTUI.init();
        CarreDAO carreDAO = new CarreDAO();
        carreDAO.initBD();

        while(true){
            // RECUPERATION PROCHAINE COMMANDE
            Commande c = drawingTUI.nextCommand(scanner.nextLine());
            // EXECUTION
            c.execute();
            // AFFICHAGE
            c.print();
            System.out.println("    -      -      -      -      -");
        }
    }

    public static void main( String[] args ) throws CommandeException, FormeDejaExistenteException, SQLException, FormeInexistanteException, NomDejaUtiliseException {
        DrawingApp drawingApp = new DrawingApp();
        drawingApp.run();
    }


}

//TODO REESSAYER TEST DAO INIT (JAI ENLEVE EFFACEMENT TABLES A CHAQUE NOUVEAU DAO
