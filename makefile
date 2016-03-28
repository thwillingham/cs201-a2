top :
	javac -d classfiles -sourcepath src src/main/Trees.java

test :
	@echo testing BST
	java -classpath classfiles main.Trees -1 ./testFiles/cor1 ./testFiles/com1
	@echo testing RBT
	java -classpath classfiles main.Trees -2 ./testFiles/cor1 ./testFiles/com1

clean : 
	@echo cleaning...
	rm -rfv ./classfiles/*
