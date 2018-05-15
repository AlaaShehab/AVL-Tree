/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

/**
 * @author Personal
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


//		tree.insert(25);
//		System.out.println(tree.height());
//		tree.delete(40);
//		System.out.println(tree.height());
//		tree.insert(192);
//		System.out.println(tree.height());
//		tree.delete(192);
//		System.out.println(tree.height());
//		tree.delete(25);
//		System.out.println(tree.height());
//		for (int i = 0; i < 10000; i++) {
//			tree.insert(28);
//		}
//		System.out.println(tree.height());

		RandomGenerator random = new RandomGenerator();
		int[] list = new int[5000000];
		for (int i = 0; i < 5000000; i++) {
			list[i] = random.nextInt();
		}
		AVLTree<Integer> avl = new AVLTree<Integer>();
		BST<Integer> bst = new BST<Integer>();

		double beg;
		double end;

		beg = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			avl.insert(list[i]);
		}
		end = System.currentTimeMillis();
		System.out.println("AVL");
		System.out.println("Time : " + (end - beg) / 1000);
		System.out.println("Height : " + avl.height());
        System.out.println();
		avl = null;

		beg = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			bst.insert(list[i]);
		}
		end = System.currentTimeMillis();
		System.out.println("BST");
		System.out.println("Time : " + (end - beg) / 1000);
		System.out.println("Height : " + bst.height());
	}

}
