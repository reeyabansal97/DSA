# Arrays: Practice Problems, Beginner → Advanced

Organized by technique, so you build pattern recognition rather than just solving problems one-off. Work top to bottom.

## 1. Fundamentals (direct traversal, no special technique)

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Find the maximum element in an array | Beginner | — | [fundamentals/Q1MaxElementInArray.java](./fundamentals/Q1MaxElementInArray.java) |
| Find the second largest element (in one pass) | Beginner | — | [fundamentals/Q2SecondLargest.java](./fundamentals/Q2SecondLargest.java) |
| Check if an array is sorted | Beginner | — | [fundamentals/Q3CheckIfArrayIsSorted.java](./fundamentals/Q3CheckIfArrayIsSorted.java) |
| Move Zeroes (shift all zeros to the end, keep order) | Beginner | [LeetCode #283](https://leetcode.com/problems/move-zeroes/) | [fundamentals/Q4MoveZeroes.java](./fundamentals/Q4MoveZeroes.java) |
| Find the missing number from 1 to n | Beginner | [LeetCode #268](https://leetcode.com/problems/missing-number/) | [fundamentals/Q5MissingNumber.java](./fundamentals/Q5MissingNumber.java) |

## 2. Two-Pointer Technique

Use when you're comparing elements from both ends, or need to partition/merge in place.

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Reverse an array in place | Beginner | (done — Day 2) | [twopointer/Q1ReverseArrayInPlace.java](./twopointer/Q1ReverseArrayInPlace.java) |
| Remove Duplicates from Sorted Array | Beginner | [LeetCode #26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | [twopointer/Q2RemoveDuplicatesFromSortedArray.java](./twopointer/Q2RemoveDuplicatesFromSortedArray.java) |
| Two Sum II (input array is sorted) | Beginner-Intermediate | [LeetCode #167](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | [twopointer/Q3TwoSum2.java](./twopointer/Q3TwoSum2.java) |
| Valid Palindrome | Beginner-Intermediate | [LeetCode #125](https://leetcode.com/problems/valid-palindrome/) | [twopointer/Q4ValidPalindrome.java](./twopointer/Q4ValidPalindrome.java) |
| Container With Most Water | Intermediate | [LeetCode #11](https://leetcode.com/problems/container-with-most-water/) | [twopointer/Q5ContainerWithMostWater.java](./twopointer/Q5ContainerWithMostWater.java) |
| 3Sum | Intermediate | [LeetCode #15](https://leetcode.com/problems/3sum/) | [twopointer/Q6ThreeSum.java](./twopointer/Q6ThreeSum.java) |
| Sort Colors (Dutch National Flag, 3-way partition) | Intermediate | [LeetCode #75](https://leetcode.com/problems/sort-colors/) | [twopointer/Q7DutchNationalFlag.java](./twopointer/Q7DutchNationalFlag.java) |

## 3. Sliding Window Technique

Use when the problem involves a **contiguous** subarray and you're tracking a running sum/count/max as the window moves.

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Maximum Sum Subarray of Size K (fixed window) | Beginner | — (common interview warm-up, not on LeetCode by this name) | — |
| Minimum Size Subarray Sum (variable window) | Intermediate | [LeetCode #209](https://leetcode.com/problems/minimum-size-subarray-sum/) | — |
| Longest Substring Without Repeating Characters | Intermediate | [LeetCode #3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | — |
| Longest Repeating Character Replacement | Intermediate-Advanced | [LeetCode #424](https://leetcode.com/problems/longest-repeating-character-replacement/) | — |
| Sliding Window Maximum (needs a monotonic deque) | Advanced | [LeetCode #239](https://leetcode.com/problems/sliding-window-maximum/) | — |

## 4. Prefix Sum Technique

Use when you need repeated range-sum queries, or "does a subarray with property X exist" type problems.

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Range Sum Query - Immutable | Beginner | [LeetCode #303](https://leetcode.com/problems/range-sum-query-immutable/) | — |
| Find Pivot Index (equilibrium index) | Beginner-Intermediate | [LeetCode #724](https://leetcode.com/problems/find-pivot-index/) | — |
| Subarray Sum Equals K | Intermediate | [LeetCode #560](https://leetcode.com/problems/subarray-sum-equals-k/) | — |
| Product of Array Except Self | Intermediate | [LeetCode #238](https://leetcode.com/problems/product-of-array-except-self/) | — |

## 5. Kadane's Algorithm (running-max pattern)

A specific, very common DP-flavored array technique — worth knowing by name.

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Maximum Subarray (Kadane's algorithm) | Intermediate | [LeetCode #53](https://leetcode.com/problems/maximum-subarray/) | — |
| Maximum Product Subarray (Kadane's variant, tracking min AND max) | Intermediate-Advanced | [LeetCode #152](https://leetcode.com/problems/maximum-product-subarray/) | — |

## 6. Sorting-Based Array Problems

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Merge Intervals | Intermediate | [LeetCode #56](https://leetcode.com/problems/merge-intervals/) | — |
| Kth Largest Element in an Array | Intermediate | [LeetCode #215](https://leetcode.com/problems/kth-largest-element-in-an-array/) | — |
| Merge Sorted Array (in place) | Beginner-Intermediate | [LeetCode #88](https://leetcode.com/problems/merge-sorted-array/) | — |

## 7. Advanced / Combined Techniques

| Problem | Difficulty | LeetCode | My Solution |
|---|---|---|---|
| Rotate Array (in place, O(1) space) | Intermediate | [LeetCode #189](https://leetcode.com/problems/rotate-array/) | — |
| Trapping Rain Water | Advanced | [LeetCode #42](https://leetcode.com/problems/trapping-rain-water/) | — |
| Next Permutation | Advanced | [LeetCode #31](https://leetcode.com/problems/next-permutation/) | — |
| Find First and Last Position of Element in Sorted Array (binary search variant) | Intermediate | [LeetCode #34](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | — |
| Median of Two Sorted Arrays | Advanced | [LeetCode #4](https://leetcode.com/problems/median-of-two-sorted-arrays/) | — |

## Progress

**Solved: 12 / 26** — all of Fundamentals and Two-Pointer complete. Next up: Sliding Window.

## Suggested order to attempt

1. Do all of **Fundamentals** first — these build comfort with basic array manipulation. ✅ Done
2. Move to **Two-Pointer** — this is the single most-reused pattern across all of DSA, worth over-practicing. ✅ Done
3. **Sliding Window** next — it's a direct extension of two-pointer for contiguous-subarray problems.
4. **Prefix Sum** — a different angle, useful once two-pointer/window feel natural.
5. **Kadane's** — a named special case worth memorizing on its own.
6. **Sorting-based** and **Advanced** last — these combine earlier techniques or need extra tricks.
