package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import eg.edu.alexu.csd.datastructure.mailServer.IPriorityQueue;

/**
 *
 * @author ziadh
 *
 */
public class PriorityQueue implements IPriorityQueue {


	/**
	 * head node .
	 */
	private PQNode head;
	/**
	 * tail node .
	 */
	private PQNode tail;
	/**
	 * length .
	 */
	private int length = 0;


	@Override
	public void insert(final Object item, final int key) {

		if (key < 1) {
			throw new RuntimeException();
		}
		PQNode newer = new PQNode(item, key, null);

		if (length == 0) {

			head = newer;
			tail = newer;
			length++;

		} else {

			PQNode temp = head, temp2 = head.getNext();
			if (key >= tail.getKey()) {
				tail.setNext(newer);
				newer.setNext(null);
				tail = newer;
				length++;
			} else if (key < head.getKey()) {
				newer.setNext(head);
				head = newer;
				length++;
			} else {
				while (true) {
					if ((temp2 != null)
						&& (key < temp2.getKey())) {

						newer.setNext(temp2);
						head.setNext(newer);
						length++;
						break;
					} else if (temp2 == null) {
						tail.setNext(newer);
						tail = newer;
						length++;
						break;
					} else {
						temp2 = temp2.getNext();
						head = head.getNext();
					}
				}
				head = temp;
			}
		}
	}

	@Override
	public Object removeMin() {

		if (length == 0) {
			throw new RuntimeException();
		}
		Object min = head.getElement();
		head = head.getNext();
		length--;
		return min;

	}

	@Override
	public Object min() {
		if (length == 0) {
			throw new RuntimeException();
		}
		return head.getElement();
	}

	@Override
	public boolean isEmpty() {
		return (length == 0);
	}

	@Override
	public int size() {
		return length;
	}

}
