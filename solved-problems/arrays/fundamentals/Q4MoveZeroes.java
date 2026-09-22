package arrays.fundamentals;

import java.util.Scanner;

public class Q4MoveZeroes {
    public static void moveZeroesToEnd(int[]a,int n){
        int j=0;
        for(int i=0;i<n;i++){
            if(a[i]!=0){
                int temp=a[i];
                a[i]=a[j];
                a[j]=temp;
                j++;
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
        moveZeroesToEnd(a,n);
        for (int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
