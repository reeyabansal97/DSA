# Day 6: Recursion Basics

## What is recursion?

A recursive function is one that **calls itself** to solve a smaller version of the same problem. It keeps shrinking the problem until it hits a version so small the answer is obvious, then builds the full answer back up.

Everyday analogy: you're in a queue and want to know your position. You ask the person in front, "what's your position?" They ask the person in front of them, and so on, until the person at the very front says "I'm 1." Then each person adds 1 and passes the answer back.

## The two parts every recursive function needs

**1. Base case.** The smallest version of the problem, answered directly without another recursive call. This is what stops the recursion.

**2. Recursive case.** Break the problem into a smaller piece, call the function on that piece, and use the result.

```java
public int factorial(int n) {
    if (n <= 1) {          // base case
        return 1;
    }
    return n * factorial(n - 1);   // recursive case
}
```

`factorial(4)` = `4 * factorial(3)` = `4 * 3 * factorial(2)` = `4 * 3 * 2 * factorial(1)` = `4 * 3 * 2 * 1` = `24`.

## How it actually runs: the call stack

Each time a function is called, Java pushes a **stack frame** onto the call stack. The frame holds that call's parameters and local variables. The frame is popped when the call returns.

For `factorial(4)`:

```
push factorial(4)   waits for factorial(3)
push factorial(3)   waits for factorial(2)
push factorial(2)   waits for factorial(1)
push factorial(1)   base case → returns 1         pop
factorial(2) gets 1, returns 2 * 1 = 2              pop
factorial(3) gets 2, returns 3 * 2 = 6              pop
factorial(4) gets 6, returns 4 * 6 = 24             pop
```

Two phases: **going down** (calls pile up until the base case) and **coming back up** (each frame finishes its multiplication and returns).

## Complexity

- **Time:** count the total number of calls × work per call. `factorial(n)` makes `n` calls, each doing O(1) work → **O(n)**.
- **Space:** the maximum depth of the call stack at any moment. `factorial(n)` has up to `n` frames stacked at once → **O(n)**.

That second point surprises people: a recursive solution with no arrays still uses O(n) memory, because of the stack frames.

## When recursion goes wrong: StackOverflowError

The call stack has a limited size. If recursion goes too deep, or never reaches its base case, Java throws `StackOverflowError`.

```java
public int broken(int n) {
    return n * broken(n - 1);   // no base case — runs until the stack overflows
}
```

## Java angle: sum of an array, recursively

**Problem:** Return the sum of all elements in an array using recursion.

```java
public class RecursiveSum {
    public int sum(int[] arr, int index) {
        if (index == arr.length) {
            return 0;                          // base case: past the end, nothing left
        }
        return arr[index] + sum(arr, index + 1);   // this element + sum of the rest
    }
}
```

Call it as `sum(arr, 0)`. **Walkthrough:** each call handles one element and hands the rest of the array to the next call. When `index` runs past the end, the base case returns 0, and the partial sums add up on the way back.

Note that the "smaller problem" here isn't a smaller array. It's the same array with a larger starting index. That trick avoids copying the array on every call.

## Practice problem (unsolved)

Write a recursive function that checks whether a string is a palindrome. Hint: compare the first and last characters, then recurse on the part in between. What's the base case when the remaining part has 0 or 1 characters?

## Common pitfalls

- **Missing or wrong base case.** The recursion never stops → `StackOverflowError`.
- **The recursive call doesn't shrink the problem.** `f(n)` calling `f(n)` again (instead of `f(n-1)`) loops forever even with a base case present.
- **Forgetting the stack costs memory.** Recursion depth = space used. For very deep inputs (say, 100,000 levels), an iterative loop is safer.
- **Recomputing the same thing over and over.** Naive recursive Fibonacci recomputes the same values exponentially many times. Fixing that is exactly what dynamic programming is about, later in the curriculum.

## Retention Check

1. What are the two parts every recursive function needs?
2. What does the base case do, and what happens without one?
3. What is stored in a stack frame?
4. Why does recursive `factorial(n)` use O(n) space even though it allocates no arrays?
5. Trace `factorial(3)` through the call stack, going down and coming back up.
6. In the recursive array sum, what makes each call "smaller" than the previous one?
7. When would you prefer an iterative loop over recursion?
8. For the palindrome practice problem, what's the base case?

**Key points to self-check against:**
- A base case and a recursive case.
- It stops the recursion; without it you get infinite calls and a `StackOverflowError`.
- That call's parameters, local variables, and where to return to.
- Each pending call occupies a stack frame, and up to `n` of them exist at once.
- Down: `f(3)` → `f(2)` → `f(1)` returns 1. Up: `f(2)` returns 2, `f(3)` returns 6.
- The starting index moves one step forward, so fewer elements remain to process.
- When recursion depth could be very large (risk of stack overflow), or when a simple loop is clearer.
- A string (or remaining range) of length 0 or 1 is a palindrome → return `true`.
