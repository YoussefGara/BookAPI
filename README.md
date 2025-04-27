# README - API de Gestion de Livres (BookAPI)

## Technologies Utilisées

Ce projet utilise les technologies suivantes :

- **Spring Boot** : Framework backend pour créer des applications Java
- **Spring MVC** : Pour la gestion des requêtes HTTP et des vues
- **Thymeleaf** : Moteur de template pour les vues HTML
- **Spring Data JPA** : Pour l'accès aux données et la persistance
- **Hibernate** : ORM (Object-Relational Mapping) pour la gestion de la base de données
- **Bootstrap** : Framework CSS pour l'interface utilisateur
- **Validation API** : Pour la validation des données (annotations comme `@NotBlank`, `@NotNull`)

## Structure du Projet

Le projet est divisé en deux parties principales :

1. **API REST** (`BookController`) - Endpoints JSON
2. **Interface Web** (`BookViewController`) - Vues HTML

## Endpoints de l'API REST

### BookController (`/api/books`)

| Méthode HTTP | Endpoint                | Description                          |
|--------------|-------------------------|--------------------------------------|
| GET          | `/api/books`            | Récupère tous les livres             |
| GET          | `/api/books/{id}`       | Récupère un livre par son ID         |
| POST         | `/api/books`            | Crée un nouveau livre                |
| PUT          | `/api/books/{id}`       | Met à jour un livre existant         |
| DELETE       | `/api/books/{id}`       | Supprime un livre                    |
| GET          | `/api/books/search`     | Recherche de livres par mot-clé      |
| GET          | `/api/books/books/count`| Compte le nombre total de livres     |

## Endpoints de l'Interface Web

### BookViewController (`/view/books`)

| Méthode HTTP | Endpoint                   | Description                                  |
|--------------|----------------------------|----------------------------------------------|
| GET          | `/view/books`              | Affiche la liste de tous les livres (HTML)  |
| GET          | `/view/books/{id}`         | Affiche les détails d'un livre (HTML)       |
| GET          | `/view/books/new`          | Affiche le formulaire de création (HTML)    |
| POST         | `/view/books`              | Traite la soumission du formulaire          |
| GET          | `/view/books/search`       | Recherche de livres (HTML)                  |
| GET          | `/view/books/delete/{id}`  | Supprime un livre (redirection après)       |
| GET          | `/view/books/edit/{id}`    | Affiche le formulaire d'édition (HTML)      |
| POST         | `/view/books/edit/{id}`    | Traite la soumission du formulaire d'édition |

## Modèle de Données (Book)

```java
public class Book {
    private Long id;
    @NotBlank private String title;    // Titre du livre (requis)
    @NotBlank private String author;   // Auteur (requis)
    @NotBlank private String isbn;     // ISBN (requis)
    @NotNull @Positive private Double price; // Prix (positif, requis)
}
```

## Prérequis

- Java JDK 11 ou supérieur
- Maven 
- Base de données configurée (H2, MySQL, PostgreSQL, etc.)

## Installation

1. Cloner le dépôt
2. Configurer la base de données dans `application.properties`
3. Lancer l'application avec `mvn spring-boot:run` ou via votre IDE

## Fonctionnalités

- CRUD complet pour les livres
- Interface web responsive avec Bootstrap
- Validation des données côté serveur
- Recherche par titre ou auteur
