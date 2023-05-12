# Tenisu Atelier

Test technique réalisé sous [Spring Boot](http://projects.spring.io/spring-boot/), permettant la gestion de joueurs de tennis

## Prérequis

- JDK 17
- Maven 3

## Lancer l'application

Il existe plusieurs manières de lancer l'application : la première étant d'exécuter la méthode `main` dans la classe `com.dev.tenisuatelier.TenisuAtelierApplication` de l'IDE.

La deuxième étant de faire appel au plugin Spring Boot Maven :
```shell
mvn spring-boot:run
```

## Tester l'application

Se rendre sur http://localhost:8080/players pour avoir la liste des joueurs en base, triés du meileur au moins bon.

D'autres endpoints sont également disponibles :

- (GET) http://localhost:8080/players/{id} : retourne les informations d'un joueur grâce à son ID
- (GET) http://localhost:8080/players?action=best-country : retourne le pays ayant le plus grand ratio de parties gagnées
- (GET) http://localhost:8080/players?action=average-bmi : retourne l'IMC moyen de tous les joueurs
- (GET) http://localhost:8080/players?action=median-height : retourne la médiane de la taille des joueurs
- (DELETE) http://localhost:8080/players/{id} : supprime le joueur grâce à son ID
```
curl -X DELETE http://localhost:8080/players/{id}
```