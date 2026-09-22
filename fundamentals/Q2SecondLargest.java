package arrays.fundamentals;

import java.util.Scanner;

public class Q2SecondLargest {
    static int secondLargest(int[]a,int n){
        if(a==null || a.length<2)return -1;
        int max=a[0];
        int secondMax=0;
        boolean found=false;
        for(int i=0;i<n;i++){
            if(a[i]>max){
                secondMax=max;
                max=a[i];
                found=true;
            }else if(a[i]<max && (!found || a[i]>secondMax)){
                secondMax=a[i];
                found=true;
            }
        }
        return found?secondMax:-1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(secondLargest(a, n));
    }
}
