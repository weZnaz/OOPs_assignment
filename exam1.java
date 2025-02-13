//IT-22023(readd)
import java.io.*;
import java.util.*;

public class exam1 {
  public static void main(String[] args) {
    String input="input.txt";
    String output="output.txt";
    Scanner read=new Scanner(new File(input));
    PrintWriter write=new PrintWriter(new File(output));
  read.useDelimiter(",");
  List<Integer> numbers= new ArrayList<>();

  while(read.hasNextLine()){
    numbers.add(read.nextInt());
  }

  if(numbers.isEmpty()){
    write.println("No number found");
    read.close();
    write.close();
    return;
  }
  int maxnum=Collections.max(numbers);
  write.println("Maximum Number : "+maxnum);
   for(int x:numbers){
    
    int sum=(x*(x+1))/2;
    write.println(sum+" ,");
   }

   write.close();
   read.close();


  }
}
