#!/bin/bash
cd src
javac *.java
cd ..
mkdir bin
mv src/*.class bin/
cd bin/
jar cvfe exe.jar Driver *.class
cd ..