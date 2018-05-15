/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

/**
 * @author Personal
 *
 */
public interface IDictionary {
	/**
	 * Load the dictionary into an AVL. Tree data structure. The file is text.
	 *
	 * @param file
	 *            the dictionary file.
	 */
	void load(java.io.File file);

	/**
	 * Takes a word and inserts it.
	 *
	 * @param word
	 *            word to insert.
	 * @return true if inserted successfully.
	 */
	boolean insert(String word);

	/**
	 * Takes a word from the user.
	 *
	 * @param word
	 *            word to lookup.
	 * @return true if exists, false if not.
	 */
	boolean exists(String word);

	/**
	 * Takes a word from the user.
	 *
	 * @param word
	 *            word to delete
	 * @return false if the word.
	 */
	boolean delete(String word);

	/**
	 * Prints the current size of your dictionary.
	 *
	 * @return dictionary size.
	 */
	int size();

	/**
	 * Print the maximum height of the current tree.
	 *
	 * @return AVL tree height.
	 */
	int height();
}