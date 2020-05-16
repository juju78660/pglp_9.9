import java.util.ArrayList;
import java.util.List;
// TODO AJOUTER CONDITION COMPOSITE VIDE TOSTRING
// TODO AJOUTER CONDITION COMPOSITE VIDE DEPLACER
public class CompositeForme extends Forme{
    private List<Forme> groupeFormes = new ArrayList<Forme>();

    @Override
    public String toString(){
        String contenu = "";
        for(Forme forme : groupeFormes){
            contenu += forme.toString() + ",";
        }
        return contenu.substring(0, contenu.length()-1);
    }

    public void ajouter(Forme forme){
        groupeFormes.add(forme);
    }

    public void deplacer(Point p){
        for(Forme forme : groupeFormes){
            forme.deplacer(p);
        }
    }

    /*      INUTILE DANS NOTRE PROJET
    public void enlever(Forme forme) throws Exception {
        if(groupeFormes.contains(forme)){
            groupeFormes.remove(forme);
        }
        else throw new Exception("La forme n'existe pas");
    }*/

    CompositeForme(String nom) {
        super(nom);
    }
}
