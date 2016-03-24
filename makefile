top :
	javac -d classfiles -sourcepath src src/main/Trees.java

test :
	@echo testing RBT
	java -classpath classfiles main.Trees -2 ./testFiles/shakespeare.txt ./testFiles/com
