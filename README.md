# Huffman File Compressor

A Java-based lossless text compression system implemented using Huffman Coding.

The project uses a greedy algorithm approach with a priority queue to build an optimal binary tree and generate variable-length binary codes for characters. It supports compression and decompression of text while maintaining the original data.

---

## Problem Statement

Storing text data efficiently requires reducing the number of bits used for representation.

Huffman Coding is a lossless compression technique that assigns shorter binary codes to frequently occurring characters and longer codes to less frequent characters.

This project implements Huffman Coding to compress text data and reconstruct the original message through decompression.

---

## Features

- Builds a Huffman Tree from character frequencies
- Generates unique binary codes for each character
- Compresses text into binary representation
- Decompresses binary data back to original text
- Uses PriorityQueue for efficient tree construction
- Implements lossless compression

---

## Algorithm / Approach

### Step 1: Frequency Calculation

The frequency of every character in the input text is calculated using a HashMap.
### Step 1: Frequency Calculation

The frequency of every character in the input text is calculated using a HashMap.

Example:
A -> 5
B -> 2
C -> 1

### Step 2: Build Huffman Tree

A Min Heap is created using character frequencies.

The two nodes with the smallest frequencies are repeatedly removed and merged into a new parent node until only one root node remains.

---

### Step 3: Generate Binary Codes

The Huffman Tree is traversed:

- Left traversal adds `0`
- Right traversal adds `1`

Each character receives a unique binary code.

Example:
A -> 0
B -> 10
C -> 11
---

### Step 4: Compression

Each character in the original text is replaced with its corresponding Huffman binary code.

---

### Step 5: Decompression

The compressed binary string is traversed and matched with stored Huffman codes to reconstruct the original text.

---
## Data Structures Used

- HashMap
  - Stores character frequencies
  - Stores character-to-code mapping

- PriorityQueue
  - Used as Min Heap for building Huffman Tree

- Binary Tree
  - Represents Huffman encoding structure

- Custom Class
  - Represents tree nodes containing character and frequency

---

## Complexity Analysis
Let:
N = Number of characters in the input text

### Frequency Calculation

Time Complexity:
O(N)

### Building Huffman Tree

Time Complexity:
O(K log K)
where K is the number of unique characters.

### Generating Codes

Time Complexity:
O(K)

### Compression

Time Complexity:
O(N)

Overall:
Time Complexity:
O(N + K log K)

Space Complexity:
O(K)
