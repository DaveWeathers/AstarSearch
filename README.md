# Assignment: Search: Maze

The goal of this assignment is to give you some experience applying the A* search algorithm. It assumes you have already familiarized yourself with Git and Maven in a previous assignment.

## Get a copy of the assignment template

1. Fork the repository https://git.cis.uab.edu/cs-460/search-maze.

2. Give your instructor access to your fork. **I cannot grade your assignment unless you complete this step.**

## Compile and test the code

1.  Compile the code. Run the following command:

        mvn clean compile

    Everything should compile and you should see a message like:

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

2.  Test the code. Run the following command:

        mvn clean test

    The tests should fail, and you should see a message like:

        Failed tests:
           testObstacles(edu.uab.cis.search.maze.SolverTest): ...
           testNoObstacles(edu.uab.cis.search.maze.SolverTest): ...

        Tests run: 5, Failures: 2, Errors: 0, Skipped: 0
        
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD FAILURE
        [INFO] ------------------------------------------------------------------------

    Note the `clean`, which ensures that Maven alone (not your development environment) is compiling your code.

## Implement your part of the code

Your task is to implement the constructor of `edu.uab.cis.search.maze.Solver`, filling in the `explored` and `path` fields as appropriate. You should implement this using A* search with an L1 heuristic. Specifically, squares should be explored with the following strategy:

* Squares are ordered for exploration using the score `f(x) = g(x) + h(x)`, with the smallest `f(x)` squares being explored first
* `g(x)` is the length of the path so far, from the start square to the current square, including the steps necessary to avoid obstacles
* `h(x)` is the L1 estimate of the path to the goal, that is, the Manhattan distance to the goal, ignoring potential obstacles
* Squares with the same `f(x)` score are ordered by the `h(x)` score, with smaller `h(x)` scores first
* Squares with the same `f(x)` and `h(x)` scores are ordered by row, with smaller rows first
* Squares with the same `f(x)`, `h(x)` and row should be ordered by column, with smaller columns first

You may modify only `src/main/java/edu/uab/cis/search/maze/Solver.java` and `src/test/java/edu/uab/cis/search/maze/SolverTest.java`. **Do not modify any other files.** If you need to declare any additional classes, please do so in one of these files.

## Test your code

1.  Re-run the tests:

        mvn clean test

    You should now see a message like:

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

    Your code is now passing the tests that were given to you. This is a good sign, but note that **a successful `mvn test` does not guarantee you full credit on an assignment**. I will run extra tests on your code when grading it.

## Submit your assignment

1.  To submit your assignment, make sure that you have pushed all of your changes to your repository at `git.cis.uab.edu`.

2.  I will inspect the date of your last push to your `git.cis.uab.edu` repository. If it is after the deadline, your submission will be marked as late. So please **do not push changes to `git.cis.uab.edu` after the assignment deadline** unless you intend to submit a late assignment.
