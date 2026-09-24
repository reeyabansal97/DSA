package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;

import java.util.Scanner;
//Input:
//
//        9
//        2 4 6 3 8 10 12 14 1
//        4

//ans : 1
public class Q5MaximumNumberOFOddNumbersInAWindowOfSizek {
    public static int maxNoOfOdd(int[]a,int n,int k){
        int i=0;
        int oddCnt=0;
        int maxOddCnt=0;
        for(int j=0;j<n;j++){
            //add new element from right
            if(a[j]%2!=0)oddCnt++;
            //window size has reached
            if(j-i+1==k){
                maxOddCnt=Math.max(maxOddCnt,oddCnt);
                //remove element (because element left the window)
                if(a[i]%2!=0)oddCnt--;
                //increasing initial index to exclude the element from left and move on to next element
                i++;
            }
        }
        return maxOddCnt;
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
        int ans = maxNoOfOdd(a, n,k);
        System.out.print("ans : " + ans);
    }
}
