package arrays.fundamentals;

import java.util.Scanner;

public class Q1MaxElementInArray {
    static int maxElement(int[]a,int n){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max=Math.max(max,a[i]);
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[]a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        sc.close();
        System.out.println(maxElement(a,n));
    }
}
