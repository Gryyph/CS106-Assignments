package spellchecker;

import static sbcc.Core.*;

import java.util.*;

public class BinaryTree {

	public BinaryTreeNode root;
	StringBuilder sb = new StringBuilder();


	public BinaryTree() {
		root = null;
	}


	public void insert(String word) {
		root = add(root, word);
	}


	public BinaryTreeNode add(BinaryTreeNode node, String word) {
		word = word.trim();

		if (node == null) {
			node = new BinaryTreeNode(word);
			return node;
		}
		if (word.compareToIgnoreCase(node.value) < 0) {
			node.left = add(node.left, word);
		} else if (word.compareToIgnoreCase(node.value) > 0) {
			node.right = add(node.right, word);
		}
		return node;
	}


	public void preOrder(BinaryTreeNode node) {
		if (node != null) {
			sb.append(node.value + "\n");
			preOrder(node.left);
			preOrder(node.right);
		}

	}
}
