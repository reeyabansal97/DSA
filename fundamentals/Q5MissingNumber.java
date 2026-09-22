package arrays.fundamentals;

import java.util.Scanner;

public class Q5MissingNumber {
    public static int missingNumber(int[]a,int n){
        int xorRange=0;
        for(int i=1;i<=n;i++){
            xorRange^=i;
        }
        int xorValue=0;
        for(int i=0;i<n;i++){
            xorValue^=a[i];
        }
        return xorRange^xorValue;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n - 1; i++) {
            a[i] = sc.nextInt();
        }
        int ans = missingNumber(a, n);
        System.out.print("ans : " + ans);
    }
}
