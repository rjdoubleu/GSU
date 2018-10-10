#!/bin/bash

read -p "Enter the first number: " num1
read -p "Enter the second number: " num2
read -p "Enter the third number: " num3

sum=`expr $num3 + $num2 + $num1`
avg=`expr $sum / 3`
echo "The sum is $sum"
echo "The average is $avg"

if [ $num1 -le $num2 ]
then
  if [ $num1 -le $num3 ]
  then
    echo "The minimum is $num1"
  else
    echo "The minimum is $num3"
  fi
elif [ $num2 -le $num3  ]
then
  echo "The minimum is $num2"
else
  echo "The minimum is $num3"
fi

if [ $num1 -ge $num2 ]
then
  if [ $num1 -ge $num3 ]
  then
    echo "The maximum is $num1"
  else
    echo "The maximum is $num3"
  fi
elif [ $num2 -ge $num3  ]
then
  echo "The maximum is $num2"
else
  echo "The maximum is $num3"
fi