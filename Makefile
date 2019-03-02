run:
	@test -s target/tictactoe/TicTacToe.class || { echo "Please compile with 'make compile' first"; exit 1; }
	java -cp target: tictactoe.TicTacToe

compile: clean
	javac -d target src/termcolor/*.java
	javac -d target -cp target src/tictactoe/*.java

.PHONY: clean
clean:
	rm -rf target/*
