#include <stdio.h>
#include <math.h>

double calculateRate(double, double);
double calculateAmountPerPeriod(double, double, double);
void printForm(double, double, double, double);

struct Payment{
    double BALANCE;
    double PRINCIPAL;
    double INTEREST;
};


int main(){
  double balance = 0.0;
  double interestRate = 0.0;
  double numberOfPayments = 0.0;

  printf("Enter amount of loan : $");
  scanf("%lf", &balance);

  printf("\nEnter interest rate per year : %% ");
  scanf("%lf", &interestRate);
  int in = (int) (balance * (1.0 + (interestRate * 0.01)) * 100.0);
  double out = (double) (in / 100.0);
  double loanAmount = out;
  interestRate = (interestRate / 12.0 * 0.01);

  printf("\nEnter the number of payments : ");
  scanf("%lf", &numberOfPayments);

  double amountPerPeriod = calculateAmountPerPeriod(balance, interestRate, numberOfPayments);
  printForm(balance, interestRate, amountPerPeriod, numberOfPayments);
  return 0;
}

double calculateAmountPerPeriod(double l, double ir, double nop){
  double numerator = pow((1.0+ir), nop) * ir;
  double denominator = pow((1.0+ir), nop) - 1;
  int amtPP = (int) ((l * ( numerator / denominator )) * 100.0);
  return (double) (amtPP / 100.0);
  }

void printForm(double bal, double r, double amtPP, double nop){
  int inop = (int) nop;
  struct Payment Amortization[inop];

  printf("\nMonthly Payment  = $%0.2f\n\nPayment    Amount   Principal   Interest    Balance\n", amtPP);
  double loanAmount = bal * (1.0 + (r * 0.01));
  for(int i=0;i<inop;i++){
    Amortization[i].INTEREST=bal*r;
    Amortization[i].PRINCIPAL=amtPP-Amortization[i].INTEREST;
    bal -= Amortization[i].PRINCIPAL;
    Amortization[i].BALANCE=bal-0.01;
  }

  for(int j=0;j<inop;j++){
    int payment=j+1;
    printf("%d.         %0.2f    %0.2f        %0.2f       %0.2f\n", payment, amtPP, Amortization[j].PRINCIPAL, Amortization[j].INTEREST, Amortization[j].BALANCE);
  }
}