package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q6CountOfNegativeNumbersInEveryWindowOfSizek {
    public static List<Integer> negativeCount(int[]a,int n,int k){
        int i=0;
        int negCnt=0;
       List<Integer>ans=new ArrayList<>();
        for(int j=0;j<n;j++){
            //add new element from right
            if(a[j]<0)negCnt++;
            //window size has reached
            if(j-i+1==k){
               ans.add(negCnt);
                //remove element (because element left the window)
                if(a[i]<0)negCnt--;
                //increasing initial index to exclude the element from left and move on to next element
                i++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int k=sc.nextInt();
        sc.close();
        List<Integer>ans = negativeCount(a, n,k);
        for(int count: ans){
            System.out.print(count+" ");
        }

    }
}
