package Command;

public class CommandeStop implements Commande {
    @Override
    public void execute() {
        System.out.println("STOPPING THE PROGRAM");
        System.exit(1);
    }

    @Override
    public void recupDonnees(String donnees) {}

    @Override
    public void print(){
        System.out.println("Arret du programme demande");
    }
}
