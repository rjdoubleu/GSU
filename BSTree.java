//BSTree (Assignment 6)
//Professor: 	Jaman L. Bhola
//Author:    	Ryan Walden [900899950]
/* Desc: A program designed to translate an unsorted LinkedList of Integers into a tree
 *
 * Prob: Using a recursive method to create a sorted tree from an unsorted LinkedList
 *
 * Solu: Trees are organized by parents and children, the addNode method determines whether
 * 		 a new node will be a leftChild or a rightChild by comparing its value to the root node.
 * 
 * Data: This program utilizes LinkedList and a Binary Search Tree data structure created within 
 * 
 * Purp: The program is designed to use the attributes of a binary search tree to sort an unsorted
 * 		 LinkedList of integers
 */

import java.util.LinkedList;
import java.util.Random;

public class BSTree {
	Node root;

	public void addNode(int key) {
		Node newNode = new Node(key);
		
		if(root == null){
			root = newNode;
		}else{
			Node currNode = root; // set root as the currNode to traverse the tree
			Node parent;
			while(true){
				parent = currNode;
				// check if the new node should be a left or right child
				if(key < currNode.key){
					currNode = currNode.leftChild;
					if(currNode == null){ 
						parent.leftChild = newNode;
						return;
					}
				}else{
					currNode = currNode.rightChild;
					if(currNode == null){
						parent.rightChild = newNode;
						return; // All Done
					}
				}
			}
		}
	}

	//Prints tree in ascending order
	public void inOrderTraverseTree(Node currNode) {
		if(currNode != null){
			inOrderTraverseTree(currNode.leftChild);
			System.out.print(currNode);
			inOrderTraverseTree(currNode.rightChild);
		}
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> data = new LinkedList<Integer>();
		BSTree tree = new BSTree();
		Random r = new Random();
		String ldat = "[";
		
		for(int i=0;i<100;i++)
			data.add(r.nextInt(98)+1);
		
		
		for(int d : data){
			ldat += d + "][";
			tree.addNode(d);
		}
		ldat = ldat.substring(0, ldat.length()-1);
		System.out.print("LinkedList (Unsorted) :  " + ldat + "\nBSTree (Tranversed)   :  ");
		tree.inOrderTraverseTree(tree.root);
	}
}

class Node{
	int key;

	Node leftChild;
	Node rightChild;

	Node(int key){
		this.key = key;
	}

	public String toString(){return"[" + key + "]";}
}
