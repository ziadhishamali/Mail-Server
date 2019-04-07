package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

/**
 *
 * @author ziadh
 *
 */
public class PQNode {

	/**
	 *
	 */
	private int key;
	/**
	 *
	 */
	private Object element;
	/**
	 *
	 */
	private PQNode next;

	/**
	 *
	 * @param e element .
	 * @param k key
	 * @param n next
	 */
	public PQNode(final Object e, final int k, final PQNode n) {
		element = e;
		key = k;
		next = n;
	}

	/**
	 *
	 * @return element .
	 */
	public Object getElement() {
		return element;
	}
	/**
	 *
	 * @param e element .
	 */
	public void setElement(final Object e) {
		element = e;
	}
	/**
	 *
	 * @param n next .
	 */
	public void setNext(final PQNode n) {
		next = n;
	}
	/**
	 *
	 * @param k key .
	 */
	public void setKey(final int k) {
		key = k;
	}
	/**
	 *
	 * @return next .
	 */
	public PQNode getNext() {
		return next;
	}
	/**
	 *
	 * @return key .
	 */
	public int getKey() {
		return key;
	}


}
