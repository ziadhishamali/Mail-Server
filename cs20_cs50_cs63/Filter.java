package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.SLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;

public class Filter implements IFilter {

	private String folderName = null;
	private String currentUser = null;
	private SLinkedList filtersFrom = new SLinkedList();
	private SLinkedList filtersSubject = new SLinkedList();
	private SLinkedList filtersTopic = new SLinkedList();


	public void setFolderName(String f) {
		folderName = f;
	}
	public void setCurrentUser(String cUser) {
		currentUser = cUser;
	}
	public void setFiltersFrom(SLinkedList s) {
		filtersFrom = s;
	}
	public void setFiltersSubject(SLinkedList s) {
		filtersSubject = s;
	}
	public void setFiltersTopic(SLinkedList s) {
		filtersTopic = s;
	}



	public String getFolderName() {
		return folderName;
	}
	public String getCurrentUser() {
		return currentUser;
	}
	public SLinkedList getFiltersFrom() {
		return filtersFrom;
	}
	public SLinkedList getFiltersSubject() {
		return filtersSubject;
	}
	public SLinkedList getFiltersTopic() {
		return filtersTopic;
	}

}
