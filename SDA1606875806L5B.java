
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SDA1606875806L5B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		TreeMapKu<Integer, TreeSetKu<String>> idol = new TreeMapKu<Integer, TreeSetKu<String>>();
		TreeSetKu<String> nama = new TreeSetKu<String>();
		
		String str;
		while((str = in.readLine()) != null) {
			String[] cmd = str.split(";");
			if(cmd[0].equals("REGISTER")) {
				if(nama.add(cmd[1])) {
					idol.add(Integer.parseInt(cmd[2]), new TreeSetKu<String>());
					idol.get(Integer.parseInt(cmd[2])).add(cmd[1]);
					System.out.println(cmd[1]+":"+cmd[2]+" berhasil ditambahkan");
				}
				else
					System.out.println(cmd[1]+" sudah terdaftar di dalam sistem");
			}
			else if(cmd[0].equals("RESIGN")) {
				if(nama.remove(cmd[1])) {
					for(int key : idol.descending()) {
						idol.get(key).remove(cmd[1]);
						if(idol.get(key).isEmpty())
							idol.remove(key);
					}
					System.out.println(cmd[1]+" mengundurkan diri");
				}
				else
					System.out.println(cmd[1]+" tidak ditemukan di dalam sistem");
			}
			else if(cmd[0].equals("RETEST")) {
				if(nama.contains(cmd[1])) {
					for(int key : idol.descending()) {
						idol.get(key).remove(cmd[1]);
						if(idol.get(key).isEmpty())
							idol.remove(key);
					}
					if(idol.contains(Integer.parseInt(cmd[2])))
						idol.get(Integer.parseInt(cmd[2])).add(cmd[1]);
					else {
						idol.add(Integer.parseInt(cmd[2]), new TreeSetKu<String>());
						idol.get(Integer.parseInt(cmd[2])).add(cmd[1]);
					}
					System.out.println(cmd[1]+":"+cmd[2]+" perubahan berhasil");
				}
				else
					System.out.println(cmd[1]+" tidak ditemukan di dalam sistem");
			}
			else if(cmd[0].equals("SMARTEST")) {
				if(!idol.isEmpty()) {
					int maxScore = idol.descending().get(0);
					String print = "";
					for(String name : idol.get(maxScore).ascending()) {
						print += name + ", ";
					}
					System.out.println(print.substring(0, print.length()-2)+" : "+maxScore);
				}
				else
					System.out.println("Tidak ada siswa yang terdaftar dalam sistem");
			}
			else if(cmd[0].equals("RANKING")) {
				if(!idol.isEmpty()) {
					int rank = 1;
					for(int key : idol.descending()) {
						String print = "";
						for(String name : idol.get(key).ascending()) {
							print += name + ", ";
						}
						System.out.println((rank++)+". "+print.substring(0, print.length()-2)+" : "+key);
					}
				}
				else
					System.out.println("Tidak ada siswa yang terdaftar dalam sistem");
			}
		}
	}
}

/**
*
* Kelas Binary Search Tree
* Mahasiswa tidak diwajibkan menggunakan template ini, namun sangat disarankan menggunakan template ini
* Pada template ini, diasumsikan kelas yang ingin dipakai mengimplementasikan (implements) interface Comparable
* NOTE : Tidak semua method yang dibutuhkan sudah disediakan templatenya pada kelas ini sehingga mahasiswa harus menambahkan sendiri method yang dianggap perlu
* @author Jahns Christian Albert
*
*/
class TreeSetKu<E extends Comparable<? super E>> {
	
	/**
	  *
	  * Kelas yang merepresentasikan node pada tree
	  * @author Jahns Christian Albert
	  *
	*/
	private static class Node<E> {
		
		E elem;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E elem, Node<E> left, Node<E> right, Node<E> parent){
			
			this.elem = elem;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
		public Node(E elem, Node<E> parent) {
			this.elem = elem;
			this.parent = parent;
		}
		
		public String toString() {
			return (String) this.elem;
		}
		
	}
	
	private Node<E> root;
	
	/**
	  *
	  * Constructor Kelas Binary Search Tree
	  *
	*/
	public TreeSetKu(){
		
		root = null;
		
	}
	
	/**
	  *
	  * Mengetahui apakah tree kosong atau tidak
	  * @return true jika kosong, false jika sebaliknya
	  *
	*/
	public boolean isEmpty(){
		
		return root == null;
		
	}

	public boolean add(E elem){
		if(root == null){
			
			// TO DO : Lengkapi bagian ini
			root = new Node<E>(elem, null);
		} else {
			
			Node<E> prev = null;
			Node<E> current = root;			
			while(current != null){
				
				E currElem = current.elem;
				if(elem.compareTo(currElem) < 0){
					
					// TO DO : Lengkapi bagian ini
					prev = current;
					current = current.left; 
					
				} else if(elem.compareTo(currElem) > 0){
					
					// TO DO : Lengkapi bagian ini
					prev = current;
					current = current.right;
				} else {
					
					// TO DO : Lengkapi bagian ini
					return false;	
				}
				
			}
			
			// TO DO : Lengkapi bagian ini
			if(elem.compareTo(prev.elem) < 0)
				prev.left = new Node<E>(elem, prev);
			else
				prev.right = new Node<E>(elem, prev);
		}
		
		return true;
		
	}

	private Node<E> find(E elem){
		
		Node<E> res = null;
		
		if(root != null){
			
			Node<E> current = root;
			boolean found = false;
			while(!found && current != null){
				
				E currElem = current.elem;
				if(elem.compareTo(currElem) < 0){
					
					// TO DO : Lengkapi bagian ini
					current = current.left;
				} else if(elem.compareTo(currElem) > 0){
					
					// TO DO : Lengkapi bagian ini
					current = current.right;
				} else {
					
					// TO DO : Lengkapi bagian ini
					found = true;
					res = current;
				}
				
			}
			
		}
		
		return res;
	
	}

	public boolean remove(E elem){
		
		boolean res = false;
		
		Node<E> node = find(elem); //node yang ingin dihapus
		
		if(node != null){
			
			if(node.left == null){
				
				if(node.right == null){
					
					// TO DO : Lengkapi bagian ini -> leaf node
					if(node.parent==null)
						root = null; //jika node merupakan root, maka tree kosong
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi null
						if(node.parent.left != null)
							node.parent.left = node.parent.left.elem.compareTo(node.elem)==0?
										   	   null : node.parent.left;
						if(node.parent.right != null)
							node.parent.right = node.parent.right.elem.compareTo(node.elem)==0?
					   							null : node.parent.right;
					}
					node = null; //mengubah node yg ditunjuk menjadi null
				} else {
					
					// TO DO : Lengkapi bagian ini -> punya anak di kanan
					node.right.parent = node.parent; //mengubah parent anak kanan menjadi parent dari node
					//jika node merupakan root
					if(node.parent == null)
						root = node.right; //ubah root menjadi anak kanan node
					//node bukan root
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi anak kanan node
						if(node.parent.left != null)
							node.parent.left = node.parent.left.elem.compareTo(node.elem)==0?
											   node.right : node.parent.left;
						if(node.parent.right != null)
							node.parent.right = node.parent.right.elem.compareTo(node.elem)==0?
												node.right : node.parent.right;
					}
					node = node.right; //mengubah node yg ditunjuk menjadi anak kanan
				}
				
			} else {
				
				if(node.right == null){
					
					// TO DO : Lengkapi bagian ini -> punya anak di kiri
					node.left.parent = node.parent; //mengubah parent anak kanan menjadi parent dari node
					//jika node merupakan root
					if(node.parent == null)
						root = node.left; //ubah root menjadi anak kiri node
					//node bukan root
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi anak kiri node
						if(node.parent.left != null) {
							node.parent.left = node.parent.left.elem.compareTo(node.elem)==0?
											   node.left : node.parent.left;
						}
						if(node.parent.right != null) {
							node.parent.right = node.parent.right.elem.compareTo(node.elem)==0?
												node.left : node.parent.right;
						}
					}
					node = node.left; //mengubah node yg ditunjuk menjadi anak kiri
				} else {
					
					// TO DO : Lengkapi bagian ini -> punya 2 anak
					Node<E> rightMinNode = minNode(node.right); //node terkecil dari subtree kanan
					Node<E> rNode = rightMinNode.right; //Anak kanan dari minNode
					
				/*
				 * kumpulan assignment variable di bawah, berfungsi mengganti node yg ingin dihapus
				 * dengan node terkecil di subtree kanan node, menyesuaikan dengan segala kondisi
				 */
					rightMinNode.left = node.left; //mengubah anak kiri node pengganti dengan anak kiri node 
					node.left.parent = rightMinNode; //mengubah parent anak kiri node menjadi node pengganti
					
					/*
					 * Jika elemen node pengganti sama dengan anak kanan node, maka anak kanan
					 * node pengganti tidak berubah, sebaliknya berubah menjadi anak kanan node.
					 * parentnya tidak berubah, sebaliknya berubah menjadi node pengganti
					 */
					rightMinNode.right = rightMinNode.elem.compareTo(node.right.elem)==0?
										 rightMinNode.right : node.right; 
					node.right.parent = node.right.elem.compareTo(rightMinNode.elem)==0?
							node.right.parent : rightMinNode; 
					
					//mengubah anak kiri dari parent node pengganti, menjadi anak kanan node pengganti
					rightMinNode.parent.left = rNode;
					
					//Jika anak kanan dari minNode tidak null
					if(rNode != null)
						rNode.parent = rightMinNode.parent; //ubah parentnya menjadi parent node pengganti
					
					rightMinNode.parent = node.parent; //mengubah parent node pengganti, menjadi parent dari node
					
					//Jika node merupakan root
					if(rightMinNode.parent==null) {
						root = rightMinNode; //ubah root menjadi node pengganti (node terkecil di subtree kanan node)
					}
					//node bukan root
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi node pengganti (node terkecil di subtree kanan node)
						if(rightMinNode.parent.left != null)
							rightMinNode.parent.left = 
										rightMinNode.parent.left.elem.compareTo(node.elem)==0?
									   	rightMinNode : rightMinNode.parent.left;
						if(rightMinNode.parent.right != null)
							rightMinNode.parent.right = 
										rightMinNode.parent.right.elem.compareTo(node.elem)==0?
									   	rightMinNode : rightMinNode.parent.right;
					}
					node = rightMinNode; //mengubah node yg ditunjuk menjadi node pengganti (node terkecil di subtree kanan node)
				}
			}
			res = true; //mengembalikan nilai true, karna berhasil menghapus
		}
		return res;
	}
	
	/**
	  *
	  * Method untuk mengembalikan node dengan elemen terkecil pada suatu subtree
	  * Hint : Manfaatkan struktur dari binary search tree
	  * @param node root dari subtree yang ingin dicari elemen terbesarnya
	  * @return node dengan elemen terkecil dari subtree yang diinginkan
	  *
	*/
	private Node<E> minNode(Node<E> node){
		
		Node<E> res = null;
		if(node != null){
			
			Node<E> current = node;
			// TO DO : Lengkapi bagian ini
			//Pada BST elemen terkecil pasti berada di leaf paling kiri
			while(current.left != null) {
				current = current.left;
			}
			res = current;
		}
		
		return res;
		
	}
	
	/**
	 *
	 * Mencari elemen dengan nilai paling besar pada tree
	 * @return elemen dengan nilai paling besar pada tree
	 *
	*/
	public E max(){
		
		E res = null;
		Node<E> maxNode = maxNode(root);
		
		if(maxNode != null){
			
			res = maxNode.elem;
			
		}
		
		return res;
		
	}
	
	/**
	  *
	  * Method untuk mengembalikan node dengan elemen terbesar pada suatu subtree
	  * Hint : Manfaatkan struktur dari binary search tree
	  * @param node root dari subtree yang ingin dicari elemen terbesarnya
	  * @return node dengan elemen terbesar dari subtree yang diinginkan
	  *
	*/
	private Node<E> maxNode(Node<E> node){
		
		Node<E> res = null;
		if(node != null){
			
			Node<E> current = node;
			// TO DO : Lengkapi bagian ini
			//Pada BST elemen terbesar pasti berada di leaf paling kanan
			while(current.right != null) {
				current = current.right;
			}
			res = current;
		}
		
		return res;
		
	}

	public boolean contains(E elem){
		
		// TO DO : Lengkapi method ini
		//bila tidak ditemukan == null
		return find(elem) != null;
		
	}

	/**
	  * Mengembalikan tree dalam bentuk in-order secara ascending
	  * @return tree dalam bentuk in-order secara ascending sebagai list of E
	*/
	public List<E> ascending(){
		
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return ascending(root,list);
		
	}
	
	/**
	  *
	  * Method helper dari ascending()
	  * @param node pointer
	  * @param list sebagai akumulator
	  * @return kumpulan elemen dari subtree yang rootnya adalah node parameter dengan urutan in-order secara ascending
	  *
	*/
	private List<E> ascending(Node<E> node, List<E> list){
		
		// TO DO : Lengkapi method ini
		if(node != null) {
			ascending(node.left, list);
			list.add(node.elem);
			ascending(node.right, list);
		}
		return list;
	}
}

/**
*
* Kelas Binary Search Tree
* Mahasiswa tidak diwajibkan menggunakan template ini, namun sangat disarankan menggunakan template ini
* Pada template ini, diasumsikan kelas yang ingin dipakai mengimplementasikan (implements) interface Comparable
* NOTE : Tidak semua method yang dibutuhkan sudah disediakan templatenya pada kelas ini sehingga mahasiswa harus menambahkan sendiri method yang dianggap perlu
* @author Jahns Christian Albert
*
*/
class TreeMapKu<K extends Comparable<? super K>, V> {
	
	/**
	  *
	  * Kelas yang merepresentasikan node pada tree
	  * @author Jahns Christian Albert
	  *
	*/
	private static class Node<K,V> {
		
		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;
		Node<K,V> parent;
		

		public Node(K key, V value, Node<K,V> left, Node<K,V> right, Node<K,V> parent){
			
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
		public Node(K elem, V value, Node<K,V> parent) {
			this.key = elem;
			this.value = value;
			this.parent = parent;
		}
		
		public String toString() {
			return (String) this.key;
		}
		
	}
	
	private Node<K,V> root;
	
	/**
	  *
	  * Constructor Kelas Binary Search Tree
	  *
	*/
	public TreeMapKu(){
		
		root = null;
		
	}
	
	/**
	  *
	  * Mengetahui apakah tree kosong atau tidak
	  * @return true jika kosong, false jika sebaliknya
	  *
	*/
	public boolean isEmpty(){
		
		return root == null;
		
	}

	public boolean add(K key, V value){
		if(root == null){
			
			// TO DO : Lengkapi bagian ini
			root = new Node<K,V>(key, value, null);
		} else {
			
			Node<K,V> prev = null;
			Node<K,V> current = root;			
			while(current != null){
				
				K currKey = current.key;
				if(key.compareTo(currKey) < 0){
					
					// TO DO : Lengkapi bagian ini
					prev = current;
					current = current.left; 
					
				} else if(key.compareTo(currKey) > 0){
					
					// TO DO : Lengkapi bagian ini
					prev = current;
					current = current.right;
				} else {
					
					// TO DO : Lengkapi bagian ini
					return false;	
				}
				
			}
			
			// TO DO : Lengkapi bagian ini
			if(key.compareTo(prev.key) < 0)
				prev.left = new Node<K,V>(key, value, prev);
			else
				prev.right = new Node<K,V>(key, value, prev);
		}
		
		return true;
		
	}

	private Node<K,V> find(K key){
		
		Node<K,V> res = null;
		
		if(root != null){
			
			Node<K,V> current = root;
			boolean found = false;
			while(!found && current != null){
				
				K currElem = current.key;
				if(key.compareTo(currElem) < 0){
					
					// TO DO : Lengkapi bagian ini
					current = current.left;
				} else if(key.compareTo(currElem) > 0){
					
					// TO DO : Lengkapi bagian ini
					current = current.right;
				} else {
					
					// TO DO : Lengkapi bagian ini
					found = true;
					res = current;
				}
				
			}
			
		}
		
		return res;
	
	}
	
	public V get(K key) {
		Node<K,V> node = find(key);
		return node.value;
	}

	public boolean remove(K key){
		
		boolean res = false;
		
		Node<K,V> node = find(key); //node yang ingin dihapus
		
		if(node != null){
			
			if(node.left == null){
				
				if(node.right == null){
					
					// TO DO : Lengkapi bagian ini -> leaf node
					if(node.parent==null)
						root = null; //jika node merupakan root, maka tree kosong
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi null
						if(node.parent.left != null)
							node.parent.left = node.parent.left.key.compareTo(node.key)==0?
										   	   null : node.parent.left;
						if(node.parent.right != null)
							node.parent.right = node.parent.right.key.compareTo(node.key)==0?
					   							null : node.parent.right;
					}
					node = null; //mengubah node yg ditunjuk menjadi null
				} else {
					
					// TO DO : Lengkapi bagian ini -> punya anak di kanan
					node.right.parent = node.parent; //mengubah parent anak kanan menjadi parent dari node
					//jika node merupakan root
					if(node.parent == null)
						root = node.right; //ubah root menjadi anak kanan node
					//node bukan root
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi anak kanan node
						if(node.parent.left != null)
							node.parent.left = node.parent.left.key.compareTo(node.key)==0?
											   node.right : node.parent.left;
						if(node.parent.right != null)
							node.parent.right = node.parent.right.key.compareTo(node.key)==0?
												node.right : node.parent.right;
					}
					node = node.right; //mengubah node yg ditunjuk menjadi anak kanan
				}
				
			} else {
				
				if(node.right == null){
					
					// TO DO : Lengkapi bagian ini -> punya anak di kiri
					node.left.parent = node.parent; //mengubah parent anak kanan menjadi parent dari node
					//jika node merupakan root
					if(node.parent == null)
						root = node.left; //ubah root menjadi anak kiri node
					//node bukan root
					else {
						//mengecek apakah node berada pada kanan/kiri dari parentnya, ubah menjadi anak kiri node
						if(node.parent.left != null) {
							node.parent.left = node.parent.left.key.compareTo(node.key)==0?
											   node.left : node.parent.left;
						}
						if(node.parent.right != null) {
							node.parent.right = node.parent.right.key.compareTo(node.key)==0?
												node.left : node.parent.right;
						}
					}
					node = node.left; //mengubah node yg ditunjuk menjadi anak kiri
				} else {
					
					// TO DO : Lengkapi bagian ini -> punya 2 anak
					Node<K,V> rightMinNode = minNode(node.right); //node terkecil dari subtree kanan
					Node<K,V> rNode = rightMinNode.right; //Anak kanan dari minNode
					
				/*
				 * kumpulan assignment variable di bawah, berfungsi mengganti node yg ingin dihapus
				 * dengan node terkecil di subtree kanan node, menyesuaikan dengan segala kondisi
				 */
					rightMinNode.left = node.left; //mengubah anak kiri node pengganti dengan anak kiri node 
					node.left.parent = rightMinNode; //mengubah parent anak kiri node menjadi node pengganti
					
					/*
					 * Jika elemen node pengganti sama dengan anak kanan node, maka anak kanan
					 * node pengganti tidak berubah, sebaliknya berubah menjadi anak kanan node.
					 * parentnya tidak berubah, sebaliknya berubah menjadi node pengganti
					 */
					rightMinNode.right = rightMinNode.key.compareTo(node.right.key)==0?
										 rightMinNode.right : node.right; 
					node.right.parent = node.right.key.compareTo(rightMinNode.key)==0?
							node.right.parent : rightMinNode; 
					
					//mengubah anak kiri dari parent node pengganti, menjadi anak kanan node pengganti
					rightMinNode.parent.left = rNode;
					
					//Jika anak kanan dari minNode tidak null, parentnya diubah
					if(rNode != null)
						rNode.parent = rightMinNode.parent;
					
					rightMinNode.parent = node.parent;
					
					//Jika node merupakan root diganti dengan sistem successor
					if(rightMinNode.parent==null) {
						root = rightMinNode;
					}
					//node bukan root, dicek node kiri dan kanannya
					else {
						if(rightMinNode.parent.left != null)
							rightMinNode.parent.left = 
										rightMinNode.parent.left.key.compareTo(node.key)==0?
									   	rightMinNode : rightMinNode.parent.left;
						if(rightMinNode.parent.right != null)
							rightMinNode.parent.right = 
										rightMinNode.parent.right.key.compareTo(node.key)==0?
									   	rightMinNode : rightMinNode.parent.right;
					}
					node = rightMinNode; //mengubah node yg ditunjuk menjadi node pengganti dengan sistem successor
				}
			}
			res = true; //mengembalikan nilai true jika berhasil menghapus elemen
		}
		return res;
	}
	
	/**
	  *
	  * Method untuk mengembalikan node dengan elemen terkecil pada suatu subtree
	  * Hint : Manfaatkan struktur dari binary search tree
	  * @param node root dari subtree yang ingin dicari elemen terbesarnya
	  * @return node dengan elemen terkecil dari subtree yang diinginkan
	  *
	*/
	private Node<K,V> minNode(Node<K,V> node){
		
		Node<K,V> res = null;
		if(node != null){
			
			Node<K,V> current = node;
			// TO DO : Lengkapi bagian ini
			//Pada BST elemen terkecil pasti berada di ujung paling kiri
			while(current.left != null) {
				current = current.left;
			}
			res = current;
		}
		
		return res;
		
	}
	
	/**
	 *
	 * Mencari elemen dengan nilai paling besar pada tree
	 * @return elemen dengan nilai paling besar pada tree
	 *
	*/
	public K max(){
		
		K res = null;
		Node<K,V> maxNode = maxNode(root);
		
		if(maxNode != null){
			
			res = maxNode.key;
			
		}
		
		return res;
		
	}
	
	/**
	  *
	  * Method untuk mengembalikan node dengan elemen terbesar pada suatu subtree
	  * Hint : Manfaatkan struktur dari binary search tree
	  * @param node root dari subtree yang ingin dicari elemen terbesarnya
	  * @return node dengan elemen terbesar dari subtree yang diinginkan
	  *
	*/
	private Node<K,V> maxNode(Node<K,V> node){
		
		Node<K,V> res = null;
		if(node != null){
			
			Node<K,V> current = node;
			// TO DO : Lengkapi bagian ini
			//Pada BST elemen terbesar pasti berada di ujung paling kanan
			while(current.right != null) {
				current = current.right;
			}
			res = current;
		}
		
		return res;
		
	}

	public boolean contains(K elem){
		
		// TO DO : Lengkapi method ini
		//bila tidak ditemukan == null
		return find(elem) != null;
		
	}

	/**
	  * Mengembalikan tree dalam bentuk in-order secara descending
	  * @return tree dalam bentuk in-order secara descending sebagai list of E
	*/
	public List<K> descending(){
		
		List<K> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return descending(root,list);
		
	}
	
	/**
	  *
	  * Method helper dari descending()
	  * @param node pointer
	  * @param list sebagai akumulator
	  * @return kumpulan elemen dari subtree yang rootnya adalah node parameter dengan urutan in-order secara descending
	  *
	*/
	private List<K> descending(Node<K,V> node, List<K> list){
		
		// TO DO : Lengkapi method ini
		if(node != null) {
			descending(node.right, list);
			list.add(node.key);
			descending(node.left, list);
		}
		return list;
	}
}