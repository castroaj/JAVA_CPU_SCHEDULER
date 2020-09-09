#!/bin/bash
javac *.java
mkdir bin
mv *.class bin/
cd bin/
jar cvfe exe.jar Driver *.class
cd ..