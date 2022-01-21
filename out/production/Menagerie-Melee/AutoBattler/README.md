# Menagerie Melee

## Overview

This is a turn-based Autobattler game based on the premise of Hearthstone Battlegrounds. It was created in Intellij, using Swing GUI and Object-Oriented-Programming, and I created the images using Procreate. This was part of a way to apply my learning about graphics in Java. The game itself supports 2 players and consists of a recruit and battle phase (see below). Each player has a warband consisting of 5 fighters that will fight their opponent in an RNG-based battle.

## Recruit

For each of the 5 fighters in each player's warband, they will be given a choice of 3 randomly-generated fighters (if they are really unlucky, they may be offered 3 of the same fighter) from a set pool, each with a different picture and combination of attack and health. By clicking on the corresponding button, a Fighter object will be created and added to the corresponding warband. 

## Battle

Starting with Player 1's 1st fighter and followed by Player 2's 1st, Player 1's 2nd, etc., each fighter will randomly attack a fighter on the opponent's warband. The attack order will continue as such (unless the attacker is dead at which point the next fighter on the same player's warband will attack) until 1 or both players' warband is completely dead. At this point, the game will end and a winner will be declared.

### Note

This is still a work in progress and will continue to be updated to improve the appearance of the GUI, add various features to the game, and add new fighters/characters. 
