package Command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DrawingTUI {

    private static DrawingTUI d = null;
    private static Map<String, Commande> commandes;


    public Commande nextCommand(String entree) throws CommandeException {
        Commande commande = null;
        entree = entree.replaceAll(" ", "");
        if(commandes.containsKey(entree)){
            commande = commandes.get(entree);
            commande.recupDonnees(entree);
        }
        else if (entree.contains("=")){
            commande = commandes.get("=");
            commande.recupDonnees(entree);
        }
        else{
            throw new CommandeException("La commande entrée n'existe pas");
        }
        return commande;
    }

    public void addCommand(String name, Commande commande) {
        commandes.put(name, commande);
    }


    public static DrawingTUI init() throws SQLException {
        d = new DrawingTUI();
        commandes = new HashMap<>();
        d.addCommand("=", new CommandeCreation());
        d.addCommand("move", new CommandeDeplacement());
        d.addCommand("remove", new CommandeSuppression());
        d.addCommand("print", new CommandeAffichage());
        d.addCommand("createGroup", new CommandeCreationGroupe());
        d.addCommand("deleteGroup", new CommandeSuppressionGroupe());
        d.addCommand("addDataGroup", new CommandeAjoutFormeGroupe());
        d.addCommand("removeDataGroup", new CommandeSuppressionFormeGroupe());
        d.addCommand("stop", new CommandeStop());

        return d;
    }

    public void print(){

    }
}