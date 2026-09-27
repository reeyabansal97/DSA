package dsa.solvedproblems.strings.frequencymap;

import java.util.Scanner;

public class Q4CheckIfSentenceIsPangram {
    public static boolean checkIfPangram(String a) {
        if(a.length()<26)return false;
        int[]hm=new int[26];
        for(int i=0;i<a.length();i++){
            hm[a.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(hm[i]<1)return false;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        sc.close();
        boolean ans=checkIfPangram(s);
        System.out.println(ans);
    }
}
