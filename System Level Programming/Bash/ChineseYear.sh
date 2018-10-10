#!/bin/bash
echo "Enter a year greater than 1948: "
read y
year=$y

if [ "$year" -lt 1948 ]
then
  echo "Please enter a year after 1948."
else
  range=`expr $year - 1948`
  rem=`expr $range % 12`
  echo -n "${year} is the year of "
  if [ "$rem" -eq 0 ]
  then
    echo "Rat"
  elif [ "$rem" -eq 1 ]
  then
    echo " Ox"
  elif [ "$rem" -eq 2 ]
  then
    echo " Tiger"
  elif [ "$rem" -eq 3 ]
  then
    echo " Rabbit"
  elif [ "$rem" -eq 4 ]
  then
    echo " Dragon"
  elif [ "$rem" -eq 5 ]
  then
    echo " Snake"
  elif [ "$rem" -eq 6 ]
  then
    echo " Horse"
  elif [ "$rem" -eq 7 ]
  then
    echo " Goat"
  elif [ "$rem" -eq 8 ]
  then
    echo " Monkey"
  elif [ "$rem" -eq 9 ]
  then
    echo " Rooster"
  elif [ "$rem" -eq 10 ]
  then
    echo " Dog"
  elif [ "$rem" -eq 11 ]
  then
    echo " Pig"
  fi
fi