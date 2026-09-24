# Day 5: Prefix Sum Technique

(Picked Prefix Sum over the next curriculum item so the concept lines up with your arrays practice list: Section 4 is Prefix Sum. Recursion comes next.)

## What problem does it solve?

Say someone asks you "what's the sum of elements from index 2 to index 6?" You loop and add them up. That's O(n). Now say they ask you 10,000 different range questions on the same array. Looping each time costs O(n) per question, so O(n × q) overall.

Prefix sum fixes this: you do **one O(n) pass upfront**, then answer **every range-sum question in O(1)**.

## How it works mechanically

Build a new array where each position holds the running total of everything before it:

```
arr    = [3, 1, 4, 1, 5]
prefix = [0, 3, 4, 8, 9, 14]
```

`prefix[i]` = the sum of the first `i` elements. So `prefix[0] = 0` (nothing added yet), `prefix[1] = 3`, `prefix[2] = 3+1 = 4`, and so on.

Notice `prefix` has length `n + 1`, one more than `arr`. That extra leading `0` is deliberate. It removes a special case, as you'll see below.

**The key formula.** Sum of `arr[l..r]` (both inclusive):

```
sum(l, r) = prefix[r + 1] - prefix[l]
```

Why this works: `prefix[r+1]` is the total of everything up to and including `r`. `prefix[l]` is the total of everything *before* `l`. Subtract, and the part before `l` cancels out, leaving exactly `l..r`.

Check: sum of `arr[1..3]` = `1 + 4 + 1 = 6`. Formula: `prefix[4] - prefix[1] = 9 - 3 = 6`. ✓

Without the leading `0`, asking for a range starting at index `0` would need `prefix[-1]`, which doesn't exist. You'd need an `if` for that case. The extra slot makes every range use the same formula.

## Complexity

| Approach | Build cost | Cost per query | Total for q queries |
|---|---|---|---|
| Loop each time | none | O(n) | O(n × q) |
| Prefix sum | O(n) once | O(1) | O(n + q) |

Space: O(n) for the prefix array.

## Java angle: Range Sum Query

**Problem:** Given an array, answer many "sum from index l to r" queries efficiently.

```java
public class RangeSumQuery {
    private final long[] prefix;

    public RangeSumQuery(int[] arr) {
        prefix = new long[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }
    }

    public long sumRange(int l, int r) {
        return prefix[r + 1] - prefix[l];
    }
}
```

**Walkthrough:** The constructor does the one-time O(n) work. Each `sumRange` call is a single subtraction. Note the use of `long` rather than `int`: running totals grow fast, and a sum of many large `int` values can overflow past `Integer.MAX_VALUE` (about 2.1 billion) even when every individual element fits fine.

## Practice problem (unsolved)

**Find Pivot Index** ([LeetCode #724](https://leetcode.com/problems/find-pivot-index/)): return the index where the sum of everything to its left equals the sum of everything to its right. Hint: if you know the total sum and the running sum to the left of index `i`, you can compute the right-side sum without another loop.

## Common pitfalls

- **Off-by-one in sizing.** Using an `n`-sized prefix array instead of `n + 1` forces an awkward special case for ranges starting at index 0. Pick one convention and stick to it.
- **Integer overflow.** Running sums can exceed `int`'s range even when inputs are small. Use `long` for the prefix array when sums could get large.
- **Using it on a changing array.** Prefix sums assume the array doesn't change. If an element updates, every prefix value after it becomes wrong, and rebuilding costs O(n). For frequent updates you need a different structure (Fenwick tree or segment tree, both later in the curriculum).

## Retention Check

1. What does `prefix[i]` represent?
2. Write the formula for the sum of `arr[l..r]` using the prefix array.
3. Why is the prefix array sized `n + 1` instead of `n`?
4. What's the total cost of answering `q` range queries with and without prefix sums?
5. Why does the Java example use `long` instead of `int` for the prefix array?
6. What happens to a prefix sum array if one element of the original array changes?
7. For Find Pivot Index, how can you get the right-side sum at index `i` without looping over the right side?
8. Using `arr = [2, 4, 6, 8]`, build the prefix array and compute the sum of `arr[1..2]`.

**Key points to self-check against:**
- The sum of the first `i` elements (indices `0` to `i-1`).
- `prefix[r + 1] - prefix[l]`.
- The leading `0` means ranges starting at index 0 use the same formula, with no special case.
- Without: O(n × q). With: O(n + q).
- Running totals can overflow `int` even when every element fits in an `int`.
- Every prefix value after that position becomes stale; fixing it costs O(n).
- Right sum = total sum − left sum − `arr[i]`.
- `prefix = [0, 2, 6, 12, 20]`; sum = `prefix[3] - prefix[1] = 12 - 2 = 10`.
