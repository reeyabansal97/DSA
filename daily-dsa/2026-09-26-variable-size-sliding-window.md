# Day 7: Variable-Size Sliding Window

## How is this different from what you've already done?

In every fixed-window problem so far, the window was always exactly `k` wide. You slid it one step at a time, and `left` moved in lockstep with `right`.

In a variable-size window, **there is no `k`**. The problem gives you a *condition* instead, and asks for the longest or shortest window that satisfies it. So the window grows when it can and shrinks when it must.

| | Fixed window | Variable window |
|---|---|---|
| Given | A size `k` | A condition (e.g. "sum ≥ target", "no repeats") |
| `left` moves when | The window reaches size `k` | The window **breaks** the condition (or, for "shortest", while it still **satisfies** it) |
| Typical question | "max/min/count for every window of size k" | "longest/shortest window where ___" |

## The two shapes of variable-window problems

Almost every variable-window problem is one of two types. Figuring out which one you're facing tells you when to shrink.

### Type 1: "Longest window where the condition holds"

Grow `right` freely. The moment the window **becomes invalid**, shrink from `left` until it's valid again. After that, record the size.

```java
int left = 0, best = 0;
for (int right = 0; right < n; right++) {
    // add arr[right] to window state

    while (/* window is INVALID */) {
        // remove arr[left] from window state
        left++;
    }

    best = Math.max(best, right - left + 1);   // window is valid here
}
```

### Type 2: "Shortest window where the condition holds"

Grow `right` until the window **becomes valid**. Then shrink from `left` for as long as it **stays valid**, recording the size each time, because every shrink is a chance at a smaller answer.

```java
int left = 0, best = Integer.MAX_VALUE;
for (int right = 0; right < n; right++) {
    // add arr[right] to window state

    while (/* window is VALID */) {
        best = Math.min(best, right - left + 1);   // record before shrinking
        // remove arr[left] from window state
        left++;
    }
}
```

Notice the mirror image: "longest" shrinks while **invalid**, and "shortest" shrinks while **valid**. If you remember only one thing from today, make it this.

## Why it's still O(n)

There's a `while` inside a `for`, which looks like O(n²). It isn't. `left` only ever moves forward, never backward. Over the entire run, `left` moves at most `n` times in total, no matter how those moves are spread across iterations. So total work is `n` (for `right`) + `n` (for `left`) = O(n).

## Worked example (Type 1): Longest Substring Without Repeating Characters

**Problem ([LeetCode #3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)):** Find the length of the longest substring with no repeated characters.

**Window state:** a frequency map of characters currently in the window.
**Invalid when:** the character just added now has count > 1.

```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> freq = new HashMap<>();
    int left = 0, best = 0;

    for (int right = 0; right < s.length(); right++) {
        char in = s.charAt(right);
        freq.put(in, freq.getOrDefault(in, 0) + 1);

        while (freq.get(in) > 1) {            // window invalid: 'in' is repeated
            char out = s.charAt(left);
            freq.put(out, freq.get(out) - 1);
            left++;
        }

        best = Math.max(best, right - left + 1);
    }
    return best;
}
```

**Trace on `"abcabcbb"`:**

| right | char added | window after shrinking | length | best |
|---|---|---|---|---|
| 0 | a | a | 1 | 1 |
| 1 | b | ab | 2 | 2 |
| 2 | c | abc | 3 | 3 |
| 3 | a | bca (dropped first a) | 3 | 3 |
| 4 | b | cab | 3 | 3 |
| 5 | c | abc | 3 | 3 |
| 6 | b | cb | 2 | 3 |
| 7 | b | b | 1 | 3 |

Answer: 3.

Why check only `freq.get(in) > 1`? The window was valid before `in` arrived, so if it's broken now, `in` is the only possible duplicate. Shrink until that one duplicate is gone.

## Practice problem (unsolved, Type 2)

**Minimum Size Subarray Sum ([LeetCode #209](https://leetcode.com/problems/minimum-size-subarray-sum/)):** Given positive integers and a `target`, return the length of the shortest subarray whose sum is ≥ `target` (or 0 if none). Window state: a running `sum`. Use the Type 2 template: shrink while the sum is still ≥ target.

## Common pitfalls

- **Using `if` instead of `while` for shrinking.** One step of shrinking isn't always enough to restore the condition. Use `while`.
- **Recording the answer in the wrong place.** Type 1 records *after* the shrink loop, when the window is valid. Type 2 records *inside* the shrink loop, before each shrink.
- **Using this on arrays with negative numbers for sum problems.** Minimum Size Subarray Sum only works with sliding window because all numbers are positive: adding always increases the sum, and removing always decreases it. With negatives, shrinking might *increase* the sum, so the window logic breaks. Those problems usually need prefix sums (Day 5) instead.

## Retention Check

1. What does a variable window have instead of a fixed `k`?
2. For a "longest" problem, when do you shrink the window?
3. For a "shortest" problem, when do you shrink the window?
4. Where do you record the answer in each type, and why?
5. Why is a `while` loop inside a `for` loop still O(n) here?
6. In the Longest Substring code, why is checking only the newly added character enough?
7. Why must shrinking use `while` rather than `if`?
8. Why does Minimum Size Subarray Sum depend on all numbers being positive?

**Key points to self-check against:**
- A condition the window must satisfy.
- While the window is invalid.
- While the window is still valid (to find a smaller valid window).
- Longest: after the shrink loop, when the window is valid. Shortest: inside the shrink loop, before each shrink, since each valid state is a candidate.
- `left` only moves forward and makes at most `n` moves in total across the whole run.
- The window was valid before that character arrived, so it's the only possible duplicate.
- One shrink step may not restore the condition; you may need several.
- With only positives, adding always grows the sum and removing always shrinks it, so the shrink logic is reliable. Negatives break that.
