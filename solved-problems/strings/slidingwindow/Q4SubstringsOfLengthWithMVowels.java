package dsa.solvedproblems.strings.slidingwindow;

import java.util.Scanner;

//Given a string s, an integer k, and an integer m, count how many substrings of length k contain exactly m vowels.
//
//        Input
//        s = "aabebc"
//k = 3
//m = 2
//
//Output
//2
//
//Explanation
//All windows of length 3:
//
//aab → 2 vowels ✅
//abe → 2 vowels ✅
//beb → 1 vowel
//ebc → 1 vowel
//
//Therefore:
//
//answer = 2
//
//
//Another example
//s = "aeiob"
//k = 3
//m = 3
//
//Windows:
//
//aei → 3 vowels ✅
//eio → 3 vowels ✅
//iob → 2 vowels
//
//Output:
//
//        2
public class Q4SubstringsOfLengthWithMVowels {
    public static boolean isVowel(char c){
        return(c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
    }
    public static int subStringWithMVowels(String s,int k,int m){
        if(k<=0 || k>s.length())return -1;
        int i=0;
        int ans=0;
        int vowCnt=0;
        for(int j=0;j<s.length();j++){
            if(isVowel(Character.toLowerCase(s.charAt(j))))vowCnt++;
            if(j-i+1==k){
                if(vowCnt==m) {
                    ans++;
                }
                if(isVowel(Character.toLowerCase(s.charAt(i))))vowCnt--;
                i++;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int k=sc.nextInt();
        int m=sc.nextInt();
        sc.close();
        int ans=subStringWithMVowels(s,k,m);
        System.out.println(ans);
    }

}
