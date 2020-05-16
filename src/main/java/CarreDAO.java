import java.sql.*;

public class CarreDAO extends DAO<Carre>{
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";

    @Override
    public boolean create(Carre obj) throws FormeDejaExistenteException, SQLException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Carre WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, obj.nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                objetDejaExistant = true;
                resultat.close();
                requete.close();
                connect.commit();
                connect.close();
                throw new FormeDejaExistenteException();
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!objetDejaExistant){
            String contenuRequete = "INSERT INTO Carre (Nom, PosX, PosY, Cote) VALUES (?, ?, ?, ?)";
            PreparedStatement requete = null;
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setInt(2, obj.p.x);
                requete.setInt(3, obj.p.y);
                requete.setInt(4, obj.cote);
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return true;
        }
        connect.commit();
        connect.close();
        return false;
    }

    @Override
    public boolean delete(String nom) throws SQLException {
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "DELETE FROM Carre WHERE nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
            connect.close();
            return false;
        }
        connect.commit();
        connect.close();
        return true;
    }

    @Override
    public Carre update(Carre obj) throws SQLException, FormeInexistanteException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Carre WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, obj.nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                objetDejaExistant = true;
                resultat.close();
                requete.close();
                connect.commit();
                connect.close();
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(objetDejaExistant) {
            try {
                connect = DriverManager.getConnection(dbURL);
                String contenuRequete = "UPDATE Carre SET PosX = ?, PosY = ?, Cote = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.p.x);
                requete.setInt(2, obj.p.y);
                requete.setInt(3, obj.cote);
                requete.setString(4, obj.nom);
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return obj;
        }
        else{
            throw new FormeInexistanteException();
        }

    }

    @Override
    public Carre find(String nom) throws SQLException, FormeInexistanteException {
        Carre carre = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Carre WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                carre = new Carre(
                        resultat.getString("Nom"),
                        new Point(resultat.getInt("PosX"), resultat.getInt("PosY")),
                        resultat.getInt("Cote"));
            }
            else{
                throw new FormeInexistanteException();
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connect.commit();
        connect.close();
        return carre;
    }
}
