package arrays.twopointer;

import java.lang.reflect.Array;
import java.util.*;

public class Q6ThreeSum {
    public static List<List<Integer>> threeSum(int[]nums){
        Arrays.sort(nums);
        List<List<Integer>>ans=new ArrayList<>();

        //approach 1->use hashset to avoid duplicates

//        for(int i=0;i<nums.length-2;i++){
//            int j=i+1;
//            int k=nums.length-1;
//            while(j<k){
//                int sum=nums[i]+nums[j]+nums[k];
//                if(sum==0){
//                    List<Integer>triplets=new ArrayList<>();
//                    triplets.add(nums[i]);
//                    triplets.add(nums[j]);
//                    triplets.add(nums[k]);
//                    ans.add(triplets);
//                    j++;
//                    k--;
//                }else if(sum<0){
//                    j++;
//                }else{
//                    k--;
//                }
//            }
//        }


        //approach 2->skip i->duplicate and similarly j and k duplicates
        for(int i=0;i<nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1])continue; // Skip duplicate i
            int j=i+1;
            int k=nums.length-1;
            while(j<k){
                int sum=nums[i]+nums[j]+nums[k];
                if(sum==0){
                    List<Integer>triplets=new ArrayList<>();
                    triplets.add(nums[i]);
                    triplets.add(nums[j]);
                    triplets.add(nums[k]);
                    ans.add(triplets);
                    while(j<k && nums[j]==nums[j+1])j++;  // 3. Skip duplicate j
                    while(j<k && nums[k]==nums[k-1])k--;  // 4. Skip duplicate k
                    j++;
                    k--;
                }else if(sum<0){
                    j++;
                }else{
                    k--;
                }
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
        sc.close();
        List<List<Integer>>ans=new ArrayList<>();
    }
}
