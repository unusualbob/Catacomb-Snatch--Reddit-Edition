Catacomb Snatch - Reddit Edition
================================
Project co-ordinator: RJackson

Project moderators (accept/deny pull requests):

* LD48
* Moussekateer
* RJackson

Project contributors:

* RJackson
* LD48
* Mikevin
* Taffaz
* michael
* unusualbob
* bvc
* phalanx
* srjek
* (if you're not listed and you've made a commit to the project, let someone know if you want to be acreddited)

Catacomb Snatch is Mojang's game created in 60-hours as part of the Humble Bundle Mojam. The game was released along with its code at the end of the 60 hours, with no licensing or restrictions.  'Reddit Edition' is the continuation of Catacomb Snatches development based upon the original games source code, adding new features and fixing up errors and bugs within the game, by the community residing at http://reddit.com/r/CatacombSnatch

Getting involved in the project is easy:  Just fork rCatacombSnatch/Catacomb-Snatch--Reddit-Edition, make your changes and submit a pull request with those changes.

The project is currently in its infancy and, as such, very open to discussion regarding its focus - i.e. what our objective is by continuing the games development.

Join the community working on the project in the #rCatacombSnatch IRC channel on the irc.esper.net.

Changes (from Mojangs source)
=============================

* Fixed crash on Harvester death.
* Fixed score being applied to the wrong player.
* Added "debug" hacks.
  * `1` - give yourself 500 moneys
  * `2` - give yourself a turret
  * `3` - give yourself a harvester
  * `4` - give yourself a bomb
  * `5` - spawn a slave 
  * `6` - increase team1 score
  * `7` - increase team2 score 
* Reduced multi-player latency issues.
* Added 3 new levels, and having the played level randomly chosen.
* Better menu visuals
* Fixed turrets shooting treasure.
* Displayed IP address on the waiting screen for multi-player hosts.
* Turret now has line-of-sight support, thereby not being able to shoot at targets if a wall is in between the turret and the target.
* Added a help menu
* Modified buttons - instead of just being a graphic (that contains the related text, they are now just a background graphic with text rendered on top.
* Implemented the ability to pause the game (escape key).
* Removed FPS counter.
* Added button to mute sound - 'M'.
* Up to 4 players when in multiplayer
* Binding Of Isaac style controls.

Ideas
=====
Nothing final, just suggestions of things to do in the future - however ambitious.
### DISCUSSED
#### APPROVED
For approved to-be-implemented ideas (must be detailed, i.e. explain what "upgradable turrets" actually entails)

* Loading maps from file
  * Load `%appdata%/Catacomb Snatch Reddit Edition/levels` contents.
  * Display a map-list when beginning a game (random being an option)

#### REJECTED (& why)
For rejected ideas, and an explanation why.

### UNDISCUSSED

* 'Treasure' being a currency source, collected by rail droids (players can fight over something else)
  * Faster money gen than by killing mobs
  * "Luxuries" such as Turrets, Harvesters and Bombs being more expensive - thus requiring the farming of treasure to use.
  * Could still be the focus of the game if each map has plenty, they're slow to depleet and the winner is whomever has the most resources by the time they all run out.

* Upgradable buildings
  * Turret
    * Increased range/damage

* Game modes
  * Shoot 'em up
	  * Player gains exp per kill which progressively increases a players 'level'.
	  * Mobs become tougher as the players level increases - the players stats do not change.
      * Higher HP?
      * Higher dmg?
      * Increased movespeed?
      * Higher spawn-count?

  * PVP / RTS focus
    * Would require aforementioned treasure farming for currency
    * Mobs could belong to each team, and need to be pushed to do gain and maintain control of resources (like Creeps in Dota 2)

  * Creative / Map editor
    * Tile-based drag/drop map builder
    * Single button to switch between 'edit mode' and 'play mode'
    * Submit levels to a central repository.
    * Load levels from "the cloud" - allow players to rate other players maps.
    * No custom resources, just the tiles within the game - keep the gameplay mechanics consistent.

* Different fog-of-war behaviour:
  * Player and players entites act as a "light source"
  * Map is constantly dark unless a teams "light source" is giving sight to an area.
  * Line-of-sight code could be utilised, not allowing players to see what's on the other side of a wall if they do not have line-of-sight.

* Strings (button string too) coming from a file, instead of hard-coded.
  * Easy to edit
  * Easier to implement and update localisation.