package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;

public class Q11FirstPositiveInEveryWindowOfSizek {
        public static List<Integer> firstPos(int[]a, int n, int k){
            int i=0;
            Queue<Integer> q=new LinkedList<>();
            List<Integer>ans=new ArrayList<>();
            for(int j=0;j<n;j++){
                //add new element from right
                if(a[j]>0)q.add(a[j]);
                //window size has reached
                if(j-i+1==k){
                    if(!q.isEmpty()) {
                        ans.add(q.peek());
                    }else{
                        ans.add(0);
                    }
                    //remove element (because element left the window)

                    if(a[i]>0 && !q.isEmpty() && q.peek()==a[i]){
                        q.poll();
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
            int k = sc.nextInt();
            sc.close();
            List<Integer> ans = firstPos(a, n, k);
            for (int pos : ans) {
                System.out.print(pos + " ");
            }
        }
}
