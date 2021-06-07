# Adding potions to the Autobattler Game

## Overview

This branch adds onto the existing Autobattler game to add a new game mechanic: potions. After each player recruits their warband, they will be offered a choice of 3 randomly generated potions (once again, very unlucky players may be offered 3 of a kinds). They will choose one which will then add a modifier to 1 or more fighters in their warband for the next battle. To see what each potion does, see below. 

This will be worked on more as the graphics of the main branch are improved. 


## Types of Potions

Names are subject to change (because they are quite bad), as are effects for balancing (unfortunately this also means I may have to change visuals)

"Enraged" potion - give each of your fighters +2 attack
"Eat your veggies" potion - give each of your fighters +2 health
"Extra training" potion - give each of your fighters +1/+1
"Defence mechanism" potion - give a random fighter shield
"Beast mode" potion - give a random fighter +10 attack
"Health drop" potion - give 2 random fighters +4 health
"2nd chance" potion - give a random fighter resurrect


### New Fighter Mechanics

The potions also introduce 2 new mechanics to the fighters. 

Shield - ignore the first hit this fighter takes (either attacking or being attacked)
Resurrect - the first time this fighter dies, come back to life with 1/2 health (rounded down)
