package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;


//Q. Last Negative Number in Every Window of Size K
//Given: arr = [12, -1, -7, 8, -15, 30, 16],k = 3
//Expected: [-7, -7, -15, -15, -15]

public class Q12LastNegativeNumberInEveryWindowOfSizek {
    public static List<Integer> lastNeg(int[]a, int n, int k){
        int i=0;
        Deque<Integer> q=new ArrayDeque<>();
        List<Integer>ans=new ArrayList<>();
        for(int j=0;j<n;j++){
            //add new element from right
            if(a[j]<0){
                q.addLast(j);
            }
            //window size has reached
            if(j-i+1==k){
                if(!q.isEmpty()) {
                    ans.add(a[q.peekLast()]);
                }else{
                    ans.add(0);
                }
                //remove element (because the element left the window)
                if(a[i]<0 && !q.isEmpty() && q.peekFirst()==i){
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
        List<Integer> ans = lastNeg(a, n,k);
        for(int neg : ans){
            System.out.print(neg+" ");
        }
    }
}
