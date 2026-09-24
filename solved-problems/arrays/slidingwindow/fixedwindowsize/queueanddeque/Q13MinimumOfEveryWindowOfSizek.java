package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;


//a monotonic increasing deque is used for solving below problem
public class Q13MinimumOfEveryWindowOfSizek {
    public static List<Integer>min(int[]a,int n,int k){
        int left=0;
        List<Integer>ans=new ArrayList<>();
        Deque<Integer>d=new ArrayDeque<>();
        for(int right=0;right<n;right++){
            //remove useless
            while(!d.isEmpty() && a[right]>=a[d.peekLast()]){
                d.pollLast();
            }
            d.addLast(right);
            if(right-left+1==k){
                ans.add(a[d.peekFirst()]);
                //remove element leaving the window
                if(!d.isEmpty() && d.peekFirst()==left){
                    d.pollFirst();
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
        List<Integer> ans = min(a, n,k);
        for(int min : ans){
            System.out.print(min+" ");
        }
    }
}
