import java.util.*;

class HuffmanNode {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}

// Creating an explicit custom comparator class
class NodeComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode n1, HuffmanNode n2) {
        // Returns negative if n1 is smaller, positive if n2 is smaller
        return n1.frequency - n2.frequency;
    }
}

public class TextCompressor {

    // Basic HashMaps for tracking binary mappings
    static Map<Character, String> charToCode = new HashMap<>();
    static Map<String, Character> codeToChar = new HashMap<>();

    // --- 1. BUILD THE HUFFMAN TREE ---
    public static HuffmanNode buildTree(String text) {
        // Step A: Count frequencies using a simple loop
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!freqMap.containsKey(c)) {
                freqMap.put(c, 1);
            } else {
                freqMap.put(c, freqMap.get(c) + 1);
            }
        }

        // Step B: Initialize PriorityQueue using our custom student NodeComparator
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new NodeComparator());

        // Push all character nodes into the heap
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minHeap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Step C: Combine nodes until only the root is left
        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            // Create a parent dummy node '#' combining frequencies
            HuffmanNode parent = new HuffmanNode('#', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            minHeap.add(parent);
        }

        return minHeap.poll();
    }

    // --- 2. GENERATE CODES (Simple Tree Traversal) ---
    public static void generateCodes(HuffmanNode root, String currentCode) {
        if (root == null) return;

        // If it's a leaf node, we map the characters to the binary string
        if (root.left == null && root.right == null) {
            charToCode.put(root.data, currentCode);
            codeToChar.put(currentCode, root.data);
            return;
        }

        // Go left (add 0), Go right (add 1)
        generateCodes(root.left, currentCode + "0");
        generateCodes(root.right, currentCode + "1");
    }

    // --- 3. COMPRESSION METHOD ---
    public static String compress(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            result += charToCode.get(c); // Basic string concatenation
        }
        return result;
    }

    // --- 4. DECOMPRESSION METHOD ---
    public static String decompress(String binaryText) {
        String finalOutput = "";
        String tempBuffer = "";

        for (int i = 0; i < binaryText.length(); i++) {
            tempBuffer += binaryText.charAt(i);
            
            // If the current sequence of 0s and 1s matches a letter, swap it out
            if (codeToChar.containsKey(tempBuffer)) {
                finalOutput += codeToChar.get(tempBuffer);
                tempBuffer = ""; // Reset the buffer string
            }
        }
        return finalOutput;
    }

    // --- MAIN METHOD FOR LAB VIVA/TESTING ---
    public static void main(String[] args) {
        String inputMessage = "HELLO BRO THIS IS MY CODES";
        
        System.out.println("--- Huffman File Compressor Assignment ---");
        System.out.println("Original String: " + inputMessage);
        
        // Step 1: Run tree building logic
        HuffmanNode treeRoot = buildTree(inputMessage);
        generateCodes(treeRoot, "");

        // Step 2: Compress text
        String binaryResult = compress(inputMessage);
        System.out.println("Compressed Binary Bits: " + binaryResult);

        // Step 3: Decompress back
        String originalResult = decompress(binaryResult);
        System.out.println("Decompressed String Output: " + originalResult);
        
        System.out.println("Is conversion successful? " + inputMessage.equals(originalResult));
    }
}