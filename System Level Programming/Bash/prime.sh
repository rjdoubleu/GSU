#!/bin/bash
read -p "Enter the value of n: " num

count=0
c=2

while [ $count -ne $num ]
do
  flag=1
  i=2
  a=`expr $c % $i`

  if [ $a -eq 0 ]
  then
    flag=0
  fi
  i=`expr $i + 1`

  if [ $flag -ne 0 ]
  then
    echo -n "$c "
    count=$[$count+1]
  fi
  c=`expr $c + 1`
done
echo ""