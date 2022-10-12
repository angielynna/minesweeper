# Personal minesweeper project
A program that takes in an input file containing a minefield of bombs and safespaces and calculates the adjacency key 
values for this minefield of bombs.

### Minesweeper driver- _main.java_

- WARNING: Project assumes correct input is passed in from the console
- #### Input:
  - A text file with minefields, a text file will auto generate when mineFieldGenerator.java is ran
  - Individual minefield specifics:
    - Bounds must be between 1-100
    - There can be only bombs or no bombs
  - Reads in from the console / System.in

- #### Output:
  - The field number
  - The minefield with the calculated adjacency values
  - Prints above points to the console / System.out



### Minefield Generator- _mineFieldGenerator.java_:

- Randomly generates a random number of minefields with a random bounds (between [1-100]).
- Randomly generates a spawn rate at which bombs are placed and places the bombs accordingly.
- Outputs as the file generated.txt