package copy;

import java.awt.Color;
import java.awt.Component;
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
import java.sql.ResultSetMetaData;
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

public class Sportsman1 {

	public JFrame sport;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;
	
	JButton update = new JButton("��������");
	JButton delete = new JButton("�������");

	public Sportsman1() {

		sport = new JFrame("Sportsman");
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

		JLabel Name = new JLabel("����������");
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

		//JButton update = new JButton("��������");
		update.setBounds(100, 700, 150, 50);
		update.setBorderPainted(false); // ������� ������� ������
		update.setFocusPainted(false); // ������� ����� ������ ������
		update.setForeground(Color.cyan);
		update.setBackground(Color.DARK_GRAY);
		update.setFont(BigFontTR);

		//JButton delete = new JButton("�������");
		delete.setBounds(100, 760, 150, 50);
		delete.setBorderPainted(false); // ������� ������� ������
		delete.setFocusPainted(false); // ������� ����� ������ ������
		delete.setForeground(Color.cyan);
		delete.setBackground(Color.DARK_GRAY);
		delete.setFont(BigFontTR);

		JButton create = new JButton("�������� ����������");
		create.setBounds(1350, 540, 220, 50);
		create.setBorderPainted(false); // ������� ������� ������
		create.setFocusPainted(false); // ������� ����� ������ ������
		create.setForeground(Color.cyan);
		create.setBackground(Color.DARK_GRAY);
		create.setFont(BigFontTR);
		
		JButton coachh = new JButton("������");
		coachh.setBounds(1350, 600, 220, 50);
		coachh.setBorderPainted(false); // ������� ������� ������
		coachh.setFocusPainted(false); // ������� ����� ������ ������
		coachh.setForeground(Color.cyan);
		coachh.setBackground(Color.DARK_GRAY);
		coachh.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new Menu1();

			}

		});

		coachh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new Coach1();
				
			}

		});
		
		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new Information1();

			}

		});

		sport.dispose(); // ��������� ������
		sport.setVisible(true); // ������ ������� �������

		try {

			JScrollPane pane = new JScrollPane(sportsmanTable);
			pane.getViewport().add(sportsmanTable);
			pane.setBounds(370, 140, 1200, 400); // �������� ������� � ���������
			sportsmanTable.setBackground(c1);
			sportsmanTable.setFont(table);
			pane.setBackground(c1);
			Vector<Vector<String>> values = getDataFromDB();

			Vector<String> hat = new Vector<String>();

			hat.add("�����");
			hat.add("������� ����������");
			hat.add("��� ����������");
			hat.add("�������� ����������");
			hat.add("���");
			hat.add("�������");
			hat.add("���"); // ��������� ������
			hat.add("����");
			hat.add("�����");
			hat.add("��� ������");
			hat.add("� �������");
			hat.add("� ���������");

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
			for (int i = 5; i <= 7; i++) {
				rowSorter.setComparator(i, new Comparator<String>() { // �������� ���� ���! ����� ������� �� �����

					@Override
					public int compare(String o1, String o2) {
						return Integer.parseInt(o1) - Integer.parseInt(o2);
					}

				});
			}
			for (int i = 10; i <= 11; i++) {
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

			JLabel namee;
			JLabel surname;
			JLabel patronimic;
			JLabel sex_sportsman;
			JLabel age_sportsman;
			JLabel weight_sportsman;
			JLabel height_sportsman;
			JLabel email_sportsman;
			JLabel sport;
			JLabel doc;
			JLabel coach;

			JTextField ID;
			JTextField t;
			JTextField t1;
			JTextField t2;
			JTextField t3;
			JTextField t4;
			JTextField t5;
			JTextField t6;
			JTextField t7; // ��� ����� �������
			JTextField t8;
			JTextField t9;
			JTextField t10;

			ID = new JTextField(10);
			t = new JTextField(10);
			t1 = new JTextField(10);
			t2 = new JTextField(10);
			t3 = new JTextField(10);
			t4 = new JTextField(10);
			t5 = new JTextField(10);
			t6 = new JTextField(10);
			t7 = new JTextField(10);
			t8 = new JTextField(10);
			t9 = new JTextField(10);
			t10 = new JTextField(10);

			namee = new JLabel("���");
			surname = new JLabel("�������");
			patronimic = new JLabel("��������");
			sex_sportsman = new JLabel("���");
			age_sportsman = new JLabel("�������");
			weight_sportsman = new JLabel("���");
			height_sportsman = new JLabel("����");
			email_sportsman = new JLabel("����������� �����");
			sport = new JLabel("��� ������");
			doc = new JLabel("� ���������");
			coach = new JLabel("� �������");

			namee.setFont(word);
			surname.setFont(word);
			patronimic.setFont(word);
			sex_sportsman.setFont(word);
			age_sportsman.setFont(word);
			weight_sportsman.setFont(word);
			height_sportsman.setFont(word);
			email_sportsman.setFont(word);
			sport.setFont(word);
			doc.setFont(word);
			coach.setFont(word);

			JComboBox tt3;
			String[] items = { "�", "�", };

			tt3 = new JComboBox(items);
			tt3.addActionListener(new ActionListener() { // �������� ��� ���������
				public void actionPerformed(ActionEvent event) {
					String st = tt3.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
					t3.setText(st); // ������ ��� ���������
				}
			});

			t.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 2) {
						super.insertString(offs, str, a);

					}
				}
			});

			t4.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
				}
			});
			t5.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
				}
			});
			t6.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
				}
			});

			t4.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 2) {
						super.insertString(offs, str, a);
					}
				}
			});

			t5.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 3) {
						super.insertString(offs, str, a);
					}
				}
			});
			t6.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 3) {
						super.insertString(offs, str, a);
					}
				}
			});
			
			 t.addKeyListener(new KeyAdapter() {
		            public void keyTyped(KeyEvent e) {
		                if (Character.isDigit(e.getKeyChar()))
		                    e.consume();
		            }
		        });
			 
			t.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 13) {
						super.insertString(offs, str, a);

					}
				}
			});
			
			 t1.addKeyListener(new KeyAdapter() {
		            public void keyTyped(KeyEvent e) {
		                if (Character.isDigit(e.getKeyChar()))
		                    e.consume();
		            }
		        });
			t1.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 15) {
						super.insertString(offs, str, a);

					}
				}
			});
			
			 t2.addKeyListener(new KeyAdapter() {
		            public void keyTyped(KeyEvent e) {
		                if (Character.isDigit(e.getKeyChar()))
		                    e.consume();
		            }
		        });
			
			t2.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 15) {
						super.insertString(offs, str, a);

					}
				}
			});

			t7.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 20) {
						super.insertString(offs, str, a);
					}

				}
			});

			/////////////
			JComboBox regi;
			regi = new JComboBox();

			conforJbox1 mycon = new conforJbox1();

			con = mycon.returnConnection();

			String win = "Select Name_tourment from Tourment";

			try {
				ptr = con.prepareStatement(win);
				rs = ptr.executeQuery();
				while (rs.next()) {

					String abc = rs.getString(1); // ��� ������� �� ���� ������ ������ � ��������
					regi.addItem(abc);
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
				ex.printStackTrace();

			}

			regi.addActionListener(new ActionListener() { // �������� ��� ���������
				public void actionPerformed(ActionEvent event) {

					String st = regi.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
					t8.setText(st); // ������ ��� ���������

				}
			});
			//////////
			/////////////
			JComboBox docy;
			docy = new JComboBox();

			conforJbox1 lol = new conforJbox1();

			con = lol.returnConnection();

			String sql = "Select ID_document from Document";

			try {
				ptr = con.prepareStatement(sql);
				rs = ptr.executeQuery();
				while (rs.next()) {

					String abc = rs.getString(1); // ��� ������� �� ���� ������ ������ � ��������
					docy.addItem(abc);
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
				ex.printStackTrace();

			}

			docy.addActionListener(new ActionListener() { // �������� ��� ���������
				public void actionPerformed(ActionEvent event) {

					String st = docy.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
					t9.setText(st); // ������ ��� ���������

				}
			});
			//////////
			/////////////
			JComboBox coala;
			coala = new JComboBox();

			conforJbox1 lola = new conforJbox1();

			con = lola.returnConnection();

			String jesus = "Select ID_coach from Coach";

			try {
				ptr = con.prepareStatement(jesus);
				rs = ptr.executeQuery();
				while (rs.next()) {

					String abc = rs.getString(1); // ��� ������� �� ���� ������ ������ � ��������
					coala.addItem(abc);
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
				ex.printStackTrace();

			}

			coala.addActionListener(new ActionListener() { // �������� ��� ���������
				public void actionPerformed(ActionEvent event) {

					String st = coala.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
					t10.setText(st); // ������ ��� ���������

				}
			});

			t3.setEditable(false);
			t8.setEditable(false);
			t9.setEditable(false);
			t10.setEditable(false);
			//////////

			panel.add(ID);
			panel.add(t);
			panel.add(t1);
			panel.add(t2);
			panel.add(t3);
			panel.add(t4);
			panel.add(t5);
			panel.add(t6);
			panel.add(t7);
			panel.add(t8);
			panel.add(t9);
			panel.add(t10);

			panel.add(coala);
			panel.add(docy);
			panel.add(regi);
			panel.add(tt3);

			panel.add(namee);
			panel.add(surname);
			panel.add(patronimic);
			panel.add(sex_sportsman);
			panel.add(age_sportsman);
			panel.add(weight_sportsman);
			panel.add(height_sportsman);
			panel.add(email_sportsman);
			panel.add(sport);
			panel.add(doc);
			panel.add(coach);

			ID.setVisible(false);
			ID.setBounds(100, 100, 200, 25);
			surname.setBounds(100, 130, 200, 25);
			t.setBounds(100, 150, 200, 25);
			namee.setBounds(100, 170, 200, 25);
			t1.setBounds(100, 200, 200, 25);
			patronimic.setBounds(100, 230, 200, 25);
			t2.setBounds(100, 250, 200, 25);
			sex_sportsman.setBounds(100, 270, 200, 25);
			tt3.setBounds(300, 300, 50, 25);//
			t3.setBounds(100, 300, 200, 25);
			age_sportsman.setBounds(100, 320, 200, 25);
			t4.setBounds(100, 350, 200, 25);
			weight_sportsman.setBounds(100, 370, 200, 25);
			t5.setBounds(100, 400, 200, 25);
			height_sportsman.setBounds(100, 420, 200, 25);
			t6.setBounds(100, 450, 200, 25);
			email_sportsman.setBounds(100, 470, 200, 25);
			t7.setBounds(100, 500, 200, 25);
			sport.setBounds(100, 520, 200, 25);
			regi.setBounds(300, 550, 100, 25);
			t8.setBounds(100, 550, 200, 25);
			doc.setBounds(100, 570, 200, 25);
			//docy.setBounds(300, 600, 50, 25);
			t9.setBounds(100, 600, 200, 25);
			coach.setBounds(100, 620, 200, 25);
			coala.setBounds(300, 650, 50, 25);
			t10.setBounds(100, 650, 200, 25);

			sportsmanTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable sportsmanTable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && sportsmanTable.getSelectedRow() != -1) { // ���������
																									// �������� �����

						confordoc1 cont = new confordoc1();

						con = cont.returnConnection();

						String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

						try {

							String sql = "Select * from Sportsman where ID=?";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								ID.setText(rs.getString("ID"));
								t.setText(rs.getString("Surname_sportsman"));
								t1.setText(rs.getString("Name_sportsman"));
								t2.setText(rs.getString("Patronymic_sportsman"));
								t3.setText(rs.getString("Sex_sportsman"));
								t4.setText(rs.getString("Age_sportsman"));
								t5.setText(rs.getString("Weight_sportsman"));
								t6.setText(rs.getString("Height_sportsman"));
								t7.setText(rs.getString("Email_sportsman"));
								t8.setText(rs.getString("Vid_sporta"));
								t10.setText(rs.getString("Coach_sportsman"));
								t9.setText(rs.getString("Document_sportsman"));

							}

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex);
						}

					}
				}

			});

			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (t.getText().equals("") || t1.getText().equals("") || t2.getText().equals("")
							|| t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("")
							|| t6.getText().equals("") || t7.getText().equals("") || t8.getText().equals("")
							|| t9.getText().equals("") || t10.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "���������� �������� ������ �� �������, ���� �� ������ ���� �������!");

					} else {

						try {

							String uppdate;
							confordoc1 cont = new confordoc1();

							con = cont.returnConnection();

							String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

							uppdate = sno;

							String sql = "Update Sportsman set Surname_sportsman=?,Name_sportsman=?,"
									+ "Patronymic_sportsman=?,Sex_sportsman=?,Age_sportsman=?,Weight_sportsman=?,Height_sportsman=?,"
									+ "Email_sportsman=?,Vid_sporta=?,Coach_sportsman=? where ID="
									+ uppdate;

							ptr = con.prepareStatement(sql);

							ptr.setString(1, t.getText());
							ptr.setString(2, t1.getText());
							ptr.setString(3, t2.getText());
							ptr.setString(4, t3.getText());
							ptr.setString(5, t4.getText());
							ptr.setString(6, t5.getText());
							ptr.setString(7, t6.getText());
							ptr.setString(8, t7.getText());
							ptr.setString(9, t8.getText());
							ptr.setString(10, t9.getText());
							//ptr.setString(11, t10.getText());
							ptr.executeUpdate();

							JOptionPane.showMessageDialog(null, "������ ���������");

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "����� �������� ��� ���� � ����������,�������� ������");
						//	JOptionPane.showMessageDialog(null, ex);
						}
					}

				}

			});

			delete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(null, "�� ������������� ������ ������� ��� ������?", "��������",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // ���������� ����

						try {
							
							confordoc1 cont = new confordoc1();

							con = cont.returnConnection();

							st = con.createStatement();

							String id = ID.getText();

							String sql = "Delete from Sportsman where ID=" + id;

							st.executeUpdate(sql);

							JOptionPane.showMessageDialog(null, "������ �������");

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "���������� �������� ������ �� �������, ���� �� ������ ���� �������!");
							//JOptionPane.showMessageDialog(null, ex);
						}

					} else {

					}

				}
			});

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		panel.add(back); // ���������� ������ �� ������
		panel.add(update);
		panel.add(delete);
		panel.add(create);
		panel.add(coachh);
		panel.add(background);
		sport.add(panel);
		sport.setVisible(true);
		
		update.setEnabled(false);
		delete.setEnabled(false);

		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

		});

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
		rs = st.executeQuery(
				"SELECT Sportsman.ID, Sportsman.Surname_sportsman, Sportsman.Name_sportsman, Sportsman.Patronymic_sportsman, Sportsman.Sex_sportsman, Sportsman.Age_sportsman, Sportsman.Weight_sportsman, Sportsman.Height_sportsman, Sportsman.Email_sportsman, Sportsman.Vid_sporta, Sportsman.Coach_sportsman, Sportsman.Document_sportsman\r\n"
						+ "FROM Sportsman;\r\n" + ""); // SQL// ������

		String p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(1);
			p3 = rs.getString(3);
			p2 = rs.getString(2);
			p4 = rs.getString(4);
			p5 = rs.getString(5);
			p6 = rs.getString(6);
			p7 = rs.getString(7);
			p8 = rs.getString(8);
			p9 = rs.getString(9);
			p10 = rs.getString(10);
			p11 = rs.getString(11);
			p12 = rs.getString(12);

			element.add(p1);
			element.add(p2);
			element.add(p3);
			element.add(p4);
			element.add(p5);
			element.add(p6);
			element.add(p7);
			element.add(p8);
			element.add(p9);
			element.add(p10);
			element.add(p11);
			element.add(p12);

			result.add(element);
		}

		rs.close();
		st.close(); // �������� ������
		con.close();
		return result;

	}

}