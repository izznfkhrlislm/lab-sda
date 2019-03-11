import java.util.*;
import java.io.*;

public class SDA1606875806L5A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd = br.readLine();
        BST<String> names = new BST<>();

        while (cmd != null){
            String[] command = cmd.split(";");

            if (command[0].equals("ADD")){
                boolean addStatus = names.add(command[1]);
                if (addStatus == true){
                    System.out.println(command[1]+" berhasil ditambahkan ke dalam tree");
                }
                else {
                    System.out.println(command[1]+" sudah dimasukkan sebelumnya");
                }
            }

            else if (command[0].equals("REMOVE")){
                boolean removeStatus = names.remove(command[1]);
                if (removeStatus == true){
                    System.out.println(command[1]+" berhasil dihapus dari tree");
                }
                else {
                    System.out.println(command[1]+" tidak ditemukan");
                }
            }

            else if (command[0].equals("CONTAINS")){
                boolean isContained = names.contains(command[1]);
                if (isContained == true){
                    System.out.println(command[1]+" terdapat pada tree");
                }
                else {
                    System.out.println(command[1]+" tidak terdapat pada tree");
                }
            }

            else if (command[0].equals("PREORDER")){
                if (names.isEmpty()){
                    System.out.println("Tidak ada elemen pada tree");
                }
                else {
                    String res = "";
                    List<String> preOrderedList = names.preOrder();
                    for (int i = 0; i < preOrderedList.size(); i++){
                        if (i == preOrderedList.size()-1){
                            res = res + preOrderedList.get(i);
                        }
                        else {
                            res = res + preOrderedList.get(i)+";";
                        }
                    }
                    System.out.println(res);
                }
            }

            else if (command[0].equals("POSTORDER")){
                if (names.isEmpty()){
                    System.out.println("Tidak ada elemen pada tree");
                }
                else {
                    String res = "";
                    List<String> postOrderedList = names.postOrder();
                    for (int i = 0; i < postOrderedList.size(); i++){
                        if (i == postOrderedList.size()-1){
                            res = res + postOrderedList.get(i);
                        }
                        else {
                            res = res + postOrderedList.get(i)+";";
                        }
                    }
                    System.out.println(res);
                }
            }

            else if (command[0].equals("ASCENDING")){
                if (names.isEmpty()){
                    System.out.println("Tidak ada elemen pada tree");
                }
                else {
                    String res = "";
                    List<String> ascendingOrderedList = names.inOrderAscending();
                    for (int i = 0; i < ascendingOrderedList.size(); i++){
                        if (i == ascendingOrderedList.size()-1){
                            res = res + ascendingOrderedList.get(i);
                        }
                        else {
                            res = res + ascendingOrderedList.get(i)+";";
                        }
                    }
                    System.out.println(res);
                }
            }

            else if (command[0].equals("DESCENDING")){
                if (names.isEmpty()){
                    System.out.println("Tidak ada elemen pada tree");
                }
                else {
                    String res = "";
                    List<String> descendingOrderedList = names.inOrderDescending();
                    for (int i = 0; i < descendingOrderedList.size(); i++){
                        if (i == descendingOrderedList.size()-1){
                            res = res + descendingOrderedList.get(i);
                        }
                        else {
                            res = res + descendingOrderedList.get(i)+";";
                        }
                    }
                    System.out.println(res);
                }
            }

            else if (command[0].equals("MAX")){
                if (names.isEmpty()){
                    System.out.println("Tidak ada elemen pada tree");
                }
                else {
                    System.out.println(names.max()+" merupakan elemen dengan nilai tertinggi");
                }
            }

            else if (command[0].equals("MIN")){
                if (names.isEmpty()){
                    System.out.println("Tidak ada elemen pada tree");
                }
                else {
                    System.out.println(names.min()+" merupakan elemen dengan nilai terendah");
                }
            }
            cmd = br.readLine();
        }
    }
}

class BST<E extends Comparable<? super E>> {
    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> left, Node<E> right, Node<E> parent){
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private Node<E> root;

    public BST(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean add(E element){
        if (root == null){
            root = new Node<>(element, null, null, null);
            return true;
        }
        else {
            Node<E> prev = null;
            Node<E> current = root;
            while (current != null){
                E currentElement = current.element;

                //kalau lebih kecil, trace ke kiri subtree
                if (element.compareTo(currentElement) < 0){
                    prev = current;
                    current = prev.left;
                }

                //kalau lebih besar, trace ke kanan subtree
                else if (element.compareTo(currentElement) > 0){
                    prev = current;
                    current = prev.right;
                }

                //kalau sama, gabisa ditambahin lagi, langsung exit loop
                else {
                    return false;
                }
            }
            current = new Node<>(element, null, null, prev);
            if (current.element.compareTo(prev.element) < 0){
                prev.left = current;
            }
            else {
                prev.right = current;
            }
            return true;
        }
    }

    private Node<E> find(E element){
        Node<E> res = null;
        if (root != null){
            Node<E> current = root;
            boolean found = false;
            while (!found && current != null){
                E currentElement = current.element;
                if (element.compareTo(currentElement) < 0){
                    current = current.left;
                }
                else if (element.compareTo(currentElement) > 0){
                    current = current.right;
                }
                else {
                    res = current;
                    found = true;
                }
            }
        }
        return res;
    }

    public boolean remove(E element){
        boolean hasRemoved = false;
        Node<E> nodeToBeRemoved = find(element);

        if (nodeToBeRemoved != null){
            hasRemoved = true;
            //Sebelah kiri node yang akan dihapus bernilai null
            if (nodeToBeRemoved.left == null){
                //Node leaf (kanan dan kiri node bernilai null)
                if (nodeToBeRemoved.right == null){
                    //Node yang ingin dihapus bukan merupakan root
                    if (nodeToBeRemoved.parent != null){
                        if (nodeToBeRemoved.parent.left == nodeToBeRemoved){
                            nodeToBeRemoved.parent.left = null;
                        }
                        else {
                            nodeToBeRemoved.parent.right = null;
                        }
                    }
                    //Node yang ingin dihapus merupakan root
                    else {
                        root = null;
                    }
                }
                //Node yang ingin dihapus hanya memiliki child di sebelah kanan (bukan leaf)
                else {
                    //Node memiliki parent
                    if (nodeToBeRemoved.parent != null){
                        if (nodeToBeRemoved.parent.right == nodeToBeRemoved){
                            nodeToBeRemoved.parent.right = nodeToBeRemoved.right;
                        }
                        else {
                            nodeToBeRemoved.parent.left = nodeToBeRemoved.right;
                        }
                    }
                    //Node merupakan root yang hanya memiliki child di sebelah kanan (nodeToBeRemoved == root)
                    else {
                        root = nodeToBeRemoved.right;
                    }
                }
            }
            //Sebelah kiri node yang akan dihapus tidak bernilai null
            else {
                //Node memiliki child hanya di sebelah kiri (bukan leaf)
                if (nodeToBeRemoved.right == null){
                    //Node yang ingin dihapus memiliki parent (bukan root)
                    if (nodeToBeRemoved.parent != null){
                        nodeToBeRemoved.parent.left = nodeToBeRemoved.left;
                    }
                    //Node merupakan root yang memiliki child di sebelah kiri (nodeToBeRemoved == root)
                    else {
                        root = nodeToBeRemoved.left;
                    }
                }
                //Node memiliki child di sebelah kiri dan sebelah kanan
                else {
                    Node<E> nodeToBeReplaced = minNode(nodeToBeRemoved.right);
                    //Node memiliki parent (bukan root)
                    nodeToBeRemoved.element = nodeToBeReplaced.element;
                    //Parent check
                    if (nodeToBeReplaced.parent.right == nodeToBeReplaced){
                        nodeToBeReplaced.parent.right = nodeToBeReplaced.right;
                    }
                    else {
                        nodeToBeReplaced.parent.left = nodeToBeReplaced.left;
                    }
                }
            }
        }
        return hasRemoved;
    }

    //Mencari nilai paling minimum di Tree tersebut
    public E min(){
        E res = null;
        Node<E> minimumNode = minNode(root);
        if (minimumNode != null){
            res = minimumNode.element;
        }
        return res;
    }

    //Mencari nilai minimum di setiap Subtree
    private Node<E> minNode(Node<E> node){
        Node<E> res = node;
        while (res.left != null){
            res = res.left;
        }
        return res;
    }

    public E max() {
        E res = null;
        Node<E> maximumNode = maxNode(root);
        if (maximumNode != null){
            res = maximumNode.element;
        }
        return res;
    }

    private Node<E> maxNode(Node<E> node){
        Node<E> res = node;
        while (res.right != null){
            res = res.right;
        }
        return res;
    }

    public boolean contains(E element){
        boolean isFound = false;
        Node<E> nodeSearched = find(element);
        if (nodeSearched != null){
            if (element.compareTo(nodeSearched.element) == 0){
                isFound = true;
            }
        }
        else {
            isFound = false;
        }
        return isFound;
    }

    public List<E> preOrder() {
        List<E> preOrderedList = new LinkedList<>();
        return preOrder(root, preOrderedList);
    }

    private List<E> preOrder (Node<E> rootNode, List<E> listTarget){
        //Node<E> vertexOrder = rootNode;
        if (rootNode == null){
            return listTarget;
        }
        else {
            listTarget.add(rootNode.element);
            listTarget = preOrder(rootNode.left, listTarget);
            listTarget = preOrder(rootNode.right, listTarget);
        }
        return listTarget;
    }

    public List<E> postOrder() {
        List<E> postOrderedList = new LinkedList<>();
        return postOrder(root, postOrderedList);
    }

    private List<E> postOrder(Node<E> rootNode, List<E> listTarget){
        if (rootNode == null){
            return listTarget;
        }
        else {
            listTarget = postOrder(rootNode.left, listTarget);
            listTarget = postOrder(rootNode.right, listTarget);
            listTarget.add(rootNode.element);

        }
        return listTarget;
    }

    public List<E> inOrderAscending(){
        List<E> inOrderedList = new LinkedList<>();
        return inOrderAscending(root, inOrderedList);
    }

    public List<E> inOrderAscending(Node<E> rootNode, List<E> listTarget){
        if (rootNode == null){
            return listTarget;
        }
        else {
            listTarget = inOrderAscending(rootNode.left, listTarget);
            listTarget.add(rootNode.element);
            listTarget = inOrderAscending(rootNode.right, listTarget);
        }
        return listTarget;
    }

    public List<E> inOrderDescending(){
        List<E> inOrderedList = new LinkedList<>();
        return inOrderDescending(root, inOrderedList);
    }

    public List<E> inOrderDescending(Node<E> rootNode, List<E> listTarget){
        if (rootNode == null){
            return listTarget;
        }
        else {
            listTarget = inOrderDescending(rootNode.right, listTarget);
            listTarget.add(rootNode.element);
            listTarget = inOrderDescending(rootNode.left, listTarget);
        }
        return listTarget;
    }
}
