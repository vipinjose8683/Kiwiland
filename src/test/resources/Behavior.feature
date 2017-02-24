@OnlyRun
Feature: Trains In Kiwiland
  I have broken down the output so that i can work on them one at a time, starting with the most challenging.
  
  Background:
  	Given the input for graph of routes between towns is "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7"

  Scenario: 8. The length of the shortest route (in terms of distance to travel) from A to C.
    When the program is executed
    Then the Output No 8 is "9"

  Scenario: 9. The length of the shortest route (in terms of distance to travel) from B to B.
    When the program is executed
    Then the Output No 9 is "9"

  Scenario: 10.The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
    When the program is executed
    Then the Output No 10 is "7"

  Scenario: 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    When the program is executed
    Then the Output No 6 is "2"

  Scenario: 7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    When the program is executed
    Then the Output No 7 is "3"

  Scenario: 1. The distance of the route A-B-C.
    When the program is executed
    Then the Output No 1 is "9"

  Scenario: 5. The distance of the route A-E-D.
    When the program is executed
    Then the Output No 5 is "NO SUCH ROUTE"

  Scenario: 2. The distance of the route A-D.
    When the program is executed
    Then the Output No 2 is "5"

  Scenario: 3. The distance of the route A-D-C.
    When the program is executed
    Then the Output No 3 is "13"

  Scenario: 4. The distance of the route A-E-B-C-D.
    When the program is executed
    Then the Output No 4 is "22"

