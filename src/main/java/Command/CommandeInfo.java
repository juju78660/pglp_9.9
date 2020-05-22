package Command;

public class CommandeInfo implements Commande{
    @Override
    public void execute(){
        System.out.println("        Commandes:\n" +
                            "Création/Mise à jour d'une forme: ex:c1=Carre((1,10),30)\n" +
                            "Déplacement d'une forme/composite: move(nomForme)\n" +
                            "Suppression d'une forme/composite: remove(nomForme)\n" +
                            "Affichage d'une forme/composite: print(nomForme)\n" +
                            "Affichage de toute les formes existantes: printAll()\n\n" +

                            "Création d'un composite: createGroup(nomGroupe)\n" +
                            "Suppression d'un composite: deleteGroup(nomGroupe)\n" +
                            "Ajout d'une forme à un composite: addDataGroup(nomForme,nomComposite)\n" +
                            "Suppression d'une forme d'un composite: removeDataGroup(nomForme,nomComposite)\n\n" +

                            "Remettre à zéro la BD: init()\n"+
                            "Arrêter le programme: stop()\n"+
                            "Afficher toutes les commandes: ?");
    }

    @Override
    public void recupDonnees(String s){}

    @Override
    public void print(){}
}
