all: Driver

Driver: Driver.java
	javac -d . -classpath . Driver.java; mkdir bin; mv *.class bin; cd bin; jar cvfe exe.jar Driver *.class; mv exe.jar ..;

run:
	cd bin; java Driver ../processes.txt;

clean:
	rm -f bin/*.class; rm -f bin/*.jar; rm -fd bin; rm -f *.jar;