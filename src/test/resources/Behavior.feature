Feature: Trains In Kiwiland
  I have broken down the output so that i can work on them one at a time, starting with the most challenging.
  
  Background:
  	Given the input for graph of routes between towns is "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7"

  Scenario: 8. The length of the shortest route (in terms of distance to travel) from A to C.
    When the program is executed
    Then the Output No 8 is "9"

