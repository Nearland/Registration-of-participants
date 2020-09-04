
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class docinfo {

	public JFrame info;

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public docinfo() {

		info = new JFrame("DocumentInfo");
		info.setLocationRelativeTo(null);// � ������ ������
		info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		info.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		info.setUndecorated(true); // ������ �� ��������� ������

		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // ���������� �������� �� ������ ���
		background.setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel(); // ������
		panel.setLayout(null);

		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17); // ����� ��� ������
		Font name = new Font("TimesRoman", Font.BOLD, 30); // ����� � ����
		Font word = new Font("TimesRoman", Font.ITALIC, 14); // ����� � label

		JLabel Name = new JLabel("���������");
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
		add.setBounds(1100, 350, 150, 50);
		add.setBorderPainted(false); // ������� ������� ������
		add.setFocusPainted(false); // ������� ����� ������ ������
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				info.dispose(); // ������ �����
				new Document();
			}

		});

		JLabel zaayava;
		JLabel pasport;
		JLabel seriya;
		JLabel nomer;
		JLabel police;
		JLabel soglasie;
		JLabel obyazatelstvo;
		JLabel sport_book;
		JLabel regis;
		JLabel dopu;

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

		JComboBox yes;
		JComboBox yes1;
		JComboBox yes2;
		JComboBox yes3;
		JComboBox yes4;
		JComboBox regi;
		JComboBox dop;
		// JComboBox num;

		String[] items = { "", "��", "���", };
		String[] dopusk = { "��", "��� ������ �� �������", "��� �������� ���������", "��� ��������",
				"��� �������������� ���������" };

		yes = new JComboBox(items);
		yes1 = new JComboBox(items);
		yes2 = new JComboBox(items);
		yes3 = new JComboBox(items);
		yes4 = new JComboBox(items);
		regi = new JComboBox();
		dop = new JComboBox(dopusk);

		yes.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {
				String st = yes.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t1.setText(st); // ������ ��� ���������
			}
		});

		yes1.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {
				String st = yes1.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t2.setText(st); // ������ ��� ���������
			}
		});

		yes2.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {
				String st = yes2.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t6.setText(st); // ������ ��� ���������
			}
		});

		yes3.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {
				String st = yes3.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t7.setText(st); // ������ ��� ���������
			}
		});

		yes4.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {
				String st = yes4.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t8.setText(st); // ������ ��� ���������
			}
		});
//////////////////////////
		conforJbox mycon = new conforJbox();

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
//////////////////////////

		dop.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {

				String st = dop.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t10.setText(st); // ������ ��� ���������

				if (t10.getText().equals("��")) {

					t10.setText("1");

				}
				else if(t10.getText().equals("��� ������ �� �������"))
					t10.setText("2");
				else if(t10.getText().equals("��� �������� ���������"))
					t10.setText("3");
				else if(t10.getText().equals("��� ��������"))
					t10.setText("4");
				else if(t10.getText().equals("��� �������������� ���������"))
					t10.setText("5");
				
			}

		});

//////////////////////////

		regi.addActionListener(new ActionListener() { // �������� ��� ���������
			public void actionPerformed(ActionEvent event) {

				String st = regi.getSelectedItem().toString(); // ����� � ��� ����� �� ����� � ���������
				t9.setText(st); // ������ ��� ���������

			}
		});

		t1.setDocument(new PlainDocument() {
			String chars = "�����";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 3) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t2.setDocument(new PlainDocument() {
			String chars = "�����";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 3) {
						super.insertString(offs, str, a);
					}
					//
					if (t2.getText().equals("���")) {

						t3.setText("0");
						t4.setText("0");
						t3.setEditable(false);
						t4.setEditable(false);
					} else {
						t3.setDocument(new PlainDocument() {
							String chars = "1234567890";

							@Override
							public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
								if (chars.indexOf(str) != -1) {
									if (getLength() < 4) {
										super.insertString(offs, str, a);
									}
								}
							}
						});
						t4.setDocument(new PlainDocument() {
							String chars = "1234567890";

							@Override
							public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
								if (chars.indexOf(str) != -1) {
									if (getLength() < 6) {
										super.insertString(offs, str, a);
									}
								}
							}
						});
						t3.setEditable(true);
						t4.setEditable(true);
					}
				}
			}
		});

		t3.setDocument(new PlainDocument() {
			String chars = "0123456789";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 4) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t4.setDocument(new PlainDocument() {
			String chars = "0123456789";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 6) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t5.setDocument(new PlainDocument() {
			String chars = "0123456789";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 16) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t9.setDocument(new PlainDocument() {
			String chars = "1234567890";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 1) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		info.dispose(); // ��������� ������
		info.setVisible(true); // ������ ������� �������
		//
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String zayava = t1.getText();
				String pasport = t2.getText();
				String seriya = t3.getText();
				String nomer = t4.getText();
				String police = t5.getText();
				String soglasie = t6.getText();
				String obyazatelstvo = t7.getText();
				String sport_cniga = t8.getText();
				String regis = t9.getText();
				String dopu = t10.getText();

				if (t1.getText().trim().length() > 0 && t2.getText().trim().length() > 0
						&& t3.getText().trim().length() > 0 && t4.getText().trim().length() > 0
						&& t5.getText().trim().length() > 0 && t6.getText().trim().length() > 0
						&& t7.getText().trim().length() > 0 && t8.getText().trim().length() > 0
						&& t9.getText().trim().length() > 0 && t10.getText().trim().length() > 0) {

					try {

						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
						ptr = con.prepareStatement(
								"insert into Document(Zayavka_na_uchastie,Passport_sportsmana,Passport_seriya,Passport_number,Medical_policy,Parental_consent,Commitment,Credit_book,Registratura,ID_reas) values (?,?,?,?,?,?,?,?,?,?)");

						ptr.setString(1, zayava);
						ptr.setString(2, pasport);
						ptr.setString(3, seriya);
						ptr.setString(4, nomer);
						ptr.setString(5, police);
						ptr.setString(6, soglasie);
						ptr.setString(7, obyazatelstvo);
						ptr.setString(8, sport_cniga);
						ptr.setString(9, regis);
						ptr.setString(10, dopu);
						ptr.executeUpdate();

						JOptionPane.showMessageDialog(null, "�������� ��������");

					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e1);
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "��������� ��� ����");
			}
		});
		//
		zaayava = new JLabel("������� ������ �� �������");
		pasport = new JLabel("�������");
		seriya = new JLabel("�����");
		nomer = new JLabel("�����");
		police = new JLabel("���. �����");
		soglasie = new JLabel("�������� ���������");
		obyazatelstvo = new JLabel("��������������");
		sport_book = new JLabel("������ ����������");
		regis = new JLabel("� ������������");
		dopu = new JLabel("������");

		zaayava.setFont(word);
		pasport.setFont(word);
		seriya.setFont(word);
		nomer.setFont(word);
		police.setFont(word);
		soglasie.setFont(word);
		obyazatelstvo.setFont(word);
		sport_book.setFont(word);
		regis.setFont(word);

		yes.setBounds(300, 150, 50, 25);//
		yes1.setBounds(300, 250, 50, 25);//
		yes2.setBounds(800, 250, 50, 25);
		yes3.setBounds(800, 350, 50, 25);//
		yes4.setBounds(800, 450, 50, 25);
		regi.setBounds(1300, 150, 50, 25);

		t1.setEditable(false);
		t2.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t8.setEditable(false);
		t9.setEditable(false);
		t10.setEnabled(false);

		zaayava.setBounds(100, 130, 200, 25);
		t1.setBounds(100, 150, 200, 25);
		pasport.setBounds(100, 230, 200, 25);
		t2.setBounds(100, 250, 200, 25);
		seriya.setBounds(100, 330, 200, 25);
		t3.setBounds(100, 350, 200, 25);
		nomer.setBounds(100, 430, 200, 25);
		t4.setBounds(100, 450, 200, 25);
		police.setBounds(600, 130, 200, 25);
		t5.setBounds(600, 150, 200, 25);
		soglasie.setBounds(600, 230, 200, 25);
		t6.setBounds(600, 250, 200, 25);
		obyazatelstvo.setBounds(600, 330, 200, 25);
		t7.setBounds(600, 350, 200, 25);

		sport_book.setBounds(600, 430, 200, 25);
		t8.setBounds(600, 450, 200, 25);
		regis.setBounds(1100, 130, 200, 25);
		t9.setBounds(1100, 150, 200, 25);

		dop.setBounds(1300, 250, 200, 25);
		dopu.setBounds(1100, 230, 200, 25);
		t10.setBounds(1100, 250, 200, 25);

		panel.add(add);

		panel.add(yes);
		panel.add(yes1);
		panel.add(yes2);
		panel.add(yes3);
		panel.add(yes4);
		panel.add(regi);
		panel.add(dop);

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

		panel.add(zaayava);
		panel.add(pasport);
		panel.add(seriya);
		panel.add(nomer);
		panel.add(police);
		panel.add(soglasie);
		panel.add(obyazatelstvo);
		panel.add(sport_book);
		panel.add(regis);
		panel.add(dopu);

		panel.add(back); // ���������� ������ �� ������
		panel.add(background);
		info.setVisible(true); // ������ ������� �������
		info.add(panel);

	}

}
