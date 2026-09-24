package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize;
import java.util.Scanner;

//Problem statement
//You have been given an array 'ARR' of integers consisting of ‘N’ integers and a positive integer ‘K’. Your task is to find a subarray(contiguous) of size ‘K’ such that the sum of its elements is minimum.
//
//        Note :
//You can assume that the value of ‘K’ will always be less than or equal to ‘N’. So, the answer will always exist.


//Constraints :
//        1 <= N <=  10^5
//        1 <= K <= N
//-10^5 <= ARR[i] <= 10^5

//Time Limit: 1sec
//Sample Input 1 :
//        8 3
//        10 4 2 5 6 3 8 1
//Sample Output 1 :
//        11

public class Q2MinimumSumSubarrayOfSizek {
    public static int minSum(int[]a,int n,int k){
        int i=0;
        int sum=0;
        int minSum=Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            sum+=a[j];
            if(j-i+1==k){
                minSum=Math.min(sum,minSum);
                sum-=a[i];
                i++;
            }
        }
        return minSum;
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
        int ans = minSum(a, n,k);
        System.out.print("ans : " + ans);
    }
}
