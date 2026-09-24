package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

import java.util.Scanner;


//Given an array (or list) of integers, we need to determine the length of the longest contiguous subarray (sequence of consecutive elements) where all the elements are even numbers.

//arr = [2, 4, 6, 3, 8, 10, 12, 14, 1]
//print(max_contiguous_even_sliding_window(arr))  # Output: 4 (the subarray [8, 10, 12, 14])
public class Q4MaximumNumberOfEvenNumbersInAWindowOfSizek {
    public static int maxNoOfEven(int[]a,int n,int k){
        int i=0;
        int evenCnt=0;
        int maxEvenCnt=0;
        for(int j=0;j<n;j++){
            //add new element from right
            if(a[j]%2==0)evenCnt++;
            //window size has reached
            if(j-i+1==k){
                maxEvenCnt=Math.max(maxEvenCnt,evenCnt);
                //remove element (because element left the window)
                if(a[i]%2==0)evenCnt--;
                //increasing initial index to exclude the element from left and move on to next element
                i++;
            }
        }
        return maxEvenCnt;
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
        int ans = maxNoOfEven(a, n,k);
        System.out.print("ans : " + ans);
    }
}
