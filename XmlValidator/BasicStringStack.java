package xmlvalidator;

import static java.lang.System.*;
import static sbcc.Core.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicStringStack implements StringStack {
	private int count;
	private String[] items;


	public BasicStringStack() {
		count = 0;
		items = new String[0];
	}


	@Override
	public void push(String item) {
		if (count == items.length) {
			String[] tempAry = new String[(int) (items.length * 1.25) + 1];
			arraycopy(items, 0, tempAry, 0, items.length);
			items = tempAry;
		}
		// insert the new item X at the top of the stack
		items[count++] = item;

	}


	@Override
	public String pop() {
		if (count == 0)
			return null; // If the stack is empty, return the null object.
		else {
			String item = items[count - 1];
			items[count - 1] = null;
			count--;
			return item;
		}
	}


	@Override
	public String peek(int position) {
		if ((position > count - 1) || (position < 0))
			return null; // too high
		else
			return items[count - position - 1]; // Return a ref to the top object
	}


	@Override
	public int getCount() {
		return count;
	}

}
