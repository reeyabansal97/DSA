package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

//Given an array arr[] and a positive integer k, find the subarray of length k having the maximum average value.
//
//Return the starting index of that subarray.
//
//If multiple subarrays have the same maximum average, return the smallest starting index.
//
//Examples:
//
//Input: k = 4, arr[] = [1, 12, -5, -6, 50, 3]
//Output: 1
//Explanation: Maximum average is (12 - 5 - 6 + 50)/4 = 51/4. Therefore answer for this test case is 1.

import java.util.Scanner;

public class Q3MaxAverageSubarray {
    public static int maxAvg(int[]a,int n,int k){
        int i=0;
        int sum=0;
        int maxSum=Integer.MIN_VALUE;
        int idx=0;
        for(int j=0;j<n;j++){
            //add new element from right
            sum+=a[j];
            //window size has reached
            if(j-i+1==k){
                if(sum>maxSum){
                    maxSum=sum;
                    idx=i;
                }
                //remove element (because element left the window)
                sum-=a[j];
                //increasing initial index to exclude the element from left and move on to next element
                i++;
            }
        }
        return idx;
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
        int ans = maxAvg(a, n,k);
        System.out.print("ans : " + ans);
    }
}
