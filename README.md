# EquationSolver
## Introduction
In my second year of uni, I have completed a course on software verification and validation. In this course, we used software called Dafny which has an in-built theorem prover which is capable of proving properties (or postcondition) in a mathematical context given some premise (or precondition). Seeing this software in action, it made me interested in whether I was able to emulate any of Dafny's behaviour.

In the same year, I also did a course on Object Oriented programming in Java and Software design, which looked at following design principles and implementing well documented design patterns. So throughout this project, I will try to use what I learned to create a loosely-coupled, easy-to-extend system.

## Goals
- Parse a mathematical expression with operations +, -, *, / with brackets ()
- Parse a mathematical expression with additional operations ^ and sqrt
- Parse a mathematical equation with the above operations
- Solve a linear equation with one variable
- Show that given one equation is true, another equation is either true or false