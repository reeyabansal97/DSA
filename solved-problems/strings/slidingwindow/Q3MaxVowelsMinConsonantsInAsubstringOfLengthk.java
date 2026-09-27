package dsa.solvedproblems.strings.slidingwindow;

import java.util.Scanner;

//Given a string s containing lowercase English letters and an integer k, find the maximum number of vowels in any substring of length k, subject to the condition that the substring contains at least 1 consonant.
//
//        Input
//        s = "aeibcdou"
//k = 4
//
//Output
//3
//
//Explanation
//Windows:
//
//aeib → 3 vowels, 1 consonant ✅
//eibc → 2 vowels, 2 consonants
//ibcd → 1 vowel, 3 consonants
//bcdo → 1 vowel, 3 consonants
//cdou → 2 vowels, 2 consonants
//
//So:
//
//answer = 3

public class Q3MaxVowelsMinConsonantsInAsubstringOfLengthk {
    public static boolean isVowel(char c){
        return(c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
    }
    public static int maxVowelsWithConsonant(String s,int k){
        if(k<=0 || k>s.length())return -1;
        int i=0;
        int ans=0;
        int vowCnt=0;
        for(int j=0;j<s.length();j++){
            if(isVowel(Character.toLowerCase(s.charAt(j))))vowCnt++;
            if(j-i+1==k){
                if(k-vowCnt!=0) {
                    ans = Math.max(ans, vowCnt);
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
        sc.close();
        int ans=maxVowelsWithConsonant(s,k);
        System.out.println(ans);
    }
}
