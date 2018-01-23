# microservice-surveys

# Docker-compose
En utilisant `docker-compose up --build` à la racine du micro-service, on peut lancer un serveur *mongo* ainsi que *mongo-express*, qui sera en écoute sur le port **8081** afin d'accéder à la base de donnée. 

# Features
## Création de sondage
- [x] Titre du sondage
- [ ] Liste de champs
  - [x] Label (description du champ)
  - [ ] Choix multiple
  - [x] Champ texte
  - [ ] Choix simple
  - [ ] Choix "autre"
- [x] Ordre des champs
- [ ] Pagination
- [ ] Public / privé
  - [ ] Uniquement personnes avec le lien
  - [ ] Protection par mot de passe
  - [ ] Protection par compte utilisateur
### Brouillon d'un sondage
## Modification de sondage existant
- [x] Changer le status
- [ ] Uniquement en mode brouillon
## Fermeture de sondage
- [x] Fermer
- [ ] Rendre le résultat public ou non
## Suppression de sondage
## Afficher un sondage
- [x] afficher formulaire
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
- [x] getAll
- [ ] Filtre par user
- [ ] Pagination
## Participer à un sondage
- [x] Répondre à une question
### Enregistrer l'état d'une réponse à un sondage
- [ ] Retourner une URL
## Récupérer les réponses à un sondage
- [ ] Réponses pour un sondage
- [ ] Réponses d'un utilisateur en particulier
- [ ] Réponses à une question en particulier
