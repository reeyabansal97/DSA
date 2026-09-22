package arrays.twopointer;

import java.util.Scanner;

public class Q3TwoSum2 {
    public static int[] sortedArrayTwoSum(int[]a,int n,int target){
        int[]ans=new int[2];
        int i=0;
        int j=a.length-1;
        while(i<j){
            if(a[i]+a[j]==target){
                ans[0]=i+1;
                ans[1]=j+1;
                break;
            }else if(a[i]+a[j]<target){
                i++;
            }else{
                j--;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int target=sc.nextInt();
        sc.close();
        sortedArrayTwoSum(a,n,target);
        for (int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
