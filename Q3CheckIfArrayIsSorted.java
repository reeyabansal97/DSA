package arrays.fundamentals;

import java.util.Scanner;

public class Q3CheckIfArrayIsSorted {
    public static boolean checkSorted(int[]a,int n){
        int i=0;
        while(i<n-1){
            if(a[i]>a[i+1])return false;
            i++;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(checkSorted(a, n));
    }
}
