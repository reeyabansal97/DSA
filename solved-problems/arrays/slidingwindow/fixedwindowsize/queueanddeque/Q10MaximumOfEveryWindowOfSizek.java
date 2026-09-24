package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;

//Input: arr[] = [5, 1, 3, 4, 2], k = 2
//Output: [5, 3, 4, 4]

//A monotonic data structure (monotonic DS) is a data structure where
// the elements are kept in a particular order — usually increasing or decreasing
// — so that we can efficiently find things like the minimum or maximum.


//a monotonic decreasing deque is used for solving below problem
public class Q10MaximumOfEveryWindowOfSizek {


    //approach 1 : brute force
//    public static List<Integer> maximumInAllSubarraysOfSizeK(int[] arr, int n, int k) {
//        List<Integer>ans=new ArrayList<>();
//        for(int i=0;i<n-k+1;i++){
//            int max=Integer.MIN_VALUE;
//            for(int j=i;j<i+k; j++){
//                max=Math.max(max,arr[j]);
//            }
//            ans.add(max);
//        }
//        return ans;
//    }
    //eliminate elements which can never be max
    public static List<Integer> max(int[]a, int n, int k){
        int i=0;
        Deque<Integer> q=new ArrayDeque<>();
        List<Integer>ans=new ArrayList<>();
        for(int j=0;j<n;j++){
            //eliminate useless candidates
            while(!q.isEmpty() && a[j]<=a[q.peekLast()]){
                q.pollLast();
            }
            //add new element from right
            q.addLast(j);
            //window size has reached
            if(j-i+1==k){
                ans.add(a[q.peekFirst()]);
                //remove element (because max element left the window)
                if(!q.isEmpty() && q.peekFirst()==i){
                    q.pollFirst();
                }
                //increasing initial index to exclude the element from left and move on to next element
                i++;
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
        int k=sc.nextInt();
        sc.close();
        List<Integer> ans = max(a, n,k);
        for(int max : ans){
            System.out.print(max+" ");
        }
    }
}
