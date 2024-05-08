# Wordle Clone in Java
- Go check out the real Wordle [here](https://www.nytimes.com/games/wordle/index.html)!

## Overview
*Inspiration*: This game is an open source clone of the immensely popular online word guessing game Wordle made by the New York Times. 
Like many others all over the world, I saw the signature pattern of green, yellow, and white squares popping up all over different social media platforms and had to check it
out for myself. After playing it pretty much daily for a month, I had decided it would make for a great final project for my java-based object oriented programming class about a year ago.

*Design*: As clearly shown through the game itself, Wordle is composed mainly on the front end, with little focus on the backend aspect of programming. Accordingly, programming this solely in Java was a bit harder than originally anticipated.
To try and replicate Wordle as best as possible, the UI was programmed mostly using the native Java swing library. Additionally, the word list used in this program was simply taken from the New York Times site! Which was surprisingly stored in an array on their
frontend which I found through Chrome Dev Tools. Initially, I designed the program to search a URL containing a list of words, where it would then generate a random number corresponding to the total number of words available. 
Subsequently, it would iterate through each line until reaching the randomly generated number, thus selecting a word at random. However, this approach occasionally resulted in performance issues (i.e causing the program to take ~10 seconds to generate a new word);
on the plus side, now this program can be run without internet connection as it scans through a local file :).

Here's an image of how the game looks full screen: ![Screenshot 2024-05-07 115559](https://github.com/yosh1maa/Wordle/assets/140118755/3a923966-e8e9-4b84-ac62-fe01b2301a93)


## Features

- Among other things, the algorithm to determine the color of a letter in a guess was also slightly difficult. Most basic programs on Wordle don't account for letters that appear twice in a guess but only once in the answer, highlighting them both yellow if placed in the 
wrong spot (when only one should be). The last method in the [Word class](https://github.com/yadelg/Wordle/blob/master/src/finalProject/Word.java) incorporates an algorithm similar to the NYT, accurately accounting for letters appearing multiple times in the hidden word, keeping track of each letter occurence utilizing a hashmap. 

- The game offers unlimited playing and keeps track of statistics as long as the program is open. Each time you either win or lose the game, the statistics page pops up showing: games played, win percentage, and all the other information the NYT shows.
 Additionally, the leaderboard icon at the top right allows for anyone to click on it and view the statistics page even while playing.

Here's an image depicting the statistics page after a game was won in one guess:

 ![statistics page](https://github.com/yosh1maa/Wordle/assets/140118755/cb70b2eb-08ea-4382-89a0-6b0f2a2445c3)

## Suggestions
- Open to any suggestions through pull requests! Especially anything regarding the layout as it was not the main aim of the project.




