package Formes;

import java.util.ArrayList;
import java.util.List;

public class CompositeForme extends Forme {
    private List<Forme> groupeFormes = new ArrayList<Forme>();

    public Forme formeAUpdate;
    public String nomTableForme;
    public Forme ancienneFormeAUpdate;
    public Point pointDeplacement;
    public int typeUpdate; // 1 : AJOUT FORME AU COMPOSITE      2 : UPDATE D'UNE FORME DU COMPOSITE        3 : DEPLACEMENT DES FORMES DU COMPOSITE
    // 4 : SUPP FORME DU COMPOSITE
    @Override
    public String toString(){
        if(groupeFormes.size() == 0) return nom + " est vide";
        else{
            String contenu = nom + ": {\n";
            for(Forme forme : groupeFormes){
                contenu += "    " + forme.toString() + "\n";
            }
            contenu.substring(0, contenu.length()-1);
            contenu+="}";
            return  contenu;
        }
    }

    public void ajouter(Forme forme) throws CompositeFormeContientDejaException {
        if(groupeFormes.contains(forme)){
            throw new CompositeFormeContientDejaException();
        }
        else groupeFormes.add(forme);
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

    public CompositeForme(String nom, Forme formeAUpdate, String nomTableForme) {
        super(nom);
        this.formeAUpdate = formeAUpdate;
        this.nomTableForme = nomTableForme;
    }

    public Forme getFormeAUpdate() {
        return formeAUpdate;
    }

    public void setFormeAUpdate(Forme formeAUpdate) {
        this.formeAUpdate = formeAUpdate;
    }

    public String getNomTableForme() {
        return nomTableForme;
    }

    public void setNomTableForme(String nomTableForme) {
        this.nomTableForme = nomTableForme;
    }

    public int getTypeUpdate() {
        return typeUpdate;
    }

    public void setTypeUpdate(int typeUpdate) {
        this.typeUpdate = typeUpdate;
    }

    public Forme getAncienneFormeAUpdate() {
        return ancienneFormeAUpdate;
    }

    public void setAncienneFormeAUpdate(Forme ancienneFormeAUpdate) {
        this.ancienneFormeAUpdate = ancienneFormeAUpdate;
    }

    public Point getPointDeplacement() {
        return pointDeplacement;
    }

    public void setPointDeplacement(Point pointDeplacement) {
        this.pointDeplacement = pointDeplacement;
    }
}
