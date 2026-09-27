package dsa.solvedproblems.strings.frequencymap;

import java.util.Scanner;

public class Q3FirstUniqueCharacterInAString {
    public static int firstUniqChar(String s) {
        int[]hm=new int[26];
        for(int i=0;i<s.length();i++){
            hm[s.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(hm[s.charAt(i)-'a']==1)return i;
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        sc.close();
        int ans=firstUniqChar(s);
        System.out.println(ans);
    }

}
