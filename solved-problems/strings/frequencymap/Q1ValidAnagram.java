package dsa.solvedproblems.strings.frequencymap;

import java.util.Scanner;

public class Q1ValidAnagram {
    public static boolean isValidAnagram(String s,String t){
        int[]hm=new int[26];
        if(s.length()!=t.length())return false;
        for(int i=0;i<s.length();i++){
            hm[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            hm[t.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            if(hm[i]!=0)return false;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String p=sc.next();
        sc.close();
        boolean ans=isValidAnagram(s,p);
        System.out.println(ans);
    }
}
