package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import eg.edu.alexu.csd.datastructure.mailServer.IFolder;

public class Folder implements IFolder {

	private String folder;
	private String currentUser;


	public void setFolder(String f) {
		folder = f;
	}
	public void setUser(String cUser) {
		currentUser = cUser;
	}


	public String getFolder() {
		return folder;
	}
	public String getUser() {
		return currentUser;
	}
}
