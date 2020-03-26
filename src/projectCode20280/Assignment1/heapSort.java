package projectCode20280.Assignment1;

import java.util.ArrayList;
import java.util.Random;
import projectCode20280.Practical1.SinglyLinkedList;
import projectCode20280.Practical6.HeapPriorityQueue;
import static java.util.stream.Collectors.toCollection;

public class heapSort {

	public heapSort(){

	}

	public static boolean isSorted(Comparable[] array, int length) {
		if (array == null || length < 2)
			return true;
		if (array[length - 2].compareTo(array[length - 1]) > 0)
			return false;
		return isSorted(array, length - 1);
	}


	public static void pqHeapSort(){
		int n = 250;
		while (n < 15000) {
			ArrayList<Integer> randomInts = new Random().ints(0, 25000).distinct().limit(n).boxed().collect(toCollection(ArrayList::new));
			ArrayList<Object> sortedList = new ArrayList<>();
			long startTime = System.nanoTime();
			HeapPriorityQueue queueToSort = new HeapPriorityQueue(randomInts.toArray(), randomInts.toArray());




			while(!queueToSort.isEmpty()){
				sortedList.add(queueToSort.removeMin().getValue());
			}

			long endTime = System.nanoTime();
			long estimatedTime = endTime - startTime;
			boolean checkIfSorted = isSorted(randomInts.toArray(new Integer[sortedList.size()]), sortedList.size());
			System.out.println(n + " , " + estimatedTime + " , " + checkIfSorted);
			n = (int)(n*1.05);
		}
	}

	public static void linkedListSelectionSort(){

		int n = 250;
		while (n < 15000) {
			ArrayList<Integer> randomInts = new Random().ints(0, 25000).distinct().limit(n).boxed().collect(toCollection(ArrayList::new));

			SinglyLinkedList<Integer> listToSort = new SinglyLinkedList<>();


			long startTime = System.nanoTime();
			while (!randomInts.isEmpty()) {
				listToSort.addFirst(randomInts.get(0));
				randomInts.remove(0);
			}

			while(!listToSort.isEmpty()){
				randomInts.add(listToSort.removeMin().getElement());
			}

			long endTime = System.nanoTime();
			long estimatedTime = endTime - startTime;
			boolean checkIfSorted = isSorted(randomInts.toArray(new Integer[randomInts.size()]), randomInts.size());
			System.out.println(n + " , " + estimatedTime + " , " + checkIfSorted);
			n = (int)(n*1.05);
		}
	}


	public static void main(String[] args) {
		pqHeapSort();
		linkedListSelectionSort();
	}

}
