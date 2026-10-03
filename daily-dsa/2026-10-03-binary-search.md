# Day 12: Binary Search

## What problem does it solve?

Finding a value in an unsorted array means checking every element: O(n). But if the array is **sorted**, you can do far better. Look at the middle element. If it's too small, the answer can only be in the right half, so throw away the left half entirely. Repeat. Each step halves what's left, so the search takes **O(log n)**.

How big is that difference? For 1 billion sorted elements, a linear search may need 1 billion checks. Binary search needs about **30**.

You've used this reasoning before: Day 1 showed binary search as the O(log n) example, and you answered "why can't binary search work on an unsorted array?" on Day 2.

## The standard template

```java
public int binarySearch(int[] arr, int target) {
    int low = 0, high = arr.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            low = mid + 1;     // target is to the right
        } else {
            high = mid - 1;    // target is to the left
        }
    }
    return -1;   // not found
}
```

**Trace: find 7 in `[1, 3, 5, 7, 9, 11]`**

| low | high | mid | arr[mid] | action |
|---|---|---|---|---|
| 0 | 5 | 2 | 5 | 5 < 7 → low = 3 |
| 3 | 5 | 4 | 9 | 9 > 7 → high = 3 |
| 3 | 3 | 3 | 7 | found, return 3 |

## Three details that cause most binary search bugs

**1. `mid = low + (high - low) / 2`, not `(low + high) / 2`.**
If `low` and `high` are both large (close to `Integer.MAX_VALUE`), `low + high` overflows into a negative number, and `mid` becomes garbage. `low + (high - low) / 2` gives the same result without ever exceeding `high`. This exact bug sat in Java's own `Arrays.binarySearch` for years before it was fixed.

**2. `while (low <= high)`, not `<`.**
With `<`, the loop stops as soon as `low == high`, before checking that last remaining element. Searching for `7` in `[7]` would wrongly return `-1`.

**3. `mid + 1` and `mid - 1`, not `mid`.**
You already know `arr[mid]` isn't the answer, so exclude it. Setting `low = mid` can cause an **infinite loop**: when `low` and `high` are next to each other, `mid` equals `low`, so `low = mid` changes nothing.

## The bigger idea: searching for a boundary

Binary search doesn't just find exact values. It finds **the point where a yes/no condition flips**. That makes it far more useful than it first looks.

**Example: find the first occurrence of `target` in a sorted array with duplicates** (`[1, 2, 2, 2, 3]`, target `2` → index 1).

```java
public int firstOccurrence(int[] arr, int target) {
    int low = 0, high = arr.length - 1, result = -1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            result = mid;      // record it, but keep looking LEFT for an earlier one
            high = mid - 1;
        } else if (arr[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return result;
}
```

The only change from the standard template: on a match, **record it and keep searching left** instead of returning. For the *last* occurrence, keep searching right (`low = mid + 1`) instead.

This is LeetCode #34, "Find First and Last Position of Element in Sorted Array", which is already in Section 7 of your arrays practice list.

## Binary search on the answer (preview)

The same idea works even when there's no array to search. If you can ask "is `x` big enough?", and the answer is no for small `x` and yes from some point on, binary search can find that point. Example: "What's the minimum speed to finish all tasks within `h` hours?" Search over possible speeds. This pattern appears in many medium and hard problems.

## Java angle

Java has binary search built in, for **sorted** data:

```java
int idx = Arrays.binarySearch(arr, 7);
int idx2 = Collections.binarySearch(list, 7);
```

When the value isn't found, these don't return `-1`. They return `-(insertionPoint) - 1`, where the insertion point is the index the value would be inserted at. That's always negative, so "is it negative?" still means "not found." On an **unsorted** array, the result is undefined: you may get a wrong answer with no error.

## Practice problem (unsolved)

**Search Insert Position ([LeetCode #35](https://leetcode.com/problems/search-insert-position/)):** Given a sorted array and a target, return its index if found, otherwise the index where it would be inserted. Hint: run the standard template. When the loop ends without finding it, think about what `low` points to.

## Common pitfalls

- **Overflow in `(low + high) / 2`.** Use `low + (high - low) / 2`.
- **`low < high` with the `mid ± 1` template.** It skips the final element.
- **`low = mid` or `high = mid`** combined with `low <= high`, which can loop forever.
- **Using it on unsorted data.** It silently returns wrong answers.

## Retention Check

1. Why is binary search O(log n)?
2. What must be true about the data for binary search to work?
3. Why use `low + (high - low) / 2` instead of `(low + high) / 2`?
4. What goes wrong with `while (low < high)` in the standard template?
5. Why can `low = mid` cause an infinite loop?
6. How do you change the template to find the *first* occurrence of a value?
7. What does "binary search on the answer" mean?
8. What does `Arrays.binarySearch` return when the value isn't found?

**Key points to self-check against:**
- Each step discards half of the remaining elements.
- It must be sorted (or, more generally, have a condition that flips from no to yes exactly once).
- `low + high` can overflow `int` when both are large.
- It exits when `low == high` without checking that last element.
- When `low` and `high` are adjacent, `mid == low`, so `low = mid` changes nothing.
- On a match, record the index and continue searching left (`high = mid - 1`).
- Searching over a range of possible answers, using a yes/no check that flips at the optimal value.
- `-(insertionPoint) - 1`, which is always negative.
