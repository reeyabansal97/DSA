package arrays.twopointer;

import java.util.Scanner;

public class Q5ContainerWithMostWater {
    public static int maxArea(int[]a,int n){
        int maxArea=0;
        int i=0;
        int j=n-1;
        while(i<j){
            int height=Math.min(a[i],a[j]);
            int b=j-1;
            int area=height*b;
            maxArea=Math.max(maxArea,area);
            if(a[i]<a[j])i++;
            else j--;
        }
        return maxArea;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();
        int ans=maxArea(a,n);
        System.out.println(ans);
    }
}
