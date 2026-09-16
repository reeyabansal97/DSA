# Day 1: Big-O Notation & Time/Space Complexity

## What is Big-O and why do we care?

When you write code, you often have a few different ways to solve the same problem. Big-O notation is just a way to describe **how the running time (or memory use) of your code grows as the input gets bigger** — not the exact number of seconds it takes, but the *shape* of the growth.

Think of it like this: if you have to find a name in a phonebook,
- Checking every single page one by one gets slower and slower the bigger the phonebook is — that's a *linear* relationship.
- Opening to the middle, deciding "is my name before or after this page?", and repeating (like binary search) barely gets slower even if the phonebook doubles in size — that's a much better relationship.

Big-O gives us a common language to compare these approaches without needing to run actual timers.

## The common growth rates, from best to worst

| Notation | Name | Example |
|---|---|---|
| O(1) | Constant | Accessing `array[5]` |
| O(log n) | Logarithmic | Binary search |
| O(n) | Linear | Looping through a list once |
| O(n log n) | Linearithmic | Efficient sorting (merge sort) |
| O(n²) | Quadratic | Nested loop over the same list |
| O(2ⁿ) | Exponential | Trying every subset of a set |

`n` here just means "the size of your input" — e.g., the number of elements in an array.

## How to actually read code and figure out its Big-O

The rule of thumb: **count how many times the "core work" runs, relative to input size `n`.**

```java
// O(n) — one loop over n items
public int sum(int[] arr) {
    int total = 0;
    for (int num : arr) {      // runs n times
        total += num;
    }
    return total;
}
```

```java
// O(n^2) — a loop inside a loop, both depending on n
public boolean hasDuplicate(int[] arr) {
    for (int i = 0; i < arr.length; i++) {        // outer: n times
        for (int j = i + 1; j < arr.length; j++) { // inner: up to n times
            if (arr[i] == arr[j]) return true;
        }
    }
    return false;
}
```

```java
// O(log n) — the amount of work HALVES each step
public int binarySearch(int[] sortedArr, int target) {
    int low = 0, high = sortedArr.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (sortedArr[mid] == target) return mid;
        else if (sortedArr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}
```

**Key habit:** look at your loops. One loop over `n` items → usually O(n). A loop inside a loop → usually O(n²). A loop that cuts the problem in half each time → O(log n).

## Space complexity — the same idea, but for memory

Space complexity asks: how much *extra* memory does your algorithm need, beyond the input itself, as `n` grows? For example, `sum()` above uses O(1) space (just one `total` variable, no matter how big the array is). A function that builds a new array of size `n` uses O(n) space.

## We only care about the biggest term

Big-O drops constants and smaller terms, because we care about behavior at large `n`. `O(2n + 5)` is written as just `O(n)` — doubling the work or adding a fixed amount doesn't change the *shape* of growth. Similarly `O(n² + n)` becomes `O(n²)` — the `n²` term dominates once `n` is large.

## Practice problem (unsolved)

What is the time complexity of this function, and why?

```java
public void printPairs(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i]);
    }
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[i] + ", " + arr[j]);
        }
    }
}
```

## Common pitfalls

- Thinking "two separate loops" always means O(n²) — two *separate, non-nested* loops over `n` items is actually O(n) + O(n) = O(2n), which simplifies to O(n). Only *nested* loops multiply.
- Forgetting that a loop that halves the problem each time (like binary search) is O(log n), not O(n).
- Confusing "worst case" with "average case" — Big-O usually refers to worst case unless stated otherwise.

## Retention Check

1. What does Big-O notation actually describe?
2. Why do we drop constants, like turning O(3n) into O(n)?
3. What's the time complexity of a single loop over an array of size `n`?
4. What's the time complexity of two nested loops, both over the same array?
5. Why is binary search O(log n) instead of O(n)?
6. What is space complexity, and how is it different from time complexity?
7. Is "two separate loops in sequence" the same complexity as "one loop nested inside another"? Why or why not?
8. Order these from fastest-growing to slowest-growing: O(n²), O(1), O(log n), O(n).

**Key points to self-check against:**
- How runtime/memory scales as input size grows, not exact seconds.
- We care about growth trend at large `n`, not small fixed differences.
- O(n) — one pass through the data.
- O(n²) — for each item, you look at (roughly) every other item again.
- Each step eliminates half the remaining possibilities.
- Extra memory used beyond the input itself, as a function of `n`.
- No — sequential loops add (O(n)+O(n)=O(n)); nested loops multiply (O(n)×O(n)=O(n²)).
- O(1) < O(log n) < O(n) < O(n²).
