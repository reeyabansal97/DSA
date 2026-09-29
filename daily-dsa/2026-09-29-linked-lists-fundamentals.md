# Day 9: Linked Lists Fundamentals

## What problem does it solve?

Arrays (Day 2) give you O(1) access by index, but inserting or deleting at the front or middle costs O(n), because every later element has to shift. A **linked list** makes the opposite trade: inserting or removing a node is O(1) *once you're at the right spot*, but reaching position `i` costs O(n).

## How it's built

A linked list is a chain of **nodes**. Each node holds a value and a reference to the next node:

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
```

```
head
 ↓
[1 | •]──►[2 | •]──►[3 | •]──►null
```

- **head**: a reference to the first node. If you lose it, you lose the whole list.
- The last node's `next` is `null`, which marks the end.

Unlike an array, the nodes are **not contiguous in memory**. Each one is a separate object somewhere on the heap (Java Day 2), connected only by references. That's why there's no `list[5]` shortcut: the address formula from Day 2 needs contiguous memory.

## Arrays vs linked lists

| Operation | Array | Linked list |
|---|---|---|
| Access by index | O(1) | O(n), walk from head |
| Insert/delete at front | O(n), shift everything | O(1) |
| Insert/delete in middle, given a reference to the previous node | O(n) | O(1) |
| Search for a value | O(n) | O(n) |
| Extra memory per element | None | One `next` reference per node |

## The three core operations

**1. Traverse:** walk from head until `null`.

```java
ListNode curr = head;
while (curr != null) {
    System.out.print(curr.val + " ");
    curr = curr.next;
}
```

Use a separate `curr` variable. If you move `head` itself, you lose your only reference to the start.

**2. Insert at the front:** O(1).

```java
ListNode newNode = new ListNode(0);
newNode.next = head;   // point new node at old first node
head = newNode;        // new node becomes the head
```

**Order matters.** If you did `head = newNode` first, you'd lose the reference to the old list.

**3. Delete a node:** make the previous node skip over it.

```java
// delete the node after 'prev'
prev.next = prev.next.next;
```

The skipped node now has nothing pointing to it, so the garbage collector cleans it up.

## The dummy node trick

Deleting the **head** is a special case, since there's no previous node. A **dummy node** placed before the head removes that special case:

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// ... now every real node, including the head, has a previous node ...
return dummy.next;   // the real head (which may have changed)
```

You'll use this constantly in linked list problems.

## Worked example: Reverse a Linked List

**Problem ([LeetCode #206](https://leetcode.com/problems/reverse-linked-list/)):** Reverse a singly linked list and return the new head.

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;   // 1. save the rest of the list
        curr.next = prev;            // 2. flip this node's arrow backward
        prev = curr;                 // 3. move prev forward
        curr = next;                 // 4. move curr forward
    }
    return prev;   // prev ends on the old last node, the new head
}
```

**Trace on `1 → 2 → 3`:**

| Step | prev | curr | List state |
|---|---|---|---|
| start | null | 1 | `1→2→3` |
| after node 1 | 1 | 2 | `null←1`, `2→3` |
| after node 2 | 2 | 3 | `null←1←2`, `3` |
| after node 3 | 3 | null | `null←1←2←3` |

Return `prev` = node 3. Step 1 is the key: once you flip `curr.next`, you've lost the path forward, unless you saved it first.

O(n) time, O(1) space. It's the same two-variable-walking idea as two pointers, applied to references instead of indices.

## Preview: fast and slow pointers

Move one pointer 1 step at a time and another 2 steps. When the fast one reaches the end, the slow one is at the **middle**. And if the list has a **cycle**, the fast pointer eventually laps the slow one and they meet (Floyd's cycle detection). This is its own pattern, and it'll show up in your linked list practice problems.

## Practice problem (unsolved)

**Middle of the Linked List ([LeetCode #876](https://leetcode.com/problems/middle-of-the-linked-list/)):** Return the middle node. Try the fast/slow pointer approach, so you don't need to count the length first.

## Common pitfalls

- **Moving `head` during traversal.** You lose the start of the list. Use `curr`.
- **Wrong pointer order when rewiring.** Always save or link the rest of the list before overwriting a `next` reference.
- **NullPointerException on `curr.next.next`.** Before going two steps ahead, check that `curr.next` isn't `null`.
- **Forgetting the empty list or single-node list.** Test `head == null` and a one-node list every time.

## Retention Check

1. Why is accessing index `i` O(n) in a linked list but O(1) in an array?
2. What does `head` represent, and why must you never lose it?
3. Why is inserting at the front O(1)? Write the two lines, in the correct order.
4. How do you delete the node after `prev`?
5. What problem does a dummy node solve?
6. In reverseList, why must you save `curr.next` before changing it?
7. Why does reverseList return `prev` instead of `curr`?
8. How can fast and slow pointers find the middle without knowing the length?

**Key points to self-check against:**
- Nodes aren't contiguous, so there's no address formula; you have to walk from the head.
- The first node; it's your only entry point to the whole list.
- No shifting is needed: `newNode.next = head;` then `head = newNode;`.
- `prev.next = prev.next.next;`
- It gives the real head a previous node, so deleting or changing the head needs no special case.
- Once you flip it, you lose the reference to the rest of the list.
- The loop ends when `curr` is `null`; `prev` is on the old last node, which is the new head.
- Fast moves 2 steps per slow's 1 step, so when fast reaches the end, slow has covered half.
