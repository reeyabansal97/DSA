package arrays.twopointer;

import java.util.Scanner;

public class Q1ReverseArrayInPlace {
    public static void reverseArray(int[]a,int n){
        int i=0;
        int j=n-1;
        while(i<=j){
            int temp=a[i];
            a[i]=a[j];
            a[j]=temp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();
        reverseArray(a,n);
        for (int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
