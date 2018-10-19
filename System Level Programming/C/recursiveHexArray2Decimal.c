//Recursive Hexadecimal to Decimal Converter

#include <stdio.h>
#include <stdlib.h>

int arrSize = 5;
const char *arr[5] = {"0x00", "0x11", "0x1F", "0x33", "0x34"};
int ind = 0;

int main() {
   long n = strtol(arr[ind], NULL, 16);
   printf("%ld ", n);

   if(ind < arrSize -1){
       ind += 1;
       main();
   }else
       printf("\n");
}