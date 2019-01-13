Boggle Solver
=============

This is a standalone webapp for solving [boggle](https://en.wikipedia.org/wiki/Boggle).

Running
-------
Project is written in kotlin and build with gradle. To run:
```
$ ./gradlew run
```

Then visit: http://localhost:8080

Fill in a 16 character board and click "Solve" to see the results.

Configuration
-------------
To try another dictionary, add it as a text file to
`src/main/resources/dictionaries` and modify `boggle.dictionary_name` [application.conf](src/main/resources/application.conf).

To try alternative grid sizes, modify `boggle.board_width` in the same conf file.

Resources
---------
* Borrows CSS from https://www.codementor.io/oyebanjijacob/creating-a-boggle-game-using-react-part-1-bd37sulcs
* Borrows a prefix tree implementation from: https://www.baeldung.com/trie-java
* Webapp is built on the [ktor](https://ktor.io) framework.
* Dictionary is a [SOWPODS](https://en.wikipedia.org/wiki/Collins_Scrabble_Words) dictionary found here: https://osdn.net/projects/sfnet_scrabbledict/downloads/Dictionary%20Files/sowpods.txt.gz/