package arrays.twopointer;

import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class Q4ValidPalindrome {
    public static boolean validChar(Character c){
        if(c>='a' && c<='z' || c>='A' && c<='Z' || c>='0' && c<='9')return true;
        return false;
    }
    public static boolean isPalindrome(String s){
        int i=0;
        int j=s.length()-1;
        while(i<j){
            if(!validChar(s.charAt(i))) {
                i++;
            }
            else if(!validChar(s.charAt(j))){
                j--;
            }
            else if(validChar(s.charAt(i)) && validChar(s.charAt(j)) && Character.toLowerCase(s.charAt(i))!= Character.toLowerCase(s.charAt(j))){
                return false;
            }else {
                i++;
                j--;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        sc.close();
        boolean ans=isPalindrome(s);
        System.out.println(ans);
    }
}
