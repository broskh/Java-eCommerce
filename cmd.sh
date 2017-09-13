#!/bin/bash

javac $(find src/ -regex '.*.java')
if [ $? -eq 0 ]
then
	echo "***********************"
  	echo "Compilazione completata."
	echo "***********************"
	echo "Avvio del programma."
	echo "***********************"
	java -cp src Java_eCommerce
fi