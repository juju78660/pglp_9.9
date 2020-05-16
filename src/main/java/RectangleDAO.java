import java.sql.*;

public class RectangleDAO extends DAO<Rectangle> {
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";

    @Override
    public boolean create(Rectangle obj) throws FormeDejaExistenteException, SQLException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Rectangle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, obj.nom);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
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
        if (!objetDejaExistant) {
            String contenuRequete = "INSERT INTO Rectangle (Nom, PosX, PosY, Longueur, Largeur) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement requete = null;
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setInt(2, obj.p.x);
                requete.setInt(3, obj.p.y);
                requete.setInt(4, obj.longueur);
                requete.setInt(5, obj.largeur);
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
            String contenuRequete = "DELETE FROM Rectangle WHERE nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
            connect.commit();
            return false;
        }
        connect.commit();
        connect.close();
        return true;
    }

    @Override
    public Rectangle update(Rectangle obj) throws SQLException, FormeInexistanteException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Rectangle WHERE Nom = ?";
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
                String contenuRequete = "UPDATE Rectangle SET PosX = ?, PosY = ?, Longueur = ?, Largeur = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.p.x);
                requete.setInt(2, obj.p.y);
                requete.setInt(3, obj.longueur);
                requete.setInt(4, obj.largeur);
                requete.setString(5, obj.nom);
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
    public Rectangle find(String nom) throws SQLException, FormeInexistanteException {
        Rectangle rectangle = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Rectangle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()){
                rectangle = new Rectangle(
                        resultat.getString("Nom"),
                        new Point(resultat.getInt("PosX"), resultat.getInt("PosY")),
                        resultat.getInt("Longueur"), resultat.getInt("Largeur"));
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
        return rectangle;
    }
}