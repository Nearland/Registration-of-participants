package copy;


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

public class addcoach1 {

	public JFrame info;

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public addcoach1() {

		info = new JFrame("Coachinfo");
		info.setLocationRelativeTo(null);// в центре экрана
		info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		info.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		info.setUndecorated(true); // делает не активными кнопки

		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // Добавление картинки на задний фон
		background.setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel(); // панель
		panel.setLayout(null);

		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17); // шрифт для кнопок
		Font name = new Font("TimesRoman", Font.BOLD, 30); // шрифт У меню
		Font word = new Font("TimesRoman", Font.ITALIC, 14); // шрифт У label

		JLabel Name = new JLabel("Тренер");
		Name.setBounds(750, 50, 400, 60);
		Name.setForeground(Color.YELLOW); // заголовок на верху
		Name.setFont(name);
		panel.add(Name);

		JButton back = new JButton("Назад");
		back.setBounds(725, 740, 150, 50);
		back.setBorderPainted(false); // убирает обводку кнопки
		back.setFocusPainted(false); // убирает рамку текста кнопки
		back.setForeground(Color.cyan);
		back.setBackground(Color.DARK_GRAY);
		back.setFont(BigFontTR);

		JButton add = new JButton("Добавить");
		add.setBounds(100, 750, 150, 50);
		add.setBorderPainted(false); // убирает обводку кнопки
		add.setFocusPainted(false); // убирает рамку текста кнопки
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				info.dispose(); // кнопка назад
				new Coach1();
			}

		});

		JLabel surname;
		JLabel nameo;
		JLabel patronimic;
		JLabel vidsporta;
		JLabel email;
		JLabel phone;

		JTextField t1;// Тут белые полоски
		JTextField t2;
		JTextField t3;
		JTextField t4;
		JTextField t5;
		JTextField t6;

		t1 = new JTextField(10);
		t2 = new JTextField(10);
		t3 = new JTextField(10);
		t4 = new JTextField(10);
		t5 = new JTextField(10);
		t6 = new JTextField(10);

		t1.setDocument(new PlainDocument() {
			String chars = " -.,АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 20) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t2.setDocument(new PlainDocument() {
			String chars = " -.,АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя1234567890";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 30) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t3.setDocument(new PlainDocument() {
			String chars = " -.,АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя1234567890";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 20) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t4.setDocument(new PlainDocument() {
			String chars = " -АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 30) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t6.setDocument(new PlainDocument() {

			String chars = " 1234567890";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 20) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t5.setDocument(new PlainDocument() {

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

				if (getLength() < 30) {
					super.insertString(offs, str, a);

				}
			}
		});

		info.dispose(); // отключает кнопки
		info.setVisible(true); // Делает таблицу видимым
		//
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String surname = t1.getText();
				String name = t2.getText();
				String patronimic = t3.getText();
				String vidsporta = t4.getText();
				String email = t5.getText();
				String phone = t6.getText();

				if (t1.getText().trim().length() > 0 && t2.getText().trim().length() > 0
						&& t3.getText().trim().length() > 0 && t4.getText().trim().length() > 0
						&& t5.getText().trim().length() > 0 && t6.getText().trim().length() > 0) {

					try {

						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
						ptr = con.prepareStatement(
								"insert into Coach(Surname_coach,Name_coach,Patronimic_coach,Vid_sporta,Email_coach,Phone_coach) values (?,?,?,?,?,?)");

						ptr.setString(1, surname);
						ptr.setString(2, name);
						ptr.setString(3, patronimic);
						ptr.setString(4, vidsporta);
						ptr.setString(5, email);
						ptr.setString(6, phone);
						ptr.executeUpdate();

						JOptionPane.showMessageDialog(null, "Тренер добавлена");

					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e1);
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "Заполните все поля");
			}
		});
		//
		surname = new JLabel("Фамилия");
		nameo = new JLabel("Имя");
		patronimic = new JLabel("Отчество");
		vidsporta = new JLabel("Вид спорта");
		email = new JLabel("Почта");
		phone = new JLabel("Телефон");

		surname.setFont(word);
		nameo.setFont(word);
		patronimic.setFont(word);
		vidsporta.setFont(word);
		email.setFont(word);
		phone.setFont(word);

		surname.setBounds(100, 130, 200, 25);
		t1.setBounds(100, 150, 200, 25);
		nameo.setBounds(100, 230, 200, 25);
		t2.setBounds(100, 250, 200, 25);
		patronimic.setBounds(100, 330, 200, 25);
		t3.setBounds(100, 350, 200, 25);
		vidsporta.setBounds(100, 430, 200, 25);
		t4.setBounds(100, 450, 200, 25);
		email.setBounds(100, 530, 200, 25);
		t5.setBounds(100, 550, 200, 25);
		phone.setBounds(100, 630, 200, 25);
		t6.setBounds(100, 650, 200, 25);

		panel.add(add);

		panel.add(t2);
		panel.add(t3);
		panel.add(t4);
		panel.add(t5);
		panel.add(t1);
		panel.add(t6);

		panel.add(surname);
		panel.add(nameo);
		panel.add(patronimic);
		panel.add(vidsporta);
		panel.add(email);
		panel.add(phone);

		panel.add(back); // добавление кнопок на панель
		panel.add(background);
		info.setVisible(true); // Делает таблицу видимым
		info.add(panel);

	}

}
