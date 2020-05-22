# pglp_9.9

# Logiciel de dessin

Une commande permet d'afficher les commandes disponibles ("?")
Si la BD ne contient pas les tables au lancement du programme, merci de regarder dans la classe DAO, plus précisément les méthodes creationTables qui permet de créer les tables, et initBD qui permet de supprimer les données de toutes les tables.

Pour créer les tables si elles n'existent pas déjà:
CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();
compositeFormeDAO.creationTables();

Projet terminé à 99%, il subsiste quelque petits bugs...

Projet universitaire de Programmation
Julien DAURAT - 21600635
