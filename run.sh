#!/bin/bash
#
echo "Compiling the project"
javac src/CountPrescriptions.java src/Prescription/Prescription.java src/Utility/Util.java src/PharmacyCounter/RecordKeeper.java src/Records/*.java

echo "Input File: $1"
echo "Output File Name: $2"

echo "Now running the project"
java -cp src/ CountPrescriptions $1 $2
