# Day 10: Stacks Fundamentals

## What is a stack?

A stack is a collection where you can only add and remove from **one end**, the **top**. The last thing you put in is the first thing you take out: **LIFO (Last In, First Out)**.

Analogy: a stack of plates. You put a new plate on top, and you take a plate off the top. You never pull one from the middle.

```
push(1), push(2), push(3)

  top → 3
        2
        1

pop() → returns 3, then top is 2
```

You've already met one stack: the **call stack** from Day 6 (recursion). Each function call is pushed on, and it's popped when the function returns. The most recent call always finishes first.

## The core operations

| Operation | What it does | Time |
|---|---|---|
| `push(x)` | Put `x` on top | O(1) |
| `pop()` | Remove and return the top | O(1) |
| `peek()` | Look at the top without removing it | O(1) |
| `isEmpty()` | Is there anything in it? | O(1) |

Every operation is O(1) because you only ever touch the top.

## How it's built underneath

A stack is an *idea* (LIFO rules), and you can build it on top of either:
- **An array:** keep an index to the top. Push writes at `top + 1`; pop reads at `top` and moves the index down.
- **A linked list (Day 9):** the head is the top. Push is "insert at front"; pop is "remove the head". Both are O(1).

## Java angle: use `ArrayDeque`, not `Stack`

Java has an old `Stack` class, but it's considered legacy. It extends `Vector`, which makes every method synchronized (slower), and it inherits methods like `insertElementAt(index)` that let you break LIFO rules. That's exactly the `Stack extends ArrayList` design problem from Java Day 5.

The recommended choice is `ArrayDeque`:

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);
stack.push(2);
int top = stack.peek();    // 2
int removed = stack.pop(); // 2
boolean empty = stack.isEmpty();
```

Careful: `pop()` on an empty `ArrayDeque` throws `NoSuchElementException`. Check `isEmpty()` first.

## When to reach for a stack

The signal: **you need to match or undo things in reverse order**. The most recent thing you saw matters most.
- Matching brackets: `(`, `[`, `{`
- Undo in an editor
- Evaluating expressions
- "Next greater element" style problems (monotonic stack, a later pattern)

## Worked example: Valid Parentheses

**Problem ([LeetCode #20](https://leetcode.com/problems/valid-parentheses/)):** Given a string of `()[]{}`, return true if every bracket is closed by the right type, in the right order.

**Idea:** push every opening bracket. When a closing bracket arrives, it must match the **most recent** unmatched opening bracket, which is the top of the stack.

```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;   // closing with nothing open
            char open = stack.pop();
            if ((c == ')' && open != '(') ||
                (c == ']' && open != '[') ||
                (c == '}' && open != '{')) {
                return false;                    // wrong type
            }
        }
    }
    return stack.isEmpty();   // leftover openers = unclosed
}
```

**Trace on `"{[()]}"`:**

| char | action | stack (bottom → top) |
|---|---|---|
| { | push | { |
| [ | push | { [ |
| ( | push | { [ ( |
| ) | pop `(`, matches | { [ |
| ] | pop `[`, matches | { |
| } | pop `{`, matches | (empty) |

Stack ends empty → valid.

There are **three** ways to fail, and each needs its own check:
1. A closing bracket arrives with nothing open (`"())"`) → `isEmpty()` check before popping.
2. The types don't match (`"(]"`) → the comparison.
3. Openers left over at the end (`"(("`) → `return stack.isEmpty()`.

O(n) time, O(n) space.

## Practice problem (unsolved)

**Min Stack ([LeetCode #155](https://leetcode.com/problems/min-stack/)):** Design a stack that supports `push`, `pop`, `top`, and `getMin`, all in O(1). Hint: keep a second stack that tracks the minimum at every level.

## Common pitfalls

- **Popping or peeking an empty stack.** Always check `isEmpty()` first.
- **Forgetting the end-of-input check.** In bracket problems, leftover openers mean invalid.
- **Using the legacy `Stack` class.** Prefer `ArrayDeque`.
- **Using a stack when order doesn't reverse.** If you need first-in-first-out, you want a queue (next).

## Retention Check

1. What does LIFO mean? Give a real-world example.
2. Why is every stack operation O(1)?
3. How would you build a stack on top of a linked list?
4. Why is `ArrayDeque` preferred over `Stack` in Java?
5. In Valid Parentheses, why does a closing bracket compare against the top of the stack?
6. What are the three different ways the input can be invalid?
7. What happens if you `pop()` an empty `ArrayDeque`?
8. How is the call stack from recursion an example of a stack?

**Key points to self-check against:**
- Last in, first out; a stack of plates.
- You only ever touch the top element.
- Use the head as the top: push = insert at front, pop = remove head.
- `Stack` extends `Vector`, so it's synchronized (slower) and inherits methods that break LIFO.
- The most recent unmatched opener is the one that must close first, and it's on top.
- Closing with nothing open, mismatched type, openers left over at the end.
- It throws `NoSuchElementException`.
- Each call is pushed; the most recent call finishes and is popped first.
