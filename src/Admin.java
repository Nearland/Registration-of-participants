import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

public class Admin {

	public JFrame info;
	JTable admintable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public Admin() {

		info = new JFrame("Admin");
		info.setLocationRelativeTo(null);// � ������ ������
		info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		info.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		info.setUndecorated(true); // ������ �� ��������� ������

		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // ���������� �������� �� ������ ���
		background.setBounds(0, 0, 1920, 1080);

		Color c1 = new Color(0, 214, 255); // color for table
		Font table = new Font("TimesRoman", Font.BOLD, 15); // ����� � ����

		JPanel panel = new JPanel(); // ������
		panel.setLayout(null);

		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17); // ����� ��� ������
		Font name = new Font("TimesRoman", Font.BOLD, 30); // ����� � ����
		Font word = new Font("TimesRoman", Font.ITALIC, 14); // ����� � label

		JLabel Name = new JLabel("�����");
		Name.setBounds(750, 50, 400, 60);
		Name.setForeground(Color.YELLOW); // ��������� �� �����
		Name.setFont(name);
		panel.add(Name);

		JButton back = new JButton("�����");
		back.setBounds(725, 740, 150, 50);
		back.setBorderPainted(false); // ������� ������� ������
		back.setFocusPainted(false); // ������� ����� ������ ������
		back.setForeground(Color.cyan);
		back.setBackground(Color.DARK_GRAY);
		back.setFont(BigFontTR);

		JButton add = new JButton("��������");
		add.setBounds(100, 450, 150, 50);
		add.setBorderPainted(false); // ������� ������� ������
		add.setFocusPainted(false); // ������� ����� ������ ������
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		JButton del = new JButton("�������");
		del.setBounds(100, 550, 150, 50);
		del.setBorderPainted(false); // ������� ������� ������
		del.setFocusPainted(false); // ������� ����� ������ ������
		del.setForeground(Color.cyan);
		del.setBackground(Color.DARK_GRAY);
		del.setFont(BigFontTR);

		JButton saveconf = new JButton("���������");
		saveconf.setBounds(600, 380, 150, 50);
		saveconf.setBorderPainted(false); // ������� ������� ������
		saveconf.setFocusPainted(false); // ������� ����� ������ ������
		saveconf.setForeground(Color.cyan);
		saveconf.setBackground(Color.DARK_GRAY);
		saveconf.setFont(BigFontTR);

		JButton recovery = new JButton("�����������");
		recovery.setBounds(600, 480, 150, 50);
		recovery.setBorderPainted(false); // ������� ������� ������
		recovery.setFocusPainted(false); // ������� ����� ������ ������
		recovery.setForeground(Color.cyan);
		recovery.setBackground(Color.DARK_GRAY);
		recovery.setFont(BigFontTR);

		JLabel image;
		ImageIcon pack = new ImageIcon("pack.png");
		image = new JLabel("", pack, JLabel.CENTER); // ���������� �������� �� ������ ���
		image.setBounds(815, 330, 25, 25);

		// , new ImageIcon("gal.jpg"), true);
		JCheckBox backup = new JCheckBox("��������� �����������");
		backup.setBounds(600, 150, 200, 25);
		backup.setOpaque(false);
		backup.setFont(word);

		// ������ �������
		Backup.getParsConfig();

		// ��������� �������� ���������
		if (Backup.BACKUP_ON) {
			backup.setSelected(true);
		} else {
			backup.setSelected(false);
		}

		/// ������ � ���� �������� �����
		backup.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (backup.isSelected()) {
					Backup.BACKUP_ON = true;
				} else {
					Backup.BACKUP_ON = false;
				}

				// ��������� �������
				try {
					FileWriter writer = new FileWriter("configuration.txt", false);
					BufferedWriter bufferWriter = new BufferedWriter(writer);

					if (Backup.BACKUP_ON)
						bufferWriter.write("backup_on 1\n");
					else
						bufferWriter.write("backup_on 0\n");
					bufferWriter.newLine();
					bufferWriter.write("backup_schedule " + Backup.Backup_frequency);
					bufferWriter.newLine();
					bufferWriter.write("backup_location " + Backup.Backup_place);

					bufferWriter.close();

				} catch (IOException ex) {

					System.out.println(ex.getMessage());
					JOptionPane pane = new JOptionPane();
					JOptionPane.showMessageDialog(pane, "�� ������� �������� ���� � �����������");
					pane.setSize(100, 100);
					pane.setVisible(false);
					Backup.getParsConfig();
				}
			}
		});
		///
		TextField speed = new TextField(Integer.toString(Backup.Backup_frequency), 20);

		JLabel frequency = new JLabel("������� ����������� � ���:");
		frequency.setFont(word);

		TextField pathBackup = new TextField(Backup.Backup_place, 20);
		JLabel pathway = new JLabel("���� ��� ���������� �����������:");
		pathway.setFont(word);

		/////////
		image.addMouseListener((MouseListener) new MouseAdapter() { // ������� ������ �� ��������
			public void mousePressed(MouseEvent me) {

				JFileChooser fc = new JFileChooser();// �������� ���� ������ ����
				fc.setCurrentDirectory(new java.io.File(".")); // ��� ������ ���������� (��� ����)
				fc.setApproveButtonText("�������"); // ��������� ������ ����������
				fc.setApproveButtonText("���������");

				setUpdateUI(fc);// ����������� JFileChooser

				fc.setDialogTitle("�������� ���� ��� ��������� ����� �����");// ������� �������� ��� ������������ ����

				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ��������� ������ � �����
				fc.setAcceptAllFileFilterUsed(false);

				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					// System.out.println("path: " + fc.getCurrentDirectory().getAbsolutePath());
					// System.out.println("path2: " + fc.getSelectedFile().getAbsolutePath());
					pathBackup.setText(fc.getSelectedFile().getAbsolutePath() + "//");// ��������� ���� � ������������ �
																						// TextField

				}
			}

		});
		/////////////

		saveconf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��������� �������
				try {
					FileWriter writer = new FileWriter("configuration.txt", false);
					BufferedWriter bufferWriter = new BufferedWriter(writer);

					if (Backup.BACKUP_ON)
						bufferWriter.write("backup_on 1\n");
					else
						bufferWriter.write("backup_on 0\n");
					bufferWriter.newLine();
					bufferWriter.write("backup_schedule " + speed.getText());
					bufferWriter.newLine();
					bufferWriter.write("backup_location " + pathBackup.getText());

					bufferWriter.close();

					BackupLog.writeLog("������������ ���� ��������");
					JOptionPane.showMessageDialog(null, "��������� ����������!");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "�� ������� ��������� ���������!");
					System.out.println(ex.getMessage());
					JOptionPane pane = new JOptionPane();
					JOptionPane.showMessageDialog(pane, "�� ������� �������� ���������");
					pane.setSize(100, 100);
					pane.setVisible(false);
					BackupLog.writeLog("�� ������� �������� ���������");
					Backup.getParsConfig();
				}
			};
		});

		/// �������������� �� ��������� �����
		recovery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Backup.recoveryDB();
					JOptionPane.showMessageDialog(null, "���� ������ ������� �������������!");
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "�� ������� ����������� ���� ������!");
					System.out.println(ex.getMessage());
					JOptionPane pane = new JOptionPane();
					JOptionPane.showMessageDialog(pane, "�� ������� ����������� ���� ������");
					pane.setSize(100, 100);
					pane.setVisible(false);
					BackupLog.writeLog("�� ������� �����������");
				}
			};
		});

		try {
			Database db = DatabaseBuilder.open(new File("Sport.accdb"));

			db.close();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		Timer timer = new Timer();
		TimerTask task = new Timerb();
		timer.schedule(task, 5000, Backup.Backup_frequency * 1000);

		///
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				info.dispose(); // ������ �����
				new Menu();
			}

		});

		JLabel l, p, a;

		JTextField login, access, pas;

		l = new JLabel("�����:");
		p = new JLabel("������:");
		a = new JLabel("����������:");
		login = new JTextField(10);
		access = new JTextField(10);
		pas = new JTextField(10);

		l.setFont(word);
		p.setFont(word);
		a.setFont(word);

		String[] items = { "admin", "moder" };
		JComboBox yes;
		yes = new JComboBox(items);

		yes.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {
				String st = yes.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				access.setText(st); // ������ ��� ���������
			}
		});

		l.setBounds(100, 130, 200, 25);
		login.setBounds(100, 150, 200, 25);
		p.setBounds(100, 230, 200, 25);
		pas.setBounds(100, 250, 200, 25);
		a.setBounds(100, 330, 200, 25);
		yes.setBounds(300, 350, 100, 25);
		access.setBounds(100, 350, 200, 25);
		frequency.setBounds(600, 200, 200, 25);
		speed.setBounds(600, 230, 200, 25);
		pathway.setBounds(600, 300, 300, 25);
		pathBackup.setBounds(600, 330, 200, 25);

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = login.getText();
				String lname = pas.getText();
				String pname = access.getText();

				String hash = getHash(lname);
				try {
					//
					String msAccDB = "Employee.accdb";
					String dbURL = "jdbc:ucanaccess://" + msAccDB;
					String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
					Class.forName(driver);
					con = DriverManager.getConnection(dbURL);
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery("SELECT * FROM Aut");
					//

					rs.moveToInsertRow();

					rs.updateString("FFname", fname);
					rs.updateString("Hash", hash);
					rs.updateString("who", pname);
					rs.insertRow();

					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery("SELECT * FROM Aut");

					login.setText(" ");
					pas.setText(" ");
					access.setText(" ");
					con.close();
					st.close();
					rs.close();

					JOptionPane.showMessageDialog(null, pname + " ��������!");
				} catch (Exception ex) {

				}
			}
		});

		try {

			JScrollPane pane = new JScrollPane(admintable);
			pane.getViewport().add(admintable);
			pane.setBounds(950, 140, 500, 400); // �������� ������� � ���������
			admintable.setBackground(c1);
			admintable.setFont(table);
			pane.setBackground(c1);
			Vector<Vector<String>> values = getDataFromDB();

			Vector<String> hat = new Vector<String>();

			hat.add("�����");
			hat.add("���"); // ��������� ������
			hat.add("����������");

			DefaultTableModel dtm = (DefaultTableModel) admintable.getModel();

			dtm.setDataVector(values, hat);

			JTextField search = new JTextField(); // ��������� ����� �����
			search.setBounds(1135, 545, 200, 25);
			JLabel words = new JLabel("������� ����� ��� ������:");
			words.setBounds(950, 545, 200, 25);
			words.setForeground(Color.DARK_GRAY);
			words.setFont(word);

			admintable.setAutoCreateRowSorter(true);// ���������� �� �������

			@SuppressWarnings("unchecked") // ��� ��� �� ����� warning�� ������
			TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) admintable.getRowSorter();

			for (int i = 0; i <= 1; i++) {
				rowSorter.setComparator(i, new Comparator<String>() { // �������� ���� ���! ����� ������� �� �����

					@Override
					public int compare(String o1, String o2) {
						return Integer.parseInt(o1) - Integer.parseInt(o2);
					}

				});
			}

			search.getDocument().addDocumentListener(new DocumentListener() { // ����� ������

				@Override
				public void insertUpdate(DocumentEvent e) {

					String text = search.getText();

					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					}

				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					String text = search.getText();

					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					}

				}

				@Override
				public void changedUpdate(DocumentEvent e) {

					throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
																					// methods, choose Tools |
																					// Templates.
				}

			});

			admintable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable admintable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && admintable.getSelectedRow() != -1) { // ���������
																								// �������� �����

						conforemploeyy cont = new conforemploeyy();

						con = cont.returnConnection();

						String sno = admintable.getValueAt(admintable.getSelectedRow(), 0).toString();

						try {

							String sql = "Select * from Aut where FFname=?";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								login.setText(rs.getString("FFname"));
								pas.setText(rs.getString("Hash"));
								access.setText(rs.getString("who"));

							}

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					}

				}

			});

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		del.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null, "�� ������������� ������ ������� ��� ������?", "��������",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // ���������� ����

					try {

						JTextField ID;
						ID = new JTextField(10);

						ID.getText();

						conforemploeyy cont = new conforemploeyy();

						ID.setText(rs.getString("FFname"));

						con = cont.returnConnection();

						st = con.createStatement();

						String id = ID.getText();

						String sql = "Delete * From Aut where FFname = ?";

						PreparedStatement pstmt = con.prepareStatement(sql);

						pstmt.setString(1, id);
						int nrows = pstmt.executeUpdate();
						ptr.executeUpdate(sql);

						JOptionPane.showMessageDialog(null, "������ �������");

					} catch (Exception ex) {

						if (login.getText().trim().length() > 0 && pas.getText().trim().length() > 0
								&& access.getText().trim().length() > 0) {
							JOptionPane.showMessageDialog(null, "������ �������");
						} else
							JOptionPane.showMessageDialog(null, "�������� ���������� ������ �� �������");

						// JOptionPane.showMessageDialog(null, ex);
					}

				} else {

				}

			}
		});

		panel.add(add);

		panel.add(l);
		panel.add(p);
		panel.add(a);
		panel.add(backup);
		panel.add(frequency);
		panel.add(pathway);
		panel.add(speed);
		panel.add(pathBackup);
		panel.add(saveconf);
		panel.add(recovery);
		panel.add(image);
		panel.add(del);

		access.setEnabled(false);
		panel.add(yes);

		panel.add(login);
		panel.add(access);
		panel.add(pas);

		panel.add(back); // ���������� ������ �� ������
		panel.add(background);
		info.setVisible(true); // ������ ������� �������
		info.add(panel);

	}

	public static String getHash(String plaintext) {
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<Vector<String>> getDataFromDB() throws Exception {

		Vector<Vector<String>> result = new Vector<Vector<String>>();
		Connection con;
		Statement st;
		ResultSet rs;
		//
		String msAccDB = "Employee.accdb";
		String dbURL = "jdbc:ucanaccess://" + msAccDB;
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver"; // ����������� � ��
		Class.forName(driver);
		con = DriverManager.getConnection(dbURL);
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery("SELECT * from Aut"); // SQL// ������

		String p1, p2, p3;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(1);
			p2 = rs.getString(3);
			p3 = rs.getString(2);

			element.add(p2);
			element.add(p1);
			element.add(p3);

			result.add(element);
		}

		rs.close();
		st.close(); // �������� ������
		con.close();
		return result;

	}

	public static void setUpdateUI(JFileChooser choose) {
		UIManager.put("FileChooser.openButtonText", "�������");
		UIManager.put("FileChooser.cancelButtonText", "������");
		UIManager.put("FileChooser.lookInLabelText", "�������� �");
		UIManager.put("FileChooser.fileNameLabelText", "��� �����");
		UIManager.put("FileChooser.filesOfTypeLabelText", "��� �����");

		UIManager.put("FileChooser.saveButtonText", "���������");
		UIManager.put("FileChooser.saveButtonToolTipText", "���������");
		UIManager.put("FileChooser.openButtonText", "�������");
		UIManager.put("FileChooser.openButtonToolTipText", "�������");
		UIManager.put("FileChooser.cancelButtonText", "������");
		UIManager.put("FileChooser.cancelButtonToolTipText", "������");

		UIManager.put("FileChooser.lookInLabelText", "�����");
		UIManager.put("FileChooser.saveInLabelText", "�����");
		UIManager.put("FileChooser.fileNameLabelText", "��� �����");
		UIManager.put("FileChooser.filesOfTypeLabelText", "��� ������");

		UIManager.put("FileChooser.upFolderToolTipText", "�� ���� ������� �����");
		UIManager.put("FileChooser.newFolderToolTipText", "�������� ����� �����");
		UIManager.put("FileChooser.listViewButtonToolTipText", "������");
		UIManager.put("FileChooser.detailsViewButtonToolTipText", "�������");
		UIManager.put("FileChooser.fileNameHeaderText", "���");
		UIManager.put("FileChooser.fileSizeHeaderText", "������");
		UIManager.put("FileChooser.fileTypeHeaderText", "���");
		UIManager.put("FileChooser.fileDateHeaderText", "�������");
		UIManager.put("FileChooser.fileAttrHeaderText", "��������");

		UIManager.put("FileChooser.acceptAllFileFilterText", "��� �����");
		choose.updateUI();
	}
}
