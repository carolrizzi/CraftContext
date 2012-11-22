CraftContext
============
###(Version 12.11)

Downloading and Running
-----------------------

The complete package of CraftContext v12.11 can be download in [here](https://github.com/downloads/carolrizzi/CraftContext/CraftContext.12.11.zip). This zip file contains all the needed executable and libraries for running both server and client of CraftContext. See the README file inside the package for detailed running instructions.

Projects
--------

* **CraftContextServer**

This project contains the CraftContext server implementation. There are two fundamental libraries to this project:

- [CraftBukkit v1.1-R4](http://dl.bukkit.org/downloads/craftbukkit/get/00720_1.1-R4/craftbukkit.jar): Bukkit is a modified server of Minecraft, which allows creating plugins for extending the game. CraftContext is currently a extension for Bukkit server. All actions in CraftContext related to Minecraft are made by means of this library.

- [JacORB v2.3.1](http://www.jacorb.org/releases/2.3.1/jacorb-2.3.1-src.zip): JacORB is a Java implementation of CORBA (Common Object Request Broker Architecture), which is a standard defined by OMG ([Object Management Group](http://www.omg.org/)) for communication among heterogeneous distributed systems. All remote connections and communication issues are handled by means of JacORB.

CraftContext is a free and open-source tool and we will be very grateful if you help us to improve it. Unfortunately, we still don't have a tutorial for building new modules of events and services for CraftContext, but we intend to provide it soon.

* **CraftContextClient**

This is the implementation of a helper for developing a client application for CraftContext. The JAR file generated from this project provides some JacORB abstractions, helping to hide from developers issues not related to their main goal, which is to test a context-aware application. This helper, however, was built only for client applications written in Java. In order to run a CraftContext client in other language than Java, you should install and use the idl compiler of the desired language and implement a client package similar to the one provided here. We intend to provide such helper for further programming languages in the next versions of CraftContext.

* **CraftContextTest**

This is a example of client application implementation. This project does not include features of context-awareness, instead it is objectively focused on testing all the functionalities of CraftContext. This application shows a menu, by typing "help", which lists all the possible actions and commands that users can test. Remember that your actions/commands maybe will not take effect if there are no players logged in the server.

Important Observations
----------------------

CraftContext has presented some unexpected errors when run by Java 7. We strongly recommend to use version 6 of Java, in order to reach an better performance and avoid errors.


Minecraft Installation Instructions
-----------------------------------

1. Installing the game

1.1. If you don't have a Minecraft account, you can buy it in the official Minecraft site: http://minecraft.net/
1.2. Download and run the game (double click on minecraft.jar).
1.3. Sign in the game and wait the downloads to finish.
1.4. Close the game.

2. Setting the correct version of Minecraft for run it with CraftContext.

2.1. Download the minecraft.jar file [here](https://github.com/downloads/carolrizzi/CraftContext/minecraft.jar)
2.2. Open the directory ".minecraft", which is inside your home folder. It may be hidden, case in which you have to change your directory visualization options for showing the hidden files.
2.3. Open the bin directory and replace the minecraft.jar file by the one you have downloaded in step 2.1.
2.4. Run the game in the same way you made on the step 1.3. If the game ask to be upgraded, click on "Not Now".
2.5. Run the CraftContext server, following the instructions on the previous section.
2.6. In the game screen, click on the button "Multiplayer" and then click in "Add server".
2.7. Provide a name and the ip address to the server where CraftContext is running. Click on "Done".
2.8. Select the server you have added on previous step and click in "Join Server".
2.9. You are now logged in the CraftContext server.

Version 12.11 - Release Notes
-----------------------------
                            
* The current version of CraftContext is capable of detecting the following events:

	- Player join: occurs when a player enters the game.
	- Player respawn: occurs when a player dies and reappear again in its spawn point.
	- Player logout: occurs when the player leaves the game.
	- Player kick: occurs when the player is kicked from the game.
	- Player death: occurs when a player dies.
	- Player move:occurs when a player moves.
	- Player chat: occurs when a player sends a message in the game.
	- Player regain health: occurs when a player regains health for some reason.
	- Player damage: occurs when a player suffers a damage for some reason.
	- Player starvation Level Change: occurs when the starvation level of a player changes, i.e., the player becomes more hungry or more fed.
	- Building presence: occurs when a player enters an existing building in the game.
	
	
* The current version of CraftContext allows the player to type commands, in order to consult and/or change things on the Minecraft world. However, in the current version, players' commands are not run by the server. Instead, the command request is sent to the client application, which is in charge of the command execution. It means that players' commands will not be performed if there is no client application to handle it, regardless of whether the server is up and running. CraftContext's server provides the required methods to implement all existing commands, so the client application can easily implement them. We intend, for future versions of CraftContext, to provide the choice of executing players' commands directly in the server.
In order to type a command, the player, when already logged in the game, have to type "t" and then "/<command-type> <command> <parameters>".

For example:

`/player set_health 20`

The first portion of the command ("player") means that this command has to do with changing and/or consulting players' stuff. The second part ("set_health") is the command itself, which is requesting to change the health level of the player who is typing the command. The last part is the set of parameters, which in case has only one parameter (the number 20), i.e., the new health level of the player.

CraftContext's current version allows players to perform the following commands:

- Set player's food level: When the player wants to change his own level of starvation. Command format:
	`/player set_food <level_number>`
	The level_number parameter defines the new starvation level of the player. This parameter should be between 0 (starving to the point of taking damage) and 20 (fully fed).

- Set player's health: When the player wants to change his own level of health. Command format:
	`/player set_health <level_number>`
	The level_number parameter defines the new health level of the player. This parameter should be between 0 (dead) and 20 (completely healed and healthy).
	
- Add new building: When the player wants to register a new building and define its physical limits. Command Format:
	`/building add "<name>" "<type>" "<address>" <x-width> <z-width> <height>`
	Where:
	- name: The name of the building that will be created.
	- type: The type of the building that will be created. It is a kind of label, such as "restaurant", "hospital", "house", etc.
	- address: The address of the building that will be created. There are no limitations related to the veracity of the provided address in the real world, since no validation is made on it.
	- x-width: The building's width in the x axis.
	- z-width: The building's width in the z axis.
	- height: The building's height, i.e., the building's length in the y axis.
	
	Obs.: Both name, type and address of the building must be written in quotes and accepts spaces and special characters, such as punctuation marks and accents.
	Obs.2: Both x-width and z-width has as center the player's position at the moment that he or she typed the add command. The building's height has as start point the position of the player's feet at the moment that he or she typed the add command. For example, if the player is standing on a block in which the y coordinate is 10 (take this position as being the block below the player's feet) and he or she added a building with height equals to 5, then the y coordinate of the floor will be 10 and the y coordinate of the roof will be 15.
	
- List existing buildings: When the player wants to know what are the existing buildings. Command format:
	`/building list`
	No parameter is required.
	
- Remove existing building: When the player want to delete a existing building from the game. Command format:
	`/building remove "<name>" <physical-destruction>`
	Where:
	- name: The name of the building that will be removed. Must be written in quotes.
	- physical-destruction: A boolean parameter for defining whether the building's deletion includes its physical destruction, i.e., whether the all the blocks in the building's area will be removed. Type "true" for physical destruction and "false" for removal only in registration level.
                            


