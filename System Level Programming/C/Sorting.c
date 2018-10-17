#include <stdio.h>
int N;

int main(){
  printf("Enter a positive integer value for N: ");
  scanf("%d", &N);
  if(N <=0){
    printf("\nYou did not enter a positive integer value for N.");
  }else{
    printf("Enter %d  integer elements below for sorting.\n", N);
    int arr[N];
    for(int i=0; i<N; i++){
      scanf("%d", &arr[i]);
    }
    printf("Now Sorting...\n");
    for(int i=0; i<N; i++){
      for(int j=1; j<N-1; j++){
        if(arr[i] > arr[i+j]){
          int temp = arr[i];
          arr[i] = arr[i+j];
          int k=i+1;
          while(k<=i+j){
            int tempo = arr[k];
            arr[k] = temp;
            temp = tempo;
            k++;
          }
        }
      }
   }

   for(int z=0;z<N;z++){
     printf(" %d ", arr[z]);
   }
 }
}