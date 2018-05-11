package spellchecker;

import static sbcc.Core.*;

import java.util.regex.*;

import org.apache.commons.lang3.*;

public class BasicSpellChecker implements SpellChecker {
	BasicDictionary dictionary = new BasicDictionary();
	StringBuilder document;
	Pattern pattern = Pattern.compile("\\b[\\w']+\\b");
	Matcher matcher;


	@Override
	public void importDictionary(String filename) throws Exception {
		dictionary.importFile(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		dictionary.load(filename);

	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		dictionary.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		document = new StringBuilder(readFile(filename));
		matcher = pattern.matcher(document);
	}


	@Override
	public void saveDocument(String filename) throws Exception {
		writeFile(filename, document.toString());
	}


	@Override
	public String getText() {
		return document.toString();
	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		String[] result = new String[4];
		String[] preResult = new String[2];

		if (continueFromPrevious == false)
			matcher = pattern.matcher(document);
		while (matcher.find()) {
			String word = matcher.group();
			// println(word);
			if ((preResult = dictionary.find(word)) != null) {
				// println(preResult.toString());
				result[0] = word;
				result[1] = Integer.toString(matcher.start());
				result[2] = preResult[0];
				result[3] = preResult[1];
				return result;

			}
		}

		return null;

	}


	@Override
	public void addWordToDictionary(String word) {
		dictionary.add(word);
	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		document.replace(startIndex, endIndex, replacementText);
	}

}
