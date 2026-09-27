# Strings: Practice Problems, Beginner → Advanced

Organized by technique, so you build pattern recognition rather than just solving problems one-off. Work top to bottom. Many of these reuse techniques you already practiced on arrays — strings are just `char` arrays with extra rules.

## 1. Fundamentals (direct traversal, no special technique)

| Problem | Difficulty | LeetCode |
|---|---|---|
| Reverse a string (in place, char array) | Beginner | [LeetCode #344](https://leetcode.com/problems/reverse-string/) |
| Count vowels/consonants in a string | Beginner | — |
| Check if all characters in a string are unique | Beginner | — |
| Roman to Integer | Beginner-Intermediate | [LeetCode #13](https://leetcode.com/problems/roman-to-integer/) |

## 2. Two-Pointer Technique

Same pattern as array reversal — one pointer from each end, moving inward.

| Problem | Difficulty | LeetCode |
|---|---|---|
| Valid Palindrome | Beginner-Intermediate | [LeetCode #125](https://leetcode.com/problems/valid-palindrome/) |
| Reverse Vowels of a String | Beginner-Intermediate | [LeetCode #345](https://leetcode.com/problems/reverse-vowels-of-a-string/) |
| Valid Palindrome II (allowed to delete at most one character) | Intermediate | [LeetCode #680](https://leetcode.com/problems/valid-palindrome-ii/) |
| Reverse Words in a String | Intermediate | [LeetCode #151](https://leetcode.com/problems/reverse-words-in-a-string/) |

## 3. Hashing / Frequency Counting

Use when the problem is about character counts, duplicates, or "does this contain the same characters as that."

| Problem | Difficulty | LeetCode |
|---|---|---|
| Valid Anagram | Beginner | [LeetCode #242](https://leetcode.com/problems/valid-anagram/) |
| First Unique Character in a String | Beginner-Intermediate | [LeetCode #387](https://leetcode.com/problems/first-unique-character-in-a-string/) |
| Ransom Note | Beginner-Intermediate | [LeetCode #383](https://leetcode.com/problems/ransom-note/) |
| Group Anagrams | Intermediate | [LeetCode #49](https://leetcode.com/problems/group-anagrams/) |

## 4. Sliding Window Technique

Use for problems about a **contiguous** substring with some running property (length, character set, count).

| Problem | Difficulty | LeetCode |
|---|---|---|
| Longest Substring Without Repeating Characters | Intermediate | [LeetCode #3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) |
| Find All Anagrams in a String | Intermediate | [LeetCode #438](https://leetcode.com/problems/find-all-anagrams-in-a-string/) |
| Permutation in String | Intermediate | [LeetCode #567](https://leetcode.com/problems/permutation-in-string/) |
| Longest Repeating Character Replacement | Intermediate-Advanced | [LeetCode #424](https://leetcode.com/problems/longest-repeating-character-replacement/) |
| Minimum Window Substring | Advanced | [LeetCode #76](https://leetcode.com/problems/minimum-window-substring/) |

## 5. String Building / Manipulation

| Problem | Difficulty | LeetCode |
|---|---|---|
| String Compression (in place) | Intermediate | [LeetCode #443](https://leetcode.com/problems/string-compression/) |
| Zigzag Conversion | Intermediate | [LeetCode #6](https://leetcode.com/problems/zigzag-conversion/) |
| Integer to Roman | Intermediate | [LeetCode #12](https://leetcode.com/problems/integer-to-roman/) |

## 6. Pattern Matching / Substring Search

| Problem | Difficulty | LeetCode |
|---|---|---|
| Find the Index of the First Occurrence in a String (implement `strStr()`) | Intermediate | [LeetCode #28](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) |
| Longest Palindromic Substring (expand around center) | Intermediate-Advanced | [LeetCode #5](https://leetcode.com/problems/longest-palindromic-substring/) |
| Repeated Substring Pattern | Intermediate | [LeetCode #459](https://leetcode.com/problems/repeated-substring-pattern/) |

## 7. Dynamic Programming on Strings (Advanced)

| Problem | Difficulty | LeetCode |
|---|---|---|
| Longest Common Subsequence | Advanced | [LeetCode #1143](https://leetcode.com/problems/longest-common-subsequence/) |
| Edit Distance | Advanced | [LeetCode #72](https://leetcode.com/problems/edit-distance/) |
| Longest Palindromic Subsequence | Advanced | [LeetCode #516](https://leetcode.com/problems/longest-palindromic-subsequence/) |
| Decode Ways | Advanced | [LeetCode #91](https://leetcode.com/problems/decode-ways/) |

## 8. Backtracking on Strings (Advanced)

| Problem | Difficulty | LeetCode |
|---|---|---|
| Letter Combinations of a Phone Number | Intermediate-Advanced | [LeetCode #17](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) |
| Generate Parentheses | Advanced | [LeetCode #22](https://leetcode.com/problems/generate-parentheses/) |
| Palindrome Partitioning | Advanced | [LeetCode #131](https://leetcode.com/problems/palindrome-partitioning/) |

## Suggested order to attempt

1. **Fundamentals** first — comfort with basic string/char-array manipulation.
2. **Two-Pointer** next — you already know this pattern from arrays (Day 2's reverse) and Day 3's palindrome check; extend it here.
3. **Hashing/Frequency Counting** — the anagram-style problems from Day 3's unsolved practice problem live here.
4. **Sliding Window** — direct extension of two-pointer for substring problems; you'll recognize the pattern from the array version of this technique.
5. **String Building** and **Pattern Matching** — more specialized, still Intermediate.
6. **DP on Strings** and **Backtracking** last — these are genuinely advanced and usually come later in interview prep, after you're comfortable with basic DP and recursion (topics still ahead in the rotation).

Log solved ones in `solved-problems/README.md` the same way as the array problems, ideally under a `solved-problems/strings/` folder per the structure we discussed.
