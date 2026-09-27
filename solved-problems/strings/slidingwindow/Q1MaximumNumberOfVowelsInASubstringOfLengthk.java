package dsa.solvedproblems.strings.slidingwindow;

//Input : s= abciiidef , k=3
//Output : 3

import java.util.Scanner;

public class Q1MaximumNumberOfVowelsInASubstringOfLengthk {
    public static boolean isVowel(char c){
       return(c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
    }
    public static int maxVow(String s,int k){
        if(k<=0 || k>s.length())return -1;
        int i=0;
        int ans=0;
        int cnt=0;
        for(int j=0;j<s.length();j++){
           if(isVowel(Character.toLowerCase(s.charAt(j))))cnt++;
            if(j-i+1==k){
                ans=Math.max(ans,cnt);
                if(isVowel(Character.toLowerCase(s.charAt(i))))cnt--;
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
        int ans=maxVow(s,k);
        System.out.println(ans);
    }
}
