import java.sql.*;

public abstract class DAO<T> {
    public Connection connect = null;
    String dbURL = "jdbc:derby:DB;create=true";

    public DAO(){
        initBD();
    }


    private void initBD() {
        try{
            connect = DriverManager.getConnection(dbURL);
            PreparedStatement requete1 = this.connect.prepareStatement("TRUNCATE TABLE Carre");
            requete1.executeUpdate();

            PreparedStatement requete2 = this.connect.prepareStatement("TRUNCATE TABLE Cercle");
            requete2.executeUpdate();

            PreparedStatement requete3 = this.connect.prepareStatement("TRUNCATE TABLE Rectangle");
            requete3.executeUpdate();

            PreparedStatement requete4 = this.connect.prepareStatement("TRUNCATE TABLE Triangle");
            requete4.executeUpdate();

            this.connect.commit();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if (connect != null) {
            try {
                connect.commit();
                connect.close();
            }
            catch (SQLException except) {
                except.printStackTrace();
            }
        }
    }

    public void affichageTable(String nomTable){
        try {
            connect = DriverManager.getConnection(dbURL);
            PreparedStatement requete = connect.prepareStatement("SELECT * FROM " + nomTable);
            ResultSet resultat = requete.executeQuery();

            ResultSetMetaData resultMeta = resultat.getMetaData();

            System.out.println("\n**********************************");
            //On affiche le nom des colonnes
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

            System.out.println("\n**********************************");

            while(resultat.next()){
                for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                    System.out.print("\t" + resultat.getObject(i).toString() + "\t |");

                System.out.println("\n---------------------------------");

            }
            resultat.close();
            requete.close();
            this.connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode de création
     * @param obj
     * @return boolean
     */
    public abstract boolean create(T obj) throws FormeDejaExistenteException, SQLException;

    /**
     * Méthode pour effacer
     * @param nom
     * @return boolean
     */
    public abstract boolean delete(String nom) throws SQLException, FormeInexistanteException;

    /**
     * Méthode de mise à jour
     * @param obj
     * @return boolean
     */
    public abstract T update(T obj) throws SQLException, FormeInexistanteException;

    /**
     * Méthode de recherche des informations
     * @param nom
     * @return T
     */
    public abstract T find(String nom) throws SQLException, FormeInexistanteException;
}
