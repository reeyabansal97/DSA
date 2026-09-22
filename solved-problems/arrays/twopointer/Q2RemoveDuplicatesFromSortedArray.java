package arrays.twopointer;

import java.util.Scanner;

public class Q2RemoveDuplicatesFromSortedArray {
    public static void removeDuplicates(int[]a,int n){
       int j=0;
       for(int i=1;i<n;i++){
           if(a[i]!=a[j]){
               j=j+1;
               a[j]=a[i];
           }
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
        removeDuplicates(a,n);
        for (int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
