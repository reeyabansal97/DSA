package arrays.twopointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q7DutchNationalFlag {
    public static void swap(int[]a,int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void sortColors(int[] nums) {
        int i=0;
        int mid=0;
        int j=nums.length-1;
        while(mid<=j){
            if(nums[mid]==0){
                swap(nums,i,mid);
                i++;
                mid++;
            }else if(nums[mid]==1){
                mid++;
            }else{
                swap(nums,mid,j);
                j--;
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
    sortColors(a);
}

}
