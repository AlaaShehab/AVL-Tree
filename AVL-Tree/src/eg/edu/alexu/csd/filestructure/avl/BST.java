/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

/**
 * @author Personal
 *
 */
public class BST<T extends Comparable<T>> {


	private Node<T> root;
	/**
	 * Boolean to value deleted.
	 */
	private boolean deleted;
	/**
	 * Boolean to value inserted.
	 */
	private boolean inserted;

	/**
	 * Tree constructor.
	 */
	public BST() {
		deleted = false;
		root = null;
		inserted = false;
	}

	/**
	 * Insert the given value using the key.
	 *
	 * @param key
	 *            the value to be inserted.
	 */
	public void insert(T key) {
		inserted = false;
		root = insertNode(key, root);
	}

	/**
	 * @param key
	 *            of inserted element.
	 * @param startingNode
	 *            of recursion.
	 */
	private Node<T> insertNode(T key, Node<T> node) {
		if (node == null) {
			inserted = true;
			node = new Node<T>();
			node.setValue(key);
			return node;
		}
		if (key.compareTo(node.getValue()) > 0) {
			node.setRightChild(insertNode(key, node.getRightChild()));
		} else if (key.compareTo(node.getValue()) < 0) {
			node.setLeftChild(insertNode(key, node.getLeftChild()));
		} else {
			return node;
		}
		node.setHeight(1 + Math.max
				(height(node.getLeftChild())
						, height(node.getRightChild())));
		return node;

	}
	/**
	 * @return if a value is inserted or not.
	 */
	public boolean isInserted() {
		return inserted;
	}
	/**
	 * Delete the key (if exists).
	 *
	 * @param key
	 *            the key of the node.
	 * @return true if node deleted.
	 */
	public boolean delete(T key) {
		deleted = false;
		root = delete(key, root);
		return deleted;
	}

	/**
	 * @param key
	 *            to be deleted
	 * @param node
	 *            of start
	 * @return deleted node
	 */
	private Node<T> delete(T key, Node<T> node) {
		if (node == null) {
			return node;
		}
		if (key.compareTo(node.getValue()) > 0) {
			node.setRightChild(delete(key, node.getRightChild()));
		} else if (key.compareTo(node.getValue()) < 0) {
			node.setLeftChild(delete(key, node.getLeftChild()));
		} else {
			deleted = true;
			node = replaceNode(node);
		}
		if (node == null) {
			return node;
		}
		node.setHeight(1 + Math.max(height(node.getLeftChild()), height(node.getRightChild())));
		return node;
	}

	/**
	 * @param node
	 *            to be deleted
	 */
	private Node<T> replaceNode(Node<T> node) {

		if (node.getRightChild() == null && node.getLeftChild() == null) {
			node = null;
		} else if (node.getRightChild() == null && node.getLeftChild() != null) {
			node = node.getLeftChild();

		} else if (node.getLeftChild() == null && node.getRightChild() != null) {
			node = node.getRightChild();
		} else {
			Comparable<T> value = findValueToReplace(node.getRightChild());
			node.setValue((T) value);
			node.setRightChild(delete((T) value, node.getRightChild()));
		}
		return node;

	}

	/**
	 * @param node
	 *            which will be deleted.
	 * @return new value to be replaced.
	 */
	private T findValueToReplace(Node<T> node) {
		Comparable<T> value = null;
		while (node != null) {
			value = node.getValue();
			node = node.getLeftChild();
		}
		return (T) value;
	}

	/**
	 * Search for a specific element.
	 *
	 * @param key
	 *            the key of the node.
	 * @return true if the key exists.
	 */
	public boolean search(T key) {
		return search(key, root);
	}

	/**
	 * @param key
	 *            required.
	 * @param node
	 *            of start.
	 * @return true of key found.
	 */
	private boolean search(T key, Node<T> node) {
		if (node == null) {
			return false;
		}
		if (key.compareTo(node.getValue()) < 0) {
			return search(key, node.getLeftChild());
		} else if (key.compareTo(node.getValue()) > 0) {
			return search(key, node.getRightChild());
		} else {
			return true;
		}
	}

	/**
	 * Return the height of the AVL tree. the root to a leaf-node.
	 *
	 * @return tree height.
	 */
	public int height() {
		if (root == null) {
			return 0;
		}
		int treeHeight = Math.max(height(root.getLeftChild()), height(root.getRightChild())) + 1;
		return treeHeight;
	}

	/**
	 * Return the height of the node.
	 *
	 * @return node height.
	 */
	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return node.getHeight();
	}

	/**
	 * Return the root of your AVL tree.
	 *
	 * @return root of the AVL tree.
	 */
	public INode<T> getTree() {
		return root;
	}
}

