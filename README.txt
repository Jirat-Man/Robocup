This is a demo of the RoboCup Reloaded. In this demo we make the basic GUI and implement the physics of player, ball and pitch.

Instruction:
For run the game, please run "RoboCupApp" class.
To change the line 56 in src/main/java/robocup/Pitch.java, change the ini_ball's position to simulator the different situation.

For run the test, please run “RoboCupTest”class.


Description: (for the detail of this demo)
Our prototype features a start screen where there are 3 buttons, Start, which then takes the user to the team selection screen, Settings, which will take the user to the settings screen, and exit which closes the program.  

The team selection screen is not functional or finished by any mans it is just there as a placeholder for the future when we come round to adding the functionality, right now it is just there so you can then click the start button within that screen and start the simulation. 

For the game screen we have a panel above the pitch image which shows information about the match, with some labels, featuring the score, the teams playing with their colours being represented on the label and the timer label. Also, this panel gives the user some control, through 2 buttons, one that opens the menu and can exit the program, go to main menu etc, and a pause button which currently just pauses the timer label, but will in future pause everything going in the match, so the players and the ball will stop moving, but still remember what they were doing so when the pause button is pressed again, they can resume the action.  

The rest of the screen is taken up by an image of a pitch, which is to scale of a real pitch, players and a ball are drawn on top of this pitch. Currently in our prototype the behaviour of the players is controlled by Boolean variables of whether the player is in possession of the ball or the opponent has possession, based on this information the player then acts accordingly, for example if the player has possession and the other player does not, then the player with the ball will choose to attack whilst the other will defend, again if neither player has possession then they both will go for the ball, and then whoever reaches it first then causes possession to change and so forth the behaviour. 

The physics of the game are calculated accurately and the ball when kicked it moves realistically, gradually slowing down until it stops, the kick method for players requires the force of the kick to be taken as a parameter, this is used in the calculation for the balls motion. 

The ball getting kicked out of bounds is partially implemented, currently we have it so the game can recognise when the ball goes out of bounds and can reposition it to the point where it went out of play for a throw in, or the correct side of the goal for a corner, but the issue is when it goes out of play, we don’t currently have it correctly working where the player would kick/throw it back into play, this will be implemented for the final product. 