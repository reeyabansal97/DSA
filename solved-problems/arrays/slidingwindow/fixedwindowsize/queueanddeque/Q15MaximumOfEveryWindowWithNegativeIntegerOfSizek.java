package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;

public class Q15MaximumOfEveryWindowWithNegativeIntegerOfSizek {
    public static List<Integer> maxPlusMin(int[]a, int n, int k){
        Deque<Integer> dec=new ArrayDeque<>();
        int left=0;
        List<Integer>ans=new ArrayList<>();
        for(int right=0;right<n;right++){
            while(!dec.isEmpty() && a[right]>=a[dec.peekLast()]){
                dec.pollLast();
            }
            dec.addLast(right);
            if(right-left+1==k){
                ans.add(a[dec.peekFirst()]);
                if(!dec.isEmpty() && dec.peekFirst()==left){
                    dec.pollFirst();
                }

                left++;
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
        List<Integer> ans = maxPlusMin(a, n,k);
        for(int sum : ans){
            System.out.print(sum+" ");
        }
    }
}
