package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.SLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class Mail implements IMail {

	private String[] email;
	private String from;
	private String to;
	private String subject;
	private SLinkedList attachments = new SLinkedList();
	private int priority;

	public void setEmail(String[] str) {
		email = str;
	}
	public void setFrom(String sender) {
		from = sender;
	}
	public void setTo(String reciever) {
		to = reciever;
	}
	public void setSubject(String sub) {
		subject = sub;
	}
	public void setAttachments(SLinkedList attach) {
		attachments = attach;
	}
	public void setPriority(int pr) {
		priority = pr;
	}


	public String[] getEmail() {
		return email;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getSubject() {
		return subject;
	}
	public SLinkedList getAttachments() {
		return attachments;
	}
	public int getPriority() {
		return priority;
	}

}
