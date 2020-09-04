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

public class registratura1 {

	public JFrame sport;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	JButton update = new JButton("��������");
	JButton delete = new JButton("�������");

	public registratura1() {

		sport = new JFrame("Registratura");
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

		JLabel Name = new JLabel("������������");
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
		update.setBounds(100, 450, 150, 50);
		update.setBorderPainted(false); // ������� ������� ������
		update.setFocusPainted(false); // ������� ����� ������ ������
		update.setForeground(Color.cyan);
		update.setBackground(Color.DARK_GRAY);
		update.setFont(BigFontTR);

		// JButton delete = new JButton("�������");
		delete.setBounds(100, 550, 150, 50);
		delete.setBorderPainted(false); // ������� ������� ������
		delete.setFocusPainted(false); // ������� ����� ������ ������
		delete.setForeground(Color.cyan);
		delete.setBackground(Color.DARK_GRAY);
		delete.setFont(BigFontTR);

		JButton create = new JButton("�������� ��������������");
		create.setBounds(1210, 540, 260, 50);
		create.setBorderPainted(false); // ������� ������� ������
		create.setFocusPainted(false); // ������� ����� ������ ������
		create.setForeground(Color.cyan);
		create.setBackground(Color.DARK_GRAY);
		create.setFont(BigFontTR);

		JButton dopusc = new JButton("�������");
		dopusc.setBounds(1210, 600, 150, 50);
		dopusc.setBorderPainted(false); // ������� ������� ������
		dopusc.setFocusPainted(false); // ������� ����� ������ ������
		dopusc.setForeground(Color.cyan);
		dopusc.setBackground(Color.DARK_GRAY);
		dopusc.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new Menu1();

			}

		});

		dopusc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new Dopusk1();

			}

		});

		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // ������ �����
				new reginfo1();

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

			hat.add("� ������������");
			hat.add("� �������"); // ��������� ������
			hat.add("�����");
			hat.add("�������");

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

			JLabel tourn;
			JLabel mail;
			JLabel phone;

			JTextField ID;
			JTextField t1;
			JTextField t2;// ��� ����� �������
			JTextField t3;

			ID = new JTextField(10);
			t1 = new JTextField(10);
			t2 = new JTextField(10);
			t3 = new JTextField(10);

			tourn = new JLabel("� �������");
			mail = new JLabel("�����");
			phone = new JLabel("�������");

			tourn.setFont(word);
			mail.setFont(word);
			phone.setFont(word);

//////////////////////////

			JComboBox regi;
			regi = new JComboBox();

			conforJbox1 mycon = new conforJbox1();

			con = mycon.returnConnection();

			String win = "Select ID_registrature from Registratura";

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
					t1.setText(st); // ������ ��� ���������

				}
			});
//////////////////////////

			t3.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 11) {
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
			ID.setBounds(100, 100, 200, 25);
			t1.setEditable(false);
			regi.setBounds(300, 150, 50, 25);//
			tourn.setBounds(100, 130, 200, 25);
			t1.setBounds(100, 150, 200, 25);
			mail.setBounds(100, 230, 200, 25);
			t2.setBounds(100, 250, 200, 25);
			phone.setBounds(100, 330, 200, 25);
			t3.setBounds(100, 350, 200, 25);

			sportsmanTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable sportsmanTable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && sportsmanTable.getSelectedRow() != -1) { // ���������
																									// �������� �����

						confordoc1 cont = new confordoc1();

						con = cont.returnConnection();

						String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

						try {

							String sql = "Select * from Registratura where ID_registrature=?";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								ID.setText(rs.getString("ID_registrature"));
								t1.setText(rs.getString("Tourmaent"));
								t2.setText(rs.getString("Email_registratura"));
								t3.setText(rs.getString("Phone_registratura"));

							}

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					}

				}

			});

			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")) {

						JOptionPane.showMessageDialog(null,
								"���������� �������� ������ �� �������, ���� �� ������ ���� �������!");

					} else {

						try {

							String uppdate;
							confordoc1 cont = new confordoc1();

							con = cont.returnConnection();

							String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

							uppdate = sno;

							String sql = "Update Registratura set Tourmaent=?,Email_registratura=?,"
									+ "Phone_registratura=? where ID_registrature=" + uppdate;

							ptr = con.prepareStatement(sql);

							ptr.setString(1, t1.getText());
							ptr.setString(2, t2.getText());
							ptr.setString(3, t3.getText());
							ptr.executeUpdate();

							JOptionPane.showMessageDialog(null, "������ ���������");

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null,
									"���������� �������� ������ �� �������, ���� �� ������ ���� �������!");
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

			sportsmanTable.getTableHeader().setReorderingAllowed(false); // ������ �� �������������� ��������
			
			panel.add(ID);
			panel.add(t1);
			panel.add(t2);
			panel.add(t3);
			panel.add(regi);

			panel.add(tourn);
			panel.add(mail);
			panel.add(phone);

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		update.setEnabled(false);
		delete.setEnabled(false);

		panel.add(back); // ���������� ������ �� ������
		panel.add(update);
		panel.add(create);
		panel.add(delete);
		panel.add(dopusc);
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
		rs = st.executeQuery(
				"SELECT Registratura.ID_registrature, Registratura.Tourmaent, Registratura.Email_registratura, Registratura.Phone_registratura\r\n"
						+ "FROM Registratura;\r\n" + ""); // SQL// ������

		String p1, p2, p3, p4, p5, p6, p7;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(1);
			p2 = rs.getString(3);
			p3 = rs.getString(2);
			p4 = rs.getString(4);

			element.add(p1);
			element.add(p3);
			element.add(p2);
			element.add(p4);

			result.add(element);
		}

		rs.close();
		st.close(); // �������� ������
		con.close();
		return result;

	}

}