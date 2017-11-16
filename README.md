# microservice-surveys

# Docker-compose
En utilisant `docker-compose up --build` à la racine du mico-service, on peut lancer un serveur *mango* ainsi que *mango-express*, qui sera en écoute sur le port **8081** afin d'accéder à la base de donnée. 

# Features
## Création de sondage
- [ ] Titre du sondage
- [ ] Liste de champs
  - [ ] Label (description du champ)
  - [ ] Choix multiple
  - [ ] Champ texte
  - [ ] Choix simple
  - [ ] Choix "autre"
- [ ] Ordre des champs
- [ ] Pagination
- [ ] Public / privé
  - [ ] Uniquement personnes avec le lien
  - [ ] Protection par mot de passe
  - [ ] Protection par compte utilisateur
### Brouillon d'un sondage
## Modification de sondage existant
- [ ] Uniquement en mode brouillon
## Fermeture de sondage
- [ ] Rendre le résultat public ou non
## Suppression de sondage
## Afficher un sondage
-> Renvoyer l'état du sondage et le lien vers les données
- [ ] Soit le formulaire (sondage ouvert)
- [ ] Soit les résultats (sondage fermé, résultats accessibles)
## Liste des sondages de l'utilisateur
- [ ] Filtre par status
  - [ ] Brouillon
  - [ ] Ouvert
  - [ ] Fermé
- [ ] Pagination
## Liste publique des sondages
- [ ] Filtre par user
- [ ] Pagination
## Participer à un sondage
### Enregistrer l'état d'une réponse à un sondage
- [ ] Retourner une URL
## Récupérer les réponses à un sondage
- [ ] Réponses pour un sondage
- [ ] Réponses d'un utilisateur en particulier
- [ ] Réponses à une question en particulier