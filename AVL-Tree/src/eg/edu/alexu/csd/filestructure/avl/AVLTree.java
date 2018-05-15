/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

/**
 * @author Personal
 *
 */
public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
	/**
	 * reference to tree.
	 */
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
	public AVLTree() {
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
	@Override
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

		if (balance(node) > 1
				&& key.compareTo(node.getLeftChild()
						.getValue()) < 0) {
			return (rightRotation(node));
		} else if (balance(node) < -1
				&& key.compareTo(node.getRightChild()
						.getValue()) > 0) {
			return (leftRotation(node));
		} else if (balance(node) > 1
				&& key.compareTo(node.getLeftChild()
						.getValue()) > 0) {
			node.setLeftChild(leftRotation(node.getLeftChild()));
			return rightRotation(node);
		} else if (balance(node) < -1
				&& key.compareTo(node.getRightChild()
						.getValue()) < 0) {
			node.setRightChild(rightRotation(node.getRightChild()));
			return leftRotation(node);
		}
		return node;

	}

	/**
	 * @param node
	 *            to check balance.
	 * @return balance.
	 */
	private int balance(Node<T> node) {
		if (node == null) {
			return 0;
		}
		int balance = height(node.getLeftChild()) - height(node.getRightChild());
		return balance;
	}

	/**
	 * @return if a value is inserted or not.
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * Rotate the node to the left.
	 *
	 * @param node
	 *            to be rotated.
	 * @return parent node.
	 */
	private Node<T> leftRotation(Node<T> node) {
		Node<T> nodeX = node.getRightChild();
		Node<T> nodeXchild = nodeX.getLeftChild();

		node.setRightChild(nodeXchild);
		nodeX.setLeftChild(node);

		node.setHeight(1 + Math.max(height(node.getLeftChild()), height(node.getRightChild())));
		nodeX.setHeight(1 + Math.max(height(nodeX.getLeftChild()), height(nodeX.getRightChild())));

		return nodeX;
	}

	/**
	 * Rotate the node to the right.
	 *
	 * @param node
	 *            to be rotated.
	 * @return parent node.
	 */
	private Node<T> rightRotation(Node<T> node) {
		Node<T> nodeX = node.getLeftChild();
		Node<T> nodeXchild = nodeX.getRightChild();

		node.setLeftChild(nodeXchild);
		nodeX.setRightChild(node);

		node.setHeight(1 + Math.max(height(node.getLeftChild()), height(node.getRightChild())));
		nodeX.setHeight(1 + Math.max(height(nodeX.getLeftChild()), height(nodeX.getRightChild())));
		return nodeX;
	}

	/**
	 * Delete the key (if exists).
	 *
	 * @param key
	 *            the key of the node.
	 * @return true if node deleted.
	 */
	@Override
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

		if (balance(node) > 1 && key.compareTo(node.getLeftChild().getValue()) > 0) {
			return (rightRotation(node));
		} else if (balance(node) < -1 && key.compareTo(node.getRightChild().getValue()) < 0) {
			return (leftRotation(node));
		} else if (balance(node) > 1 && key.compareTo(node.getLeftChild().getValue()) < 0) {
			node.setLeftChild(leftRotation(node.getLeftChild()));
			return rightRotation(node);
		} else if (balance(node) < -1 && key.compareTo(node.getRightChild().getValue()) > 0) {
			node.setRightChild(rightRotation(node.getRightChild()));
			return leftRotation(node);
		}
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
	@Override
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
	@Override
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
	@Override
	public INode<T> getTree() {
		return root;
	}
}
