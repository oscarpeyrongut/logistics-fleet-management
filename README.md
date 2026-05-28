# Logistics Fleet Management — Auth Service

Ce microservice est la brique dédiée à l'**authentification** et à la **gestion des utilisateurs** pour l'application globale de gestion de flotte logistique. Il expose une API REST permettant l'inscription et la connexion des différents acteurs (Chauffeurs, Administrateurs, etc.).

---

## 🏗️ Architecture Technique

Le projet est développé avec le framework **Spring Boot 3** et s'appuie sur une architecture standard en **3-tiers** (Controller ➔ Service ➔ Repository) pour garantir une séparation stricte des responsabilités.

| Composant | Technologie |
|---|---|
| Framework | Spring Boot 3 / Java 17 |
| Base de données | PostgreSQL 15 (propulsé par Docker) |
| ORM | Spring Data JPA / Hibernate |
| Gestion des dépendances | Maven |

---

## 🚀 Prérequis

Avant de lancer l'application, assure-toi d'avoir installé sur ton poste :

- **Docker Desktop**
- **Java Development Kit (JDK) 17**
- **pgAdmin 4** — pour la visualisation de la base de données
- **Bruno** — pour le test des API

---

## 🛠️ Installation et Démarrage

### 1. Lancement de la base de données (Docker)

Le microservice nécessite une instance PostgreSQL active. À la racine du projet, lance la commande suivante pour démarrer le conteneur en arrière-plan :

```bash
docker-compose up -d
```

> ⚠️ **Note :** Si tu as besoin de réinitialiser complètement la base de données locale (vidage des tables), utilise la commande `docker-compose down -v` puis relance le conteneur.

### 2. Configuration de l'environnement

Le fichier `src/main/resources/application.properties` est déjà configuré pour pointer sur le conteneur local avec les identifiants sécurisés :

- **URL :** `jdbc:postgresql://localhost:5432/logistique_db`
- **Utilisateur :** `admin_user`
- **Mot de passe :** `super_password`

### 3. Démarrage du microservice Spring Boot

Dans ton terminal, à la racine du projet, exécute la commande de build et de lancement :

```bash
./mvnw spring-boot:run
```

L'application est prête dès que la ligne `Started AuthServiceApplication` apparaît. Elle écoute sur le port **8080**.

---

## 🗄️ Structure de la Base de Données

Grâce à la configuration d'Hibernate (`ddl-auto=update`), la table `t_users` est générée automatiquement à partir de l'entité Java. Elle possède la structure suivante :

| Colonne | Type | Description |
|---|---|---|
| `id` | UUID (Clé primaire) | Identifiant unique généré par la base |
| `username` | VARCHAR (Unique) | Nom d'utilisateur unique |
| `email` | VARCHAR | Adresse e-mail de l'utilisateur |
| `password` | VARCHAR | Mot de passe (en clair pour le moment) |
| `role` | VARCHAR | Rôle de l'utilisateur (`ROLE_CHAUFFEUR`, `ROLE_ADMIN`) |

---

## 🛣️ Endpoints de l'API REST

Tous les endpoints possèdent le préfixe `/api/auth`.

### 1. Inscription d'un utilisateur

**URL :** `POST http://localhost:8080/api/auth/register`

Corps de la requête (JSON) :

```json
{
  "username": "chauffeur_jean",
  "password": "mon_mot_de_passe",
  "email": "jean@flotte.com",
  "role": "ROLE_CHAUFFEUR"
}
```

Réponses :

- `200 OK` — Renvoie l'objet utilisateur créé avec son UUID.
- `400 Bad Request` — Si le `username` est déjà utilisé.

---

### 2. Connexion (Login)

**URL :** `POST http://localhost:8080/api/auth/login`

Corps de la requête (JSON) :

```json
{
  "username": "chauffeur_jean",
  "password": "mon_mot_de_passe"
}
```

Réponses :

- `200 OK` — Connexion réussie, renvoie les informations de l'utilisateur.
- `400 Bad Request` — Si l'utilisateur n'existe pas ou si le mot de passe est incorrect.

---

## 🧪 Tests des routes

Les requêtes HTTP peuvent être testées via le client d'API **Bruno** en important la collection créée localement, ou en ligne de commande via l'outil `curl` intégré à Windows CMD.
