MiniBank Java project (extracted from Minibank.docx)

Requirements:
- Install a JDK (Java 11+ recommended because the code uses String.isBlank()).
- Open this MiniBank folder in VS Code.
- In the terminal, from this folder, compile:
  javac -d out src/MiniBank.java src/model/annotation/Id.java src/model/Account.java src/repository/Repository.java src/exception/*.java src/service/*.java src/util/*.java
- Run:
  java -cp out MiniBank

Use menu option 9 to save account data before exiting. The program creates accounts.dat and transactions.txt in the current working directory.

Note:
- JUnit test files from the document are not included in this basic runnable project because JUnit is an external dependency.
- PartATest.java is for a separate partA Item/ItemService practice exercise, not this MiniBank app.
