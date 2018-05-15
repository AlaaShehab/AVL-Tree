/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

/**
 * @author Personal
 *
 */
public interface IAVLTree<T extends Comparable<T>> {
	/**
	 * Insert the given value using the key.
	 *
	 * @param key
	 *            the value to be inserted.
	 */
	void insert(T key);

	/**
	 * Delete the key (if exists).
	 *
	 * @param key
	 *            the key of the node.
	 * @return true if node deleted.
	 */
	boolean delete(T key);

	/**
	 * Search for a specific element.
	 *
	 * @param key
	 *            the key of the node.
	 * @return true if the key exists.
	 */
	boolean search(T key);

	/**
	 * Return the height of the AVL tree. the root to a leaf-node.
	 *
	 * @return tree height.
	 */
	int height();

	/**
	 * Return the root of your AVL tree.
	 *
	 * @return root of the AVL tree.
	 */
	INode<T> getTree();
}