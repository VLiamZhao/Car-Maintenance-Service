# Car Maintenance Project by Can Zhao
##Description: 
A Car Maintenance Service web application implemented by Spring MVC and Web Flow with functionalities including service listing, searching, and ordering. 
Simplified and streamlined database operations with Hibernate. Implemented JWT security workflow based on Stateless authentication mechanisms.



##Assumption: 
1. Users are provided player and team informations based on the authorities.
2. The users information need to be created before searching.
3. The relation between team and player is "One to Many", the relationship between player and player statistics is "One to One".

##Approach:
1. Created User, Team, Player, and Player Statistics object, and created related table and col in the database.
2. The relation between Team and Player is One to Many, the team_id will be the foregin key and will be stored in the player table.
3. The relation between Player and Player Statistics is One to One, the player_id will be the foregin key and will be stored in the player statistics table.

###Compile
1. XXX
2. XXX

###Test
