package dsa.solvedproblems.arrays.slidingwindow.fixedwindowsize.queueanddeque;

import java.util.*;

public class Q9FirstNegativeNumberInEveryWindowOfSizek {
    public static List<Integer> firstNeg(int[]a,int n,int k){
        int i=0;
        Queue<Integer>q=new LinkedList<>();
        List<Integer>ans=new ArrayList<>();
        for(int j=0;j<n;j++){
            //add new element from right
            if(a[j]<0)q.add(a[j]);
            //window size has reached
            if(j-i+1==k){
                if(!q.isEmpty()) {
                    ans.add(q.peek());
                }else{
                    ans.add(0);
                }
                //remove element (because element left the window)

//                unboxing in action
//                Integer == int(q.peek()==a[i])
//
//                and automatically converts/unboxes the Integer into an int.
//
//                        So it's effectively comparing:
//
//                (int) q.peek() == a[i]

//                Comparison	What == does
//                int == int	compares values ✅
//                Integer == int	unboxes Integer, compares values ✅
//                Integer == Integer	compares object references ⚠️
//                Integer.equals(Integer)	compares values ✅


                if(a[i]<0 && !q.isEmpty() && q.peek()==a[i]){
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
        int k=sc.nextInt();
        sc.close();
        List<Integer> ans = firstNeg(a, n,k);
        for(int neg : ans){
            System.out.print(neg+" ");
        }
    }
}
