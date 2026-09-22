# Day 3: Strings — Fundamentals & Common Operations

## What is a string and what makes it different from an array?

A string is, under the hood, a sequence of characters — conceptually very close to a `char[]` array, which is why the array techniques you already know (two-pointer, sliding window) apply directly to strings too. The key difference in Java: **`String` is immutable.**

```java
String s = "hello";
s.toUpperCase();       // does NOT change s
System.out.println(s); // still prints "hello"

String upper = s.toUpperCase(); // this is how you actually use the result
```

Every method that looks like it "modifies" a string actually returns a **brand new** `String` object. The original is never touched. This single fact explains a huge number of beginner bugs and performance mistakes.

## Why immutability matters for performance

Because every "modification" creates a new object, doing this in a loop is a hidden performance trap:

```java
// BAD: O(n^2) time overall
String result = "";
for (int i = 0; i < n; i++) {
    result += someChar;   // creates a NEW string every single iteration
}
```

Each `+=` copies the entire string built so far into a new, slightly longer string. Over `n` iterations, that's `1 + 2 + 3 + ... + n` characters copied in total — which is O(n²), not O(n).

The fix is `StringBuilder`, which is mutable and designed exactly for this:

```java
// GOOD: O(n) time overall
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(someChar);   // grows in place, amortized O(1) per append
}
String result = sb.toString();
```

## Complexity of common operations

| Operation | Time | Why |
|---|---|---|
| `charAt(i)` | O(1) | strings are backed by a contiguous array internally |
| `length()` | O(1) | length is stored, not recomputed |
| `s1 + s2` (concatenation) | O(n) | must copy both strings into a new one |
| `substring()` | O(n) | (in modern Java) copies the relevant characters into a new array |
| `StringBuilder.append()` | O(1) amortized | same idea as `ArrayList` resizing |
| Comparing with `.equals()` | O(n) | must check characters one by one until a mismatch or the end |

## A critical gotcha: `==` vs `.equals()`

```java
String a = new String("hi");
String b = new String("hi");
System.out.println(a == b);        // false! different objects in memory
System.out.println(a.equals(b));   // true — compares actual characters
```

`==` checks whether two references point to the *same object in memory*. `.equals()` checks whether the *contents* are the same. For strings, you almost always want `.equals()`. (String literals like `"hi" == "hi"` can sometimes return `true` due to Java's string pool caching identical literals — but relying on this is fragile and considered bad practice; always use `.equals()`.)

## Java angle: checking if a string is a palindrome

**Problem:** Given a string, determine if it reads the same forwards and backwards.

```java
public class PalindromeCheck {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

**Walkthrough:** This is the exact same two-pointer pattern from Day 2's array reversal — one pointer starts at the front, one at the back, and they move toward each other comparing characters. The moment they mismatch, it's not a palindrome. If they meet without ever mismatching, it is. O(n) time, O(1) extra space.

## Practice problem (unsolved)

Given two strings, determine if they are **anagrams** of each other (contain exactly the same characters, same counts, possibly in a different order) — e.g., `"listen"` and `"silent"`. Try solving it using a character-frequency count (hint: an `int[26]` array works well if you're only dealing with lowercase letters).

## Common pitfalls

- **String concatenation in a loop** using `+` or `+=` — silently turns an O(n) algorithm into O(n²). Use `StringBuilder` instead.
- **Using `==` to compare string content** — this compares object identity, not character content, and can pass by coincidence in small test cases (due to string pool caching) while failing in real usage.
- **Forgetting strings are immutable** — calling `s.trim()` or `s.replace(...)` without capturing the return value does nothing to `s` itself.

## Retention Check

1. Why does building a string with repeated `+=` in a loop cost O(n²) instead of O(n)?
2. What data structure should you use instead of `+=` to build a string efficiently in a loop, and why is it faster?
3. What's the difference between what `==` checks and what `.equals()` checks for strings?
4. Why does calling `s.toUpperCase()` without assigning the result do nothing useful?
5. Why can you reuse the array two-pointer technique directly on strings?
6. What is the time complexity of `charAt(i)`, and why?
7. If you call `s.trim()`, does it modify `s` in place? Why or why not?
8. Why is `substring()` O(n) rather than O(1) in modern Java?

**Key points to self-check against:**
- Because each concatenation copies the entire string built so far into a new object; the copies add up to `1+2+...+n`.
- `StringBuilder` — it's mutable and grows in place instead of allocating a new object on every append.
- `==` compares whether two references point to the same object in memory; `.equals()` compares actual character content.
- Because `String` is immutable — `toUpperCase()` returns a *new* string; it never changes the original.
- Because a string is essentially a `char` array under the hood, so index-based techniques like two-pointer work identically.
- O(1) — characters are stored contiguously, so any index can be accessed directly via arithmetic, just like an array.
- No — `String` methods never mutate the original; you must capture the return value (`s = s.trim();`).
- Because modern Java copies the requested characters into a new array rather than sharing memory with the original string (older Java versions used to share memory, which had its own memory-leak pitfalls).
