package dsa.solvedproblems.strings.slidingwindow;

import java.util.Scanner;

public class Q2MaximumNumberOfDigitsInASubstringOfLengthk {
    public static boolean isDigit(char c){
        return(c>='0' && c<='9');
    }
    public static int maxDig(String s,int k){
        if(k<=0 || k>s.length())return -1;
        int i=0;
        int ans=0;
        int cnt=0;
        for(int j=0;j<s.length();j++){
            if(isDigit(Character.toLowerCase(s.charAt(j))))cnt++;
            if(j-i+1==k){
                ans=Math.max(ans,cnt);
                if(isDigit(Character.toLowerCase(s.charAt(i))))cnt--;
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
        int ans=maxDig(s,k);
        System.out.println(ans);
    }
}
