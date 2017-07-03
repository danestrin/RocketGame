# Rocket

A mobile/HTML5 game built using Libgdx created by Dan Estrin

# Acknowledgements

An enormous thank you goes to the Kilbolt Zombie Bird series of tutorials. Although Rocket is unique in its gameplay, the structure of the
project, including its classes (GameWorld, GameRenderer, AssetLoader, etc) and the overall design of the code followed their game almost
identically. Their tutorial series can be found here: http://www.kilobolt.com/zombie-bird-tutorial-flappy-bird-remake.html

# Gameplay

The game is a mix of Asteroids and Snake. The only control is touchscreen - hold the screen to rotate the ship clockwise. The ship burns
fuel constantly and the only way to replenish it is to collect a fuel powerup that appears randomly on the screen. The score is determined
by the amount of time the ship stays flying before it runs out of fuel.

# Platforms

The libgdx project contains modules for Desktop, Android, iOS, and HTML5. The game is currently intended to be shipped for HTML5, and only the Desktop and Android versions have been compiled and tested.

# Development/TODO

- HTML5 project needs to be compiled, tested, and the screen resolution needs to be decided
- iOS project needs to be compiled and tested
- Android app-related stuff needs to be finished (icons, permissions, signing, etc) as well as Google Play Service implementation (only if it's actually gonna be shipped)
- More playtesting!
