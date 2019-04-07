package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.DLinkedList;

public class Sort {

	public static void main(String[] args) {
		/*
		 * // String [] list = {{ "2010/05/05 22:27:45   ziad   zzzzzz   sadsas   0",
		 * "2018/05/05   22:27:45   mostaf   www   fff   0",
		 * "2009/05/05   22:27:45   yossif   ffff   sadsas   0",
		 * "2019/05/05   22:27:45   abdelrahman   safy.mohamed533   sadsas   0",
		 * "2018/06/30   22:27:45   kamal   safy.mohamed533   sadsas   0" };};
		 */

		String[] list = { "05/06/2017 22:27:45   ziad   zzzzzz   sadsas   0",
				"05/05/2017   22:27:45   mostaf   www   fff   0", "05/05/2019   22:27:45   yossif   ffff   sadsas   0",
				"05/05/2015   22:27:45   abdelrahman   safy.mohamed533   sadsas   0",
				"05/05/2010   22:27:45   kamal   safy.mohamed533   sadsas   0" };
		int k = 0;
		// int []list = {1,5,2,3,10,50,60 , 0};
		sort(list, 0, list.length - 1, 2);
		System.out.println(Arrays.toString(list));
		Arrays.sort(list);
		System.out.println(Arrays.toString(list));
		System.out.println(binarySearch(list, "mostaf", 2));
	}

	private static int partition(String arr[], int low, int high, int k) {
		String[] P = arr[high].split("   ");
		String pivot = P[k];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			String[] s = arr[j].split("   ");
			// If current element is smaller than or
			// equal to pivot
			if (s[k].compareTo(pivot) < 0 || s[k].compareTo(pivot) == 0) {
				i++;

				// swap arr[i] and arr[j]
				String temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		String temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	public static String[] sort(String[] list, int low, int high, int k) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(0);
		s.push(-1);
		// a marker to identify the bottom of the stack
		while (!s.isEmpty()) {
			while (low <= high) {
				int pivotLocation = partition(list, low, high, k);
				s.push(pivotLocation + 1);
				s.push(high);
				high = pivotLocation - 1; // execute first recursive call
			}
			high = s.pop(); // fetch next recursive call to execute
			low = s.pop();

		}
		return list;
	}

	static int binarySearch(String arr[], String x, int k) {

		int l = 0, r = arr.length - 1;
		while (l <= r) {

			int m = l + (r - l) / 2;
			String[] temp = arr[m].split("   ");
			// Check if x is present at mid
			if (temp[k].compareTo(x) == 0)
				return m;

			// If x greater, ignore left half
			if (temp[k].compareTo(x) < 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		// if we reach here, then element was
		// not present
		return -1;
	}

	public static String[] ele(String[] list, String word, int index, int k) {
		DLinkedList d = new DLinkedList();
		d.add(list[index]);
		int index2 = index-1;
        index++;

		while (index2 >= 0) {
			String[] s = list[index2].split("   ");
			if (s[k].equals(word)) {
				d.add(list[index2]);
				index2--;
			} else {
				break;
			}
		}
		while (index < list.length) {
			String[] s = list[index].split("   ");
			if (s[k].equals(word)) {
				String a = list[index];
				d.add(a);
				index++;
			} else {
				break;
			}
		}
		
	
		String [] list2 = new String[d.size()];
		for (int i = 0; i < list2.length; i++) {
			//System.out.println(d.get(0));
			list2[i] = (String) d.get(0);
			d.remove(0);
			
		}
		sort(list2, 0, list2.length-1,0 );
	//	System.out.println(Arrays.toString(list2));
		return list2;
	}

}
