package sortcomparison;

import static sbcc.Core.*;
import java.util.Random;

import java.util.*;

public class BasicSorter implements Sorter {
	Random random = new Random();


	@Override
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		for (int i = firstIndex + 1; i < firstIndex + numberToSort; i++) {
			String key = data.get(i);
			int j = i - 1;
			while (j >= firstIndex && key.compareTo(data.get(j)) < 0) {
				data.set(j + 1, data.get(j));
				j--;
			}
			data.set(j + 1, key);
		}
		// println(data);
	}


	@Override
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		if (numberToSort > 15) {

			int point = partition(data, firstIndex, numberToSort);
			quickSort(data, firstIndex, point - firstIndex);
			quickSort(data, point + 1, numberToSort - (point - firstIndex) - 1);

		} else
			insertionSort(data, firstIndex, numberToSort);

	}


	@Override
	public int partition(ArrayList<String> data, int firstIndex, int numberToPartition) {
		HashMap<String, Integer> indices = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int randIndex = random.nextInt(numberToPartition) + firstIndex;
			indices.put(data.get(randIndex), randIndex);
			list.add(data.get(randIndex));
		}
		Collections.sort(list);
		String middle = list.get(1);
		Collections.swap(data, firstIndex, indices.get(middle));

		String pivot = data.get(firstIndex);
		int bigNdx = firstIndex + 1;
		int smallNdx = firstIndex + numberToPartition - 1;
		while (bigNdx < smallNdx) {
			while (bigNdx < smallNdx && data.get(bigNdx).compareTo(pivot) <= 0) {
				bigNdx++;
			}
			while (smallNdx > firstIndex && data.get(smallNdx).compareTo(pivot) > 0) {
				smallNdx--;
			}
			if (bigNdx < smallNdx) {

				Collections.swap(data, smallNdx, bigNdx);
			}

		}
		if (pivot.compareTo(data.get(smallNdx)) >= 0) {

			Collections.swap(data, smallNdx, firstIndex);
			return smallNdx;
		} else {
			return firstIndex;
		}

	}


	@Override
	public void mergeSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		if (numberToSort > 15) {
			int leftSegmentSize = numberToSort / 2;
			int rightSegmentSize = numberToSort - leftSegmentSize;

			mergeSort(data, firstIndex, leftSegmentSize);
			mergeSort(data, firstIndex + leftSegmentSize, rightSegmentSize);
			// boolean thing = false;
			if (data.get(firstIndex + leftSegmentSize - 1).compareTo(data.get(firstIndex + leftSegmentSize)) > 0)
				merge(data, firstIndex, leftSegmentSize, rightSegmentSize);

		} else
			insertionSort(data, firstIndex, numberToSort);

	}


	@Override
	public void merge(ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {
		ArrayList<String> temp = new ArrayList<String>();
		int leftSegmentSizeCount = leftSegmentSize;
		int rightSegmentSizeCount = rightSegmentSize;
		int headLeft = firstIndex;
		int headRight = firstIndex + leftSegmentSize;
		while (leftSegmentSizeCount != 0 && rightSegmentSizeCount != 0) {
			if (data.get(headLeft).compareTo(data.get(headRight)) <= 0) {
				temp.add(data.get(headLeft));
				headLeft++;
				leftSegmentSizeCount--;
			} else {
				temp.add(data.get(headRight));
				headRight++;
				rightSegmentSizeCount--;
			}

		}
		while (leftSegmentSizeCount != 0) {
			temp.add(data.get(headLeft));
			headLeft++;
			leftSegmentSizeCount--;
		}
		while (rightSegmentSizeCount != 0) {
			temp.add(data.get(headRight));
			headRight++;
			rightSegmentSizeCount--;
		}

		for (int i = 0; i < temp.size(); i++) {
			data.set(firstIndex + i, temp.get(i));
		}

	}


	@Override
	public void heapSort(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}


	@Override
	public void heapify(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}

}
