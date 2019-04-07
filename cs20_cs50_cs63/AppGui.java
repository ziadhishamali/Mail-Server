package eg.edu.alexu.csd.datastructure.mailServer.cs20_cs50_cs63;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs20_cs63.SLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.queue.cs20_cs63_cs50.LinkedBasedQueue;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DesktopManager;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

public class AppGui {

	private JFrame frame;
	private JTextField textFieldTo;
	private JTextField textFieldSubject;
	private JTextArea textAreaLetter;
	private JPanel composeField;
	private JPanel inboxField;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JTextField textUsernameLog;
	private JPasswordField passwordFieldLog;
	private JPasswordField passwordField_9;
	private JPanel signupField;
	private JPanel loginSignupField;
	private JPanel loginField;
	private JPanel Menu;
	private String currentUser = "";
	private String currentOpened = "Inbox";
	private int currentIndex = -1;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JPanel ContactsField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField subop;
	private JList contactList;
	private JList listAttachments;
	private JList list_2;
	private JList list_file;
	private JList list;
	private JList listPriority;
	private JList trashlist;
	private JLabel label;
	private JComboBox MovingFilesComboBox; // ComboBox
	private JComboBox comboBoxContacts;
	private JComboBox comboBox;
	private JComboBox comboBoxPriorityInb;
	private DefaultListModel modelCon = new DefaultListModel();
	private JPanel filterField;
	private JPanel moveFilesField; // moveField
	private JPanel panelRename;
	private JButton btnPreviousPage;
	private JButton btnNextPage;
	private JLabel lblPage;
	private JPanel File_add_panel;
	private JTextField add_folder_name;
	private JTextField destTextfiled; // destination text field
	private JComboBox comboBox_add;
	private JList list_add_option;
	private JTextArea textAreaMail;
	private JPanel panel_1;
	private JButton btnRename;
	private JButton btnNewButton;
	private JButton btnRemove;
	private JButton btnBack_2;
	private JButton btnBack_4;
	private JButton btnBack_8 = new JButton();
	private JButton btnBack_3;
	private JButton btnMove;
	private JButton btnMove10;
	private Filter f = new Filter();
	private DLinkedList dMain = new DLinkedList();
	private SLinkedList attachments = new SLinkedList();
	private SLinkedList filtersFrom = new SLinkedList();
	private SLinkedList filtersSubject = new SLinkedList();
	private SLinkedList filtersTopic = new SLinkedList();
	private JTextField textField_4;
	private JList list_4;
	private JTextField textFieldProfile;
	private JPasswordField passwordFieldProfile;
	private JTextField textFieldFirstNameProfile;
	private JTextField textFieldLastNameProfile;
	private JTextField jtxSearchitem;
	private JComboBox foldername;
	private JComboBox topics;
	private JButton btnSearch;
	private JList searchlist;
	private JButton btnMoveEmail;
	private String x;
	private String y;
	private JTextField fromop;
	private JTextField textop;
	private JPasswordField passwordField_1;
	private JTextField esuboption;
	private JTextField ebodyop;
	private JTextField efromop;
	private JPanel panelDraft;
	private JPanel panelProfile;
	private JButton btnEditOption;
	private JPanel editoption;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGui window = new AppGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		App a = new App();

		DefaultListModel model = new DefaultListModel();
		DefaultListModel modelTrash = new DefaultListModel();
		DefaultListModel modelFilters = new DefaultListModel();

		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane);
		desktopPane.setLayout(null);

		/*
		 * *****************************************************************************
		 * **********************
		 */

		File_add_panel = new JPanel();
		File_add_panel.setVisible(false);
		File_add_panel.setBounds(380, 144, 622, 361);
		desktopPane.add(File_add_panel);
		File_add_panel.setLayout(null);

		add_folder_name = new JTextField();
		add_folder_name.setBounds(169, 15, 179, 26);
		add_folder_name.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		File_add_panel.add(add_folder_name);
		add_folder_name.setColumns(10);

		JLabel lblFolderName = new JLabel("folder name :");
		lblFolderName.setBounds(22, 11, 125, 29);
		lblFolderName.setFont(new Font("Bahnschrift", Font.PLAIN, 19));
		File_add_panel.add(lblFolderName);

		JButton btnCancel_add = new JButton("cancel");
		btnCancel_add.setBounds(482, 307, 97, 25);
		btnCancel_add.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnCancel_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_add_panel.setVisible(false);
			}
		});
		File_add_panel.add(btnCancel_add);

		JLabel lblSetYourOption = new JLabel("set your subject option :");
		lblSetYourOption.setBounds(22, 76, 179, 29);
		lblSetYourOption.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		File_add_panel.add(lblSetYourOption);

		JButton btnNewButton_1 = new JButton("ok");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.setCurrentUser(currentUser);
				f.setFolderName(add_folder_name.getText());

				boolean check = a.folderMaker(f);

				String foldername = add_folder_name.getText();
				add_folder_name.setText("");
				File_add_panel.setVisible(false);
				doinglist();
				f = new Filter();
				String sub = subop.getText();
				String fom = fromop.getText();
				String text = textop.getText();
				String all = foldername;
				if (sub.equals("")) {
					all = all + "   " + "+%+%";
				} else {
					all = all + "   " + sub;
				}
				if (fom.equals("")) {
					all = all + "   " + "+%+%";
				} else {
					all = all + "   " + fom;
				}
				if (text.equals("")) {
					all = all + "   " + "+%+%";
				} else {
					all = all + "   " + text;
				}
				FileWriter contacts = null;
				try {
					contacts = new FileWriter("Users/" + currentUser + "/folderoption.txt", true);
				} catch (Exception e) {

				}
				BufferedWriter bWriter = new BufferedWriter(contacts);
				try {
					bWriter.write(all);
					bWriter.newLine();
				} catch (IOException e) {

				}

				try {
					bWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(366, 307, 97, 25);
		btnNewButton_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		File_add_panel.add(btnNewButton_1);

		subop = new JTextField();
		subop.setBounds(22, 116, 231, 20);
		File_add_panel.add(subop);
		subop.setColumns(10);

		fromop = new JTextField();
		fromop.setBounds(22, 199, 231, 22);
		File_add_panel.add(fromop);
		fromop.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("set your from option :");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 161, 179, 29);
		File_add_panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("set your text option :");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(22, 243, 179, 29);
		File_add_panel.add(lblNewLabel_2);

		textop = new JTextField();
		textop.setBounds(22, 283, 231, 22);
		File_add_panel.add(textop);
		textop.setColumns(10);

		JPanel panelProfile_1 = new JPanel();
		panelProfile_1.setBounds(315, 11, 759, 637);
		desktopPane.add(panelProfile_1);
		panelProfile_1.setLayout(null);
		panelProfile_1.setVisible(false);

		JLabel label_2 = new JLabel("Username: ");
		label_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		label_2.setBounds(87, 191, 118, 20);
		panelProfile_1.add(label_2);

		textFieldProfile = new JTextField();
		textFieldProfile.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textFieldProfile.setColumns(10);
		textFieldProfile.setBounds(87, 222, 506, 25);
		panelProfile_1.add(textFieldProfile);

		JLabel label_3 = new JLabel("Password: ");
		label_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		label_3.setBounds(87, 274, 104, 20);
		panelProfile_1.add(label_3);

		passwordFieldProfile = new JPasswordField();
		passwordFieldProfile.setFont(new Font("Arial", Font.PLAIN, 15));
		passwordFieldProfile.setBounds(87, 305, 506, 25);
		panelProfile_1.add(passwordFieldProfile);

		JButton btnSaveProfile = new JButton("Save");
		btnSaveProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DLinkedList d = new DLinkedList();
				d = a.usersLoad(currentUser);
				int i = 0;
				for (i = 0; i < d.size(); i++) {
					String[] temp = ((String) d.get(i)).split("  ");
					if (temp[0].equals(currentUser)) {
						break;
					}
				}
				if (textFieldFirstNameProfile.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter your first name!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldLastNameProfile.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter your last name!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldProfile.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a username!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (passwordFieldProfile.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (textFieldProfile.getText().contains(" ") || textFieldProfile.getText().contains("\\")
							|| textFieldProfile.getText().contains("/") || textFieldProfile.getText().contains("&")) {
						JOptionPane.showMessageDialog(null, "Username Can't contain spaces or special characters!!",
								null, JOptionPane.ERROR_MESSAGE);
					} else if (!textFieldProfile.getText().toLowerCase().equals(textFieldProfile.getText())) {
						JOptionPane.showMessageDialog(null, "Username Can't contain capital letters", null,
								JOptionPane.ERROR_MESSAGE);
					} else if (textFieldFirstNameProfile.getText().contains(" ")
							|| textFieldFirstNameProfile.getText().contains("\\")
							|| textFieldFirstNameProfile.getText().contains("/")
							|| textFieldFirstNameProfile.getText().contains("&")) {
						JOptionPane.showMessageDialog(null, "First name Can't contain spaces or special characters!!",
								null, JOptionPane.ERROR_MESSAGE);
					} else if (textFieldLastNameProfile.getText().contains(" ")
							|| textFieldLastNameProfile.getText().contains("\\")
							|| textFieldLastNameProfile.getText().contains("/")
							|| textFieldLastNameProfile.getText().contains("&")) {
						JOptionPane.showMessageDialog(null, "Last name Can't contain spaces or special characters!!",
								null, JOptionPane.ERROR_MESSAGE);
					} else if (Files.exists(Paths.get("Users/" + textFieldProfile.getText()))
							&& !textFieldProfile.getText().equals(currentUser)) {
						JOptionPane.showMessageDialog(null, "Username Already Exists!!", null,
								JOptionPane.ERROR_MESSAGE);
					} else {
						String res = "";
						res = textFieldProfile.getText() + "  " + passwordFieldProfile.getText() + "  "
								+ textFieldFirstNameProfile.getText() + "  " + textFieldLastNameProfile.getText();

						File f = new File("Users/" + currentUser);
						File newDir = new File("Users/" + textFieldProfile.getText());

						/*
						 * File file = new File("Users/" + currentUser + "/" + folder);
						 * 
						 * try {
						 * 
						 * file.renameTo(new File("Users/" + currentUser + "/" + newFolder));
						 * doinglist(); textField_4.setText(""); panelRename.setVisible(false);
						 * 
						 * } catch (Exception e) {
						 * 
						 * JOptionPane.showMessageDialog(null,
						 * "The folder name has wrong sequence of charachters");
						 * 
						 * }
						 */

						if (currentUser.equals(textFieldProfile.getText())) {

							currentUser = textFieldProfile.getText();
							d.set(i, res);
							a.userWriter(d);
							panelProfile_1.setVisible(false);
							inboxField.setVisible(true);

						} else {

							boolean check = f.renameTo(new File("Users/" + textFieldProfile.getText()));

							if (check) {

								currentUser = textFieldProfile.getText();
								d.set(i, res);
								a.userWriter(d);
								panelProfile_1.setVisible(false);
								inboxField.setVisible(true);

							} else {
								JOptionPane.showMessageDialog(null, "Couldn't rename your username!!");
							}

						}

					}
				}
			}
		});
		btnSaveProfile.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		btnSaveProfile.setBounds(328, 411, 118, 36);
		panelProfile_1.add(btnSaveProfile);

		JButton btnBackProfile = new JButton("Back");
		btnBackProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldProfile.setText("");
				passwordFieldProfile.setText("");
				textFieldFirstNameProfile.setText("");
				textFieldLastNameProfile.setText("");
				panelProfile_1.setVisible(false);
				inboxField.setVisible(true);
			}
		});
		btnBackProfile.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		btnBackProfile.setBounds(10, 11, 94, 29);
		panelProfile_1.add(btnBackProfile);

		JLabel label_4 = new JLabel("First Name:");
		label_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		label_4.setBounds(87, 132, 118, 23);
		panelProfile_1.add(label_4);

		textFieldFirstNameProfile = new JTextField();
		textFieldFirstNameProfile.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textFieldFirstNameProfile.setColumns(10);
		textFieldFirstNameProfile.setBounds(197, 134, 104, 25);
		panelProfile_1.add(textFieldFirstNameProfile);

		JLabel label_5 = new JLabel("Last Name:");
		label_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		label_5.setBounds(375, 137, 94, 14);
		panelProfile_1.add(label_5);

		textFieldLastNameProfile = new JTextField();
		textFieldLastNameProfile.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textFieldLastNameProfile.setColumns(10);
		textFieldLastNameProfile.setBounds(479, 134, 114, 25);
		panelProfile_1.add(textFieldLastNameProfile);

		JPanel panelDraft_1 = new JPanel();
		panelDraft_1.setBounds(315, 11, 759, 637);
		desktopPane.add(panelDraft_1);
		panelDraft_1.setLayout(null);
		panelDraft_1.setVisible(false);

		JLabel lblYourDrafts = new JLabel("Your Drafts:");
		lblYourDrafts.setBounds(333, 11, 96, 21);
		lblYourDrafts.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		panelDraft_1.add(lblYourDrafts);

		list_4 = new JList();
		list_4.setVisible(true);
		list_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JList list_4 = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					Mail m = new Mail();
					int index = list_4.getSelectedIndex();
					String selectedEmail = (String) list_4.getSelectedValue();

					if (index != -1) {
						textFieldTo.setText("");
						textFieldSubject.setText("");
						textAreaLetter.setText("");
						panelDraft_1.setVisible(false);
						composeField.setVisible(true);
						m = a.draftLoad(currentUser, index);

						// I want to get the original priority of the email
						String[] temp = selectedEmail.split("    ");
						int i = 0;
						for (i = 0; i < temp[2].length(); i++) {
							if (Character.isDigit(temp[2].charAt(i))) {
								break;
							}
						}
						comboBox.setSelectedIndex((int) temp[2].charAt(i) - 48);

						textFieldTo.setText(m.getTo());
						textFieldSubject.setText(m.getSubject());
						String[] tempMail = m.getEmail();
						textAreaLetter.setText("");
						for (i = 0; i < tempMail.length; i++) {
							if (tempMail[i] == null) {
								break;
							}
							textAreaLetter.append(tempMail[i]);
							textAreaLetter.append("\n");
						}
						attachments = new SLinkedList();
						attachments = m.getAttachments();
						DefaultListModel md = new DefaultListModel();
						for (int j = 0; j < attachments.size(); j++) {
							String s = (String) attachments.get(j);
							String[] s1 = (s).split("\\\\");
							String s2 = s1[s1.length - 1];
							listAttachments.setModel(md);
							md.addElement(s2);
						}
					}
				}
			}
		});
		list_4.setVisibleRowCount(10);
		list_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		list_4.setBounds(10, 45, 739, 581);
		panelDraft_1.add(list_4);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_4.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a draft to be deleted!!");
				} else {
					DLinkedList d = new DLinkedList();
					d = a.indexLoad(currentUser, "Draft");
					String sub = ((String) d.get(list_4.getSelectedIndex())).split("&&&&")[4];
					File file = new File("Users/" + currentUser + "/Draft/" + sub);
					d.remove(list_4.getSelectedIndex());
					a.delete(file);
					a.indexWriter(d, currentUser, "Draft");
					DefaultListModel mod = a.modelMakerDraft(currentUser, "Draft");
					list_4.setModel(mod);
				}
			}
		});
		btnDelete.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnDelete.setBounds(10, 13, 96, 23);
		panelDraft_1.add(btnDelete);

		loginSignupField = new JPanel();
		loginSignupField.setBounds(10, 11, 1064, 637);
		desktopPane.add(loginSignupField);
		loginSignupField.setLayout(null);
		loginSignupField.setVisible(true);

		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginField.setVisible(true);
				loginSignupField.setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		btnLogin.setBounds(411, 250, 210, 31);
		loginSignupField.add(btnLogin);

		JButton btnSignup = new JButton("Sign Up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signupField.setVisible(true);
				loginSignupField.setVisible(false);
			}
		});
		btnSignup.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		btnSignup.setBounds(411, 306, 210, 31);
		loginSignupField.add(btnSignup);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		btnExit.setBounds(472, 400, 89, 23);
		loginSignupField.add(btnExit);

		panelRename = new JPanel();
		panelRename.setBounds(380, 144, 621, 247);
		desktopPane.add(panelRename);
		panelRename.setLayout(null);
		panelRename.setVisible(false);

		textField_4 = new JTextField();
		textField_4.setBounds(213, 94, 213, 20);
		panelRename.add(textField_4);
		textField_4.setColumns(10);

		btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String folder = (String) list_file.getSelectedValue();

				String newFolder = textField_4.getText();

				if (Files.exists(Paths.get("Users/" + currentUser + "/" + newFolder))) {

					JOptionPane.showMessageDialog(null, "A folder with the same name already exists");

				} else {

					File file = new File("Users/" + currentUser + "/" + folder);

					try {

						file.renameTo(new File("Users/" + currentUser + "/" + newFolder));
						doinglist();
						textField_4.setText("");
						panelRename.setVisible(false);
						DLinkedList d = new DLinkedList();
						d = a.folderOptionReader(currentUser);
						a.userOptionRename(d, currentUser, folder, newFolder);
					} catch (Exception e) {

						JOptionPane.showMessageDialog(null, "The folder name has wrong sequence of charachters");

					}

				}

			}
		});
		btnRename.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnRename.setBounds(251, 159, 108, 23);
		panelRename.add(btnRename);

		btnBack_4 = new JButton("Back");
		btnBack_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.setText("");
				panelRename.setVisible(false);
			}
		});
		btnBack_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnBack_4.setBounds(261, 213, 89, 23);
		panelRename.add(btnBack_4);

		JLabel lblNewName = new JLabel("New Name: ");
		lblNewName.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewName.setBounds(105, 95, 98, 14);
		panelRename.add(lblNewName);

		editoption = new JPanel();
		editoption.setBounds(380, 144, 632, 299);
		desktopPane.add(editoption);
		editoption.setLayout(null);
		editoption.setVisible(false);

		JLabel lblSubject_1 = new JLabel("subject");
		lblSubject_1.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblSubject_1.setBounds(49, 42, 56, 16);
		editoption.add(lblSubject_1);

		JLabel lblTopic = new JLabel("body");
		lblTopic.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblTopic.setBounds(49, 117, 56, 16);
		editoption.add(lblTopic);

		JLabel lblNewLabel_3 = new JLabel("from");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(49, 201, 56, 16);
		editoption.add(lblNewLabel_3);

		esuboption = new JTextField();
		esuboption.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		esuboption.setBounds(49, 69, 190, 22);
		editoption.add(esuboption);
		esuboption.setColumns(10);

		ebodyop = new JTextField();
		ebodyop.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		ebodyop.setBounds(49, 144, 190, 22);
		editoption.add(ebodyop);
		ebodyop.setColumns(10);

		efromop = new JTextField();
		efromop.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		efromop.setBounds(49, 228, 190, 22);
		editoption.add(efromop);
		efromop.setColumns(10);

		JButton setnewop = new JButton("set new option");
		setnewop.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		setnewop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String foldername = (String) list_file.getSelectedValue();
				add_folder_name.setText("");
				File_add_panel.setVisible(false);
				doinglist();
				f = new Filter();
				String sub = esuboption.getText();
				String fom = efromop.getText();
				String text = ebodyop.getText();
				String all = foldername;
				if (sub.equals("")) {
					all = all + "   " + "+%+%";
				} else {
					all = all + "   " + sub;
				}
				if (fom.equals("")) {
					all = all + "   " + "+%+%";
				} else {
					all = all + "   " + fom;
				}
				if (text.equals("")) {
					all = all + "   " + "+%+%";
				} else {
					all = all + "   " + text;
				}

				DLinkedList d = new DLinkedList();
				d = a.folderOptionReader(currentUser);

				FileWriter contacts = null;
				try {
					contacts = new FileWriter("Users/" + currentUser + "/folderoption.txt");
				} catch (Exception e) {

				}
				BufferedWriter bWriter = new BufferedWriter(contacts);
				int l = d.size();
				for (int i = 0; i < l; i++) {
					String s = (String) d.get(0);
					String[] ss = s.split("   ");
					if (ss[0].equals(foldername)) {
						s = all;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				editoption.setVisible(false);
			}
		});
		setnewop.setBounds(411, 134, 159, 25);
		editoption.add(setnewop);

		JButton cancel = new JButton("cancel");
		cancel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editoption.setVisible(false);
			}
		});
		cancel.setBounds(434, 179, 118, 25);
		editoption.add(cancel);

		Menu = new JPanel();
		Menu.setBounds(10, 11, 295, 637);
		desktopPane.add(Menu);
		Menu.setVisible(false);
		JButton btnCompose = new JButton("Compose");
		btnCompose.setBounds(91, 143, 116, 23);
		btnCompose.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnCompose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!attachments.isEmpty()) {
					attachments.clear();
				}
				textAreaLetter.setText("");
				textFieldSubject.setText("");
				textFieldTo.setText("");
				attachments = new SLinkedList();
				DefaultComboBoxModel md = new DefaultComboBoxModel();
				listAttachments.setModel(md);
				DefaultComboBoxModel coModel = new DefaultComboBoxModel();
				coModel.addElement("");
				String[] tempTemp = a.contactMaker(currentUser);
				int j = 0;
				while (tempTemp[j] != null && !tempTemp[j].equals("")) {
					String[] temp = tempTemp[j].split("   ");
					for (int m = 1; m < temp.length; m++) {
						coModel.addElement(temp[m]);
					}
					j++;
				}
				editoption.setVisible(false);
				panelProfile_1.setVisible(false);
				panelDraft_1.setVisible(false);
				comboBoxContacts.setModel(coModel);
				ContactsField.setVisible(false);
				inboxField.setVisible(false);
				filterField.setVisible(false);
				composeField.setVisible(true);
			}
		});
		Menu.setLayout(null);
		Menu.add(btnCompose);
		JButton btnMoveEmail_1 = new JButton("Move Emails");
		btnMoveEmail_1.setBounds(10, 245, 275, 23);
		btnMoveEmail_1.setEnabled(false);
		btnMoveEmail_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBoxPriorityInb.getSelectedItem().equals("Priority")) {
					if (list_file.getSelectedIndex() == -1) {
						if (listPriority.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be Moved!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							int[] inboxSelected = listPriority.getSelectedIndices();
							a.setSelected(inboxSelected);
							a.setSelected2("Inbox");
							panelProfile_1.setVisible(false);
							panelDraft_1.setVisible(false);
							editoption.setVisible(false);
							ContactsField.setVisible(false);
							inboxField.setVisible(false);
							filterField.setVisible(false);
							composeField.setVisible(false);
							moveFilesField.setVisible(true);
							// a.deleteORMove(currentUser, "Inbox", "Trash", inboxSelected);
						}
					} else {
						if (listPriority.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be Moved!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							String folder = (String) list_file.getSelectedValue();
							int[] inboxSelected = listPriority.getSelectedIndices();
							a.setSelected(inboxSelected);
							a.setSelected2(folder);
							panelProfile_1.setVisible(false);
							panelDraft_1.setVisible(false);
							editoption.setVisible(false);
							ContactsField.setVisible(false);
							inboxField.setVisible(false);
							filterField.setVisible(false);
							composeField.setVisible(false);
							moveFilesField.setVisible(true);
							// a.deleteORMove(currentUser, folder, "Trash", inboxSelected);
						}
					}
				} else if (comboBoxPriorityInb.getSelectedItem().equals("Default")) {
					if (list_file.getSelectedIndex() == -1) {
						if (list.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be Moved!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							int[] inboxSelected = list.getSelectedIndices();
							a.setSelected(inboxSelected);
							a.setSelected2("Inbox");
							panelProfile_1.setVisible(false);
							panelDraft_1.setVisible(false);
							ContactsField.setVisible(false);
							inboxField.setVisible(false);
							filterField.setVisible(false);
							composeField.setVisible(false);
							moveFilesField.setVisible(true);
							// a.deleteORMove(currentUser, "Inbox", "Trash", inboxSelected);
						}
					} else {
						if (list.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be Moved!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							String folder = (String) list_file.getSelectedValue();
							int[] inboxSelected = list.getSelectedIndices();
							a.setSelected(inboxSelected);
							a.setSelected2(folder);
							panelProfile_1.setVisible(false);
							panelDraft_1.setVisible(false);
							ContactsField.setVisible(false);
							inboxField.setVisible(false);
							filterField.setVisible(false);
							composeField.setVisible(false);
							moveFilesField.setVisible(true);
							// a.deleteORMove(currentUser, folder, "Trash", inboxSelected);
						}
					}
				}

				DefaultComboBoxModel coModel = new DefaultComboBoxModel();
				coModel.addElement("");
				Path path = Paths.get("Users/" + currentUser);
				String x = path.toString();
				File directory = new File(x);
				// get all the files from a directory
				File[] fList = directory.listFiles();
				for (File file1 : fList) {
					if (file1.getName().equals("Trash") || file1.getName().equals("Sent")
							|| file1.getName().equals("Inbox") || file1.getName().equals("Trash")
							|| file1.getName().endsWith(".txt") || file1.getName().equals("Draft")) {

					} else {
						coModel.addElement(file1.getName());
					}
				}
				MovingFilesComboBox.setModel(coModel);
				String selected = (String) MovingFilesComboBox.getSelectedItem();
				// a.setSelected3(selected);
				// a.moveEmails(null, null);
			}
		});
		btnMoveEmail_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnMoveEmail_1);

		btnMoveEmail_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnMoveEmail_1);
		JButton btnDeleteEmail = new JButton("Delete Emails");
		btnDeleteEmail.setBounds(10, 279, 275, 23);
		btnDeleteEmail.setEnabled(false);
		btnDeleteEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBoxPriorityInb.getSelectedItem().equals("Priority")) {
					if (list_file.getSelectedIndex() == -1) {
						if (listPriority.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be deleted!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							int[] inboxSelected = listPriority.getSelectedIndices();
							a.deleteORMove(currentUser, "Inbox", "Trash", inboxSelected);
						}
					} else {
						if (listPriority.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be deleted!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							String folder = (String) list_file.getSelectedValue();
							int[] inboxSelected = listPriority.getSelectedIndices();
							a.deleteORMove(currentUser, folder, "Trash", inboxSelected);
						}
					}
				} else if (comboBoxPriorityInb.getSelectedItem().equals("Default")) {
					if (list_file.getSelectedIndex() == -1) {
						if (list.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be deleted!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							int[] inboxSelected = list.getSelectedIndices();
							a.deleteORMove(currentUser, "Inbox", "Trash", inboxSelected);
						}
					} else {
						if (list.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(null, "Please Choose an Email to be deleted!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							String folder = (String) list_file.getSelectedValue();
							int[] inboxSelected = list.getSelectedIndices();
							a.deleteORMove(currentUser, folder, "Trash", inboxSelected);
						}
					}
				}

				doinglist();
				// refresh the list of emails in the current folder selected
				String temp = (String) list_file.getSelectedValue();
				if (list_file.getSelectedIndex() == -1) {
					DefaultListModel mod = a.modelMaker(currentUser, "Inbox");
					list.setModel(mod);
					DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
					listPriority.setModel(modPrio);
				} else {
					DefaultListModel mod = a.modelMaker(currentUser, temp);
					list.setModel(mod);
					DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);
					listPriority.setModel(modPrio);
				}
			}
		});
		btnDeleteEmail.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnDeleteEmail);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(10, 51, 97, 23);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaLetter.setText("");
				textFieldSubject.setText("");
				textFieldTo.setText("");

				a.checkTrashTime(currentUser);

				// refresh the list of emails in the current folder selected
				// String folderOpened = (String) list_file.getSelectedValue();
				if (currentOpened.equals("")) {
					doinglist();
					int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

					DefaultListModel modPrio = a.modelMaker(currentUser, "Inbox");

					DefaultListModel modPrioTemp = new DefaultListModel();
					for (int i = counter; i < counter + 10; i++) {
						if (i >= modPrio.size()) {
							break;
						}
						modPrioTemp.addElement(modPrio.getElementAt(i));
					}
					list.setModel(modPrioTemp);
					/*
					 * DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
					 * listPriority.setModel(modPrio);
					 */
				} else {
					doinglist();
					int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

					DefaultListModel modPrio = a.modelMaker(currentUser, currentOpened);

					DefaultListModel modPrioTemp = new DefaultListModel();
					for (int i = counter; i < counter + 10; i++) {
						if (i >= modPrio.size()) {
							break;
						}
						modPrioTemp.addElement(modPrio.getElementAt(i));
					}
					list.setModel(modPrioTemp);
					/*
					 * DefaultListModel modPrio = a.modelMakerPriority(currentUser, currentOpened);
					 * listPriority.setModel(modPrio);
					 */
					list_file.setSelectedValue(currentOpened, true);
				}
			}
		});
		btnRefresh.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnRefresh);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setBounds(10, 85, 97, 23);
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaLetter.setText("");
				textFieldSubject.setText("");
				textFieldTo.setText("");
				editoption.setVisible(false);
				panelProfile_1.setVisible(false);
				panelDraft_1.setVisible(false);
				ContactsField.setVisible(false);
				loginField.setVisible(false);
				signupField.setVisible(false);
				loginSignupField.setVisible(true);
				inboxField.setVisible(false);
				composeField.setVisible(false);
				Menu.setVisible(false);
				filterField.setVisible(false);
			}
		});
		btnSignOut.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnSignOut);

		list_file = new JList();
		list_file.setBounds(12, 315, 273, 239);
		list_file.setSelectedIndex(0);
		list_file.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				String folder = (String) list_file.getSelectedValue();
				if (folder.equals("Inbox") || folder.equals("Sent") || folder.equals("Trash")) {
					btnNewButton.setEnabled(false);
					btnRemove.setEnabled(false);
					btnEditOption.setEnabled(false);

				} else {
					btnNewButton.setEnabled(true);
					btnRemove.setEnabled(true);
					btnEditOption.setEnabled(true);
				}
				JList list_file = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					currentOpened = (String) list_file.getSelectedValue();
					panelProfile_1.setVisible(false);
					panelDraft_1.setVisible(false);
					editoption.setVisible(false);
					add_folder_name.setVisible(false);
					btnMoveEmail_1.setEnabled(true);
					btnDeleteEmail.setEnabled(true);
					filterField.setVisible(false);
					ContactsField.setVisible(false);
					composeField.setVisible(false);
					inboxField.setVisible(true);
					panel_1.setVisible(false);
					String temp = (String) list_file.getSelectedValue();
					int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

					DefaultListModel mod = a.modelMakerPriority(currentUser, temp);

					DefaultListModel modTemp = new DefaultListModel();
					for (int i = counter; i < counter + 10; i++) {
						if (i >= mod.size()) {
							break;
						}
						modTemp.addElement(mod.getElementAt(i));
					}
					listPriority.setModel(modTemp);

					dMain = new DLinkedList();
					dMain = a.indexLoad(currentUser, temp);
					list.setVisible(false);
					listPriority.setVisible(true);
					counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

					DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);

					DefaultListModel modPrioTemp = new DefaultListModel();
					for (int i = counter; i < counter + 10; i++) {
						if (i >= modPrio.size()) {
							break;
						}
						modPrioTemp.addElement(modPrio.getElementAt(i));
					}
					listPriority.setModel(modPrioTemp);

					dMain = new DLinkedList();
					dMain = a.indexLoad(currentUser, temp);
				}
			}
		});
		list_file.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		Menu.add(list_file);

		JButton btnAdd2 = new JButton("add folder ");
		btnAdd2.setBounds(10, 565, 107, 25);
		btnAdd2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editoption.setVisible(false);
				f = new Filter();
				attachments = new SLinkedList();
				filtersFrom = new SLinkedList();
				filtersSubject = new SLinkedList();
				filtersTopic = new SLinkedList();
				File_add_panel.setVisible(true);
			}
		});
		Menu.add(btnAdd2);

		btnRemove = new JButton("remove");
		btnRemove.setBounds(182, 565, 103, 25);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_file.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please Choose a folder to be removed!!");
				} else {
					String folder = (String) list_file.getSelectedValue();
					File file = new File("Users/" + currentUser + "/" + folder);
					if (!Files.exists(Paths.get("Users/" + currentUser + "/" + folder))) {
						JOptionPane.showMessageDialog(null, "Something went wrong while removing folder!!");
					} else {
						DLinkedList d = new DLinkedList();
						d = a.folderOptionReader(currentUser);
						for (int i = 0; i < d.size(); i++) {
							if (((String) d.get(i)).split("   ")[0].equals(folder)) {
								d.remove(i);
							}
						}
						a.delete(file);
						a.folderOptionWriter(d, currentUser);
						doinglist();
					}
				}

			}
		});
		btnRemove.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		Menu.add(btnRemove);

		btnNewButton = new JButton("Rename");
		btnNewButton.setBounds(182, 603, 103, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_file.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please choose a folder to be renamed");
				} else {
					editoption.setVisible(false);
					panelRename.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		Menu.add(btnNewButton);

		JButton btnContacts = new JButton("Contacts");
		btnContacts.setBounds(188, 11, 97, 23);
		btnContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp[] = a.contactMaker(currentUser);
				modelCon = new DefaultListModel();
				int j = 0;
				while (temp[j] != null) {
					modelCon.addElement(temp[j]);
					j++;
				}
				editoption.setVisible(false);
				panelProfile_1.setVisible(false);
				contactList.setModel(modelCon);
				panelDraft_1.setVisible(false);
				composeField.setVisible(false);
				inboxField.setVisible(false);
				filterField.setVisible(false);
				ContactsField.setVisible(true);
			}
		});
		btnContacts.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnContacts);

		JButton btnFilters = new JButton("Filters");
		btnFilters.setBounds(10, 211, 275, 23);
		btnFilters.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				panelProfile_1.setVisible(false);
				panelDraft_1.setVisible(false);
				editoption.setVisible(false);
				btnMoveEmail_1.setEnabled(false);
				inboxField.setVisible(false);
				composeField.setVisible(false);
				ContactsField.setVisible(false);
				filterField.setVisible(true);
				DefaultComboBoxModel tr = new DefaultComboBoxModel();
				tr.removeAllElements();
				foldername.setModel(tr);
				String location = "Users/" + currentUser;
				File filename = new File(location);
				File[] files = filename.listFiles();
				for (File file : files) {
					String[] allfiles = file.getAbsolutePath().split("\\\\");
					if (!allfiles[allfiles.length - 1].equals("contacts.txt")
							&& !allfiles[allfiles.length - 1].equals("filters.txt")
							&& !allfiles[allfiles.length - 1].equals("folderoption.txt")
							&& !allfiles[allfiles.length - 1].equals("Draft")) {
						tr.addElement(allfiles[allfiles.length - 1]);
					}
				}
			}
		});
		btnFilters.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnFilters);

		JButton btnMyProfile = new JButton("My Profile");
		btnMyProfile.setBounds(10, 11, 97, 23);
		btnMyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editoption.setVisible(false);
				panelProfile_1.setVisible(true);
				panelDraft_1.setVisible(false);
				inboxField.setVisible(false);
				composeField.setVisible(false);
				ContactsField.setVisible(false);
				filterField.setVisible(false);
				DLinkedList d = new DLinkedList();
				d = a.usersLoad(currentUser);
				for (int i = 0; i < d.size(); i++) {
					String[] temp = ((String) d.get(i)).split("  ");
					if (temp[0].equals(currentUser)) {
						textFieldProfile.setText(currentUser);
						passwordFieldProfile.setText(temp[1]);
						textFieldFirstNameProfile.setText(temp[2]);
						textFieldLastNameProfile.setText(temp[3]);
						break;
					}
				}

			}
		});
		btnMyProfile.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnMyProfile);

		JButton btnMyDraft = new JButton("My Draft");
		btnMyDraft.setBounds(91, 177, 116, 23);
		btnMyDraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelProfile_1.setVisible(false);
				editoption.setVisible(false);
				filterField.setVisible(false);
				btnMoveEmail_1.setEnabled(false);
				btnDeleteEmail.setEnabled(false);
				textAreaLetter.setText("");
				ContactsField.setVisible(false);
				composeField.setVisible(false);
				inboxField.setVisible(false);
				panel_1.setVisible(false);
				panelDraft_1.setVisible(true);
				DefaultListModel mod = a.modelMakerDraft(currentUser, "Draft");
				list_4.setModel(mod);
			}
		});
		btnMyDraft.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		Menu.add(btnMyDraft);

		btnEditOption = new JButton("edit option");
		btnEditOption.setBounds(10, 603, 107, 25);
		btnEditOption.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnEditOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list_file.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please choose a folder to be renamed");
				} else {

					String folder = (String) list_file.getSelectedValue();
					DLinkedList d = new DLinkedList();
					d = a.folderOptionReader(currentUser);
					int size = d.size();
					for (int i = 0; i < size; i++) {
						String s = (String) d.get(0);
						String[] ss = s.split("   ");

						if (ss[0].equals(folder)) {
							if (ss[3].equals("+%+%")) {
								ebodyop.setText("");
							} else {
								ebodyop.setText(ss[3]);
							}

							if (ss[2].equals("+%+%")) {
								efromop.setText("");
							} else {
								efromop.setText(ss[2]);
							}

							if (ss[1].equals("+%+%")) {
								esuboption.setText("");
							} else {
								esuboption.setText(ss[1]);
							}

							break;
						}
						d.remove(0);
					}
					moveFilesField.setVisible(false);
					panelRename.setVisible(false);
					ContactsField.setVisible(false);
					composeField.setVisible(false);
					panel_1.setVisible(false);
					editoption.setVisible(true);
					panelDraft_1.setVisible(false);
				}
			}
		});
		Menu.add(btnEditOption);

		filterField = new JPanel();
		filterField.setBounds(315, 11, 759, 637);
		desktopPane.add(filterField);
		filterField.setLayout(null);
		filterField.setVisible(false);

		searchlist = new JList();
		searchlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JList searchlist = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					btnBack_2.setVisible(false);
					btnBack_8.setVisible(true);
					int index = searchlist.getSelectedIndex();
					String selectedEmail = (String) searchlist.getSelectedValue();

					if (index != -1) {
						textAreaMail.setText("");
						filterField.setVisible(false);
						panel_1.setVisible(true);
						DefaultListModel tempModel5 = new DefaultListModel();

						// I want to get the original priority of the email
						String[] temp = selectedEmail.split("   ");

						String folder = x;

						attachments = new SLinkedList();
						attachments = a.attachmentLoader(currentUser, x, temp[5]);
						for (int j = 0; j < attachments.size(); j++) {
							String s = (String) attachments.get(j);
							String[] s1 = (s).split("\\\\");
							String s2 = s1[s1.length - 1];
							tempModel5.addElement(s2);
						}
						list_2.setModel(tempModel5);
						list_2.setEnabled(false);

						String[] letter = a.mailLoaderNoInd(currentUser, folder, temp[5]);
						comboBox.setVisible(false);
						label.setVisible(false);
						int i = 0;
						while (letter[i] != null) {
							textAreaMail.append(letter[i]);
							textAreaMail.append("\n");
							i++;
						}

						dMain = a.indexLoad(currentUser, folder);
					}
				}
			}

		});
		searchlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchlist.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		searchlist.setBounds(10, 112, 739, 473);
		filterField.add(searchlist);

		jtxSearchitem = new JTextField();
		jtxSearchitem.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		jtxSearchitem.setBounds(10, 45, 428, 32);
		filterField.add(jtxSearchitem);
		jtxSearchitem.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel tempModel6 = new DefaultListModel();
				tempModel6.clear();
				searchlist.setModel(tempModel6);
				x = String.valueOf(foldername.getSelectedItem());
				y = String.valueOf(topics.getSelectedItem());
				int k;

				if (jtxSearchitem.getText() == "") {

				} else {
					String word = jtxSearchitem.getText();
					DLinkedList d = new DLinkedList();
					d = a.indexLoad(currentUser, x);
					String[] list = new String[d.size()];
					for (int i = 0; i < list.length; i++) {
						list[i] = (String) d.get(i);
						// list[i] = (String) d.get(0);
						// d.remove(0);
					}
					// System.out.println(Arrays.toString(list));
					if (y == "subject") {
						Sort.sort(list, 0, list.length - 1, 3);
						int index = Sort.binarySearch(list, word, 3);
						if (index == -1) {
						} else {
							String[] list2 = Sort.ele(list, word, index, 3);
							DefaultListModel model = new DefaultListModel();
							for (int i = 0; i < list2.length; i++) {
								model.addElement(list2[i]);
								searchlist.setModel(model);
							}

						}

					} else if (y == "from") {
						Sort.sort(list, 0, list.length - 1, 1);
						int index = Sort.binarySearch(list, word, 1);
						if (index == -1) {
							System.out.println("there is no");
						} else {
							String[] list2 = Sort.ele(list, word, index, 1);
							DefaultListModel model = new DefaultListModel();
							for (int i = 0; i < list2.length; i++) {
								model.addElement(list2[i]);
								searchlist.setModel(model);
							}
						}
					} else if (y == "to") {
						Sort.sort(list, 0, list.length - 1, 2);
						int index = Sort.binarySearch(list, word, 2);
						if (index == -1) {
							System.out.println("there is no");
						} else {
							String[] list2 = Sort.ele(list, word, index, 2);
							DefaultListModel model = new DefaultListModel();
							for (int i = 0; i < list2.length; i++) {
								model.addElement(list2[i]);
								searchlist.setModel(model);
							}
						}
					} else {
						for (int i = 0; i < list.length; i++) {
							String[] sub = list[i].split("   ");
							FileReader body = null;
							try {
								body = new FileReader("Users/" + currentUser + "/" + x + "/" + sub[5] + "/Body.txt");
							} catch (FileNotFoundException e1) {

							}
							BufferedReader bReader = new BufferedReader(body);
							String tempTemp = "";
							while (true) {

								try {
									tempTemp = bReader.readLine();
								} catch (IOException e) {

								}
								if (tempTemp == null || tempTemp.contains(word)) {
									break;
								}

							}
							if (tempTemp != null) {
								DefaultListModel model = new DefaultListModel();
								model.addElement(list[i]);
								searchlist.setModel(model);
							}
						}

					}

				}

			}
		});
		btnSearch.setBounds(629, 598, 97, 26);
		filterField.add(btnSearch);

		foldername = new JComboBox();
		foldername.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		foldername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				foldername = (JComboBox) event.getSource();

				String selected = foldername.getSelectedItem().toString();
				if (selected.equals("Sent")) {
					topics.removeAllItems();
					topics.addItem("to");
					topics.addItem("subject");
					topics.addItem("topic");
				} else {
					topics.removeAllItems();
					topics.addItem("from");
					topics.addItem("subject");
					topics.addItem("topic");
				}

			}
		});
		foldername.setBounds(453, 48, 112, 26);
		filterField.add(foldername);

		topics = new JComboBox();
		topics.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		topics.setBounds(585, 48, 112, 26);
		filterField.add(topics);

		JButton cancelbtn = new JButton("cancel");
		cancelbtn.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filterField.setVisible(false);
				inboxField.setVisible(true);
			}
		});
		cancelbtn.setBounds(58, 599, 97, 25);
		filterField.add(cancelbtn);
		moveFilesField = new JPanel();
		moveFilesField.setBounds(315, 11, 759, 637);
		desktopPane.add(moveFilesField);
		moveFilesField.setLayout(null);
		moveFilesField.setVisible(false);

		btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (a.getSelected3().trim().equals("") || a.getSelected3() == null) {
					JOptionPane.showMessageDialog(null, "Please Choose a destination place!!", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					a.deleteORMove(currentUser, a.getSelected2(), a.getSelected3(), a.getSelected());
					doinglist();
					String temp = (String) list_file.getSelectedValue();
					if (list_file.getSelectedIndex() == -1) {
						DefaultListModel mod = a.modelMaker(currentUser, "Inbox");
						list.setModel(mod);
						DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
						listPriority.setModel(modPrio);
					} else {
						DefaultListModel mod = a.modelMaker(currentUser, temp);
						list.setModel(mod);
						DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);
						listPriority.setModel(modPrio);
					}
				}
				moveFilesField.setVisible(false);
				inboxField.setVisible(true);
			}
		});

		btnBack_3 = new JButton("Back");
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				destTextfiled.setText("");
				moveFilesField.setVisible(false);
				inboxField.setVisible(true);
			}
		});

		btnMove10 = new JButton("Move");
		btnMove10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (a.getSelected3().equals("") || a.getSelected3().equals(null)) {
					JOptionPane.showMessageDialog(null, "Please Choose a destination place!!", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					a.deleteORMoveString(currentUser, a.getSelected2(), a.getSelected3(), a.getSelected4());
					doinglist();
					String temp = (String) list_file.getSelectedValue();
					if (list_file.getSelectedIndex() == -1) {
						DefaultListModel mod = a.modelMaker(currentUser, "Inbox");
						list.setModel(mod);
						DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
						listPriority.setModel(modPrio);
					} else {
						DefaultListModel mod = a.modelMaker(currentUser, temp);
						list.setModel(mod);
						DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);
						listPriority.setModel(modPrio);
					}
				}
				moveFilesField.setVisible(false);
				inboxField.setVisible(true);
				btnMove10.setVisible(false);
				btnMove.setVisible(true);
			}
		});
		btnMove10.setBounds(303, 305, 115, 35);
		moveFilesField.add(btnMove10);
		btnMove10.setVisible(false);
		btnBack_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnBack_3.setBounds(76, 144, 89, 23);
		moveFilesField.add(btnBack_3);
		btnMove.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnMove.setBounds(303, 305, 115, 35);
		moveFilesField.add(btnMove);

		JLabel lblMove = new JLabel("Moving");
		lblMove.setHorizontalAlignment(SwingConstants.CENTER);
		lblMove.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		lblMove.setBounds(270, 11, 195, 28);
		moveFilesField.add(lblMove);

		destTextfiled = new JTextField();
		destTextfiled.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		destTextfiled.setBounds(330, 217, 150, 35);
		moveFilesField.add(destTextfiled);
		destTextfiled.setColumns(10);

		MovingFilesComboBox = new JComboBox();
		MovingFilesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (destTextfiled.getText().equals("")) {
					destTextfiled.setText((String) MovingFilesComboBox.getSelectedItem());
					a.setSelected3(destTextfiled.getText());
				} else {
					destTextfiled.setText(destTextfiled.getText() + " " + MovingFilesComboBox.getSelectedItem());
				}

				if (btnMove10.isVisible()) {
					a.setSelected2((String) foldername.getSelectedItem());
					Object[] temp = searchlist.getSelectedValues();
					String[] temp5 = new String[temp.length];
					for (int k = 0; k < temp.length; k++) {
						temp5[k] = (String) temp[k];
					}
					String[] res = new String[temp.length];
					int i = 0, j = 0;
					while (true) {
						if (temp5[j] != null) {
							String[] temp2 = temp5[j].split("   ");
							res[i] = temp2[5];
							i++;
							j++;
							if (j >= temp5.length) {
								break;
							}
						} else {
							break;
						}
					}
					a.setSeected4(res);
				}

			}
		});
		MovingFilesComboBox.setBounds(538, 217, 129, 35);
		moveFilesField.add(MovingFilesComboBox);

		JLabel lblNewLabel = new JLabel("Choose The destination Place :");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel.setBounds(105, 220, 213, 28);
		moveFilesField.add(lblNewLabel);

		JList list_3 = new JList();
		list_3.setBounds(65, 134, 619, 245);
		moveFilesField.add(list_3);

		loginField = new JPanel();
		loginField.setBounds(10, 11, 1064, 637);
		desktopPane.add(loginField);
		loginField.setVisible(false);
		loginField.setLayout(null);

		JLabel lblUsernameLog = new JLabel("Username: ");
		lblUsernameLog.setBounds(231, 222, 102, 20);
		lblUsernameLog.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		loginField.add(lblUsernameLog);

		textUsernameLog = new JTextField();
		textUsernameLog.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textUsernameLog.setBounds(365, 222, 306, 23);
		textUsernameLog.setColumns(10);
		loginField.add(textUsernameLog);

		JLabel lblPasswordLog = new JLabel("Password: ");
		lblPasswordLog.setBounds(231, 273, 102, 20);
		lblPasswordLog.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		loginField.add(lblPasswordLog);

		passwordFieldLog = new JPasswordField();
		passwordFieldLog.setFont(new Font("Arial", Font.PLAIN, 15));
		passwordFieldLog.setBounds(365, 274, 395, 23);
		loginField.add(passwordFieldLog);

		JButton btnLogInfin = new JButton("Log In");
		btnLogInfin.setBounds(453, 335, 118, 29);
		btnLogInfin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean signinCheck;

				if (textUsernameLog.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a username!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (passwordFieldLog.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					signinCheck = a.signin(textUsernameLog.getText(), passwordFieldLog.getText());

					signupField.setVisible(false);
					loginSignupField.setVisible(false);
					inboxField.setVisible(false);
					composeField.setVisible(false);
					if (!signinCheck) {
						JOptionPane.showMessageDialog(null, "Username or Password is wrong!!", null,
								JOptionPane.ERROR_MESSAGE);
					} else {
						currentUser = textUsernameLog.getText();
						textUsernameLog.setText("");
						passwordFieldLog.setText("");
						loginField.setVisible(false);
						Menu.setVisible(true);
						inboxField.setVisible(true);
					}
					doinglist();
					DefaultListModel mod = a.modelMaker(currentUser, "Inbox");
					list.setModel(mod);
					DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
					listPriority.setModel(modPrio);
					a.checkTrashTime(currentUser);
				}

			}
		});
		btnLogInfin.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		loginField.add(btnLogInfin);

		JButton btnBackLog = new JButton("Back");
		btnBackLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textUsernameLog.setText("");
				passwordFieldLog.setText("");
				loginField.setVisible(false);
				loginSignupField.setVisible(true);
			}
		});
		btnBackLog.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		btnBackLog.setBounds(249, 502, 77, 29);
		loginField.add(btnBackLog);

		JLabel lblmailcom = new JLabel("@Mail.com");
		lblmailcom.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblmailcom.setBounds(681, 222, 96, 20);
		loginField.add(lblmailcom);

		/*
		 * *****************************************************************************
		 * ************************
		 */

		ContactsField = new JPanel();
		ContactsField.setBounds(315, 11, 759, 637);
		desktopPane.add(ContactsField);
		ContactsField.setLayout(null);
		ContactsField.setVisible(false);

		JLabel lblContacts = new JLabel("Contacts");
		lblContacts.setHorizontalAlignment(SwingConstants.CENTER);
		lblContacts.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblContacts.setBounds(289, 11, 177, 26);
		ContactsField.add(lblContacts);

		JPanel panel = new JPanel();
		panel.setBounds(75, 157, 606, 280);
		ContactsField.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		textField = new JTextField();
		textField.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		textField.setBounds(206, 79, 230, 31);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblFirstName_1 = new JLabel("First name:");
		lblFirstName_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblFirstName_1.setBounds(113, 87, 83, 14);
		panel.add(lblFirstName_1);

		JLabel lblLastName_1 = new JLabel("Last name:");
		lblLastName_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblLastName_1.setBounds(113, 167, 83, 14);
		panel.add(lblLastName_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		textField_1.setBounds(206, 161, 230, 31);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnAddFin = new JButton("Add");
		btnAddFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String firstName = textField.getText();
				String lastName = textField_1.getText();
				DefaultListModel modelConTemp = a.contactCheck(currentUser, firstName, lastName);
				if (modelConTemp.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error! there is no such user");
				} else {
					DLinkedList dCont = new DLinkedList();
					dCont = a.contactLoader(currentUser);
					a.contactWriterSorting(dCont, currentUser);
					modelCon = a.contactModelMaker(currentUser);
					contactList.setModel(modelCon);
					panel.setVisible(false);
				}

				textField.setText("");
				textField_1.setText("");
			}
		});
		btnAddFin.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		btnAddFin.setBounds(266, 232, 89, 23);
		panel.add(btnAddFin);

		JButton btnBack_1 = new JButton("back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				panel.setVisible(false);
			}
		});
		btnBack_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnBack_1.setBounds(15, 15, 89, 23);
		panel.add(btnBack_1);

		contactList = new JList();
		contactList.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		contactList.setBounds(10, 48, 739, 544);
		ContactsField.add(contactList);

		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
			}
		});
		btnAddContact.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnAddContact.setBounds(10, 603, 119, 23);
		ContactsField.add(btnAddContact);

		JButton btnRemoveContact = new JButton("Remove Contact");
		btnRemoveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contactList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please choose a contact to be removed!");
				} else {
					DLinkedList dCont = new DLinkedList();
					dCont = a.contactLoader(currentUser);
					dCont.remove(contactList.getSelectedIndex());
					a.contactWriterSorting(dCont, currentUser);
					modelCon = a.contactModelMaker(currentUser);
					contactList.setModel(modelCon);
				}
			}
		});
		btnRemoveContact.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnRemoveContact.setBounds(587, 605, 162, 23);
		ContactsField.add(btnRemoveContact);

		panel_1 = new JPanel();
		panel_1.setBounds(315, 11, 759, 637);
		desktopPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);

		label = new JLabel("Priority:");
		label.setFont(new Font("Bell MT", Font.PLAIN, 13));
		label.setBounds(596, 16, 46, 14);
		panel_1.add(label);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "low", "1", "2", "3", "Max" }));
		comboBox.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		comboBox.setBounds(654, 11, 95, 20);
		panel_1.add(comboBox);

		textAreaMail = new JTextArea();
		textAreaMail.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		textAreaMail.setBounds(10, 43, 739, 481);
		textAreaMail.setEditable(false);
		JScrollPane textAreaMailScroll = new JScrollPane(textAreaMail);
		textAreaMailScroll.setBounds(10, 43, 739, 481);
		panel_1.add(textAreaMailScroll);

		btnBack_2 = new JButton("back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setVisible(true);
				comboBox.setVisible(true);
				panel_1.setVisible(false);
				inboxField.setVisible(true);

				// I change the priority of the selected email if changed
				if (comboBoxPriorityInb.getSelectedItem().equals("Priority")) {
					String tempTemp = (String) dMain
							.get(listPriority.getSelectedIndex() + (Integer.parseInt(lblPage.getText()) - 1) * 10);

					String[] te = tempTemp.split("   ");
					String fin = "";
					if (currentOpened.equals("Trash")) {
						fin = te[0] + "   " + te[1] + "   " + te[2] + "   " + te[3] + "   "
								+ comboBox.getSelectedIndex() + "   " + te[5] + "   " + te[6];
					} else {
						fin = te[0] + "   " + te[1] + "   " + te[2] + "   " + te[3] + "   "
								+ comboBox.getSelectedIndex() + "   " + te[5];
					}

					if (currentOpened.equals("")) {
						currentOpened = "Inbox";
					}

					dMain.set(listPriority.getSelectedIndex() + (Integer.parseInt(lblPage.getText()) - 1) * 10, fin);
					a.indexWriter(dMain, currentUser, currentOpened);
					int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

					DefaultListModel modPrio = a.modelMakerPriority(currentUser, currentOpened);

					DefaultListModel modPrioTemp = new DefaultListModel();
					for (int i = counter; i < counter + 10; i++) {
						if (i >= modPrio.size()) {
							break;
						}
						modPrioTemp.addElement(modPrio.getElementAt(i));
					}

					listPriority.setModel(modPrioTemp);
					doinglist();
					list_file.setSelectedValue(currentOpened, true);

				} else {
					int f = currentIndex;
					String tempTemp = (String) dMain.get(f);

					String[] te = tempTemp.split("   ");
					String fin = "";
					if (currentOpened.equals("Trash")) {
						fin = te[0] + "   " + te[1] + "   " + te[2] + "   " + te[3] + "   "
								+ comboBox.getSelectedIndex() + "   " + te[5] + "   " + te[6];
					} else {
						fin = te[0] + "   " + te[1] + "   " + te[2] + "   " + te[3] + "   "
								+ comboBox.getSelectedIndex() + "   " + te[5];
					}

					if (currentOpened.equals("")) {
						currentOpened = "Inbox";
					}

					dMain.set(currentIndex, fin);
					a.indexWriter(dMain, currentUser, currentOpened);
					int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

					DefaultListModel mod = a.modelMaker(currentUser, currentOpened);

					DefaultListModel modTemp = new DefaultListModel();
					for (int i = counter; i < counter + 10; i++) {
						if (i >= mod.size()) {
							break;
						}
						modTemp.addElement(mod.getElementAt(i));
					}
					list.setModel(modTemp);
					doinglist();
					list_file.setSelectedValue(currentOpened, true);

				}

			}
		});
		btnBack_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnBack_2.setBounds(10, 11, 76, 23);
		panel_1.add(btnBack_2);

		JLabel lblAttachments = new JLabel("Attachments:");
		lblAttachments.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblAttachments.setBounds(10, 535, 96, 14);
		panel_1.add(lblAttachments);

		list_2 = new JList();
		list_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JList list_2 = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					if (list_2.getSelectedIndex() != -1) {

						DLinkedList d = new DLinkedList();
						d = a.indexLoad(currentUser, currentOpened);

						int index = 0;

						int t = comboBoxPriorityInb.getSelectedIndex();

						if (t == 0 || t == -1) {
							// index = list.getSelectedIndex() + (Integer.parseInt(lblPage.getText()) - 1) *
							// 10;
							index = currentIndex;
						} else {
							// index = listPriority.getSelectedIndex() +
							// (Integer.parseInt(lblPage.getText()) - 1) * 10;
							index = currentIndex;
						}

						String e = (String) d.get(index);
						String[] f = e.split("   ");

						File file = new File("Users/" + currentUser + "/" + currentOpened + "/" + f[5] + "/attachments/"
								+ list_2.getSelectedValue());

						try {
							Desktop.getDesktop().open(file);
						} catch (IOException e1) {

						}

					}

				}
			}
		});
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setBounds(10, 560, 739, 66);
		JScrollPane scrollPane = new JScrollPane(list_2);
		scrollPane.setBounds(10, 560, 739, 66);
		panel_1.add(scrollPane);

		btnBack_8 = new JButton("back");
		btnBack_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBack_2.setVisible(true);
				panel_1.setVisible(false);
				btnBack_8.setVisible(false);
				filterField.setVisible(true);
				comboBox.setVisible(true);
				label.setVisible(true);
			}
		});
		btnBack_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnBack_8.setBounds(10, 11, 76, 23);
		panel_1.add(btnBack_8);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 739, 66);
		panel_1.add(scrollPane_1);

		JList list_1 = new JList();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		list_1.setBounds(0, 0, 737, 64);
		panel_1.add(list_1);
		btnBack_8.setVisible(false);

		inboxField = new JPanel();
		inboxField.setBounds(315, 11, 759, 637);
		desktopPane.add(inboxField);
		inboxField.setLayout(null);
		inboxField.setVisible(false);

		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.getSelectedIndex() + (Integer.parseInt(lblPage.getText()) - 1) * 10;
					currentIndex = index;
					String selectedEmail = (String) list.getSelectedValue();

					if (index != -1) {
						textAreaMail.setText("");
						inboxField.setVisible(false);
						panel_1.setVisible(true);

						// I want to get the original priority of the email
						String[] temp = selectedEmail.split("    ");
						comboBox.setSelectedIndex((int) temp[3].charAt(10) - 48);

						if (list_file.getSelectedIndex() == -1) {
							list_file.setSelectedIndex(0);
						}
						// String folder = (String) list_file.getSelectedValue();
						if (list_file.getSelectedIndex() != -1) {

							if (index == -1) {

							} else {
								String[] letter = a.mailLoader(currentUser, currentOpened, index);
								int i = 0;
								while (letter[i] != null) {
									textAreaMail.append(letter[i]);
									textAreaMail.append("\n");
									i++;
								}

								dMain = a.indexLoad(currentUser, currentOpened);

								String e = (String) dMain.get(index);
								String[] f = e.split("   ");

								attachments = new SLinkedList();
								attachments = a.attachmentLoader(currentUser, currentOpened, f[5]);

								DefaultListModel md = new DefaultListModel();
								md.clear();
								list_2.setModel(md);
								for (int j = 0; j < attachments.size(); j++) {
									String s = (String) attachments.get(j);
									String[] s1 = (s).split("\\\\");
									String s2 = s1[s1.length - 1];
									list_2.setModel(md);
									md.addElement(s2);
								}
								list_2.setEnabled(true);
							}
						}
					}
				}
			}
		});
		list.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		list.setVisibleRowCount(10);
		list.setBounds(10, 48, 739, 545);
		inboxField.add(list);

		listPriority = new JList();
		listPriority.setVisibleRowCount(10);
		listPriority.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.getSelectedIndex() + (Integer.parseInt(lblPage.getText()) - 1) * 10;
					currentIndex = index;
					String selectedEmail = (String) list.getSelectedValue();

					if (index != -1) {
						textAreaMail.setText("");
						inboxField.setVisible(false);
						panel_1.setVisible(true);

						// I want to get the original priority of the email
						String[] temp = selectedEmail.split("    ");
						comboBox.setSelectedIndex((int) temp[3].charAt(10) - 48);

						if (list_file.getSelectedIndex() == -1) {
							list_file.setSelectedIndex(0);
						}

						// String folder = (String) list_file.getSelectedValue();
						if (list_file.getSelectedIndex() == -1) {
							currentOpened = "Inbox";
						}
						if (index == -1) {

						} else {
							String[] letter = a.mailLoader(currentUser, currentOpened, index);
							int i = 0;
							while (letter[i] != null) {
								textAreaMail.append(letter[i]);
								textAreaMail.append("\n");
								i++;
							}

							dMain = a.indexLoad(currentUser, currentOpened);

							String e = (String) dMain.get(index);
							String[] f = e.split("   ");

							attachments = new SLinkedList();
							attachments = a.attachmentLoader(currentUser, currentOpened, f[5]);

							DefaultListModel md = new DefaultListModel();
							md.clear();
							list_2.setModel(md);
							for (int j = 0; j < attachments.size(); j++) {
								String s = (String) attachments.get(j);
								String[] s1 = (s).split("\\\\");
								String s2 = s1[s1.length - 1];
								list_2.setModel(md);
								md.addElement(s2);
							}
							list_2.setEnabled(true);
						}
					}
				}
			}
		});
		listPriority.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		listPriority.setBounds(10, 48, 739, 545);
		listPriority.setVisible(false);
		inboxField.add(listPriority);

		JLabel lblYourInbox = new JLabel("Your Mails:");
		lblYourInbox.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		lblYourInbox.setBounds(10, 11, 110, 26);
		inboxField.add(lblYourInbox);

		comboBoxPriorityInb = new JComboBox();
		comboBoxPriorityInb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentUser != "") {
					if (comboBoxPriorityInb.getSelectedIndex() == 0) {
						String temp = "";
						if (list_file.getSelectedIndex() == -1) {
							temp = "Inbox";
						} else {
							temp = (String) list_file.getSelectedValue();
						}
						int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

						DefaultListModel modPrio = a.modelMaker(currentUser, temp);

						DefaultListModel modPrioTemp = new DefaultListModel();
						for (int i = counter; i < counter + 10; i++) {
							if (i >= modPrio.size()) {
								break;
							}
							modPrioTemp.addElement(modPrio.getElementAt(i));
						}
						list.setModel(modPrioTemp);
						dMain = new DLinkedList();
						dMain = a.indexLoad(currentUser, temp);
						list.setVisible(true);
						listPriority.setVisible(false);
					} else {
						String temp = "";
						if (list_file.getSelectedIndex() == -1) {
							temp = "Inbox";
						} else {
							temp = (String) list_file.getSelectedValue();
						}

						int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

						DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);

						DefaultListModel modPrioTemp = new DefaultListModel();
						for (int i = counter; i < counter + 10; i++) {
							if (i >= modPrio.size()) {
								break;
							}
							modPrioTemp.addElement(modPrio.getElementAt(i));
						}
						listPriority.setModel(modPrioTemp);

						dMain = new DLinkedList();
						dMain = a.indexLoad(currentUser, temp);
						list.setVisible(false);
						listPriority.setVisible(true);
					}
				}
			}
		});
		comboBoxPriorityInb.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		comboBoxPriorityInb.setModel(new DefaultComboBoxModel(new String[] { "Default", "Priority" }));
		comboBoxPriorityInb.setSelectedIndex(0);
		comboBoxPriorityInb.setBounds(623, 17, 126, 20);
		inboxField.add(comboBoxPriorityInb);

		btnPreviousPage = new JButton("Previous Page");
		btnPreviousPage.setEnabled(false);
		btnPreviousPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPage.setText(String.valueOf(Integer.parseInt(lblPage.getText()) - 1));
				if (Integer.parseInt(lblPage.getText()) == 1) {
					btnPreviousPage.setEnabled(false);
				}
				if (currentUser != "") {
					if (comboBoxPriorityInb.getSelectedIndex() == 0) {
						String temp = "";
						if (list_file.getSelectedIndex() == -1) {
							temp = "Inbox";
						} else {
							temp = (String) list_file.getSelectedValue();
						}
						int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

						DefaultListModel modPrio = a.modelMaker(currentUser, temp);

						DefaultListModel modPrioTemp = new DefaultListModel();
						for (int i = counter; i < counter + 10; i++) {
							if (i >= modPrio.size()) {
								break;
							}
							modPrioTemp.addElement(modPrio.getElementAt(i));
						}
						list.setModel(modPrioTemp);
						dMain = new DLinkedList();
						dMain = a.indexLoad(currentUser, temp);
						list.setVisible(true);
						listPriority.setVisible(false);
					} else {
						String temp = "";
						if (list_file.getSelectedIndex() == -1) {
							temp = "Inbox";
						} else {
							temp = (String) list_file.getSelectedValue();
						}

						int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

						DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);

						DefaultListModel modPrioTemp = new DefaultListModel();
						for (int i = counter; i < counter + 10; i++) {
							if (i >= modPrio.size()) {
								break;
							}
							modPrioTemp.addElement(modPrio.getElementAt(i));
						}
						listPriority.setModel(modPrioTemp);

						dMain = new DLinkedList();
						dMain = a.indexLoad(currentUser, temp);
						list.setVisible(false);
						listPriority.setVisible(true);
					}
				}
			}
		});
		btnPreviousPage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnPreviousPage.setBounds(10, 604, 144, 23);
		inboxField.add(btnPreviousPage);

		btnNextPage = new JButton("Next Page");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPage.setText(String.valueOf(Integer.parseInt(lblPage.getText()) + 1));
				btnPreviousPage.setEnabled(true);
				if (currentUser != "") {
					if (comboBoxPriorityInb.getSelectedIndex() == 0) {
						String temp = "";
						if (list_file.getSelectedIndex() == -1) {
							temp = "Inbox";
						} else {
							temp = (String) list_file.getSelectedValue();
						}
						int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

						DefaultListModel modPrio = a.modelMaker(currentUser, temp);

						DefaultListModel modPrioTemp = new DefaultListModel();
						for (int i = counter; i < counter + 10; i++) {
							if (i >= modPrio.size()) {
								break;
							}
							modPrioTemp.addElement(modPrio.getElementAt(i));
						}
						list.setModel(modPrioTemp);
						dMain = new DLinkedList();
						dMain = a.indexLoad(currentUser, temp);
						list.setVisible(true);
						listPriority.setVisible(false);
					} else {
						String temp = "";
						if (list_file.getSelectedIndex() == -1) {
							temp = "Inbox";
						} else {
							temp = (String) list_file.getSelectedValue();
						}

						int counter = (Integer.parseInt(lblPage.getText()) * 10) - 10;

						DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);

						DefaultListModel modPrioTemp = new DefaultListModel();
						for (int i = counter; i < counter + 10; i++) {
							if (i >= modPrio.size()) {
								break;
							}
							modPrioTemp.addElement(modPrio.getElementAt(i));
						}
						listPriority.setModel(modPrioTemp);

						dMain = new DLinkedList();
						dMain = a.indexLoad(currentUser, temp);
						list.setVisible(false);
						listPriority.setVisible(true);
					}
				}
			}
		});
		btnNextPage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNextPage.setBounds(623, 604, 126, 23);
		inboxField.add(btnNextPage);

		lblPage = new JLabel("1");
		lblPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPage.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblPage.setBounds(370, 610, 46, 14);
		inboxField.add(lblPage);

		/*
		 * *****************************************************************************
		 * ******************
		 */

		/*
		 * *****************************************************************************
		 * ******************************
		 */

		/*
		 * *****************************************************************************
		 * ***************
		 */
		SLinkedList trList = new SLinkedList();
		/*
		 * *****************************************************************************
		 * ******************
		 */

		DefaultListModel md = new DefaultListModel();

		composeField = new JPanel();
		composeField.setBounds(315, 11, 759, 637);
		desktopPane.add(composeField);
		composeField.setVisible(false);

		textFieldTo = new JTextField();
		textFieldTo.setBounds(79, 11, 501, 30);
		textFieldTo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		textFieldTo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		composeField.setLayout(null);
		composeField.add(textFieldTo);
		textFieldTo.setColumns(10);
		textFieldTo.setToolTipText("username1 username2 username3 ....");

		textFieldSubject = new JTextField();
		textFieldSubject.setBounds(79, 60, 670, 30);
		textFieldSubject.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		composeField.add(textFieldSubject);
		textFieldSubject.setColumns(10);

		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(10, 19, 46, 14);
		lblTo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		composeField.add(lblTo);

		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(10, 68, 59, 14);
		lblSubject.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		composeField.add(lblSubject);

		textAreaLetter = new JTextArea();
		textAreaLetter.setBounds(10, 101, 739, 440);
		textAreaLetter.setFont(new Font("Courier New", Font.PLAIN, 15));
		JScrollPane textAreaLetterScroll = new JScrollPane(textAreaLetter);
		textAreaLetterScroll.setBounds(10, 101, 739, 440);
		composeField.add(textAreaLetterScroll);

		listAttachments = new JList();
		listAttachments.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		listAttachments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAttachments.setBounds(10, 549, 740, 48);
		JScrollPane listAttachmentsScroll = new JScrollPane(listAttachments);
		listAttachmentsScroll.setBounds(10, 549, 740, 48);
		composeField.add(listAttachmentsScroll);

		JButton btnAddAnAttachment = new JButton("Add an Attachment");
		btnAddAnAttachment.setBounds(10, 603, 205, 23);
		btnAddAnAttachment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int response = chooser.showOpenDialog(composeField);
				if (response == JFileChooser.APPROVE_OPTION) {
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					File file = chooser.getSelectedFile();
					String filePath = file.toString();
					attachments.add(filePath);
					String[] s1 = filePath.split("\\\\");
					String s2 = s1[s1.length - 1];
					listAttachments.setModel(md);
					md.addElement(s2);
				}
			}
		});
		JButton btnDeleteAnAttachment = new JButton("Remove an Attachment");
		btnDeleteAnAttachment.setBounds(218, 603, 205, 23);
		btnDeleteAnAttachment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listAttachments.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "Error! Choose an attachment to be removed");
				} else {
					md.removeElementAt(index);
					attachments.remove(index);
				}
			}
		});
		btnDeleteAnAttachment.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		composeField.add(btnDeleteAnAttachment);
		btnAddAnAttachment.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		composeField.add(btnAddAnAttachment);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(660, 603, 89, 23);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				LinkedBasedQueue queueMails = new LinkedBasedQueue();

				// WE must use a queue here
				String usernames = textFieldTo.getText();
				usernames.trim();
				String[] toto = usernames.split(" ");
				Integer counter = 0;
				for (int i = 0; i < toto.length; i++) {

					Mail mail = new Mail();
					// Save the to username
					String to = toto[i];
					mail.setTo(to);

					// Save the body of the email
					String str = textAreaLetter.getText();
					String[] allLines = str.split("\n");
					mail.setEmail(allLines);

					// Save the from username
					String from = currentUser;
					mail.setFrom(from);

					// Save the subject
					String subject = textFieldSubject.getText();
					mail.setSubject(subject);

					// Save the attachments
					mail.setAttachments(attachments);

					// Saves the priority as default '0'
					mail.setPriority(0);

					queueMails.enqueue(mail);

				}

				while (!queueMails.isEmpty()) {

					Mail mail = (Mail) queueMails.dequeue();

					if (!a.compose(mail)) {
						/*
						 * JOptionPane.showMessageDialog(null,
						 * "Error! the Sent to username isn't valid", null, JOptionPane.ERROR_MESSAGE);
						 */

					} else {
						a.checkoption(mail);
						counter++;
					}

				}
				if (toto.length == 0) {
					JOptionPane.showMessageDialog(null, "Error! the Sent to username isn't valid", null,
							JOptionPane.ERROR_MESSAGE);
				} else if (toto.length == 1) {
					if (counter == 1) {
						JOptionPane.showMessageDialog(null, "the Email Has been Sent successfully");
					} else {
						JOptionPane.showMessageDialog(null, "Error! the Sent to username isn't valid", null,
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (counter == toto.length) {
						JOptionPane.showMessageDialog(null,
								toto.length + " of " + toto.length + " Has been sent successfully");
					} else {
						JOptionPane.showMessageDialog(null, String.valueOf(counter) + " of " + toto.length
								+ " Has been sent Successfully.. " + (toto.length - counter) + " wrong Username(s)!!");
					}
				}
				doinglist();
				// refresh the list of emails in the current folder selected
				String temp = (String) list_file.getSelectedValue();
				if (list_file.getSelectedIndex() == -1) {
					DefaultListModel mod = a.modelMaker(currentUser, "Inbox");
					list.setModel(mod);
					DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
					listPriority.setModel(modPrio);
				} else {
					DefaultListModel mod = a.modelMaker(currentUser, temp);
					list.setModel(mod);
					DefaultListModel modPrio = a.modelMakerPriority(currentUser, temp);
					listPriority.setModel(modPrio);
				}

			}

		});
		btnSend.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		composeField.add(btnSend);

		comboBoxContacts = new JComboBox();
		comboBoxContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldTo.getText().equals("")) {
					textFieldTo.setText((String) comboBoxContacts.getSelectedItem());
				} else {
					textFieldTo.setText(textFieldTo.getText() + " " + comboBoxContacts.getSelectedItem());
				}
			}
		});

		JButton btnDraft = new JButton("Draft");
		btnDraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedBasedQueue queueMails = new LinkedBasedQueue();

				// WE must use a queue here
				String usernames = textFieldTo.getText();

				Mail mail = new Mail();

				// Save the to username
				mail.setTo(usernames);

				// Save the body of the email
				String str = textAreaLetter.getText();
				String[] allLines = str.split("\n");
				mail.setEmail(allLines);

				// Save the from username
				String from = currentUser;
				mail.setFrom(from);

				// Save the subject
				String subject = textFieldSubject.getText();
				mail.setSubject(subject);

				// Save the attachments
				mail.setAttachments(attachments);

				// Saves the priority as default '0'
				mail.setPriority(0);

				a.savingEmailDraft(mail, currentUser);

			}
		});
		btnDraft.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnDraft.setBounds(553, 603, 89, 23);
		composeField.add(btnDraft);
		comboBoxContacts.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		comboBoxContacts.setBounds(590, 11, 159, 30);
		composeField.add(comboBoxContacts);

		signupField = new JPanel();
		signupField.setForeground(SystemColor.menu);
		signupField.setBounds(10, 11, 1064, 637);
		desktopPane.add(signupField);
		signupField.setLayout(null);
		signupField.setVisible(false);

		JLabel lblRetypeAPassword = new JLabel("Retype the Password: ");
		lblRetypeAPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		lblRetypeAPassword.setBounds(249, 384, 202, 20);
		signupField.add(lblRetypeAPassword);

		passwordField_9 = new JPasswordField();
		passwordField_9.setFont(new Font("Arial", Font.PLAIN, 15));
		passwordField_9.setBounds(249, 414, 479, 23);
		signupField.add(passwordField_9);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(249, 215, 94, 20);
		lblUsername.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		signupField.add(lblUsername);

		textUsername = new JTextField();
		textUsername.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textUsername.setBounds(249, 246, 341, 23);
		signupField.add(textUsername);
		textUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Type a Password: ");
		lblPassword.setBounds(249, 297, 165, 20);
		lblPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		signupField.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
		passwordField.setBounds(249, 331, 479, 23);
		signupField.add(passwordField);

		JButton btnSignupFin = new JButton("Sign Up");
		btnSignupFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean signupCheck;

				if (textFirstName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter your first name!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textLastName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter your last name!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textUsername.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a username!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					Contact con = new Contact();
					con.setFirstName(textFirstName.getText());
					con.setLastName(textLastName.getText());
					con.setUsername(textUsername.getText());
					con.setPassword(passwordField.getText());
					if (textUsername.getText().contains(" ") || textUsername.getText().contains("\\")
							|| textUsername.getText().contains("/") || textUsername.getText().contains("&")) {
						JOptionPane.showMessageDialog(null, "Username Can't contain spaces or special characters!!",
								null, JOptionPane.ERROR_MESSAGE);
					} else if (!textUsername.getText().toLowerCase().equals(textUsername.getText())) {
						JOptionPane.showMessageDialog(null, "Username Can't contain capital letters", null,
								JOptionPane.ERROR_MESSAGE);
					} else if (textFirstName.getText().contains(" ") || textFirstName.getText().contains("\\")
							|| textFirstName.getText().contains("/") || textFirstName.getText().contains("&")) {
						JOptionPane.showMessageDialog(null, "First name Can't contain spaces or special characters!!",
								null, JOptionPane.ERROR_MESSAGE);
					} else if (textLastName.getText().contains(" ") || textLastName.getText().contains("\\")
							|| textLastName.getText().contains("/") || textLastName.getText().contains("&")) {
						JOptionPane.showMessageDialog(null, "Last name Can't contain spaces or special characters!!",
								null, JOptionPane.ERROR_MESSAGE);
					} else if (!passwordField.getText().equals(passwordField_9.getText())) {

						JOptionPane.showMessageDialog(null, "The retyped Password doesn't match!!");

					} else {
						signupCheck = a.signup(con);
						loginField.setVisible(false);
						loginSignupField.setVisible(false);
						inboxField.setVisible(false);
						composeField.setVisible(false);
						panelDraft_1.setVisible(false);
						if (!signupCheck) {
							JOptionPane.showMessageDialog(null, "Username Already Exist!!", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							currentUser = con.getUsername();
							textFirstName.setText("");
							textLastName.setText("");
							textUsername.setText("");
							passwordField.setText("");
							signupField.setVisible(false);
							Menu.setVisible(true);
							inboxField.setVisible(true);
						}
					}
					doinglist();
					DefaultListModel mod = a.modelMaker(currentUser, "Inbox");
					list.setModel(mod);
					DefaultListModel modPrio = a.modelMakerPriority(currentUser, "Inbox");
					listPriority.setModel(modPrio);
				}

			}
		});
		btnSignupFin.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		btnSignupFin.setBounds(490, 470, 118, 29);
		signupField.add(btnSignupFin);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFirstName.setText("");
				textLastName.setText("");
				textUsername.setText("");
				passwordField.setText("");
				signupField.setVisible(false);
				loginSignupField.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		btnBack.setBounds(249, 471, 77, 29);
		signupField.add(btnBack);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		lblFirstName.setBounds(249, 149, 111, 14);
		signupField.add(lblFirstName);

		textFirstName = new JTextField();
		textFirstName.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textFirstName.setBounds(365, 146, 86, 23);
		signupField.add(textFirstName);
		textFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		lblLastName.setBounds(509, 148, 111, 14);
		signupField.add(lblLastName);

		textLastName = new JTextField();
		textLastName.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		textLastName.setBounds(630, 146, 94, 23);
		signupField.add(textLastName);
		textLastName.setColumns(10);

		JLabel lblomgcom = new JLabel("@Mail.com");
		lblomgcom.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblomgcom.setBounds(617, 247, 111, 20);
		signupField.add(lblomgcom);

	}

	@SuppressWarnings("unchecked")
	private void doinglist() {
		@SuppressWarnings("rawtypes")
		DefaultListModel model = new DefaultListModel();

		String location = "Users/" + currentUser;
		File filename = new File(location);
		File[] files = filename.listFiles();
		for (File file : files) {
			String[] allfiles = file.getAbsolutePath().split("\\\\");
			if (!allfiles[allfiles.length - 1].equals("contacts.txt")
					&& !allfiles[allfiles.length - 1].equals("filters.txt")
					&& !allfiles[allfiles.length - 1].equals("folderoption.txt")
					&& !allfiles[allfiles.length - 1].equals("Draft")) {
				model.addElement(allfiles[allfiles.length - 1]);
				list_file.setModel(model);
			}
		}
	}
}
