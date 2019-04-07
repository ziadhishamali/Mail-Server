package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.SLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

/**
 *
 * @author ziadh
 *
 */
public class App implements IApp {

	private String currentUserApp = "";
	private String fromFolder = "";

	/**
	 * renames the Option folder
	 *
	 * @param d
	 *            ... Double linked list containing the contents of the folderOption
	 * @param user
	 *            ...current logged in user
	 * @param oldfolder
	 *            ...the old folder name
	 * @param newfolder
	 *            ...the new folder name
	 */
	public void userOptionRename(DLinkedList d, String user, String oldfolder, String newfolder) {

		FileWriter n = null;
		try {
			n = new FileWriter(new File("Users/" + user + "/folderoption.txt"));
		} catch (Exception e) {

		}

		BufferedWriter bWriter = new BufferedWriter(n);
		int l = d.size();
		for (int i = 0; i < l; i++) {
			String s = (String) d.get(0);
			String[] ss = s.split("   ");
			if (ss[0].equals(oldfolder)) {
				s = newfolder + "   " + ss[1] + "   " + ss[2] + "   " + ss[3];
			}
			try {
				bWriter.write(s);
				bWriter.newLine();
				d.remove(0);
			} catch (IOException e) {

			}

		}
		try {
			bWriter.close();
		} catch (IOException e) {

		}
	}

	/**
	 * Checks if any of the options meets the new coming email and if so saves it in
	 * the specified folder
	 *
	 * @param email
	 *            the email to be checked
	 */
	public void checkoption(Mail email) {
		Mail m = new Mail();
		m = (Mail) email;
		FileReader contacts = null;
		try {
			contacts = new FileReader("Users/" + m.getTo() + "/folderoption.txt");
		} catch (FileNotFoundException e) {
			return;
		}

		BufferedReader bReader = new BufferedReader(contacts);
		while (true) {
			try {
				String temp = bReader.readLine();
				if (temp == null) {
					break;
				}
				String[] s = temp.split("   ");

				if (s[2].equals(currentUserApp) || s[1].equals(m.getSubject())
						|| m.getEmail().toString().contains(s[3])) {
					sendingEmail(m, s[0]);
				}

			} catch (IOException e) {

			}

		}
		try {
			bReader.close();
		} catch (IOException e) {

		}

	}

	/**
	 * It calculates the difference between the date of an email and the present
	 * date
	 *
	 * @param date
	 *            ...the date of any email
	 * @param now
	 *            ...the present date
	 * @return ...the difference between the two dates in days
	 */
	public long timeDifference(String date, String now) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date1 = null, date2 = null;

		try {
			date1 = format.parse(date);
			date2 = format.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long difference = date2.getTime() - date1.getTime();
		difference /= (24 * 60 * 60 * 1000);

		return difference;
	}

	/**
	 * Checks for the Emails which have been in Trash for more than 30 Days and
	 * deletes them
	 *
	 * @param currentUser
	 *            ...the current logged in user
	 */
	public void checkTrashTime(String currentUser) {

		DLinkedList d = new DLinkedList();
		d = indexLoad(currentUser, "Trash");

		for (int i = 0; i < d.size(); i++) {

			String temp = (String) d.get(i);
			String[] temp5 = temp.split("   ");

			String time = temp5[0];

			DateTimeFormatter dtfIndex = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime nowIndex = LocalDateTime.now();

			String now = nowIndex.format(dtfIndex).toString();

			long diff = timeDifference(time, now);

			if (diff >= 30) {
				d.remove(i);
				delete(new File("Users/" + currentUser + "/Trash/" + temp5[5]));
				i--;
			}

		}

		indexWriter(d, currentUser, "Trash");

	}

	/**
	 * It writes the usernames after changing or editing (wallah nfs elm3na e4 haza
	 * xD)
	 *
	 * @param d
	 *            the DLinked list in which all usernames are stored
	 */
	public void userWriter(DLinkedList d) {

		FileWriter n = null;
		try {
			n = new FileWriter(new File("Users/usernames.txt"));
		} catch (Exception e) {

		}

		BufferedWriter bWriter = new BufferedWriter(n);

		for (int i = 0; i < d.size(); i++) {

			try {
				bWriter.write((String) d.get(i));
				bWriter.newLine();
			} catch (IOException e) {

			}

		}
		try {
			bWriter.close();
		} catch (IOException e) {

		}
	}

	/**
	 * It loads all the usernames data into DLinked list
	 *
	 * @param currentUser
	 *            ...the current logged in user
	 * @return ...returns DLinked list containing all usernames data
	 */
	public DLinkedList usersLoad(String currentUser) {

		DLinkedList d = new DLinkedList();

		FileReader n = null;
		try {
			n = new FileReader("Users/usernames.txt");
		} catch (FileNotFoundException e) {

		}

		BufferedReader bReader = new BufferedReader(n);

		while (true) {

			String user = "";
			try {
				user = bReader.readLine();
			} catch (IOException e) {

			}
			if (user == null) {
				break;
			}
			d.add(user);

		}

		try {
			bReader.close();
		} catch (IOException e) {

		}

		return d;
	}

	/**
	 * It deletes the choosen folder and all of its contents
	 *
	 * @param file
	 *            ...the folder to be deleted
	 */
	public boolean delete(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = delete(children[i]);
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/**
	 * It makes a model of the contacts of the current logged in user
	 *
	 * @param currentUser
	 *            ...the current logged in user
	 * @return ...returns a model of the contacts list
	 */
	public DefaultListModel contactModelMaker(String currentUser) {

		DefaultListModel model = new DefaultListModel();

		FileReader contacts = null;
		try {
			contacts = new FileReader("Users/" + currentUser + "/contacts.txt");
		} catch (FileNotFoundException e) {

		}

		BufferedReader bReader = new BufferedReader(contacts);

		while (true) {
			try {
				String temp = bReader.readLine();
				if (temp == null) {
					break;
				} else {
					model.addElement(temp);
				}
			} catch (IOException e) {

			}

		}

		try {
			bReader.close();
		} catch (IOException e) {

		}

		return model;
	}

	/**
	 * It writes the contacts list of the current logged in user into the contacts
	 * file after sorting
	 *
	 * @param d
	 *            ...double linked list of the contacts
	 * @param currentUser
	 *            ...the current logged in user
	 */
	public void contactWriterSorting(DLinkedList d, String currentUser) {

		String[] temp = new String[d.size()];

		for (int i = 0; i < d.size(); i++) {
			temp[i] = (String) d.get(i);
		}

		Sort s = new Sort();

		temp = s.sort(temp, 0, temp.length - 1, 0);

		FileWriter contacts = null;
		try {
			contacts = new FileWriter("Users/" + currentUser + "/contacts.txt");
		} catch (Exception e) {

		}

		BufferedWriter bWriter = new BufferedWriter(contacts);

		for (int i = 0; i < temp.length; i++) {
			try {
				bWriter.write(temp[i]);
				bWriter.newLine();
			} catch (IOException e) {

			}
		}
		try {
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * It loads the contacts of the current logged in user into double Linked list
	 *
	 * @param currentUser
	 *            ...current logged in user
	 * @return ...double linked list of the contacts
	 */
	public DLinkedList contactLoader(String currentUser) {
		DLinkedList d = new DLinkedList();

		FileReader contacts = null;
		try {
			contacts = new FileReader("Users/" + currentUser + "/contacts.txt");
		} catch (FileNotFoundException e) {

		}

		BufferedReader bReader = new BufferedReader(contacts);

		while (true) {
			try {
				String temp = bReader.readLine();
				if (temp == null) {
					break;
				} else {
					d.add(temp);
				}
			} catch (IOException e) {

			}

		}

		try {
			bReader.close();
		} catch (IOException e) {

		}
		return d;
	}

	/**
	 * It writes the index file of a certain folder from the already loaded double
	 * linked list.
	 *
	 * @param ....dMain
	 *            Pre Loaded before changing index file double linked list
	 * @param currentUser
	 *            ....current logged in user
	 * @param folder
	 *            ....the folder inwhich the index file is written
	 */
	public void indexWriterDraft(DLinkedList dMain, String currentUser, String folder) {
		currentUserApp = currentUser;
		FileWriter n = null;
		try {
			n = new FileWriter("Users/" + currentUser + "/" + folder + "/indexfile.txt");
		} catch (IOException e1) {

			return;
		}

		BufferedWriter bWriterIndex = new BufferedWriter(n);

		for (int j = 0; j < dMain.size(); j++) {
			try {
				bWriterIndex.write((String) dMain.get(j));
				bWriterIndex.newLine();
			} catch (IOException e) {

			}
		}
		try {
			bWriterIndex.close();
		} catch (IOException e) {

		}
	}

	/**
	 * It writes the index file of a certain folder from the already loaded double
	 * linked list.
	 *
	 * @param ....dMain
	 *            Pre Loaded before changing index file double linked list
	 * @param currentUser
	 *            ....current logged in user
	 * @param folder
	 *            ....the folder inwhich the index file is written
	 */
	public void indexWriter(DLinkedList dMain, String currentUser, String folder) {
		currentUserApp = currentUser;
		FileWriter n = null;
		try {
			n = new FileWriter("Users/" + currentUser + "/" + folder + "/indexfile.txt");
		} catch (IOException e1) {

			return;
		}

		BufferedWriter bWriterIndex = new BufferedWriter(n);

		for (int j = 0; j < dMain.size(); j++) {
			try {
				bWriterIndex.write((String) dMain.get(j));
				bWriterIndex.newLine();
			} catch (IOException e) {

			}
		}
		try {
			bWriterIndex.close();
		} catch (IOException e) {

		}
	}

	/**
	 * it creates an SLinked list of the selected emails
	 *
	 * @param index
	 *            ...array of selected indices for emails selected
	 * @param currentUser
	 *            ...current logged in user
	 * @param folder
	 *            ....folder containing the emails selected
	 * @return returns an SLinked list of the selected emails
	 */
	public SLinkedList loadMails(int[] index, String currentUser, String folder) {

		currentUserApp = currentUser;
		SLinkedList s = new SLinkedList();

		// Loads the folder content in double linked list
		DLinkedList dMain = new DLinkedList();
		dMain = indexLoad(currentUser, folder);

		for (int i = 0; i < index.length; i++) {
			String str = (String) dMain.get(index[i]);
			String[] temp = str.split("   ");

			Mail m = new Mail();
			m = new Mail();
			m.setFrom(temp[1]);
			m.setTo(temp[2]);
			m.setSubject(temp[3]);
			m.setPriority(Integer.parseInt(temp[4]));

			FileReader body = null;
			try {
				body = new FileReader("Users/" + currentUser + "/" + folder + "/" + temp[5] + "/Body.txt");
			} catch (FileNotFoundException e1) {

			}
			BufferedReader bReader = new BufferedReader(body);
			String[] letter = new String[1000];
			int j = 0;
			while (true) {

				String tempTemp = "";
				try {
					tempTemp = bReader.readLine();
				} catch (IOException e) {

				}
				if (tempTemp == null) {
					try {
						bReader.close();
					} catch (IOException e) {

					}
					break;
				}
				letter[j] = tempTemp;
				j++;
			}

			try {
				bReader.close();
			} catch (IOException e) {

			}

			m.setEmail(letter);

			String location = "Users/" + currentUser + "/" + folder + "/" + temp[5] + "/attachments";
			File filename = new File(location);
			File[] files = filename.listFiles();
			SLinkedList attach = new SLinkedList();
			if (Files.exists(Paths.get(location))) {
				for (File file : files) {
					attach.add(file.getAbsolutePath());
				}
				m.setAttachments(attach);
			}
			s.add(m);
		}
		return s;
	}

	/*
	 * *****************************************************************************
	 * **********************
	 */
	private int[] selectedIndices = null;
	int selectedIndex = 0;
	String currentPlace = null;
	String selectedComboBox = null;
	String[] selected = null;

	public void setSelected(int[] x) {
		selectedIndices = x;
	}

	public void setSeected4(String[] l) {
		selected = l;
	}

	public void setSelected2(String y) {
		currentPlace = y;
	}

	public void setSelected3(String z) {
		selectedComboBox = z;
	}

	public int[] getSelected() {
		return selectedIndices;
	}

	public String getSelected2() {
		return currentPlace;
	}

	public String getSelected3() {
		return selectedComboBox;
	}

	public String[] getSelected4() {
		return selected;
	}
	/*
	 * *****************************************************************************
	 * ***********************
	 */

	/**
	 * Delete or move for the filter move to button option
	 *
	 * @param currentUser
	 *            ...current logged in user
	 * @param folder
	 *            ...folder selected in filtering
	 * @param option
	 *            ...the folder to which the selected emails will be moved
	 * @param index
	 *            ...array of strings of the emails folders names to be moved
	 */
	public void deleteORMoveString(String currentUser, String folder, String option, String[] index) {

		currentUserApp = currentUser;
		fromFolder = folder;
		// Loads the folder content in double linked list
		DLinkedList dMain = new DLinkedList();
		DLinkedList dSub = new DLinkedList();
		dMain = indexLoad(currentUser, folder);
		dSub = indexLoad(currentUser, option);
		int[] in = new int[index.length];

		SLinkedList sMain = new SLinkedList();
		// sMain = loadMails(index, currentUser, folder);

		Folder x = new Folder();
		x.setFolder(option);

		// Creates the trash folder for the current logged in user and creates an index
		// file for it
		if (!Files.exists(Paths.get("Users/" + currentUser + "/" + option))) {
			JOptionPane.showMessageDialog(null, "Folder doesn't exist");
			return;
		}

		int j = 0;
		if (option.equals("Trash")) {
			// deleteEmails(sMain);
			for (int i = 0; i < dMain.size(); i++) {
				sMain = new SLinkedList();
				String d = (String) dMain.get(i);
				// dMain.remove(index[i]);
				String[] d5 = d.split("   ");
				for (int k = 0; k < index.length; k++) {
					if (d5[5].equals(index[k])) {
						File file = new File("Users/" + currentUser + "/" + folder + "/" + d5[5]);
						delete(file);
						in[j] = i;
						j++;
					}
				}
			}

			Arrays.sort(in);

			for (j = in.length - 1; j >= 0; j--) {
				dMain.remove(in[j]);
			}

			indexWriter(dMain, currentUser, folder);
			indexWriter(dSub, currentUser, option);

		} else {

			for (int i = dMain.size() - 1; i >= 0; i--) {
				sMain = new SLinkedList();
				String d = (String) dMain.get(i);
				// dMain.remove(index[i]);
				String[] d5 = d.split("   ");
				for (int k = 0; k < index.length; k++) {

					if (d5[5].equals(index[k])) {
						String temp = "";
						int counter = 0;
						File file = new File("Users/" + currentUser + "/" + folder + "/" + d5[5]);
						File fileDest = new File("Users/" + currentUser + "/" + x + "/" + d5[3]);
						String t = d5[3];
						while (Files.exists(Paths.get("Users/" + currentUser + "/" + x + "/" + t))) {
							t += counter;
							counter++;
							fileDest = new File("Users/" + currentUser + "/" + x + "/" + t);
						}
						try {
							FileReader r = new FileReader(file);
							r.close();
							FileReader r2 = new FileReader(fileDest);
							r2.close();
							Files.move(Paths.get(file.getAbsolutePath()), Paths.get(fileDest.getAbsolutePath()));
							dSub.add(d5[0] + "   " + d5[1] + "   " + d5[2] + "   " + d5[3] + "   " + d5[4] + "   " + t);
						} catch (IOException e) {

						}
						delete(file);
						in[j] = i;
						j++;
					}
				}

			}

			Arrays.sort(in);

			for (j = in.length - 1; j >= 0; j--) {
				dMain.remove(in[j]);
			}

			indexWriter(dMain, currentUser, folder);
			indexWriter(dSub, currentUser, option);
		}
	}

	public void deleteORMove(String currentUser, String folder, String option, int[] index) {

		currentUserApp = currentUser;
		fromFolder = folder;
		// Loads the folder content in double linked list
		DLinkedList dMain = new DLinkedList();
		dMain = indexLoad(currentUser, folder);

		SLinkedList sMain = new SLinkedList();
		sMain = loadMails(index, currentUser, folder);

		Folder x = new Folder();
		x.setFolder(option);

		// Creates the trash folder for the current logged in user and creates an index
		// file for it
		if (!Files.exists(Paths.get("Users/" + currentUser + "/" + option))) {
			JOptionPane.showMessageDialog(null, "Folder doesn't exist");
			return;
		}

		if (option.equals("Trash")) {
			deleteEmails(sMain);
			for (int i = index.length - 1; i >= 0; i--) {
				sMain = new SLinkedList();
				String d = (String) dMain.get(index[i]);
				dMain.remove(index[i]);
				String[] d5 = d.split("   ");
				File file = new File("Users/" + currentUser + "/" + folder + "/" + d5[5]);
				delete(file);
			}

			indexWriter(dMain, currentUser, folder);
		} else {
			moveEmails(sMain, x);
			for (int i = index.length - 1; i >= 0; i--) {
				sMain = new SLinkedList();
				String d = (String) dMain.get(index[i]);
				dMain.remove(index[i]);
				String[] d5 = d.split("   ");
				File file = new File("Users/" + currentUser + "/" + folder + "/" + d5[5]);
				delete(file);
			}
			indexWriter(dMain, currentUser, folder);
		}
	}

	/**
	 * it writes the content of DLinked list with the content of folder option
	 *
	 * @param d
	 *            ...DLinked list with the content of folder option
	 * @param currentUser
	 *            ...current logged in user
	 */
	public void folderOptionWriter(DLinkedList d, String currentUser) {

		FileWriter r = null;
		try {
			r = new FileWriter("Users/" + currentUser + "/folderoption.txt");
		} catch (Exception e) {

		}
		BufferedWriter bWriter = new BufferedWriter(r);

		String temp = "";
		for (int i = 0; i < d.size(); i++) {

			try {
				bWriter.write((String) d.get(i));
				bWriter.newLine();
			} catch (IOException e) {

			}

		}
		try {
			bWriter.close();
		} catch (IOException e) {

		}

	}

	/**
	 * it reads the content of folder option
	 *
	 * @param currentUser
	 *            ...current logged in user
	 * @return ...DLinked list with the content of folder option
	 */
	public DLinkedList folderOptionReader(String currentUser) {

		FileReader r = null;
		try {
			r = new FileReader("Users/" + currentUser + "/folderoption.txt");
		} catch (FileNotFoundException e) {

		}
		BufferedReader bReader = new BufferedReader(r);
		DLinkedList d = new DLinkedList();

		String temp = "";
		while (true) {

			try {
				temp = bReader.readLine();
			} catch (IOException e) {

			}

			if (temp == null) {
				break;
			}

			d.add(temp);
		}
		try {
			bReader.close();
		} catch (IOException e) {

		}

		return d;
	}

	/**
	 * It loads the indexfile.txt of any folder into a double linked list
	 * 
	 * @param currentUser
	 *            ...current logged in user
	 * @param folder
	 *            ...folder required to be loaded
	 * @return ...returns double linked list with the files loaded
	 */
	public DLinkedList indexLoad(String currentUser, String folder) {

		currentUserApp = currentUser;
		DLinkedList d = new DLinkedList();
		FileReader content = null;
		try {
			content = new FileReader("Users/" + currentUser + "/" + folder + "/indexfile.txt");
		} catch (IOException e1) {

		}

		if (!Files.exists(Paths.get("Users/" + currentUser + "/" + folder + "/indexfile.txt"))) {
			return d;
		}
		BufferedReader bReader = new BufferedReader(content);

		while (true) {
			try {
				String temp = bReader.readLine();
				if (temp == null) {
					try {
						bReader.close();
					} catch (IOException e) {

					}
					break;
				}
				d.add(temp);
			} catch (Exception e) {

			}
		}
		try {
			bReader.close();
		} catch (IOException e) {

		}
		return d;
	}

	/**
	 * creates a model priority list for the selected folder from the list of
	 * folders
	 *
	 * @param currentUser
	 *            the current logged in user
	 * @param folder
	 *            the folder chosen from the list
	 * @return the model priority list made from the index file
	 */
	public DefaultListModel modelMakerPriority(String currentUser, String folder) {

		currentUserApp = currentUser;
		DefaultListModel model = new DefaultListModel();
		DLinkedList d = new DLinkedList();
		PriorityQueue pQ = new PriorityQueue();
		String res = "";

		d = indexLoad(currentUser, folder);
		String[] te = new String[d.size()];

		for (int i = 0; i < d.size(); i++) {
			te[i] = (String) d.get(i);
			String[] te5 = te[i].split("   ");
			pQ.insert(te[i], 5 - Integer.parseInt(te5[4]));
		}

		d = new DLinkedList();
		while (!pQ.isEmpty()) {
			try {
				String temp = (String) pQ.removeMin();
				d.add(temp);
				String[] temp1 = temp.split("   ");
				if (folder.equals("Sent")) {
					if (Files.exists(
							Paths.get("Users/" + currentUser + "/" + folder + "/" + temp1[5] + "/attachments"))) {
						res = "Date: " + temp1[0] + "    To: " + temp1[2] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: YES";
					} else {
						res = "Date: " + temp1[0] + "    To: " + temp1[2] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: NO";
					}
				} else {
					if (Files.exists(
							Paths.get("Users/" + currentUser + "/" + folder + "/" + temp1[5] + "/attachments"))) {
						res = "Date: " + temp1[0] + "    From: " + temp1[1] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: YES";
					} else {
						res = "Date: " + temp1[0] + "    From: " + temp1[1] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: NO";
					}
				}

				model.addElement(res);
			} catch (Exception e) {
				break;
			}
		}

		indexWriter(d, currentUser, folder);

		return model;
	}

	/**
	 * creates a model list for the selected folder from the list of folders
	 *
	 * @param currentUser
	 *            the current logged in user
	 * @param folder
	 *            the folder chosen from the list
	 * @return the model list made from the index file
	 */
	public DefaultListModel modelMaker(String currentUser, String folder) {

		currentUserApp = currentUser;
		DefaultListModel model = new DefaultListModel();
		DLinkedList d = new DLinkedList();
		String res = "";

		d = indexLoad(currentUser, folder);

		String[] te = new String[d.size()];

		for (int i = 0; i < d.size(); i++) {
			te[i] = (String) d.get(i);
		}

		Sort s = new Sort();
		te = s.sort(te, 0, te.length - 1, 0);

		d = new DLinkedList();

		for (int i = 0; i < te.length; i++) {
			try {
				String temp = te[i];
				d.add(temp);
				String[] temp1 = temp.split("   ");
				if (folder.equals("Sent")) {
					if (Files.exists(
							Paths.get("Users/" + currentUser + "/" + folder + "/" + temp1[5] + "/attachments"))) {
						res = "Date: " + temp1[0] + "    To: " + temp1[2] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: YES";
					} else {
						res = "Date: " + temp1[0] + "    To: " + temp1[2] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: NO";
					}
				} else {
					if (Files.exists(
							Paths.get("Users/" + currentUser + "/" + folder + "/" + temp1[5] + "/attachments"))) {
						res = "Date: " + temp1[0] + "    From: " + temp1[1] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: YES";
					} else {
						res = "Date: " + temp1[0] + "    From: " + temp1[1] + "    Subject: " + temp1[3]
								+ "    Priority: " + temp1[4] + "    Attach.: NO";
					}
				}

				model.addElement(res);
			} catch (Exception e) {
				break;
			}
		}

		indexWriter(d, currentUser, folder);

		return model;
	}

	/**
	 * It loads the selected email.
	 *
	 * @param currentUser
	 *            ....the current logged in user
	 * @param folder
	 *            ....the folder where the email exists
	 * @param index
	 *            ....which email has been selected
	 * @return ....array of strings containing the body of the email
	 */
	public String[] mailLoaderNoInd(String currentUser, String folder, String index) {

		currentUserApp = currentUser;
		String[] letter = new String[1000];
		String fromTo = "";

		// Reads the index file to acces the selected email index
		FileReader email = null;
		try {
			email = new FileReader("Users/" + currentUser + "/" + folder + "/indexfile.txt");
		} catch (FileNotFoundException e) {

		}

		BufferedReader bReader = new BufferedReader(email);
		DLinkedList d = new DLinkedList();
		String temp = "";
		d = indexLoad(currentUser, folder);
		for (int i = 0; i < d.size(); i++) {
			try {
				temp = bReader.readLine();
				if (temp.contains(index)) {
					break;
				}
			} catch (IOException e) {

			}
		}
		try {
			bReader.close();
		} catch (IOException e2) {

		}

		// It reads the line of the selected email and get the name of its folder

		String[] strTemp = temp.split("   ");

		if (folder.equals("Sent")) {
			fromTo = "To:  " + strTemp[2];
		} else {
			fromTo = "From:  " + strTemp[1];
		}

		try {
			email = new FileReader("Users/" + currentUser + "/" + folder + "/" + strTemp[5] + "/Body.txt");
		} catch (FileNotFoundException e) {

		}

		// It reads the body of the email selected and add its content to the array of
		// strings
		bReader = new BufferedReader(email);
		try {
			letter[0] = "Date & Time:  " + bReader.readLine();
			letter[1] = "Subject:  " + bReader.readLine();
			letter[2] = fromTo;
			letter[3] = " ";
		} catch (IOException e1) {

		}

		int i = 4;
		while (true) {

			String temp5 = "";
			try {
				temp5 = bReader.readLine();
			} catch (IOException e) {

			}
			if (temp5 == null) {
				break;
			}
			letter[i] = temp5;
			i++;
		}

		try {
			bReader.close();
		} catch (IOException e) {

		}

		return letter;
	}

	/**
	 * It loads the selected email.
	 *
	 * @param currentUser
	 *            ....the current logged in user
	 * @param folder
	 *            ....the folder where the email exists
	 * @param index
	 *            ....which email has been selected
	 * @return ....array of strings containing the body of the email
	 */
	public String[] mailLoader(String currentUser, String folder, int index) {

		currentUserApp = currentUser;
		String[] letter = new String[1000];
		String fromTo = "";

		// Reads the index file to acces the selected email index
		FileReader email = null;
		try {
			email = new FileReader("Users/" + currentUser + "/" + folder + "/indexfile.txt");
		} catch (FileNotFoundException e) {

		}

		BufferedReader bReader = new BufferedReader(email);
		for (int i = 0; i < index; i++) {
			try {
				bReader.readLine();
			} catch (IOException e) {

			}
		}

		// It reads the line of the selected email and get the name of its folder
		String str = "";
		try {
			str = bReader.readLine();
		} catch (IOException e) {

		}
		String[] strTemp = str.split("   ");

		if (folder.equals("Sent")) {
			fromTo = "To:  " + strTemp[2];
		} else {
			fromTo = "From:  " + strTemp[1];
		}

		try {
			email = new FileReader("Users/" + currentUser + "/" + folder + "/" + strTemp[5] + "/Body.txt");
		} catch (FileNotFoundException e) {

		}

		// It reads the body of the email selected and add its content to the array of
		// strings
		bReader = new BufferedReader(email);
		try {
			letter[0] = "Date & Time:  " + bReader.readLine();
			letter[1] = "Subject:  " + bReader.readLine();
			letter[2] = fromTo;
			letter[3] = " ";
		} catch (IOException e1) {

		}

		int i = 4;
		while (true) {

			String temp = "";
			try {
				temp = bReader.readLine();
			} catch (IOException e) {

			}
			if (temp == null) {
				break;
			}
			letter[i] = temp;
			i++;
		}

		try {
			bReader.close();
		} catch (IOException e) {

		}

		return letter;
	}

	/**
	 * Copies the attachment files from its source path into a new folder inside the
	 * inbox
	 *
	 * @param source
	 *            the source of the attachment
	 * @param dest
	 *            the destination to which the attachment is going
	 */
	private static void StreamCopy(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}
	}

	/**
	 * creates a model list for the selected folder from the list of folders
	 *
	 * @param currentUser
	 *            the current logged in user
	 * @param folder
	 *            the folder chosen from the list
	 * @return the model list made from the index file
	 */
	public DefaultListModel modelMakerDraft(String currentUser, String folder) {

		currentUserApp = currentUser;
		DefaultListModel model = new DefaultListModel();
		DLinkedList d = new DLinkedList();
		String res = "";

		d = indexLoad(currentUser, folder);

		String[] te = new String[d.size()];

		for (int i = 0; i < d.size(); i++) {
			te[i] = (String) d.get(i);
		}

		Sort s = new Sort();
		te = s.sort(te, 0, te.length - 1, 0);

		d = new DLinkedList();

		for (int i = 0; i < te.length; i++) {
			try {
				String temp = te[i];
				d.add(temp);
				String[] temp1 = temp.split("&&&&");
				if (Files.exists(
						Paths.get("Users/" + currentUser + "/" + folder + "/" + temp1[4].trim() + "/attachments"))) {

					res = "To: " + temp1[1] + "    Subject: " + temp1[2] + "    Priority: " + temp1[3]
							+ "    Attach.: YES";
				} else {

					res = "To: " + temp1[1] + "    Subject: " + temp1[2] + "    Priority: " + temp1[3]
							+ "    Attach.: NO";
				}

				model.addElement(res);
			} catch (Exception e) {
				break;
			}
		}

		indexWriter(d, currentUser, folder);

		return model;
	}

	/**
	 * It loads the attachments of a certain Email
	 *
	 * @param currentUser
	 *            ...current logged in user
	 * @param folder
	 *            ...the parent folder
	 * @param sub
	 *            ...the email folder
	 * @return ...returns an SLinked list of the attachments
	 */
	public SLinkedList attachmentLoader(String currentUser, String folder, String sub) {

		SLinkedList attach = new SLinkedList();

		Path a = Paths.get("Users/" + currentUser + "/" + folder + "/" + sub + "/attachments");

		if (!Files.exists(a)) {
			return attach;
		}

		@SuppressWarnings("rawtypes")
		DefaultListModel model = new DefaultListModel();

		String location = "Users/" + currentUser + "/" + folder + "/" + sub + "/attachments";
		File filename = new File(location);
		File[] files = filename.listFiles();
		for (File file : files) {
			String allfiles = file.getAbsolutePath();
			attach.add(allfiles);
		}

		return attach;
	}

	/**
	 * It loads the body of the draft email
	 *
	 * @param currentUser
	 *            ...current logged in user
	 * @param folder
	 *            ...the parent folder
	 * @param sub
	 *            ...the email folder
	 * @return ...returns an array of strings of the body
	 */
	public String[] draftBodyLoader(String currentUser, String folder, String sub) {
		FileReader m = null;
		try {
			m = new FileReader("Users/" + currentUser + "/" + folder + "/" + sub + "/Body.txt");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedReader bReader = new BufferedReader(m);

		String[] temp = new String[1000];

		// Saving the email content in a text file
		try {

			int i = 0;
			while (true) {
				String t = bReader.readLine();
				if (t == null) {
					break;
				}
				temp[i] = t;
				i++;
			}
			bReader.close();
		} catch (Exception e) {
			return temp;
		}
		return temp;
	}

	/**
	 * It loads the selected draft email to be displayed in the compose again
	 *
	 * @param currentUser
	 *            ...the current logged in user
	 * @param index
	 *            ...the index of the selected draft email
	 * @return ...returns the mail selected
	 */
	public Mail draftLoad(String currentUser, int index) {

		Mail m = new Mail();

		DLinkedList d = new DLinkedList();
		SLinkedList attach = new SLinkedList();

		d = indexLoad(currentUser, "Draft");

		String draft = (String) d.get(index);

		String[] temp = draft.split("&&&&");

		m.setFrom(currentUser);
		m.setTo(temp[1]);
		m.setSubject(temp[2]);
		m.setPriority(Integer.parseInt(temp[3]));
		String sub = temp[4];
		attach = attachmentLoader(currentUser, "Draft", sub);
		m.setAttachments(attach);
		m.setEmail(draftBodyLoader(currentUser, "Draft", sub));

		return m;
	}

	/**
	 * It handles the saving of the drafted email
	 *
	 * @param mail
	 *            ...the mail to be drafted
	 * @param currentUser
	 *            ...the current logged in user
	 */
	public void savingEmailDraft(Mail mail, String currentUser) {

		currentUserApp = currentUser;
		int counter = 0;
		String temp = "Users/" + currentUser + "/Draft" + "/" + String.valueOf(counter);
		Path data = Paths.get(temp);
		String temp2 = "Users/" + currentUser + "/Draft";
		Path draft = Paths.get(temp2);
		Path inbox = Paths.get(temp2);
		Path indexFile = Paths.get(temp2 + "/indexfile.txt");

		// Checks if the draft folder exists or not and if not creates it
		if (!Files.exists(draft)) {
			try {
				Files.createDirectory(draft);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Checks if the index file for emails in draft exists or not and if not creates
		// it
		if (!Files.exists(indexFile)) {
			try {
				Files.createFile(indexFile);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Writes the important data about the sent mails
		FileWriter n = null;
		try {
			n = new FileWriter(temp2 + "/indexfile.txt", true);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriterIndex = new BufferedWriter(n);

		// Creating new folder whose name is the email subject
		while (Files.exists(data)) {
			temp += counter;
			data = Paths.get(temp);
		}
		String[] t = temp.split("/");

		try {
			String y = mail.getFrom() + "&&&&" + mail.getTo() + "&&&&" + mail.getSubject() + "&&&&" + mail.getPriority()
					+ "&&&&" + t[t.length - 1];
			bWriterIndex.write(y);
			bWriterIndex.newLine();
			bWriterIndex.close();
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		try {
			Files.createDirectory(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// Creating a text file for saving the body of the letter
		String emailFile = temp + "/Body.txt";
		Path letter = Paths.get(emailFile);

		try {
			Files.createFile(letter);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		FileWriter m = null;
		try {
			m = new FileWriter(emailFile);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriter = new BufferedWriter(m);

		// Saving the email content in a text file
		try {

			for (int i = 0; i < mail.getEmail().length; i++) {
				if (mail.getEmail()[i] == null) {
					break;
				}
				bWriter.write(mail.getEmail()[i]);
				bWriter.newLine();
			}
			bWriter.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// makes a new folder for attachments
		if (!mail.getAttachments().isEmpty()) {
			temp += "/attachments";
			Path dataAttach = Paths.get(temp);
			try {
				Files.createDirectory(dataAttach);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// saves the attachments
		for (int i = 0; i < mail.getAttachments().size(); i++) {

			String source = (String) mail.getAttachments().get(i);
			String[] te = source.split("\\\\");
			String destination = temp + "/" + te[te.length - 1];
			File src = new File(source);
			File des = new File(destination);
			try {
				StreamCopy(src, des);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}

		}
	}

	/**
	 * It handles the move and delete of emails
	 *
	 * @param mail
	 *            ...the mail to be deleted or moved
	 * @param currentUser
	 *            ...the current logged in user
	 * @param folder
	 *            ...the folder to be moved to (Trash in case of deleting)
	 * @param time
	 *            ...the date of the email
	 */
	public void savingEmailTrashOrMoved(Mail mail, String currentUser, String folder, String time) {

		currentUserApp = currentUser;
		int counter = 0;
		String temp = "Users/" + currentUser + "/" + folder + "/" + mail.getSubject();
		Path data = Paths.get(temp);
		String temp2 = "Users/" + currentUser + "/" + folder;
		Path sent = Paths.get(temp2);
		Path inbox = Paths.get(temp2);
		Path indexFile = Paths.get(temp2 + "/indexfile.txt");

		// Checks if the inbox folder exists or not and if not creates it
		if (!Files.exists(sent)) {
			try {
				Files.createDirectory(sent);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Checks if the index file for emails in inbox exists or not and if not creates
		// it
		if (!Files.exists(indexFile)) {
			try {
				Files.createFile(indexFile);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Writes the important data about the sent mails
		FileWriter n = null;
		try {
			n = new FileWriter(temp2 + "/indexfile.txt", true);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriterIndex = new BufferedWriter(n);

		// Creating new folder whose name is the email subject
		while (Files.exists(data)) {
			temp += counter;
			data = Paths.get(temp);
		}
		String[] t = temp.split("/");

		try {
			String y = time + "   " + mail.getFrom() + "   " + mail.getTo() + "   " + mail.getSubject() + "   "
					+ mail.getPriority() + "   " + t[t.length - 1] + "   " + fromFolder;
			bWriterIndex.write(y);
			bWriterIndex.newLine();
			bWriterIndex.close();
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		try {
			Files.createDirectory(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// Creating a text file for saving the body of the letter
		String emailFile = temp + "/Body.txt";
		Path letter = Paths.get(emailFile);

		try {
			Files.createFile(letter);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		FileWriter m = null;
		try {
			m = new FileWriter(emailFile);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriter = new BufferedWriter(m);

		// Saving the email content in a text file
		try {

			for (int i = 0; i < mail.getEmail().length; i++) {
				if (mail.getEmail()[i] == null) {
					break;
				}
				bWriter.write(mail.getEmail()[i]);
				bWriter.newLine();
			}
			bWriter.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// makes a new folder for attachments
		if (!mail.getAttachments().isEmpty()) {
			temp += "/attachments";
			Path dataAttach = Paths.get(temp);
			try {
				Files.createDirectory(dataAttach);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// saves the attachments
		for (int i = 0; i < mail.getAttachments().size(); i++) {

			String source = (String) mail.getAttachments().get(i);
			String[] te = source.split("\\\\");
			String destination = temp + "/" + te[te.length - 1];
			File src = new File(source);
			File des = new File(destination);
			try {
				StreamCopy(src, des);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}

		}
	}

	/**
	 * it saves the email in the sent folder of the sending user.
	 *
	 * @param email
	 * @param from
	 * @param subject
	 */
	public void savingEmailSent(Mail mail, String currentUser, String folder) {

		int counter = 0;
		String temp = "Users/" + currentUser + "/" + folder + "/" + mail.getSubject();
		Path data = Paths.get(temp);
		String temp2 = "Users/" + currentUser + "/" + folder;
		Path sent = Paths.get(temp2);
		Path inbox = Paths.get(temp2);
		Path indexFile = Paths.get(temp2 + "/indexfile.txt");

		// Checks if the inbox folder exists or not and if not creates it
		if (!Files.exists(sent)) {
			try {
				Files.createDirectory(sent);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Checks if the index file for emails in inbox exists or not and if not creates
		// it
		if (!Files.exists(indexFile)) {
			try {
				Files.createFile(indexFile);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Writes the important data about the sent mails
		FileWriter n = null;
		try {
			n = new FileWriter(temp2 + "/indexfile.txt", true);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriterIndex = new BufferedWriter(n);
		// get the date and time at which the email is sent
		DateTimeFormatter dtfIndex = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime nowIndex = LocalDateTime.now();

		// Creating new folder whose name is the email subject
		while (Files.exists(data)) {
			temp += counter;
			data = Paths.get(temp);
		}
		String[] t = temp.split("/");

		try {
			bWriterIndex.write(dtfIndex.format(nowIndex) + "   " + mail.getFrom() + "   " + mail.getTo() + "   "
					+ mail.getSubject() + "   " + mail.getPriority() + "   " + t[t.length - 1]);
			bWriterIndex.newLine();
			bWriterIndex.close();
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		try {
			Files.createDirectory(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// Creating a text file for saving the body of the letter
		String emailFile = temp + "/Body.txt";
		Path letter = Paths.get(emailFile);

		try {
			Files.createFile(letter);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		FileWriter m = null;
		try {
			m = new FileWriter(emailFile);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriter = new BufferedWriter(m);
		// get the date and time at which the email is sent
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// Saving the email content in a text file
		try {
			// saving the date and time, subject of the email
			bWriter.write(dtf.format(now));
			bWriter.newLine();
			bWriter.write(mail.getSubject());
			bWriter.newLine();
			for (int i = 0; i < mail.getEmail().length; i++) {
				if (mail.getEmail()[i] == null) {
					break;
				}
				bWriter.write(mail.getEmail()[i]);
				bWriter.newLine();
			}
			bWriter.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// makes a new folder for attachments
		if (!mail.getAttachments().isEmpty()) {
			temp += "/attachments";
			Path dataAttach = Paths.get(temp);
			try {
				Files.createDirectory(dataAttach);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// saves the attachments
		for (int i = 0; i < mail.getAttachments().size(); i++) {

			String source = (String) mail.getAttachments().get(i);
			String[] te = source.split("\\\\");
			String destination = temp + "/" + te[te.length - 1];
			File src = new File(source);
			File des = new File(destination);
			try {
				StreamCopy(src, des);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}

		}

	}

	/**
	 * It sends the email to the requested user.
	 *
	 * @param email
	 *            ....The body of the email to be sent
	 * @param to
	 *            ....the username to which the email is sent
	 * @param subject
	 *            ....the subject of the email to be sent
	 */
	private void sendingEmail(Mail mail, String folder) {

		int counter = 0;
		String temp = "Users/" + mail.getTo() + "/" + folder + "/" + mail.getSubject();
		Path data = Paths.get(temp);
		String temp2 = "Users/" + mail.getTo() + "/" + folder;
		Path inbox = Paths.get(temp2);
		Path indexFile = Paths.get(temp2 + "/indexfile.txt");

		// Checks if the inbox folder exists or not and if not creates it
		if (!Files.exists(inbox)) {
			try {
				Files.createDirectory(inbox);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Checks if the index file for emails in inbox exists or not and if not creates
		// it
		if (!Files.exists(indexFile)) {
			try {
				Files.createFile(indexFile);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// Writes the important data about the sent mails
		FileWriter n = null;
		try {
			n = new FileWriter(temp2 + "/indexfile.txt", true);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriterIndex = new BufferedWriter(n);
		// get the date and time at which the email is sent
		DateTimeFormatter dtfIndex = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime nowIndex = LocalDateTime.now();

		// Creating new folder whose name is the email subject
		while (Files.exists(data)) {
			temp += counter;
			data = Paths.get(temp);
		}
		String[] t = temp.split("/");

		try {
			bWriterIndex.write(dtfIndex.format(nowIndex) + "   " + mail.getFrom() + "   " + mail.getTo() + "   "
					+ mail.getSubject() + "   " + mail.getPriority() + "   " + t[t.length - 1]);
			bWriterIndex.newLine();
			bWriterIndex.close();
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		try {
			Files.createDirectory(data);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// Creating a text file for saving the body of the letter
		String emailFile = temp + "/Body.txt";
		Path letter = Paths.get(emailFile);

		try {
			Files.createFile(letter);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		FileWriter m = null;
		try {
			m = new FileWriter(emailFile);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		BufferedWriter bWriter = new BufferedWriter(m);
		// get the date and time at which the email is sent
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// Saving the email content in a text file
		try {
			// saving the date and time, subject of the email
			bWriter.write(dtf.format(now));
			bWriter.newLine();
			bWriter.write(mail.getSubject());
			bWriter.newLine();
			int i = 0;
			for (i = 0; i < mail.getEmail().length; i++) {
				bWriter.write(mail.getEmail()[i]);
				bWriter.newLine();
			}
			bWriter.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong!!");
		}

		// makes a new folder for attachments
		if (!mail.getAttachments().isEmpty()) {
			temp += "/attachments";
			Path dataAttach = Paths.get(temp);
			try {
				Files.createDirectory(dataAttach);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}
		}

		// saves the attachments
		for (int i = 0; i < mail.getAttachments().size(); i++) {

			String source = (String) mail.getAttachments().get(i);
			String[] te = source.split("\\\\");
			String destination = temp + "/" + te[te.length - 1];
			File src = new File(source);
			File des = new File(destination);
			try {
				StreamCopy(src, des);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
			}

		}

	}

	public boolean folderMaker(Filter filter) {

		Path f = Paths.get("Users/" + filter.getCurrentUser() + "/" + filter.getFolderName());

		// Checks if the folder already exists
		if (Files.exists(f)) {
			return false;
		}

		// Creates the required folder
		try {
			Files.createDirectory(f);
			Files.createFile(
					Paths.get("Users/" + filter.getCurrentUser() + "/" + filter.getFolderName() + "/indexfile.txt"));
			if (!Files.exists(Paths.get("Users/" + filter.getCurrentUser() + "/filters.txt"))) {
				Files.createFile(Paths.get("Users/" + filter.getCurrentUser() + "/filters.txt"));
			}
		} catch (IOException e) {

		}

		FileWriter fil = null;
		try {
			fil = new FileWriter("Users/" + filter.getCurrentUser() + "/filters.txt", true);
		} catch (IOException e1) {
			return false;
		}

		int counter = filter.getFiltersFrom().size() + filter.getFiltersSubject().size()
				+ filter.getFiltersTopic().size();

		BufferedWriter bWriter;
		try {
			bWriter = new BufferedWriter(fil);
		} catch (Exception e) {
			return false;
		}

		try {
			bWriter.write(filter.getFolderName());
			bWriter.newLine();
			bWriter.write(String.valueOf(counter));
			bWriter.newLine();
			if (!filter.getFiltersFrom().equals(null)) {
				for (int i = 0; i < filter.getFiltersFrom().size(); i++) {
					bWriter.write((String) filter.getFiltersFrom().get(i));
					bWriter.newLine();
				}
			}

			if (!filter.getFiltersSubject().equals(null)) {
				for (int i = 0; i < filter.getFiltersSubject().size(); i++) {
					bWriter.write((String) filter.getFiltersSubject().get(i));
					bWriter.newLine();
				}
			}
			if (!filter.getFiltersTopic().equals(null)) {
				for (int i = 0; i < filter.getFiltersTopic().size(); i++) {
					bWriter.write((String) filter.getFiltersTopic().get(i));
					bWriter.newLine();
				}
			}
			bWriter.close();
		} catch (IOException e) {

		}

		return true;
	}

	/**
	 * checks if the username exists
	 *
	 * @param username
	 *            ....the username to be checked.
	 * @return ....false if it doesn't exist or true if does exist.
	 */
	private boolean userCheck(String username) {

		if (username.equals(null) || username.equals("")) {
			return false;
		}
		String temp = "Users/" + username;
		Path user = Paths.get(temp);

		if (!Files.exists(user)) {
			return false;
		}
		return true;
	}

	/**
	 * Loads the contacts of the current logged in user
	 * 
	 * @param currentUser
	 * @return
	 */
	public String[] contactMaker(String currentUser) {
		FileReader users = null;
		if (!Files.exists(Paths.get("Users/" + currentUser + "/contacts.txt"))) {
			try {
				Files.createFile(Paths.get("Users/" + currentUser + "/contacts.txt"));
			} catch (IOException e) {

			}
		}
		try {
			users = new FileReader("Users/" + currentUser + "/contacts.txt");
		} catch (FileNotFoundException e) {

		}

		BufferedReader bReader = new BufferedReader(users);
		String[] contacts = new String[1000];
		int i = 0;
		while (true) {
			try {
				String temp = bReader.readLine();
				if (temp != null && !temp.equals("")) {
					contacts[i] = temp;
					i++;
				} else {
					bReader.close();
					return contacts;
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Something went wrong!!");
				return contacts;
			}
		}
	}

	/**
	 * it checks if the contact who the current user trying to add exists.
	 *
	 * @param firstName
	 *            ....new contact's first name
	 * @param lastName
	 *            ....new contact's last name
	 * @return ....it returns a string containing the infos about the added contact
	 *         to be added to the model list
	 */
	public DefaultListModel contactCheck(String currentUser, String firstName, String lastName) {

		String temp = "";
		DefaultListModel model = new DefaultListModel();
		FileReader users = null;
		FileWriter finalCont = null;
		try {
			users = new FileReader("Users/usernames.txt");
		} catch (IOException e1) {

		}

		BufferedReader bReader = new BufferedReader(users);

		// reading the usernames text file
		while (true) {
			try {
				String tempUserContent = bReader.readLine();
				if (tempUserContent == null || tempUserContent.equals("")) {
					if (temp.equals("")) {
						bReader.close();
						return model;
					} else {
						try {
							finalCont = new FileWriter("Users/" + currentUser + "/contacts.txt", true);
							BufferedWriter bWriter = new BufferedWriter(finalCont);
							bWriter.write(firstName + " " + lastName + ":   " + temp);
							bWriter.newLine();
							model.addElement(firstName + " " + lastName + ":   " + temp);
							bReader.close();
							bWriter.close();
							return model;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Something went wrong!!");
							return model;
						}

					}

				}
				String[] userContent = tempUserContent.split("  ");
				String tempFist = userContent[2];
				String tempLast = userContent[3];
				if (tempFist.equals(firstName)) {
					if (tempLast.equals(lastName)) {
						temp += userContent[0] + "   ";
					}
				}
			} catch (IOException e) {
				try {
					bReader.close();
				} catch (IOException e1) {

				}
				return model;
			}
		}

	}

	/**
	 * Checks if the username and password of the user logging in are correct.
	 *
	 * @param username
	 *            ....entered username
	 * @param password
	 *            ....entered password
	 * @return false if not valid data, true if the username and password are
	 *         correct.
	 */
	private boolean loginUser(String username, String password) {

		// opens and read the all the usernames existing and search for the entered
		// username and password
		FileReader users = null;
		try {
			users = new FileReader("Users/usernames.txt");
		} catch (IOException e1) {

		}

		BufferedReader bReader = new BufferedReader(users);

		// reading the usernames text file
		while (true) {
			try {
				String tempUserContent = bReader.readLine();
				String[] userContent = tempUserContent.split("  ");
				String tempUser = userContent[0];
				String tempPass = userContent[1];
				if (tempUser.equals(username)) {
					if (tempPass.equals(password)) {
						bReader.close();
						return true;
					} else {
						bReader.close();
						return false;
					}
				}
			} catch (IOException e) {
				try {
					bReader.close();
				} catch (IOException e1) {

				}
				return false;
			}
		}

	}

	/**
	 * creates new user by creating new folder for him. And adding his info to the
	 * data text file.
	 *
	 * @param username
	 *            ....new username
	 * @param password
	 *            ....new password
	 * @return false if already exists, true if not.
	 */
	private boolean createUser(IContact contact) {

		Contact con = new Contact();
		con = (Contact) contact;
		// main folder which contains all user folders
		Path user = Paths.get("Users");
		// check if the main folder of users not exists and if so creates it
		if (!Files.exists(user)) {
			try {
				Files.createDirectory(user);
			} catch (IOException e) {
				JOptionPane.showInputDialog("Oops! Something went wrong.");
			}
		}

		// data text file which contains usernames and passwords of all users
		Path data = Paths.get("Users/usernames.txt");
		// check if the data text file not exist and if so creates it
		if (!Files.exists(data)) {
			try {
				Files.createFile(data);
			} catch (IOException e) {
				JOptionPane.showInputDialog("Oops! Something went wrong.");
			}
		}

		FileWriter users = null;
		try {
			users = new FileWriter("Users/usernames.txt", true);
		} catch (IOException e1) {

		}

		BufferedWriter bWriter = new BufferedWriter(users);

		// checks if the username already exist by checking if there is a folder with
		// the same name
		String url = "Users/" + con.getUsername();
		Path folder = Paths.get(url);
		if (Files.exists(folder)) {
			return false;
		}

		// If the username is valid we create a folder with his name
		try {
			Files.createDirectory(folder);
			Files.createDirectory(Paths.get("Users/" + con.getUsername() + "/Trash"));
			Files.createFile(Paths.get("Users/" + con.getUsername() + "/Trash/indexfile.txt"));
		} catch (IOException e) {
			return false;
		}

		// Here i append new user data to the existing data text file
		try {
			bWriter.write(con.getUsername() + "  ");
			bWriter.write(con.getPassword() + "  ");
			bWriter.write(con.getFirstName() + "  ");
			bWriter.write(con.getLastName() + "  ");
			bWriter.newLine();
			bWriter.close();
		} catch (IOException e) {

		}

		// Makes an Inbox folder inside it
		try {
			Files.createDirectory(Paths.get("Users/" + con.getUsername() + "/Inbox"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean signin(final String email, final String password) {

		// main folder which contains all user folders
		Path user = Paths.get("Users");
		// check if the main folder of users not exists and if so creates it
		if (!Files.exists(user)) {
			return false;
		}

		String url = "Users/" + email;
		Path folder = Paths.get(url);
		Path data = Paths.get("Users/usernames.txt");

		if (Files.exists(folder)) {
			return loginUser(email, password);
		} else {
			return false;
		}
	}

	@Override
	public boolean signup(final IContact contact) {

		boolean check = createUser(contact);

		if (check) {
			JOptionPane.showMessageDialog(null, "Sign up went successfully.", null, JOptionPane.DEFAULT_OPTION);
		}

		return check;
	}

	@Override
	public void setViewingOptions(final IFolder folder, final IFilter filter, final ISort sort) {

	}

	@Override
	public IMail[] listEmails(final int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmails(final ILinkedList mails) {
		for (int i = 0; i < mails.size(); i++) {
			Mail m = new Mail();
			m = (Mail) mails.get(i);
			String[] temp = m.getEmail();
			String time = m.getEmail()[0];
			savingEmailTrashOrMoved(m, currentUserApp, "Trash", time);
		}
	}

	@Override
	public void moveEmails(final ILinkedList mails, final IFolder des) {
		Folder destination = (Folder) des;
		for (int i = 0; i < mails.size(); i++) {
			Mail m = new Mail();
			m = (Mail) mails.get(i);
			String[] temp = m.getEmail();
			String time = m.getEmail()[0];
			savingEmailTrashOrMoved(m, currentUserApp, destination.getFolder(), time);
		}
	}

	@Override
	public boolean compose(final IMail email) {

		Mail e = new Mail();
		e = (Mail) email;
		if (!userCheck(e.getTo())) {
			return false;
		}

		sendingEmail(e, "Inbox");
		savingEmailSent(e, e.getFrom(), "Sent");

		return true;
	}

}
