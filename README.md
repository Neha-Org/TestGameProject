# BestSellerGameApp

## Problem Statement:
### Directory of gamers and their favorite games to auto match for a centralized app of all games

### 1. APIs to enroll gamers and their interest Which takes the general details (name, gender, nickname, geography(europe,asia,usa))
### 2. Lookup up Any 5 games(fortnite, call of duty, dota, valhalla, amongus,…).
### 3. Gamers individual interests with their levels (noob, pro, invincible)
### 4. Search API based on gamers levels, games and geography for auto-matching
### 5. API to give credits to individual users
### 6. API to get the gamer with maximum credits for each game based on their levels.

## URLs
* H2 Console : 
http://localhost:8080/v1/bestseller/h2-console

* Swagger :
http://localhost:8080/v1/bestseller/swagger-ui

* API DOCS JSON :
http://localhost:8080/v1/bestseller/v3/api-docs/

## Entity Relationship Diagram

![ER diagram Test game](https://user-images.githubusercontent.com/15629062/171558203-768e97ba-ba3d-4827-adf0-bde020882642.png)

## How to Use

### Start Application
Run Main Spring Boot Application class **BestsellerTestGameApplication** as Java Application

### CREATE USER
curl --location --request POST 'http://localhost:8080/v1/bestseller/user' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Neha Gawhane",
  "nickname": "dolly",
  "geography": "Europe"
}'

### GET ALL USERS
curl --location --request GET 'http://localhost:8080/v1/bestseller/users'

### CREATE TEST DATA for GAMES
curl --location --request POST 'http://localhost:8080/v1/bestseller/createdummygames' \
--header 'Content-Type: application/json' \
--data-raw '[
  {
    "name": "test"
  }
]'

### GET ALL GAMES
curl --location --request GET 'http://localhost:8080/v1/bestseller/games'

### CREATE USER GAME LEVEL
curl --location --request POST 'http://localhost:8080/v1/bestseller/user/level' \
--header 'Content-Type: application/json' \
--data-raw '{
  "userId": 1,
  "gameId": 1,
  "level": "PRO"
}
'

### CREATE USER GAME CREDIT
curl --location --request POST 'http://localhost:8080/v1/bestseller/user/credit' \
--header 'Content-Type: application/json' \
--data-raw '{
  "userId": 3,
  "gameId": 3,
  "credit": 2500
}'

### SEARCH BY PARAM
curl --location --request GET 'http://localhost:8080/v1/bestseller/search?level=PRO&geography=Europe&gameName=call of duty'

### MAX CREDIT FOR EACH GAME BASED ON LEVELs
curl --location --request GET 'http://localhost:8080/v1/bestseller/user/max_credit'



--------------------------------------------------------------------------------------------------------------------------------------------------------------------

### APIs to enroll gamers and their interest
#### Which takes the general details (name, gender, nickname, geography(europe,asia,usa))
#####  I have created user table and POST API to create users
#### Lookup up Any 5 games(fortnite, call of duty, dota, valhalla, amongus,…).
#####  I have created game table and POST API to create games.( createdummygames API will create some data for test)
#### Gamers individual interests with their levels (noob, pro, invincible)
##### Created user/level API which takes input REQUEST BODY as 
{
  "userId": 2,
  "gameId": 4,
  "level": "INVINCIBLE"
}
	
####	Search API based on gamers levels, games and geography for auto-matching
##### 	game-search API  is created
##### http://localhost:8080/v1/bestseller/search?level=PRO&geography=Europe&gameName=call of duty
####	API to give credits to individual users
#####	Created user/credit API which takes input REQUEST BODY as 
{
  "userId": 2,
  "gameId": 4,
  "credit": 2500
}
####	API to get the gamer with maximum credits for each game based on their levels.
#####	GET 'http://localhost:8080/v1/bestseller/user/max_credit'

