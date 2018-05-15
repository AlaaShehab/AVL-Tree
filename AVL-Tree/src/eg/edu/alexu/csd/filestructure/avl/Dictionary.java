/**
 *
 */
package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author Personal
 *
 */
public class Dictionary implements IDictionary {
	/**
	 * avl tree.
	 */
	private AVLTree tree;
	/**
	 * dictionary size.
	 */
	private int size;

	/**
	 * Dictionary constructor.
	 */
	public Dictionary() {
		tree = new AVLTree();
		size = 0;
	}

	/**
	 * Load the dictionary into an AVL. Tree data structure. The file is text.
	 *
	 * @param file
	 *            the dictionary file.
	 */
	@Override
	public void load(final File file) {
		FileReader ifile;
		try {
			ifile = new FileReader(file);
			BufferedReader readFile = new BufferedReader(ifile);

			String line = null;
			while ((line = readFile.readLine()) != null) {
				tree.insert(line);
				if (tree.isInserted()) {
					size++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes a word and inserts it.
	 *
	 * @param word
	 *            word to insert.
	 * @return true if inserted successfully.
	 */
	@Override
	public boolean insert(final String word) {
		insert(word);
		if (tree.isInserted()) {
			size++;
		}
		return tree.isInserted();
	}

	/**
	 * Takes a word from the user.
	 *
	 * @param word
	 *            word to lookup.
	 * @return true if exists, false if not.
	 */
	@Override
	public boolean exists(final String word) {
		return tree.search(word);
	}

	/**
	 * Takes a word from the user.
	 *
	 * @param word
	 *            word to delete
	 * @return false if the word.
	 */
	@Override
	public boolean delete(final String word) {
		boolean deleted = tree.delete(word);
		if (deleted) {
			size--;
		}
		return deleted;
	}

	/**
	 * Prints the current size of your dictionary.
	 *
	 * @return dictionary size.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Print the maximum height of the current tree.
	 *
	 * @return AVL tree height.
	 */
	@Override
	public int height() {
		return tree.height();
	}

}
