# Car Maintenance Project by Can Zhao
## Description: 
A Car Maintenance Service web application implemented by Spring MVC and Web Flow with functionalities including service listing, searching, and ordering. 
Simplified and streamlined database operations with Hibernate. Implemented JWT Stateless authentication security workflow.

## Assumption: 
1. Users are provided personal informations and car maintenance service information based on the authorities.
2. The information is created before searching.
3. The relation between user and their authorities is "Many to Many".

## Approach:
1. Created User, Car, Maintenance Service objects, and created relational tables and columns in the database.
2. The relation between User and Car is "One to Many".
3. The relation between Player and Player Statistics is One to One, the player_id will be the foregin key and will be stored in the player statistics table.
## Building Project
### Compile
1. ---------------------
2. ---------------------

### Test
