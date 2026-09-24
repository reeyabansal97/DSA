package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;

public class Q14MaximumMinimumOfEveryWindowOfSizek {
    //Given:arr = [1, 3, -1, -3, 5, 3, 6, 7],k = 3
    //For every window of size k, calculate: maximum - minimum
    //Expected Output : [4, 6, 8, 8, 3, 4]
        public static List<Integer> maxminusmin(int[]a, int n, int k){
            Deque<Integer> dec=new ArrayDeque<>();
            Deque<Integer>inc=new ArrayDeque<>();
            int left=0;
            List<Integer>ans=new ArrayList<>();
            for(int right=0;right<n;right++){
                while(!dec.isEmpty() && a[right]<=a[dec.peekLast()]){
                    dec.pollLast();
                }
                dec.addLast(right);
                while(!inc.isEmpty() && a[right]>=a[inc.peekLast()]){
                    inc.pollLast();
                }
                inc.addLast(right);
                if(right-left+1==k){
                    ans.add(a[dec.peekFirst()]-a[inc.peekFirst()]);
                    if(!dec.isEmpty() && dec.peekFirst()==left){
                        dec.pollFirst();
                    }
                    if(!inc.isEmpty() && inc.peekFirst()==left){
                        inc.pollFirst();
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
            List<Integer> ans = maxminusmin(a, n,k);
            for(int min : ans){
                System.out.print(min+" ");
            }
        }
    }
