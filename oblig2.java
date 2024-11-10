import java.util.Scanner;

public class oblig2 {
    
    public static void main(String[] args){
        
        BinarySearchTree tree = new BinarySearchTree();
        
        // testdata
        tree.addItem(100);
        tree.addItem(35);
        tree.addItem(120);
        tree.addItem(10);
        tree.addItem(40);
        tree.addItem(110);
        tree.addItem(140);

        Scanner scanner = new Scanner(System.in);
        int input;
        
        do{
            System.out.println("\nMenu select (1-6)");
            System.out.println("1. Add node to tree.");
            System.out.println("2. Find smallest value in tree.");
            System.out.println("3. Find largest value in tree.");
            System.out.println("4. Remove biggest value in tree.");
            System.out.println("5. Find instance in tree.");
            System.out.println("6. Exit.");
            
            input = scanner.nextInt();

            switch(input){
                case 1:
                    System.out.println("Enter value to add.");
                    int data = scanner.nextInt();
                    tree.addItem(data);
                    break;
                case 2:
                    System.out.println("Smallest element in tree is: " + tree.findSmallest());
                    break;
                case 3:
                    System.out.println("Biggest element in tree is: " + tree.findBiggest());
                    break;
                case 4:
                    System.out.println("Biggest element removed is: " + tree.removeBiggest());
                    break;
                case 5:
                    System.out.println("Enter target to find in tree.");
                    int target = scanner.nextInt();
                    int result = tree.findInstance(target);
                    if(result == 1) {
                        System.out.println("Target " + target + " is found in the tree.");
                    } else {
                        System.out.println("Target " + target + " is not found in the tree.");
                    }
                    break;
                default:
                    System.out.println("Error please enter 1-6.");
                    

            }
            
            

        }while(input != 6);


        scanner.close(); 

    }



}

class Node {

    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
        left = null;
        right = null;
    }

}


class BinarySearchTree{

    Node root = null;

    public void addItem(int data){
        Node newNode = new Node(data);

        if(root == null){
            
            root = newNode; // hvis treet er tomt blir nye noden til root

        }else{
            
            Node temp = root;

            while(true){
                if(newNode.data < temp.data){ // hvis nynode er mindre enn node i treet går vi til venstre
                    if(temp.left == null){
                        temp.left = newNode; 
                        break;
                    }else{
                        temp = temp.left;
                    }
                }else if(newNode.data > temp.data){
                    if(temp.right == null){
                        temp.right = newNode;
                        break;
                    }else{
                        temp = temp.right;
                    }
                }else{
                    System.out.println("Error. Can't have two nodes of the same value");
                    break;
                }
            }
        }
    }

    public int findSmallest(){
    
        Node temp = root;

        if(temp == null){
            System.out.println("The tree is empty");
            return -1;
        }

        while(temp.left != null){
            temp = temp.left;
        }
        
        return temp.data;
    }

    public int findBiggest(){

        Node temp = root;

        if(temp == null){
            System.out.println("The tree is empty");
            return -1;
        }

        while(temp.right != null){
            temp = temp.right;
        }

        return temp.data;
    }

    public int removeBiggest(){
        
        Node parent = null;
        Node current = root;
        int value;

        if(root == null){
            System.out.println("The tree is empty");
            return -1;
        }

        if(current.right == null){
            value = current.data;
            root = current.left;
            return value;
        }

        while(current.right != null){
            parent = current;
            current = current.right;
        }

        value = current.data;

        if(current.left != null){ // tar hennsyn hvis det er en venstre barn til foreldrenoden
            parent.right = current.left;
        }else{
            parent.right = null;
        }
        return value;
        
    }

    public int findInstance(int target){
        
        Node temp = root;

        if(root == null){
            System.out.println("The tree is empty");
            return -1;
        }

        while(temp != null){
            if(target < temp.data){
                temp = temp.left;
            } else if(target > temp.data){
                temp = temp.right;
            } else {
                // target funnet
                return 1;
            }
        }

        // target ikke funnet
        return -1;

    }

}