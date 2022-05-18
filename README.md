# Groupe 3

### Comment utiliser
Lancez `db.sh` pour lancer le container de la BDD ou modifier les paramètres de connexion
dans le fichier `application.properties`.

Si jamais vous n'utilisez pas postgresSQL, ajoutez le driver de votre BDD dans `build.gradle` et modifier le `application.properties`.

Pour accéder à l'interface de swagger http://localhost:8080/swagger-ui

`gradle BootRun` pour lancer le serveur

Java 17 requis

### API HTTP
Notre api est une api RESTful pour tous se qui ne concerne pas directement les jeux.
Pour les jeux l'api devient un genre d'automate à état.

### Deployment
L'application client déployer et disponible sur https://cyberchamis.tuturu.fr
Le serveur déployer est disponible ici https://grechat.herokuapp.com/swagger-ui/
