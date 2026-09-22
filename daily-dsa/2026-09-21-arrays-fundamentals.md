# Day 2: Arrays — Fundamentals & Common Operations

## What is an array and what problem does it solve?

An array is the simplest way to store multiple values of the same type **contiguously in memory**, accessed by a numeric index. "Contiguous" is the key word — because all elements sit back-to-back in memory, the computer can jump directly to any element if it knows the starting address and the index, without having to walk through the other elements first.

This is what gives arrays their signature property: **O(1) random access**. `arr[7]` is exactly as fast as `arr[0]`, no matter how big the array is.

## How it works mechanically

If an array starts at memory address `base`, and each element takes up `size` bytes, then element `i` lives at:

```
address = base + (i * size)
```

That's just arithmetic — no searching required. This is *why* array access is O(1): it's a direct calculation, not a traversal.

In Java, arrays have a **fixed size**, decided at creation time:

```java
int[] arr = new int[5];   // exactly 5 slots, all initialized to 0
int[] arr2 = {1, 2, 3};   // size 3, fixed
```

You cannot resize a Java array after creating it — if you need "resizable," that's what `ArrayList` is for (internally, `ArrayList` is backed by an array that gets copied to a bigger array when it fills up).

## Complexity of common operations

| Operation | Time | Why |
|---|---|---|
| Access by index (`arr[i]`) | O(1) | direct address calculation |
| Search (unsorted) | O(n) | must check elements one by one |
| Search (sorted, binary search) | O(log n) | can eliminate half each step |
| Insert/delete at the end | O(1)* | no shifting needed |
| Insert/delete at the start or middle | O(n) | every element after must shift by one |

*For a fixed-size Java array, "the end" only works if there's already a free slot; for `ArrayList`, this is amortized O(1) because of how resizing works.

## Java angle: reversing an array in place

**Problem:** Given an array, reverse it without using extra space for a second array.

```java
public class ReverseArray {
    public void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
```

**Walkthrough:** This uses the **two-pointer technique** — one pointer starts at the beginning, one at the end, and they swap elements while moving toward each other. Since we only ever touch each pair of elements once, this is O(n) time and O(1) extra space (we're modifying the array itself, not building a new one).

## Practice problem (unsolved)

Given an array of integers and a number `k`, rotate the array to the right by `k` steps. For example, `[1,2,3,4,5]` rotated right by `2` becomes `[4,5,1,2,3]`. Try to solve it using O(1) extra space (hint: think about what three reversals in a row can accomplish).

## Common pitfalls

- **Off-by-one errors** — forgetting that a Java array of size `n` has valid indices `0` through `n-1`, not `1` through `n`. Accessing `arr[arr.length]` throws `ArrayIndexOutOfBoundsException`.
- **Confusing fixed-size arrays with `ArrayList`** — trying to "add" to a plain `int[]` doesn't exist as an operation; you'd need to create a new, bigger array and copy elements over.
- **Assuming insertion is always O(1)** — inserting at the end (if there's room) is cheap, but inserting at the front or middle requires shifting every subsequent element, which is O(n).

## Retention Check

1. Why is array access O(1) regardless of array size?
2. What is the address formula used to compute where element `i` lives?
3. Why is inserting at the front of an array O(n) but inserting at the end is O(1)?
4. What's the difference between a Java `int[]` and an `ArrayList<Integer>` in terms of resizing?
5. Why does the two-pointer technique used in `reverse()` only need O(1) extra space?
6. What exception does Java throw if you access an out-of-bounds index?
7. If an array is sorted, what search technique lets you find an element in O(log n) instead of O(n)?
8. Why can't binary search be used directly on an unsorted array?

**Key points to self-check against:**
- Because the memory address is computed directly via arithmetic, not searched for.
- `address = base + (index * elementSize)`.
- Every element after the insertion point must shift over by one position.
- A plain array has a fixed size set at creation; `ArrayList` grows by allocating a new, bigger backing array and copying elements when it runs out of room.
- It swaps elements in place using two index variables, without allocating any new array.
- `ArrayIndexOutOfBoundsException`.
- Binary search.
- Binary search relies on being able to eliminate half the remaining elements by comparing to the middle — that only works if order is guaranteed.
