import java.util.ArrayList;
import java.util.List;

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

    // J'AI CHOISI DE FAIRE HERITER LES FORMES DE FORME, JE DOIS DONC METTRE LE CODE CI-DESSOUS
    CompositeForme(String nom) {
        super(nom);
    }
}
