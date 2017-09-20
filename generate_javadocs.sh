#!/bin/bash

javadoc -d doc -link https://docs.oracle.com/javase/8/docs/api/ -sourcepath src grafica negozio utenza src/Java_eCommerce.java
if [ $? -eq 0 ]
then
	echo "*******************************"
  	echo "Javadocs generati con successo."
	echo "*******************************"
else
	echo "***********************************"
  	echo "Javadocs non generati con successo."
	echo "***********************************"
fi