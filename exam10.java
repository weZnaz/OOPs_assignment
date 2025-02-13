import java.util.Scanner;

public class exam1 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int length=s.length();
        if(length%2==0){
            int a=0,b=length-1;
            while(a<b){
                if(s.charAt(a)!=s.charAt(b)){
                    System.out.println("Not a palindrom");
                    return;
                }a++;
                b--;
            }
        }
        else{
            int a=0,b=length-1;
            while(a<=b){
                if(s.charAt(a)!=s.charAt(b)){
                    System.out.println("Not a palindrom");
                    return;
                }a++;
                b--;
            }
        }


        System.out.println("Its a pallindrom");
    }
}
