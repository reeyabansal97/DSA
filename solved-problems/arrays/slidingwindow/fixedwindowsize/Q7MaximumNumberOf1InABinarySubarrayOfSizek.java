package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

//Problem Statement
//Given a binary array containing only 0s and 1s and an integer k, find the maximum number of 1s present in any contiguous subarray (window) of size k.
//
//Use the fixed-size sliding window technique.
//
//Input
//The first line contains an integer n, the size of the array.
//
//The second line contains n space-separated binary integers (0 or 1).
//
//The third line contains an integer k, the window size.
//
//        Output
//Print the maximum number of 1s present in any contiguous subarray of size k.
//
//        Example
//Input:
//
//        8
//        1 0 1 1 0 1 1 0
//        4
//
//Expected Output:
//
//        3

import java.util.Scanner;

public class Q7MaximumNumberOf1InABinarySubarrayOfSizek {
    public static int oneCount(int[]a, int n, int k){
        int i=0;
        int oneCnt=0;
        int maxOneCnt=0;
        for(int j=0;j<n;j++){
            //add new element from right
            if(a[j]==1)oneCnt++;
            //window size has reached
            if(j-i+1==k){
                maxOneCnt=Math.max(oneCnt,maxOneCnt);
                //remove element (because element left the window)
                if(a[i]==1)oneCnt--;
                //increasing initial index to exclude the element from left and move on to next element
                i++;
            }
        }
        return maxOneCnt;
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
        int ans = oneCount(a, n,k);
        System.out.print(ans);

    }
}
