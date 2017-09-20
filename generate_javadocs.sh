#!/bin/bash

javadoc -d doc $(find src/ -regex '.*.java')
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