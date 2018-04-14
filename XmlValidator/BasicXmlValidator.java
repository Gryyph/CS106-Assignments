package xmlvalidator;

import static java.lang.System.*;
import static sbcc.Core.*;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class BasicXmlValidator implements XmlValidator {

	BasicStringStack tagHolder = new BasicStringStack();
	BasicStringStack lineNumber = new BasicStringStack();
	String[] allMatchHolder;
	List<Integer> lineHolder = new ArrayList<>();
	String[] errorHolder;


	private boolean canIgnore(String match) {
		return (match.startsWith("<?") || match.endsWith("/>") || match.startsWith("<!--"));
	}


	public String extractName(String match) {

		return match.substring(1, match.length() - 1).split(" ")[0];
	}


	public boolean checkIfOpen(String match) {
		if (!match.startsWith("/")) {
			return true;
		} else

			return false;
	}


	public Integer lineCalculator(String xmlDocument, int position) {
		String result = xmlDocument.substring(0, position);
		int a = StringUtils.countMatches(result, "\n");
		position = a + 1;
		return position;

	}


	@Override
	public String[] validate(String xmlDocument) {

		String expr = "<[^<>]+>";
		Pattern p = Pattern.compile(expr);
		Matcher m = p.matcher(xmlDocument);
		errorHolder = new String[5];

		while (m.find()) {
			boolean removeSlash = false;
			String match = m.group();
			if (canIgnore(match))
				continue;
			int position = m.start();
			position = lineCalculator(xmlDocument, position);
			match = extractName(match);
			checkIfOpen(match);
			if (checkIfOpen(match) == false) {
				removeSlash = true;
				match = match.substring(1, match.length());
			}
			if (removeSlash == false) {
				tagHolder.push(match);
				lineNumber.push(Integer.toString(position));
			} else if (removeSlash == true && match.equals(tagHolder.peek(0)) == true) {
				tagHolder.pop();
				lineNumber.pop();
			} else if (removeSlash == true && !match.equals(tagHolder.peek(0)) && tagHolder.peek(0) != null) {
				errorHolder[0] = "Tag mismatch";
				errorHolder[1] = tagHolder.peek(0);
				errorHolder[2] = lineNumber.peek(0);
				errorHolder[3] = match;
				errorHolder[4] = Integer.toString(position);
				return errorHolder;
			} else if (removeSlash == true && tagHolder.peek(0) == null) {
				errorHolder[0] = "Orphan closing tag";
				errorHolder[1] = match;
				errorHolder[2] = Integer.toString(position);
				return errorHolder;
			}
		}

		if (tagHolder.peek(0) != null) {
			errorHolder[0] = "Unclosed tag at end";
			errorHolder[1] = tagHolder.peek(0);
			errorHolder[2] = lineNumber.peek(0);
			return errorHolder;

		}
		return null;

	}
}
