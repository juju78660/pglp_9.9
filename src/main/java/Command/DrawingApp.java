package Command;

import DAO.CarreDAO;
import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DrawingApp {
    private DrawingTUI drawingTUI;

    /**
     * Méthode qui initialise les données, va recuperer les entrees clavier et utiliser DrawingTUI pour les traiter
     *
     * @throws CommandeException           the commande exception
     * @throws FormeDejaExistenteException the forme deja existente exception
     * @throws SQLException                the sql exception
     * @throws FormeInexistanteException   the forme inexistante exception
     * @throws NomDejaUtiliseException     the nom deja utilise exception
     */
    public void run() throws CommandeException, FormeDejaExistenteException, SQLException, FormeInexistanteException, NomDejaUtiliseException {
        Scanner scanner = new Scanner(System.in);
        drawingTUI = drawingTUI.init();
        CarreDAO carreDAO = new CarreDAO();
        Map<String, String> listeFormes;
        listeFormes = carreDAO.recupListeFormes();
        Set cles = listeFormes.keySet();
        Iterator it = cles.iterator();
        System.out.println("/---Formes existantes---\\");
        while (it.hasNext()) {
            Object cle = it.next();
            System.out.println("    " + cle + " - " + listeFormes.get(cle));
        }
        System.out.println("\\-----------------------/");
        System.out.println("   Taper '?' pour infos\n");
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

    /**
     * Méthode de lancement du programme
     *
     * @param args the input arguments
     * @throws CommandeException           the commande exception
     * @throws FormeDejaExistenteException the forme deja existente exception
     * @throws SQLException                the sql exception
     * @throws FormeInexistanteException   the forme inexistante exception
     * @throws NomDejaUtiliseException     the nom deja utilise exception
     */
    public static void main( String[] args ) throws CommandeException, FormeDejaExistenteException, SQLException, FormeInexistanteException, NomDejaUtiliseException {
        DrawingApp drawingApp = new DrawingApp();
        drawingApp.run();
    }


}

/*
    Jeu de données de test:
    c1=Carre((1,10),30)
    r1=Rectangle((1,10),30,40)
    createGroup(compo1)
    createGroup(compo2)
    addDataGroup(r1,compo1)
    addDataGroup(c1,compo1)
    addDataGroup(c1,compo2)
    move(compo1,(5,6))
 */