package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

import java.util.Scanner;

public class Q1MaximumSumSubarrayOfSizek {
    public static int maxSum(int[]a,int n,int k){
        int i=0;
        int sum=0;
        int ans=Integer.MIN_VALUE;
        for(int j=0;j<n;j++){
            //add new element from right
            sum+=a[j];
            //window size has reached
            if(j-i+1==k){
                ans=Math.max(sum,ans);
                //remove element (because element left the window)
                sum-=a[j];
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
        int ans = maxSum(a, n,k);
        System.out.print("ans : " + ans);
    }
}
