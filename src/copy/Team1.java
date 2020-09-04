package copy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Team1 {

	public JFrame sport;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	JButton update = new JButton("��������");
	JButton delete = new JButton("�������");

	public Team1() {

		sport = new JFrame("Team");
		sport.setLocationRelativeTo(null);// � ������ ������
		sport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sport.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		sport.setUndecorated(true); // ������ �� ��������� ������

		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // ���������� �������� �� ������ ���
		background.setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel(); // ������
		panel.setLayout(null);

		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17); // ����� ��� ������
		Font name = new Font("TimesRoman", Font.BOLD, 30); // ����� � ����
		Color c1 = new Color(0, 214, 255); // color for table
		Font table = new Font("TimesRoman", Font.BOLD, 15); // ����� � ����
		Font word = new Font("TimesRoman", Font.ITALIC, 14); // ����� � label

		JLabel Name = new JLabel("�������");
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

		// JButton update = new JButton("��������");
		update.setBounds(100, 550, 150, 50);
		update.setBorderPainted(false); // ������� ������� ������
		update.setFocusPainted(false); // ������� ����� ������ ������
		update.setForeground(Color.cyan);
		update.setBackground(Color.DARK_GRAY);
		update.setFont(BigFontTR);

		// JButton delete = new JButton("�������");
		delete.setBounds(100, 650, 150, 50);
		delete.setBorderPainted(false); // ������� ������� ������
		delete.setFocusPainted(false); // ������� ����� ������ ������
		delete.setForeground(Color.cyan);
		delete.setBackground(Color.DARK_GRAY);
		delete.setFont(BigFontTR);

		JButton add = new JButton("��������");
		add.setBounds(100, 750, 150, 50);
		add.setBorderPainted(false); // ������� ������� ������
		add.setFocusPainted(false); // ������� ����� ������ ������
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new Menu1();

			}

		});

		sport.dispose(); // ��������� ������
		sport.setVisible(true); // ������ ������� �������

		try {

			JScrollPane pane = new JScrollPane(sportsmanTable);
			pane.getViewport().add(sportsmanTable);
			pane.setBounds(470, 140, 1000, 400); // �������� ������� � ���������
			sportsmanTable.setBackground(c1);
			sportsmanTable.setFont(table);
			pane.setBackground(c1);
			Vector<Vector<String>> values = getDataFromDB();

			Vector<String> hat = new Vector<String>();

			hat.add("� �������");
			hat.add("��������"); // ��������� ������
			hat.add("�����������");
			hat.add("� ����������");
			hat.add("� �������");

			DefaultTableModel dtm = (DefaultTableModel) sportsmanTable.getModel();

			dtm.setDataVector(values, hat);

			JTextField search = new JTextField(); // ��������� ����� �����
			search.setBounds(655, 545, 200, 25);
			JLabel words = new JLabel("������� ����� ��� ������:");
			words.setBounds(470, 545, 200, 25);
			words.setForeground(Color.DARK_GRAY);
			words.setFont(word);

			sportsmanTable.setAutoCreateRowSorter(true);// ���������� �� �������

			@SuppressWarnings("unchecked") //  ��� ��� �� ����� warning�� ������
			TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) sportsmanTable
					.getRowSorter();

			rowSorter.setComparator(0, new Comparator<String>() { // ��� ���������� �� ��������

				@Override
				public int compare(String o1, String o2) {
					return Integer.parseInt(o1) - Integer.parseInt(o2);
				}

			});
			for (int i = 3; i <= 4; i++) {
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

			JLabel id_team;
			JLabel name_team;
			JLabel scoach;
			JLabel ssportsman;
			JLabel ucher;

			JTextField ID;
			JTextField t1;
			JTextField t2;// ��� ����� �������
			JTextField t3;
			JTextField t4;

			ID = new JTextField(10);
			t1 = new JTextField(10);
			t2 = new JTextField(10);
			t3 = new JTextField(10);
			t4 = new JTextField(10);

			id_team = new JLabel("� �������");
			name_team = new JLabel("��������");
			ucher = new JLabel("�����������");
			scoach = new JLabel("� ����������");
			ssportsman = new JLabel("� �������");

			id_team.setFont(word);
			name_team.setFont(word);
			ucher.setFont(word);
			scoach.setFont(word);
			ssportsman.setFont(word);

//////////////////////////

			JComboBox idcoach;
			String[] nocoach = { "��� �������", };
			idcoach = new JComboBox(nocoach);
			conforJbox1 coach = new conforJbox1();

			con = coach.returnConnection();

			String sql = "Select ID_coach from Coach";

			try {
				ptr = con.prepareStatement(sql);
				rs = ptr.executeQuery();
				while (rs.next()) {

					String abc = rs.getString(1); // ��� ������� �� ���� ������ ������ � ��������
					idcoach.addItem(abc);
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
				ex.printStackTrace();

			}

			idcoach.addActionListener(new ActionListener() { // �������� ��� ���������
				public void actionPerformed(ActionEvent event) {

					String st = idcoach.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
					t4.setText(st); // ������ ��� ���������
					if (t4.getText().equals("��� �������")) 
						t4.setText("10");
				}
			});

//////////////////////////

			JComboBox idsport;
			idsport = new JComboBox();

			conforJbox1 sportsman = new conforJbox1();

			con = sportsman.returnConnection();

			String sql1 = "Select ID from Sportsman";

			try {
				ptr = con.prepareStatement(sql1);
				rs = ptr.executeQuery();
				while (rs.next()) {

					String abc = rs.getString(1); // ��� ������� �� ���� ������ ������ � ��������
					idsport.addItem(abc);
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
				ex.printStackTrace();

			}

			idsport.addActionListener(new ActionListener() { // �������� ��� ���������
				public void actionPerformed(ActionEvent event) {

					String st = idsport.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
					t3.setText(st); // ������ ��� ���������

				}
			});
//////////////////////////////////

			t3.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 11) {
						super.insertString(offs, str, a);

					}
				}
			});

			t1.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 25) {
						super.insertString(offs, str, a);

					}
				}
			});

			t3.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
				}
			});

			t2.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 20) {
						super.insertString(offs, str, a);

					}
				}
			});

			ID.setVisible(false);
			t3.setEnabled(false);
			t4.setEnabled(false);
			
			update.setEnabled(false);
			delete.setEnabled(false);
			

			ID.setBounds(100, 100, 200, 25);
			name_team.setBounds(100, 130, 200, 25);
			t1.setBounds(100, 150, 200, 25);
			ucher.setBounds(100, 230, 200, 25);
			t2.setBounds(100, 250, 200, 25);
			idsport.setBounds(300, 350, 50, 25);
			scoach.setBounds(100, 330, 200, 25);
			t3.setBounds(100, 350, 200, 25);
			idcoach.setBounds(300, 450, 100, 25);
			ssportsman.setBounds(100, 430, 200, 25);
			t4.setBounds(100, 450, 200, 25);

			sportsmanTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable sportsmanTable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && sportsmanTable.getSelectedRow() != -1) { // ���������
																									// �������� �����

						confordoc1 cont = new confordoc1();

						con = cont.returnConnection();

						String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

						try {

							String sql = "SELECT * from Team where ID_team=?";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								ID.setText(rs.getString("ID_team"));
								t1.setText(rs.getString("Name_team"));
								t2.setText(rs.getString("Ucrejdenie"));
								t3.setText(rs.getString("ID_sportsman"));
								t4.setText(rs.getString("ID_coach"));

							}

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					}

				}

			});

			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")
							|| t4.getText().equals("")) {

						JOptionPane.showMessageDialog(null,
								"���������� �������� ������ �� �������, ���� �� ������ ���� �������!");

					} else {

						try {

							String uppdate;
							confordoc1 cont = new confordoc1();

							con = cont.returnConnection();

							String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

							uppdate = sno;

							String sql = "Update Team set ID_team=?,Name_team=?,Ucrejdenie=?,"
									+ "ID_sportsman=?,ID_coach=? where ID_team=" + uppdate;

							ptr = con.prepareStatement(sql);

							ptr.setString(1, ID.getText());
							ptr.setString(2, t1.getText());
							ptr.setString(3, t2.getText());
							ptr.setString(4, t3.getText());
							ptr.setString(5, t4.getText());
							ptr.executeUpdate();

							JOptionPane.showMessageDialog(null, "������ ���������");

						} catch (Exception ex) {

							// JOptionPane.showMessageDialog(null,
							// "���������� �������� ������ �� �������, ���� �� ������ ���� �������!");
							JOptionPane.showMessageDialog(null, "��������� ���� ��������� ��� ������� � �������!");
							// JOptionPane.showMessageDialog(null, ex);
						}
					}
				}

			});

			delete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (JOptionPane.showConfirmDialog(null, "�� ������������� ������ ������� ��� ������?", "��������",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // ���������� ����

						try {

							String del;
							confordoc1 cont = new confordoc1();

							con = cont.returnConnection();

							st = con.createStatement();

							String id = ID.getText();

							String sql = "Delete from Registratura where ID_registrature=" + id;

							st.executeUpdate(sql);

							JOptionPane.showMessageDialog(null, "������ �������");

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null,
									"���������� �������� ������ �� �������, ���� �� ������ ���� �������!");
							// JOptionPane.showMessageDialog(null, ex);
						}

					} else {

					}

				}
			});

			add.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					String q = t1.getText();
					String w = t2.getText();
					String ee = t3.getText();
					String r = t4.getText();

					if (t1.getText().trim().length() > 0 && t2.getText().trim().length() > 0
							&& t3.getText().trim().length() > 0 && t4.getText().trim().length() > 0) {

						try {

							Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
							con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
							ptr = con.prepareStatement(
									"insert into Team(Name_team,Ucrejdenie,ID_sportsman,ID_coach) values (?,?,?,?)");

							ptr.setString(1, q);
							ptr.setString(2, w);
							ptr.setString(3, ee);
							ptr.setString(4, r);
							ptr.executeUpdate();

							JOptionPane.showMessageDialog(null, "������� ���������");

						} catch (ClassNotFoundException e1) {
							JOptionPane.showMessageDialog(null, e1);
							System.out.println("������ ���");
							e1.printStackTrace();
						} catch (SQLException e1) {
							System.out.println("��� �����");
							JOptionPane.showMessageDialog(null, "��������� ���� ��������� ��� ������� � �������!");
							e1.printStackTrace();
						}
					} else
						JOptionPane.showMessageDialog(null, "��������� ��� ����");
				}

			});
			panel.add(ID);
			panel.add(t1);
			panel.add(t2);
			panel.add(t3);
			panel.add(t4);
			panel.add(idcoach);
			panel.add(idsport);

			panel.add(id_team);
			panel.add(name_team);
			panel.add(ucher);
			panel.add(scoach);
			panel.add(ssportsman);

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		panel.add(back); // ���������� ������ �� ������
		panel.add(update);
		panel.add(delete);
		panel.add(add);
		panel.add(background);
		sport.add(panel);
		sport.setVisible(true);

	}

	public Vector<Vector<String>> getDataFromDB() throws Exception {

		Vector<Vector<String>> result = new Vector<Vector<String>>();
		Connection con;
		Statement st;
		ResultSet rs;
		//
		String msAccDB = "Sport.accdb";
		String dbURL = "jdbc:ucanaccess://" + msAccDB;
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver"; // ����������� � ��
		Class.forName(driver);
		con = DriverManager.getConnection(dbURL);
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery("SELECT * from Team"); // SQL// ������

		String p1, p2, p3, p4, p5, p6, p7;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(5);
			p2 = rs.getString(1);
			p3 = rs.getString(2);
			p4 = rs.getString(3);
			p5 = rs.getString(4);

			element.add(p1);
			element.add(p2);
			element.add(p3);
			element.add(p4);
			element.add(p5);

			result.add(element);
		}

		rs.close();
		st.close(); // �������� ������
		con.close();
		return result;

	}

}