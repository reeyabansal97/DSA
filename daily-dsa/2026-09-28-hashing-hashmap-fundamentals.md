# Day 8: Hashing & HashMap Fundamentals

## What problem does it solve?

You've been using `HashMap` in your sliding window problems to count characters. But why is `map.get(key)` fast? If you stored key-value pairs in a plain list, finding a key would mean checking every entry: O(n). A hash map does it in **O(1) on average**, no matter how many entries it holds.

## The core idea: turn a key into an array index

An array lookup is O(1) if you know the index (DSA Day 2). A hash map uses that fact:

1. Take the key and run it through a **hash function**, which turns it into a number (the **hash code**).
2. Squash that number into a valid array position: roughly `index = hash % arraySize`.
3. Store the entry at that position. Each slot in the array is called a **bucket**.

To look a key up later, repeat the same steps and go straight to that bucket. No scanning.

```
key "apple" → hashCode() → 93029210 → % 16 → bucket 10
key "mango" → hashCode() → 103666243 → % 16 → bucket 3
```

(Java's actual `HashMap` also mixes the hash bits a little before picking the bucket, to spread keys more evenly, but the idea is the same.)

## Collisions: when two keys land in the same bucket

The array has a limited number of buckets, and there are infinitely many possible keys. So sometimes two different keys map to the same bucket. That's a **collision**, and it's normal, not a bug.

Java's `HashMap` handles it by **chaining**: each bucket holds a small linked list of entries. On lookup, it goes to the bucket and walks that short list, comparing keys with `.equals()` until it finds the match.

Since Java 8, if one bucket's list grows long (more than 8 entries, once the table is big enough), Java converts it into a balanced tree, so even a badly-crowded bucket is searched in O(log n) instead of O(n).

## Resizing: keeping buckets short

If you keep adding entries to a fixed number of buckets, the chains get longer and lookups slow down. So `HashMap` tracks its **load factor**: entries ÷ buckets. The default threshold is **0.75**. When the map gets 75% full, it roughly **doubles** its bucket array and moves every entry to its new position (**rehashing**).

A single resize is O(n), but it happens rarely enough that inserts are still **amortized O(1)**, the same idea as `ArrayList` growing (DSA Day 2).

## Complexity

| Operation | Average | Worst case |
|---|---|---|
| `put`, `get`, `containsKey`, `remove` | O(1) | O(log n) since Java 8 (O(n) before) |
| Iterate over all entries | O(n + buckets) | same |

The worst case happens when many keys collide into the same bucket, usually because of a poor `hashCode()`.

## The rule that makes it all work: `equals()` and `hashCode()`

`HashMap` relies on two methods from every key:
- `hashCode()` decides **which bucket** to look in.
- `equals()` decides **which entry in that bucket** is the match.

**The contract:** if two objects are `equals()`, they **must** return the same `hashCode()`.

Break it, and the map breaks silently:

```java
class Point {
    int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return x == p.x && y == p.y;
    }
    // hashCode() NOT overridden: bug
}

Map<Point, String> map = new HashMap<>();
map.put(new Point(1, 2), "A");
map.get(new Point(1, 2));   // returns null!
```

The two `Point(1, 2)` objects are equal, but without an overridden `hashCode()`, each object gets a different default hash code. So `get` looks in a **different bucket** and never even reaches the `equals()` check. The fix is to override `hashCode()` alongside `equals()`, e.g. `return Objects.hash(x, y);`.

`String` and `Integer` already implement both correctly. That's why they work as keys out of the box.

## Java angle: the frequency-count pattern

The pattern you've been using in your window problems:

```java
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

Or the same thing in one call: `freq.merge(c, 1, Integer::sum);`

**When a plain array beats a HashMap:** if keys are only lowercase letters, use `int[] count = new int[26]` and index with `c - 'a'`. No hashing, no boxing `char` into `Character`, and less memory. Use a `HashMap` when the set of possible keys is large or unknown.

## Practice problem (unsolved)

**Two Sum ([LeetCode #1](https://leetcode.com/problems/two-sum/)):** Given an unsorted array and a target, return the indices of two numbers that add up to the target. You solved the *sorted* version with two pointers. Now do it in one pass with a HashMap: for each number, check whether `target - number` is already in the map.

## Common pitfalls

- **Overriding `equals()` without `hashCode()`.** Equal keys land in different buckets, so lookups fail.
- **Mutating a key after putting it in the map.** If the key's fields change, its hash code changes, and the map looks in the wrong bucket. Keys should be immutable, which is another reason `String` makes a great key.
- **Assuming a `HashMap` keeps insertion order.** It doesn't. Use `LinkedHashMap` for insertion order, or `TreeMap` for sorted keys (O(log n) operations).
- **Treating O(1) as a guarantee.** It's an average. A bad `hashCode()` that sends everything to one bucket degrades performance badly.

## Interviewer follow-ups

**"How does HashMap work internally?"** An array of buckets. The key's `hashCode()` picks the bucket; collisions are chained in a linked list (converted to a balanced tree when a bucket gets long); `equals()` finds the exact entry. When the load factor passes 0.75, the array doubles and entries are rehashed.

**"What happens if two keys have the same hashCode?"** They go to the same bucket, and `equals()` tells them apart. Same hash code does **not** mean equal; equal does mean same hash code.

## Retention Check

1. How does a hash map turn a key into an array position?
2. What is a collision, and how does Java's `HashMap` handle it?
3. What changed in Java 8 for buckets with many collisions?
4. What is the load factor, and what happens when it's exceeded?
5. Why are inserts still amortized O(1) even though resizing is O(n)?
6. State the `equals()`/`hashCode()` contract.
7. In the `Point` example, why does `get` return `null`?
8. When should you use `int[26]` instead of a `HashMap` for counting?

**Key points to self-check against:**
- Hash function → hash code → reduced to an index (roughly `hash % size`) → that bucket.
- Two keys map to the same bucket; Java chains them in a linked list within the bucket and uses `equals()` to find the right one.
- Long chains (more than 8, once the table is large enough) turn into balanced trees, so worst case is O(log n).
- Entries ÷ buckets; past 0.75 the bucket array roughly doubles and every entry is rehashed.
- Resizes happen rarely; spread across all inserts, the average cost per insert stays constant.
- Equal objects must have equal hash codes (not necessarily the reverse).
- The two equal `Point`s have different default hash codes, so `get` searches a different bucket.
- When keys come from a small known range, like lowercase letters.
