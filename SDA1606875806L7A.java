import java.util.*;
import java.io.*;

public class SDA1606875806L7A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AvlTree<Comic> jaringToon = new AvlTree<Comic>();
        String str;
        while((str=br.readLine())!=null) {
            String[] cmd = str.split(" ");
            if(cmd[0].equalsIgnoreCase("ADD")) {
                Comic comicToBeAdded = new Comic(cmd[1], Integer.parseInt(cmd[2]));
                jaringToon.insert(comicToBeAdded);
                System.out.println("Komik "+cmd[1]+" sudah disimpan dalam JaringToon");
            }
            else if(cmd[0].equalsIgnoreCase("REMOVE")) {
                if(!jaringToon.isEmpty()) {
                    Comic comicToBeRemoved = new Comic(cmd[1], Integer.parseInt(cmd[2]));
                    jaringToon.remove(comicToBeRemoved);
                    System.out.println("Komik "+cmd[1]+" sudah dihapus dari JaringToon");
                }
                else System.out.println("Tidak ada komik dalam JaringToon");
            }
            else if(cmd[0].equalsIgnoreCase("POPULARITY")) {
                if(jaringToon.isEmpty())
                    System.out.println("Tidak ada komik dalam JaringToon");
                else{
                    Comic maxComic =  jaringToon.findMaxElem();
                    Comic minComic = jaringToon.findMinElem();
                    if(minComic.compareTo(maxComic)==0) {
                        System.out.println("Hanya ada komik "+maxComic.toString());
                    }
                    else{
                        System.out.println("Tertinggi "+maxComic.toString()+"; "+"Terendah "+minComic.toString());
                    }
                }
            }

            else if(cmd[0].equalsIgnoreCase("PRINT")) {
                if(jaringToon.isEmpty()) {
                    System.out.println("Tidak ada komik dalam JaringToon");
                }
                else {
                    String inOrder = jaringToon.printInOrder();
                    String preOrder = jaringToon.printPreOrder();
                    String postOrder = jaringToon.printPostOrder();

                    System.out.println("In Order: "+inOrder.substring(0, inOrder.length()-2));
                    System.out.println("Pre Order: "+preOrder.substring(0, preOrder.length()-2));
                    System.out.println("Post Order: "+postOrder.substring(0, postOrder.length()-2));
                }
            }
        }
    }
}


/**
 *
 * Kelas AVL Tree
 * Mahasiswa tidak diwajibkan menggunakan template ini, namun sangat disarankan menggunakan template ini
 * Pada template ini, diasumsikan kelas yang ingin dipakai mengimplementasikan (implements) interface Comparable
 * NOTE : Tidak semua method yang dibutuhkan sudah disediakan templatenya pada kelas ini sehingga mahasiswa harus menambahkan sendiri method yang dianggap perlu
 * @author Teuku Amrullah Faisal
 *
 */
class AvlTree<T extends Comparable<? super T>>
{
    /**
     *
     * Kelas yang merepresentasikan node pada tree
     *
     */
    private static class AvlNode<T>
    {
        private T           element;      // Data di dalam node
        private AvlNode<T>  left;         // Left child
        private AvlNode<T>  right;        // Right child
        private int         height;       // Height dari node

        /**
         *
         * Constructor
         * @param theElement elemen pada node
         *
         */
        AvlNode( T theElement )
        {
            this( theElement, null, null );
        }

        /**
         *
         * Constructor
         * @param theElement elemen pada node
         * @param lt left node
         * @param rt right node
         *
         */
        AvlNode( T theElement, AvlNode<T> lt, AvlNode<T> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }
    }

    private AvlNode<T> root;

    /**
     *
     * Constructor Kelas AVL Tree
     *
     */
    public AvlTree( )
    {
        root = null;
    }

    /**
     * Memasukkan elemen ke dalam tree
     * @param x elemen yang akan dimasukkan.
     */
    public boolean insert(T x) {
        try {
            root = insert(x, root);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private AvlNode<T> insert(T x, AvlNode<T> t) throws Exception {
        if (t == null)
            t = new AvlNode<T>(x);
        else if (x.compareTo(t.element) < 0) {
            t.left = insert(x, t.left);

            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild(t);
                    System.out.println("Lakukan rotasi sekali pada " + t.element);
                } else {
                    t = doubleWithLeftChild(t);
                    System.out.println("Lakukan rotasi dua kali pada " + t.element);
                }
            }
        } else if (x.compareTo(t.element) > 0) {
            t.right = insert(x, t.right);

            if (height(t.right) - height(t.left) == 2)
                if (x.compareTo(t.right.element) > 0) {
                    t = rotateWithRightChild(t);
                    System.out.println("Lakukan rotasi sekali pada " + t.element);
                } else {
                    t = doubleWithRightChild(t);
                    System.out.println("Lakukan rotasi dua kali pada " + t.element);
                }
        } else {
            throw new Exception("Attempting to insert duplicate value");
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * Menghapus elemen dari tree
     * @param x elemen yang akan dihapus
     */
    public void remove( T x )
    {
        root = remove( x, root );
    }

    /**
     *
     * Menghapus objek dari tree, menggunakan predecessor inorder untuk menghapus elemen yang memiliki left node dan right node
     * Manfaatkan method findMax(AvlNode<E> node) untuk mencari predecessor inorder
     * @param x elemen yang ingin dihapus
     * @param t posisi node saat ini.
     * @return true root baru dari subtree
     *
     */
    private AvlNode<T> remove( T x, AvlNode<T> t )
    {
        if( t == null ) {
            return t;   // Item not found; do nothing
        }

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 ) {
            // TO DO : Lengkapi bagian ini
            t.left = remove(x, t.left);
        }

        else if( compareResult > 0 ) {
            // TO DO : Lengkapi bagian ini
            t.right = remove(x, t.right);
        }

        else if( t.left != null && t.right != null ) {
            // TO DO : Lengkapi bagian ini
            t.element = findMax(t.left).element;
            t.left = remove(t.element, t.left);
        }

        else { //jika elemen yang ingin dihapus ditemukan
            // TO DO : Lengkapi bagian ini
            t = ( t.left != null ) ? t.left : t.right;
        }
        if(t != null)
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        return balance( t );
    }

    /**
     * Mengosongkan tree
     */
    public void makeEmpty( )
    {
        // TO DO : Lengkapi bagian ini
        root = null;
    }

    private static final int ALLOWED_IMBALANCE = 1;

    /**
     * Mem-balance kan tree
     * Gunakan method rotateWithLeftChild, rotateWithRightChild,
     * doubleWithRightChild, dan doubleWithLeftChild
     * Jangan lupa update height dari node
     * @param t root dari subtree yang ingin dibalance
     * @return node setelah balance
     */
    private AvlNode<T> balance( AvlNode<T> t )
    {
        if( t == null ) {
            // TO DO : Lengkapi bagian ini
            return t;
        }

        if( height( t.left )-height( t.right ) > ALLOWED_IMBALANCE ) {
            if( height( t.left.left ) >= height( t.left.right ) ) {
                // TO DO : Lengkapi bagian ini
                t = rotateWithLeftChild(t);
                System.out.println("Lakukan rotasi sekali pada "+t.element);
            }
            else {
                // TO DO : Lengkapi bagian ini
                t = doubleWithLeftChild(t);
                System.out.println("Lakukan rotasi dua kali pada "+t.element);
            }
        }
        else {
            if( Math.abs(height( t.right )-height( t.left )) > ALLOWED_IMBALANCE ) {
                if( height( t.right.right ) >= height( t.right.left ) ) {
                    // TO DO : Lengkapi bagian ini
                    t = rotateWithRightChild(t);
                    System.out.println("Lakukan rotasi sekali pada "+t.element);
                }

                else {
                    // TO DO : Lengkapi bagian ini
                    t = doubleWithRightChild(t);
                    System.out.println("Lakukan rotasi dua kali pada "+t.element);
                }
            }

        }

        // TO DO : Lengkapi bagian ini (update height dari node)
        if(t != null) t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     *
     * Mengetahui apakah tree kosong atau tidak
     * @return true jika kosong, false jika sebaliknya
     *
     */
    public boolean isEmpty( )
    {
        // TO DO : Lengkapi bagian ini

        return root==null;
    }

    public T findMinElem() {
        if (isEmpty())
            return null;
        return findMin(root).element;
    }

    public T findMaxElem() {
        if (isEmpty())
            return null;
        return findMax(root).element;
    }

    /**
     * Mencari min dari sebuah subtree
     * @param t root dari sebuah subtree
     * @return node node terkecil dari subtree
     */
    private AvlNode<T> findMin( AvlNode<T> t )
    {
        if( t == null ) {
            // TO DO : Lengkapi bagian in
            return t;
        }

        while( t.left != null ) {
            // TO DO : Lengkapi bagian ini
            t = t.left;
        }

        return t;
    }

    /**
     * Mencari max dari sebuah subtree
     * @param t root dari sebuah subtree
     * @return node node terbesar dari subtree
     */
    private AvlNode<T> findMax( AvlNode<T> t )
    {
        if( t == null ) {
            // TO DO : Lengkapi bagian ini
            return t;
        }

        while( t.right != null ) {
            // TO DO : Lengkapi bagian ini
            t = t.right;
        }

        return t;
    }

    /**
     * Method untuk mencetak tree secara inorder
     *
     * @return String representasi dari tree
     */
    public String printInOrder() {
        StringBuilder str = new StringBuilder();
        printInOrder (root, str);
        return str.toString();
    }

    /**
     * Method internal untuk mencetak tree secara inorder
     *
     * @param t   node dari tree
     * @param str hasil dari pencetakan tree
     */
    protected void printInOrder(AvlNode<T> t, StringBuilder str) {
        if (t != null) {
            // TO DO : Lengkapi bagian ini
            printInOrder(t.left, str);
            str.append(t.element+"; ");
            printInOrder(t.right, str);
        }
    }

    /**
     * Method untuk mencetak tree secara preorder
     *
     * @return String representasi dari tree
     */
    public String printPreOrder(){
        StringBuilder str = new StringBuilder();
        printPreOrder (root, str);
        return str.toString();
    }

    /**
     * Method internal untuk mencetak tree secara preorder
     *
     * @param t   node dari tree
     * @param str hasil dari pencetakan tree
     */
    private void printPreOrder (AvlNode<T> t, StringBuilder str){
        if (t != null){
            // TO DO : Lengkapi bagian ini
            str.append(t.element+"; ");
            printPreOrder(t.left, str);
            printPreOrder(t.right, str);
        }
    }

    /**
     * Method untuk mencetak tree secara postorder
     *
     * @return String representasi dari tree
     */
    public String printPostOrder(){
        StringBuilder str = new StringBuilder();
        printPostOrder (root, str);
        return str.toString();
    }

    /**
     * Method internal untuk mencetak tree secara postorder
     *
     * @param t   node dari tree
     * @param str hasil dari pencetakan tree
     */
    private void printPostOrder (AvlNode<T> t, StringBuilder str){
        if (t != null){
            // TO DO : Lengkapi bagian ini
            printPostOrder(t.left, str);
            printPostOrder(t.right, str);
            str.append(t.element+"; ");
        }
    }

    /**
     * Mengembalikan height dari sebuah node
     */
    private int height( AvlNode<T> t )
    {
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;

        k1.right = k2.left;
        k2.left = k1;

        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;

        return (k2);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }
}

class Comic implements Comparable<Comic>{
    String name;
    int read;

    public Comic(String name, int read){
        this.name = name;
        this.read = read;
    }

    public String toString(){
        return this.name;
    }

    @Override
    public int compareTo(Comic otherComic) {
        return this.read - otherComic.read;
    }
}