package Command;

import java.util.HashMap;
import java.util.Map;

public class DrawingTUI {

    private static DrawingTUI d = null;
    private static Map<String, Commande> commandes;

    /**
     * Méthode qui récupère la commande entrée et ses données, et instantie un objet correspondant à la commande entrée
     *
     * @param entree the entree
     * @return the commande
     * @throws CommandeException the commande exception
     */
    public Commande nextCommand(String entree) throws CommandeException {
        Commande commande = null;
        entree = entree.replaceAll(" ", "");

        if (entree.contains("=")){  // CREATION
            commande = commandes.get("=");
            commande.recupDonnees(entree);
        }
        else if(entree.contains("?")){  // AIDE
            commande = commandes.get("?");
        }
        else if(commandes.containsKey(entree.split("\\(")[0]) && entree.contains("(") && entree.contains(")")){ // AUTRE COMMANDE
            commande = commandes.get(entree.split("\\(")[0]);
            commande.recupDonnees(entree);
        }
        else{
            throw new CommandeException("La commande entrée n'existe pas/n'est pas correctement entrée");
        }
        return commande;
    }

    /**
     * Ajout d'une commande à la liste des commandes
     *
     * @param name     the name
     * @param commande the commande
     */
    public void addCommand(String name, Commande commande) {
        commandes.put(name, commande);
    }


    /**
     * Initialise le programme avec les commandes connues
     *
     * @return the drawing tui
     */
    public static DrawingTUI init() {
        d = new DrawingTUI();
        commandes = new HashMap<>();
        d.addCommand("=", new CommandeCreationUpdate());
        d.addCommand("move", new CommandeDeplacement());
        d.addCommand("remove", new CommandeSuppression());
        d.addCommand("print", new CommandeAffichage());
        d.addCommand("printAll", new CommandeAffichageListeFormes());
        d.addCommand("createGroup", new CommandeCreationGroupe());
        d.addCommand("deleteGroup", new CommandeSuppressionGroupe());
        d.addCommand("addDataGroup", new CommandeAjoutFormeGroupe());
        d.addCommand("removeDataGroup", new CommandeSuppressionFormeGroupe());
        d.addCommand("init", new CommandeRemiseZeroBD());
        d.addCommand("stop", new CommandeStop());
        d.addCommand("?", new CommandeInfo());

        return d;
    }
}