import java.util.*;
public class binaryTree{
	public static int t_nodes;
	public static int arr[];
	public static List<Node> nodes; //this will act as a reverse stack
    Node root;
    
    binaryTree() { root = null; } 
  
    public static void main(String[] args) { 
    	Scanner in = new Scanner(System.in);
    	System.out.println("Enter the number of elements in your array: ");
    	t_nodes = in.nextInt();
    	
    	nodes = new ArrayList<Node>(t_nodes-1);
    	arr = new int[t_nodes];
    	
    	for(int i=0;i<t_nodes;i++) {
    		System.out.printf("Enter the value for element %d:\n", i);
    		int elm = in.nextInt();
    		arr[i] = elm;
    	}
    	
    	in.close();
    	
        binaryTree tree = new binaryTree();
        tree.root = new Node(arr[0]);
        nodes.add(tree.root);
        t_nodes -= 1;
        buildTree(tree.root);
        System.out.println("The right left node key = " + tree.root.right.left.key);
    }
    
    public static void buildTree(Node k) {
    	if(t_nodes-2>=0) {
    		int a = arr.length - t_nodes;
    		int b = arr.length - t_nodes + 1;
    		Node x = new Node(arr[a]);
    		Node y = new Node(arr[b]);
    		
    		if(x.key>y.key) {
    			k.right = x;
    			k.left  = y;
    			nodes.add(y);
        		nodes.add(x);
    		}else {
    			k.right = y;
    			k.left  = x;
    			nodes.add(x);
        		nodes.add(y);
    		}
      		
    		t_nodes -= 2;
      		
    		nodes.remove(0); //acts like a stack in reverse
    		
    		if(t_nodes!=0) buildTree(nodes.get(0));		
    	}else k.left = new Node(arr[arr.length-1]); 
    }
} 