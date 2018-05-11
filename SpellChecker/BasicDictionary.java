package spellchecker;

import static java.lang.Math.*;
import static org.apache.commons.lang3.StringUtils.*;
import static sbcc.Core.*;
import java.util.*;
import java.util.List;
import org.apache.commons.lang3.*;

public class BasicDictionary implements Dictionary {
	List<String> fileList;
	List<String> finalList;
	int count = 0;
	BinaryTree tree = new BinaryTree();
	String preList;
	String start;


	@Override
	public void importFile(String filename) throws Exception {
		fileList = readFileAsLines(filename);

		Collections.shuffle(fileList);

		for (int i = 0; i < fileList.size(); i++) {
			add(fileList.get(i));
		}
	}


	@Override
	public void load(String filename) throws Exception {
		fileList = readFileAsLines(filename);

		for (int i = 0; i < fileList.size(); i++) {
			add(fileList.get(i));
		}
	}


	@Override
	public void save(String filename) throws Exception {
		tree.preOrder(getRoot());
		preList = tree.sb.toString();
		finalList = new ArrayList<String>(Arrays.asList(preList.split("\n")));
		writeFileAsLines(filename, finalList);

	}


	@Override
	public String[] find(String word) {
		word = word.trim();
		BinaryTreeNode cursor = getRoot();
		String before = "", after = "";
		String[] result = new String[2];
		while (cursor != null) {
			if (word.compareToIgnoreCase(cursor.value) > 0) {
				// before = cursor.value;
				before = cursor.value;
				cursor = cursor.right;

			} else if (word.compareToIgnoreCase(cursor.value) < 0) {
				after = cursor.value;
				cursor = cursor.left;
			} else if (word.compareToIgnoreCase(cursor.value) == 0)
				return null;
		}
		result[0] = before;
		result[1] = after;
		return result;
	}
	// println(result);


	@Override
	public void add(String word) {
		count++;
		tree.insert(word);

	}


	@Override
	public BinaryTreeNode getRoot() {
		return tree.root;
	}


	@Override
	public int getCount() {

		return count;
	}

}
