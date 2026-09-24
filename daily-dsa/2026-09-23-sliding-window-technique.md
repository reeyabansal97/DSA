# Day 4: Sliding Window Technique

## What problem does it solve?

Many problems ask you something like "find the best/longest/shortest **contiguous** chunk of an array or string that satisfies some condition" — e.g., the maximum sum of any 3 consecutive elements, or the shortest run of elements that adds up to at least some target. The brute-force approach checks every possible contiguous chunk, which is typically O(n²) or worse. The sliding window technique gets this down to O(n) by **reusing work from the previous window instead of recomputing from scratch.**

## How it works mechanically

A "window" is just a contiguous range `[left, right]` within the array. There are two flavors:

**Fixed-size window** — the window size `k` is given upfront and never changes. You compute the result for the first window, then slide it one step at a time: subtract the element leaving on the left, add the element entering on the right.

**Variable-size window** — the window grows and shrinks based on a condition. You expand `right` to include more elements; when the window violates some condition (e.g., sum too big, contains a duplicate), you shrink from `left` until it's valid again.

The key invariant that makes this O(n) instead of O(n²): **each element is added to the window exactly once and removed at most once**, across the entire algorithm. Even though there's a nested feel (an outer loop for `right`, an inner "shrink" loop for `left`), `left` never moves backward — across the whole run, it moves forward at most `n` times total, so the total work stays linear.

## Complexity

| Approach | Time | Space |
|---|---|---|
| Brute force (check every subarray) | O(n²) or O(n³) | O(1) |
| Sliding window | O(n) | O(1) (or O(k) if tracking a window's contents, e.g. a character set) |

## Java angle: Maximum Sum Subarray of Size K (fixed window)

**Problem:** Given an array and an integer `k`, find the maximum sum of any `k` consecutive elements.

```java
public class MaxSumSubarrayFixedWindow {
    public int maxSumSubarray(int[] arr, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];      // build the first window
        }

        int maxSum = windowSum;
        for (int right = k; right < arr.length; right++) {
            windowSum += arr[right];           // add the new element entering
            windowSum -= arr[right - k];       // remove the old element leaving
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}
```

**Walkthrough:** Instead of re-summing all `k` elements every time the window slides (which would be O(n·k)), we maintain a running `windowSum` and update it in O(1) per slide — add what just entered, subtract what just left. That's the entire trick: turning a "recompute from scratch" cost into an "incremental update" cost.

## Practice problem (unsolved)

Given an array of positive integers and a target sum, find the length of the **smallest** contiguous subarray whose sum is greater than or equal to the target (return 0 if no such subarray exists). This is a **variable-size window** — try growing `right` to increase the sum, and shrinking `left` whenever the current window's sum already meets the target, tracking the minimum window length seen along the way.

## Common pitfalls

- **Recomputing the window sum from scratch on every slide** — defeats the entire purpose of the technique and silently turns your O(n) solution back into O(n·k).
- **Forgetting to shrink the window in a variable-size problem** — if you only ever expand `right` and never move `left`, you're not sliding a window at all, you're just scanning the whole array once (which gives wrong answers for "shortest"/"smallest" style problems).
- **Off-by-one on window boundaries** — mixing up whether `right` is inclusive or exclusive of the window is one of the most common sources of bugs in window problems; be explicit and consistent about it.

## Retention Check

1. What's the key difference between a fixed-size and variable-size sliding window?
2. Why is the total work still O(n) even though there's a nested "shrink" loop inside the main loop for variable windows?
3. In the fixed-window Java example, what two operations happen when the window slides by one position?
4. What would go wrong (performance-wise) if you recomputed the entire window sum from scratch on every slide instead of incrementally updating it?
5. For the "smallest subarray with sum ≥ target" practice problem, when should you shrink the window from the left?
6. Why does the technique specifically require the subarray to be **contiguous** — would it work for "any k elements, not necessarily adjacent"?
7. What's the space complexity of a basic sum-tracking sliding window, and when might it grow beyond O(1)?
8. What kind of bug is most commonly introduced when implementing sliding window problems?

**Key points to self-check against:**
- Fixed: the window size is constant and given upfront. Variable: the window grows/shrinks dynamically based on a condition.
- Because `left` only ever moves forward, never backward — across the entire run it makes at most `n` total moves, so total work across all shrink operations is still bounded by `n`.
- Add the element newly entering on the right, subtract the element leaving on the left.
- It would cost O(k) work per slide instead of O(1), making the whole algorithm O(n·k) instead of O(n).
- Whenever the current window's sum already meets or exceeds the target — shrink to find the smallest valid window, then keep shrinking further as long as it's still valid.
- No — sliding window relies on contiguity so that adding/removing one element at a time makes sense; for non-contiguous subsets you'd need a different approach entirely (e.g., sorting, or a different algorithm).
- O(1) for a simple running sum; it can grow to O(k) or O(26) etc. if you need to track the actual contents of the window (like a character frequency map).
- Off-by-one errors on the window boundaries (inclusive vs exclusive of `left`/`right`).
