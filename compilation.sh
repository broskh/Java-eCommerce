#!/bin/bash

javac $(find src/ -regex '.*.java')
if [ $? -eq 0 ]
then
	echo "************************"
  	echo "Compilazione completata."
	echo "************************"
else
	echo "*************************"
  	echo "Compilazione non riuscita."
	echo "*************************"
fi