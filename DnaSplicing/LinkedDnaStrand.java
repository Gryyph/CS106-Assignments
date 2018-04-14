package dnasplicing;

public class LinkedDnaStrand implements DnaStrand {

	private DnaSequenceNode first;

	private int nodeCount = 0;
	private int appendCounter = 0;


	public LinkedDnaStrand() {
		first = null;
	}


	public LinkedDnaStrand(String dnaSequence) {
		// super();

		DnaSequenceNode newNode = new DnaSequenceNode(dnaSequence);
		newNode.next = first;
		first = newNode;
		nodeCount++;

	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DnaSequenceNode cursor = first;
		while (cursor != null) {
			sb.append(cursor.dnaSequence);
			cursor = cursor.next;
		}

		return sb.toString();
	}


	@Override
	public long getNucleotideCount() {
		// TODO Auto-generated method stub
		return toString().length();
	}


	@Override
	public void append(String dnaSequence) {
		if (dnaSequence.isEmpty())
			return;
		DnaSequenceNode cursor = first;
		DnaSequenceNode newNode = new DnaSequenceNode(dnaSequence);
		if (cursor != null) { // populated linked list, i.e. first is not null
			while (cursor != null && cursor.next != null)
				cursor = cursor.next;
			cursor.next = newNode;
			nodeCount++;
			appendCounter++;
		} else {

			first = newNode;
			nodeCount = 1;
		}
	}


	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		String[] preNode = first.dnaSequence.split(enzyme, -1);
		DnaStrand result = new LinkedDnaStrand();
		int i;
		for (i = 0; i < preNode.length - 1; i++) {
			if (i == 0 && preNode[i].isEmpty()) {
				result.append(splicee);
				continue;
			}
			result.append(preNode[i]);
			result.append(splicee);
		}
		result.append(preNode[preNode.length - 1]);
		return result;
	}


	@Override
	public DnaStrand createReversedDnaStrand() {
		return new LinkedDnaStrand(new StringBuilder(toString()).reverse().toString());
	}


	@Override
	public int getAppendCount() {

		return appendCounter;
	}


	@Override
	public DnaSequenceNode getFirstNode() {

		return first;
	}


	@Override
	public int getNodeCount() {

		return nodeCount;
	}

}