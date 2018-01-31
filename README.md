# microservice-surveys

## Installation
	
### Pré-requis
- Docker
- Maven
		
## Compilation depuis les sources
Lancer le script suivant afin de compiler les sources et créer les images Docker :

```sh build-image.sh```
	
## Lancement du microservice
Après avoir construit les images, vous pouvez lancer le microservice en utilisant `Docker-compose`.

Serveur uniquement : `docker-compose up mongo server`


Serveur et client : `docker-compose up mongo server client`

	
## Accéder au microservice
Une fois le serveur lancé, la documentation Swagger de l'API se trouve à l'adresse `http://{host docker-machine}:8080/api`.

Si vous avez lancé le client, il se trouve à l'adresse `http://{host docker-machine}:4200`.

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
