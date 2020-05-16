# pglp_9.9

# Logiciel de dessin

Le but de cet exercice est de réaliser un logiciel de dessin. On se limitera ici à un affichage textuel, i.e. seule une description des figures sera affichée. Par exemple, un cercle de centre (0, 0) et de rayon 50 sera "affiché" par la chaîne de caractères "Formes.Cercle(centre=(0,0),rayon=50)".

L'utilisateur interagira avec l'application par l'intermédiaire de la ligne de commandes. Chaque commande débutera par une instruction suivie des arguments de cette instruction. Par exemple, pour créer un cercle, l'utilisateur pourra taper "c1 = Formes.Cercle((0, 0), 50)", puis pour le déplacer "move(c1, (10, 20))".

Le logiciel devra offrir les fonctionnalités suivantes:
* Chaque forme sera identifiée par un nom ("c1", "unCercle", ...).
* L'application permettra de manipuler des cercles, des rectangles, des carrés et des triangles.
* Chaque forme devra pouvoir être affichée et déplacée.
* Les formes devront pouvoir être regroupées et pourront subir des traitements globaux comme par exemple déplacer ensemble un cercle et un triangle.
* Un dessin (ensemble de formes) pourra être sauvegardé/chargé dans un SGBD embarqué comme [HyperSQL](http://hsqldb.org/), [H2](http://www.h2database.com/html/main.html) ou [Derby](https://db.apache.org/derby/).

Dans cet exercice, vous appliquerez au mieux les principes de conception ainsi que les fonctionnalités du langage Java (exceptions, collections, ...).
Vous respecterez les mêmes modalités que pour les exercices précédents.
Le dépôt _github_ sera nommé `pglp_9.9`.

1. Proposer et implémenter une hiérarchie de classe représentant les formes graphiques.
1. Représenter la notion de groupe de formes en appliquant le pattern `Composite`.
2. Render les formes et les groupes persistants en utilisant le pattern `DAO.DAO` et JDBC.
1. Réaliser la classe `Command.DrawingTUI` qui se chargera des interactions avec l’utilisateur.
Cette classe fournira une méthode `nextCommand` qui analysera le texte saisi par l’utilisateur et retournera un objet implémentant l’interface Command.Commande (cf. question suivante).
Elle proposera également une méthode permettant d'afficher un dessin.
1. Les commandes seront implémentées à l’aide du modèle de conception _Commande_.
   1. créer l’interface `Command` comportant la méthode `execute`,
   1. créer une classe implémentant cette interface pour chaque action.
1. Réaliser la classe principale `Command.DrawingApp`.
La méthode `run` de cette classe interagira avec `Command.DrawingTUI` pour récupérer la prochaine commande, l’exécutera puis affichera le résultat.
