package Command;

import java.util.HashMap;
import java.util.Map;

public class DrawingTUI {

    DrawingTUI d = null;

    private Map<String, Commande> commandes;

    public Commande nextCommand(String entree) throws CommandeException {
        Commande commande = null;
        System.out.println("Entree:" + entree);
        entree = entree.replaceAll(" ", "");
        System.out.println("Entree modif:" + entree);

        if(entree.contains("=")){ // CREATION
            System.out.println("creation");
            commande = new CommandeCreation();
        }
        else if(entree.contains("(")) {
            if(entree.contains("move")){
                commande = new CommandeDeplacement();
            }
            else if(entree.contains("remove")){
                commande = new CommandeSuppression();
            }
            else if(entree.contains("print")){
                commande = new CommandeAffichage();
            }
            else if(entree.contains("createGroup")){
                commande = new CommandeCreationGroupe();
            }
            else if(entree.contains("deleteGroup")){
                commande = new CommandeSuppressionGroupe();
            }
            else if(entree.contains("addDataGroup")){
                commande = new CommandeAjoutFormeGroupe();
            }
            else if(entree.contains("removeDataGroup")){
                commande = new CommandeSuppressionFormeGroupe();
            }
        }
        else if(entree.contains("stop")){
            commande = new CommandeStop();
        }
        else{
            throw new CommandeException("No command registered for " + entree);
        }
        return commande;
    }

    public void addCommand(String name, Commande commande) {
        this.commandes.put(name, commande);
    }

    public void executeCommand(String name) throws CommandeException {
        if(this.commandes.containsKey(name)){
            this.commandes.get(name).execute();
        }
        else{
            throw new CommandeException("La commande entrée n'existe pas");
        }
    }

    public DrawingTUI init() {
        d = new DrawingTUI();
        commandes = new HashMap<>();
        return d;
    }

    public void print(){

    }
}