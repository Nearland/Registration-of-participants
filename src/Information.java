import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Information {

	public JFrame info;

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public Information() {

		info = new JFrame("Information");
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

		JLabel Name = new JLabel("Информация");
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
		add.setBounds(1100, 450, 150, 50);
		add.setBorderPainted(false); // убирает обводку кнопки
		add.setFocusPainted(false); // убирает рамку текста кнопки
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				info.dispose(); // кнопка назад
				new Sportsman();
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

		JTextField t;
		JTextField t1;
		JTextField t2;
		JTextField t3;
		JTextField t4;
		JTextField t5;
		JTextField t6;
		JTextField t7; // Тут белые полоски
		JTextField t8;
		JTextField t9;
		JTextField t10;

		JComboBox tt3;
		String[] items = { "М", "Ж", };

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

		tt3 = new JComboBox(items);
		tt3.addActionListener(new ActionListener() { // Джейбокс это комбобокс
			public void actionPerformed(ActionEvent event) {
				String st = tt3.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
				t3.setText(st); // джейти это текстфилд
			}
		});

		t4.setDocument(new PlainDocument() {
			String chars = "0123456789";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) { // тут я запрещаю вводы лишних символов
					if (getLength() < 2) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t3.setDocument(new PlainDocument() {
			String chars = "МЖ";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) { // тут я запрещаю вводы лишних символов
					if (getLength() < 1) {
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
					if (getLength() < 3) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t6.setDocument(new PlainDocument() {
			String chars = "0123456789";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 3) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t9.setDocument(new PlainDocument() {
			// String chars = "0123456789";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				// if (chars.indexOf(str) != -1) {
				if (getLength() < 4) {
					super.insertString(offs, str, a);
				}
				// }
			}
		});

		t.setDocument(new PlainDocument() {
			String chars = " -.,АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 13) {
						super.insertString(offs, str, a);
					}
				}
			}
		});
		t8.setDocument(new PlainDocument() {

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (getLength() < 20) {
					super.insertString(offs, str, a);
				}

			}
		});
		t1.setDocument(new PlainDocument() {
			String chars = " -.,АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 15) {
						super.insertString(offs, str, a);
					}
				}
			}
		});
		t2.setDocument(new PlainDocument() {
			String chars = " -.,АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 15) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		t7.setDocument(new PlainDocument() {
			// String chars = " -.,@#АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯа"
			// + "бвгдеёжзийклмнопрстуфхцчшщъыьэюяqwertyuiopa"
			// + "sdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				// if (chars.indexOf(str) != -1) {
				if (getLength() < 20) {
					super.insertString(offs, str, a);
				}
				// }
			}
		});

		/////////////
		JComboBox regi;
		regi = new JComboBox();

		conforJbox mycon = new conforJbox();

		con = mycon.returnConnection();

		String win = "Select Name_tourment from Tourment";

		try {
			ptr = con.prepareStatement(win);
			rs = ptr.executeQuery();
			while (rs.next()) {

				String abc = rs.getString(1); // тут индексы из базы данных сунуты в Джейбокс
				regi.addItem(abc);
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
			ex.printStackTrace();

		}

		regi.addActionListener(new ActionListener() { // Джейбокс это комбобокс
			public void actionPerformed(ActionEvent event) {

				String st = regi.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
				t8.setText(st); // джейти это текстфилд

			}
		});
		//////////
/////////////
		JComboBox docy;
		docy = new JComboBox();

		conforJbox lol = new conforJbox();

		con = lol.returnConnection();

		String sql = "Select ID_document from Document";

		try {
			ptr = con.prepareStatement(sql);
			rs = ptr.executeQuery();
			while (rs.next()) {

				String abc = rs.getString(1); // тут индексы из базы данных сунуты в Джейбокс
				docy.addItem(abc);
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
			ex.printStackTrace();

		}

		docy.addActionListener(new ActionListener() { // Джейбокс это комбобокс
			public void actionPerformed(ActionEvent event) {

				String st = docy.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
				t9.setText(st); // джейти это текстфилд

			}
		});
		//////////
/////////////
		String[] nocoach = { "Нет тренера" };
		JComboBox coala;
		coala = new JComboBox(nocoach);

		conforJbox lola = new conforJbox();

		con = lola.returnConnection();

		String jesus = "Select ID_coach from Coach";

		try {
			ptr = con.prepareStatement(jesus);
			rs = ptr.executeQuery();
			while (rs.next()) {

				String abc = rs.getString(1); // тут индексы из базы данных сунуты в Джейбокс
				coala.addItem(abc);
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
			ex.printStackTrace();

		}

		coala.addActionListener(new ActionListener() { // Джейбокс это комбобокс
			public void actionPerformed(ActionEvent event) {

				String st = coala.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
				t10.setText(st); // джейти это текстфилд

				if (t10.getText().equals("Нет тренера"))
					t10.setText("10");

			}
		});

		t8.setEditable(false);
		t9.setEditable(false);
		t10.setEditable(false);
//////////

		info.dispose(); // отключает кнопки
		info.setVisible(true); // Делает таблицу видимым
		//
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String lname = t.getText();
				String fname = t1.getText();
				String pname = t2.getText();
				String sex = t3.getText();
				String age = t4.getText();
				String weight = t5.getText();
				String height = t6.getText();
				String email = t7.getText();
				String sport = t8.getText();
				String doc = t9.getText();
				String coach = t10.getText();

				if (t.getText().equals("") || t1.getText().equals("") || t2.getText().equals("")
						|| t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("")
						|| t6.getText().equals("") || t7.getText().equals("") || t8.getText().equals("")
						|| t9.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Пожалуйста заполните все поля");

				} else {

					try {

						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
						ptr = con.prepareStatement(
								"insert into Sportsman(Name_sportsman,Surname_sportsman,Patronymic_sportsman,Sex_sportsman,Age_sportsman,Weight_sportsman,Height_sportsman,Email_sportsman,Vid_sporta,Document_sportsman,Coach_sportsman) values (?,?,?,?,?,?,?,?,?,?,?)");

						ptr.setString(1, lname);
						ptr.setString(2, fname);
						ptr.setString(3, pname);
						ptr.setString(4, sex);
						ptr.setString(5, age);
						ptr.setString(6, weight);
						ptr.setString(7, height);
						ptr.setString(8, email);
						ptr.setString(9, sport);
						ptr.setString(10, doc);
						ptr.setString(11, coach);
						ptr.executeUpdate();

						JOptionPane.showMessageDialog(null, "Запись добавлена");

					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Такой документ уже есть у спортсмена,выберите другой");
						// JOptionPane.showMessageDialog(null, e1);
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Такой документ уже есть у спортсмена,выберите другой");
						// JOptionPane.showMessageDialog(null, e1);
						e1.printStackTrace();
					}

				}
			}
		});
		//
		namee = new JLabel("Имя");
		surname = new JLabel("Фамилия");
		patronimic = new JLabel("Отчество");
		sex_sportsman = new JLabel("Пол");
		age_sportsman = new JLabel("Возраст");
		weight_sportsman = new JLabel("Вес");
		height_sportsman = new JLabel("Рост");
		email_sportsman = new JLabel("Электронная почта");
		sport = new JLabel("Вид спорта");
		doc = new JLabel("№ документа");
		coach = new JLabel("№ тренера");

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

		namee.setBounds(100, 130, 200, 25);
		t.setBounds(100, 150, 200, 25);
		surname.setBounds(100, 230, 200, 25);
		t1.setBounds(100, 250, 200, 25);
		patronimic.setBounds(100, 330, 200, 25);
		t2.setBounds(100, 350, 200, 25);
		tt3.setBounds(300, 450, 50, 25);//
		sex_sportsman.setBounds(100, 430, 200, 25);
		t3.setBounds(100, 450, 200, 25);
		age_sportsman.setBounds(600, 130, 200, 25);
		t4.setBounds(600, 150, 200, 25);
		weight_sportsman.setBounds(600, 230, 200, 25);
		t5.setBounds(600, 250, 200, 25);
		height_sportsman.setBounds(600, 330, 200, 25);
		t6.setBounds(600, 350, 200, 25);
		email_sportsman.setBounds(600, 430, 200, 25);
		t7.setBounds(600, 450, 200, 25);
		sport.setBounds(1100, 130, 200, 25);
		regi.setBounds(1300, 150, 100, 25);
		t8.setBounds(1100, 150, 200, 25);
		coach.setBounds(1100, 330, 200, 25);
		docy.setBounds(1300, 250, 50, 25);
		t9.setBounds(1100, 250, 200, 25);
		doc.setBounds(1100, 230, 200, 25);
		coala.setBounds(1300, 350, 100, 25);
		t10.setBounds(1100, 350, 200, 25);

		panel.add(add);

		panel.add(regi);
		panel.add(docy);
		panel.add(coala);

		panel.add(t);
		panel.add(t1);
		panel.add(t2);
		panel.add(t3);
		panel.add(tt3);
		panel.add(t4);
		panel.add(t5);
		panel.add(t6);
		panel.add(t7);
		panel.add(t8);
		panel.add(t9);
		panel.add(t10);

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

		panel.add(back); // добавление кнопок на панель
		panel.add(background);
		info.setVisible(true); // Делает таблицу видимым
		info.add(panel);

	}

}
