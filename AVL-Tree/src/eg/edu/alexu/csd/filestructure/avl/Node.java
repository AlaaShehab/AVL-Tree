/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

/**
 * @author Personal
 *
 */
public class Node<T extends Comparable<T>> implements INode<T> {
	/**
	 * left child of the current element.
	 */
	private Node<T> leftChild;
	/**
	 * right child of the current element.
	 */
	private Node<T> rightChild;
	/**
	 * value of the current element.
	 */
	private Comparable<T> nodeValue;
	/**
	 * height of the current element.
	 */
	private int height;

	/**
	 * node constructor.
	 */
	public Node() {
		height = 1;
	}

	/**
	 * Returns the left child.
	 *
	 * @return INode wrapper to the left child.
	 */
	@Override
	public Node<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * sets the left child.
	 */
	public void setLeftChild(final Node<T> nodeLeftChild) {
		leftChild = nodeLeftChild;
	}

	/**
	 * Returns the right child.
	 *
	 * @return INode wrapper to the right child.
	 */
	@Override
	public Node<T> getRightChild() {
		return rightChild;
	}

	/**
	 * sets the right child of the.
	 */
	public void setRightChild(final Node<T> nodeRightChild) {
		rightChild = nodeRightChild;
	}

	/**
	 * Get the value of the current node.
	 *
	 * @return nodeValue of the current node.
	 */
	@Override
	public T getValue() {
		return (T) nodeValue;
	}

	/**
	 * Set the value of the current node.
	 */
	@Override
	public void setValue(T value) {
		nodeValue = value;
	}

	/**
	 * Get the height of the current node.
	 *
	 * @return height of the current node.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the current node.
	 */
	public void setHeight(final int nodeHeight) {
		height = nodeHeight;

	}

}
