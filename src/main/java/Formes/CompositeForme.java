package Formes;

import java.util.ArrayList;
import java.util.List;

public class CompositeForme extends Forme {
    private List<Forme> groupeFormes = new ArrayList<Forme>();

    @Override
    public String toString(){
        try{
            if(groupeFormes.size() == 0) throw new CompositeFormeVideException();
            else{
                String contenu = "";
                for(Forme forme : groupeFormes){
                    contenu += forme.toString() + ",";
                }
                return contenu.substring(0, contenu.length()-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ajouter(Forme forme){
        groupeFormes.add(forme);
    }

    public void deplacer(Point p) throws CompositeFormeVideException {
        if(groupeFormes.size() == 0) throw new CompositeFormeVideException();
        else{
            for(Forme forme : groupeFormes){
                forme.deplacer(p);
            }
        }
    }

    /*      INUTILE DANS NOTRE PROJET
    public void enlever(Formes.Forme forme) throws Exception {
        if(groupeFormes.contains(forme)){
            groupeFormes.remove(forme);
        }
        else throw new Exception("La forme n'existe pas");
    }*/

    public CompositeForme(String nom) {
        super(nom);
    }
}
