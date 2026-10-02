# Day 11: Queues Fundamentals

## What is a queue?

A queue adds at one end (the **back**, or **tail**) and removes from the other end (the **front**, or **head**). The first thing in is the first thing out: **FIFO (First In, First Out)**.

Analogy: a line at a ticket counter. The person who joined first gets served first, and new people join at the back.

```
offer(1), offer(2), offer(3)

front → 1  2  3 ← back

poll() → returns 1, then front is 2
```

Compare with yesterday's stack: a stack reverses order (LIFO), a queue preserves it (FIFO).

## The core operations

| Operation | What it does | Time |
|---|---|---|
| `offer(x)` | Add `x` at the back | O(1) |
| `poll()` | Remove and return the front | O(1) |
| `peek()` | Look at the front without removing it | O(1) |
| `isEmpty()` | Is it empty? | O(1) |

## How it's built underneath

**Linked list (Day 9):** keep references to both the head and the tail. `offer` attaches a node at the tail; `poll` removes the head. Both are O(1).

**Array, as a circular buffer:** a naive array queue that removes from index 0 would shift every element left, which is O(n) per `poll`. Instead, keep `front` and `back` indices that **wrap around** to the start when they reach the end (`index = (index + 1) % capacity`). The array is reused in a circle, so nothing ever shifts. This is how `ArrayDeque` works internally.

## Java angle

`Queue` is an interface (more on interfaces in today's Java read). The two common implementations:

```java
Queue<Integer> q = new ArrayDeque<>();   // usually the best default
Queue<Integer> q2 = new LinkedList<>();  // also works, slightly more memory per element
```

**The two method families.** `Queue` gives you two versions of each operation, which behave differently when something goes wrong:

| | Throws an exception | Returns a special value |
|---|---|---|
| Add | `add(x)` | `offer(x)` → `false` if it can't add |
| Remove | `remove()` | `poll()` → `null` if empty |
| Look | `element()` | `peek()` → `null` if empty |

Most code uses `offer`/`poll`/`peek`, and checks for `null` or `isEmpty()`.

**Watch out:** `ArrayDeque` doesn't allow `null` elements. That's deliberate: `poll()` returning `null` has to mean "empty," not "the element was null."

## Deque: both ends at once

`Deque` (pronounced "deck") is a double-ended queue: add and remove at **both** ends in O(1). That's why yesterday's stack used `ArrayDeque` too: `push`/`pop` work on the front. One class gives you both a stack and a queue:

```java
Deque<Integer> dq = new ArrayDeque<>();
dq.offerLast(1);   // queue-style: add at back
dq.offerFirst(0);  // add at front
dq.pollFirst();    // remove from front
dq.pollLast();     // remove from back
```

A deque is also the tool behind **Sliding Window Maximum** (LeetCode #239), the hard problem you parked earlier.

## When to reach for a queue

The signal: **process things in the order they arrived**, or **level by level**.
- Task schedulers, print queues, request buffers
- **Breadth-first search (BFS)** on trees and graphs, coming later: a queue makes you visit everything 1 step away before anything 2 steps away
- "First negative number in every window of size k": the problem from your fixed-window list uses a queue to track candidates in arrival order

## Worked example: First Negative Number in Every Window of Size K

**Problem:** For each window of size `k`, output the first negative number (or 0 if none).

**Idea:** a queue holds the **indices** of negative numbers in the current window, in arrival order. The front is always the earliest one, which is exactly the "first negative."

```java
public List<Integer> firstNegatives(int[] arr, int k) {
    Queue<Integer> negIdx = new ArrayDeque<>();
    List<Integer> result = new ArrayList<>();
    int left = 0;

    for (int right = 0; right < arr.length; right++) {
        if (arr[right] < 0) negIdx.offer(right);        // a new negative joins at the back

        if (right - left + 1 == k) {
            result.add(negIdx.isEmpty() ? 0 : arr[negIdx.peek()]);
            if (!negIdx.isEmpty() && negIdx.peek() == left) {
                negIdx.poll();                           // leaving element was the front negative
            }
            left++;
        }
    }
    return result;
}
```

It's your fixed-window template, with a queue as the window state instead of a sum or count. Why store indices instead of values? To know when the front negative has slid out of the window (`peek() == left`).

**Trace on `[-1, 2, -3, 4]`, `k = 2`:** windows `[-1,2]` → -1, `[2,-3]` → -3, `[-3,4]` → -3.

## Practice problem (unsolved)

**Implement Queue using Stacks ([LeetCode #232](https://leetcode.com/problems/implement-queue-using-stacks/)):** Build a FIFO queue using only two stacks. Hint: one stack takes new elements, and when you need the front, pour everything into the second stack, which reverses the order. Only pour when the second stack is empty.

## Common pitfalls

- **Removing from index 0 of an `ArrayList` as a queue.** That's O(n) per removal, because everything shifts. Use `ArrayDeque`.
- **Mixing up the two method families.** `remove()` throws on empty; `poll()` returns `null`.
- **Unboxing a `null` from `poll()`.** `int x = q.poll();` on an empty `Queue<Integer>` throws `NullPointerException` (Java Day 2: unboxing null).
- **Storing values when you need positions.** Window problems often need indices, to know when something has left the window.

## Retention Check

1. What does FIFO mean, and how does it differ from a stack's LIFO?
2. Why is removing from the front of a naive array-based queue O(n)?
3. How does a circular buffer avoid that cost?
4. What's the difference between `poll()` and `remove()`?
5. Why doesn't `ArrayDeque` allow `null` elements?
6. What makes a deque different from a queue?
7. In First Negative Number, why does the queue store indices instead of values?
8. What happens with `int x = q.poll();` on an empty `Queue<Integer>`?

**Key points to self-check against:**
- First in, first out; a queue preserves arrival order, while a stack reverses it.
- Every remaining element shifts left one position.
- The front and back indices wrap around, so nothing ever shifts.
- On an empty queue, `poll()` returns `null` and `remove()` throws an exception.
- So that `null` from `poll()` unambiguously means "empty."
- A deque supports adding and removing at both ends in O(1).
- To check whether the front negative has slid out of the window (`peek() == left`).
- It throws `NullPointerException`, from unboxing `null` into an `int`.
