#!/bin/bash

javac $(find src/ -regex '.*.java')
java -cp src Java_eCommerce