# The WeebCompiler Ultra Special Edition
A visual compiler made in Java for learning purposes. *Work in progress*
<img width="945" alt="Screenshot 2023-10-11 at 19 59 38" src="https://github.com/pal-ef/weebCompiler/assets/63682116/65624ac8-a036-4351-a2cc-ecfcd4ae51b4">

### What is this?
It's your every day compiler really, with a basic **Lexer** and **Parser**.
You can open up a file with some code (the parser is made so that it identifies C-ish types of files), run the program and see the internal work of a compiler.

It comes with an integrated text editor so you can write and edit your code in here!

### Ok, how do I set this up?
If you are developer simple cloning this project with a `git clone` and using the Gradle Wrapper shipped in should suffice. 

To build this project using Gradle:
```
git clone projectUrl
cd weebCompiler
./gradlew clean build
```
#### Open this project with IntelliJ IDEA
1) Open IntelliJ
2) Select `Open..` and search for the weebCompiler folder
3) Done!


In the case you just want to see it working go to the *Releases* and download an appropiate executable for your operative system.

### How can I contribute?
Do a pull request and I pretty much accept anything that makes the understanding of a compiler easier.
