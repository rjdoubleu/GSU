#include <stdio.h>
int N;
int fib = 1;

int main(){
  printf("Please enter a positive integer value for N: ");
  scanf("%d", &N);
  if(N <= 0){
    printf("\nYou did not enter a positive integer value for N.\n\n");
  }else if(N < 3){
    if(N == 1)
      printf("%d", fib);
    else
      printf("%d,%d", fib, fib);
  }else{
    int arr[N];
    int j = 2;
    arr[0] = 1;
    arr[1] = 1;
    printf("%d,%d,", fib, fib);
    while(j < N){
      fib = arr[j-2] + arr[j-1];
      arr[j] = fib;
      printf("%d,", fib);
      j++;
    }
    printf("\n");
  }
}