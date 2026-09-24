package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Problem Statement
//Given an array of integers and an integer k, find the average of every contiguous subarray of size k.
//
//Use the fixed-size sliding window technique.
//
//For each window of size k, calculate the sum of its elements and divide the sum by k.
//
//        Input
//The first line contains an integer n, representing the size of the array.
//
//The second line contains n space-separated integers.
//
//The third line contains an integer k, representing the window size.
//
//Output
//Print the average of every contiguous subarray of size k.
//
//The output should contain n - k + 1 averages.
//
//        Example
//Input:
//
//        8
//        1 3 2 6 4 8 5 7
//        3
//
//Expected Output:
//
//        2.00 3.67 4.00 6.00 5.67 6.67
public class Q8AverageOfEverySubarrayOfSizek {
    public static List<Double> avg(int[]a, int n, int k){
        int i=0;
        int sum=0;
        List<Double>ans=new ArrayList<>();
        for(int j=0;j<n;j++){
            //add new element from right
            sum+=a[j];
            //window size has reached
            if(j-i+1==k){
               ans.add((double)sum/k);
                //remove element (because element left the window)
                sum-=a[i];
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
        List<Double>ans = avg(a, n,k);
        for(double count: ans){
            System.out.printf("%.2f ", count);
        }

    }
}
