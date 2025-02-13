import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class exam1 {

  public static void main(String[] args) {
int start,end;
Scanner sc=new Scanner(System.in);
start=sc.nextInt();
end=sc.nextInt();

   for(int j=start;j<=end;j++){
    int num=j;
    int numo=num;
    List<Integer> arr=new ArrayList<>();
    while(num!=0){
      int r=num%10;
      num=num/10;
      arr.add(r);

    }
    int sum=0;
    for(int x:arr){
      int mul=1;
      for(int i=1;i<=x;i++){
        mul=mul*i;
      }
      sum=sum+mul;
    }

    if(sum==numo){
      System.out.print(numo+" ,");
    }
  }
}
}

