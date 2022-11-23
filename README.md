AUTHORS AND ACKNOWLEDGEMENT  
This program reproduces a simplified version of the board game ScrabbleModel.
This program was made for the SYSC 3110 team project, by group 8, which consists of Khalid Merai, Scharara Islam, Marina Latif and Saad Eid.

RULES OF SCRABBLE 

ScrabbleModel is a word game in which two to four players score points by placing tiles, each bearing a single letter, onto a game board divided into a 15×15 grid of squares.

The game is played by two to four players on a square game board imprinted with a 15×15 grid of cells (individually known as "squares"), each of which accommodates a single letter tile.
In an English-language set, the game contains 100 tiles, 98 of which are marked with a letter and a point value ranging from 1 to 10. The number of points for each lettered tile is based on the letter's frequency in standard English. Commonly used letters such as vowels are worth one point, while less common letters score higher, with Q and Z each worth 10 points. The game also has two blank tiles that are unmarked and carry no point value. The blank tiles can be used as substitutes for any letter; once laid on the board, however, the choice is fixed. Other language sets use different letter set distributions with different point values.

The letter values of the tiles are added up, and tiles placed on Double Letter Score (DLS) and Triple Letter Score (TLS) squares are doubled or tripled in value, respectively. Tiles placed on Double Word Score (DWS) or Triple Word Score (TWS) squares double or triple the value of the word(s) that include those tiles, respectively. In particular, the center square (H8) is considered a DWS, and the first play is doubled in value.

 
For more information on the scrabble game it can be found in the link below.

https://en.wikipedia.org/wiki/ScrabbleModel

HOW TO PLAY SCRABBLE
----------------------------------
1. Run the Class ScrabbleFrame 
2. Enter the name of Player 1 and 2
3. Create a word from the given random letters from the rack, then press the submit button to check the validity of the inputted word.
4. If the word is invalid a pop up message will appear saying "Invalid Move. Try again"
5. If the word is valid, the score is calculated from the set of letters used within the frame.
6. Then player 2 plays
7. Steps 3-5 will repeat for player 2
8. then player 1 again 
9. After filling all possible tiles the frame will calculate the highest scoring player and that player wins the game.

JBUTTONS
-----------------------
1. PASS: If a player fails to construct a valid word, the player will press pass to end his turn and the next player gets to play.
2. UNDO: Removes all letters from the frame that are inputted from the players
3. Swap Tiles: Each player gets to invoke the swap tiles button during the game if the player fails to make any valid word. But when invoking the swap tile method the players turn ends.
4. Tiles left : Informs the player on how many tiles is left in the game.
5. Submit: This button makes sure that the inputted word is valid, if so, generates the players new score then the next player plays.

BREAK DOWN OF TASKS FOR MILESTONE 1
---------------------------------------

Player.java and UML : Marina

Dictionary.java, bag.java, ScrabbleModel.java : Scharara

Tile.java, bag.java, ScrabbleModel.java, and readme file. Assisted in Dictionary.java : Khalid (Macbook)

DoubleLetterSquare, DoubleWordSquare, trippleLetterSquare, TripleWordSquare, startingSquare, Square.java, ScrabbleModel.java and gameBoard.java. Also assisted in bag.java and tile.java : Saad

BREAK DOWN OF TASKS FOR MILESTONE 2
-----------------------------------------

ScrabbleController.java, ScrabbleModel.java ,Player.java and UML and readMe file: Marina

Dictionary.java, bag.java, ScrabbleModel.java and ScrabbleFrame.java: Scharara

Tile.java, bag.java, UML,  ScrabbleModel.java, and readme file. Assisted in Dictionary.java and ScrabbleFrame.java: Khalid (Macbook)

DoubleLetterSquare, DoubleWordSquare, trippleLetterSquare, TripleWordSquare, startingSquare, Square.java, ScrabbleModel.java and gameBoard.java and TokenScanner.java, ScrabbleEvent, ScrabbleView, ScrabbleFrame and ScrabbleController. Also assisted in bag.java and tile.java : Saad

Whats missing from Milestone 2 submission: The unit testing for the methods.

BREAK DOWN OF TASKS FOR MILESTONE 3
-----------------------------------------
ScrabbleController.java, AI.java, ScrabbleModel.java ,Player.java and UML and readMe file: Marina
Dictionary.java, bag.java, ScrabbleModel.java and ScrabbleFrame.java, AI.java: Scharara
Tile.java, bag.java, UML,  ScrabbleModel.java, and readme file. Assisted in Dictionary.java and ScrabbleFrame.java, AI.java, FreePlay.java: Khalid (Macbook)
DoubleLetterSquare, DoubleWordSquare, trippleLetterSquare, TripleWordSquare, startingSquare, Square.java, ScrabbleModel.java and gameBoard.java and TokenScanner.java, ScrabbleEvent, ScrabbleView, ScrabbleFrame and ScrabbleController. Also assisted in bag.java and tile.java, AI.java and updated the board with the premium tiles in the frame of the game : Saad

HOW TO RUN THE PROGRAM FOR SUBMISSION 1:
1. Download a junit.framework package to ensure all test methods run and the whole code implementation runs. Can be downloaded from this link https://search.maven.org/search?q=g:junit%20AND%20a:junit
2. If user does not have a junit tester and does not want to dowloand it just remove or bypass the SquareTest.java and TileTest.java it by putting it in /** */
3. Dowloand the whole src and open it on intelij then configure the project then run the program by running class scrabble.java.
