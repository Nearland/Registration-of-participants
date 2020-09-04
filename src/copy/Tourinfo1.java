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

public class Tourinfo1 {

	public JFrame info;

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public Tourinfo1() {

		info = new JFrame("TourInfo");
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

		JLabel Name = new JLabel("Турниры");
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
		add.setBounds(100, 650, 150, 50);
		add.setBorderPainted(false); // убирает обводку кнопки
		add.setFocusPainted(false); // убирает рамку текста кнопки
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				info.dispose(); // кнопка назад
				new Tournament1();
			}

		});

		JLabel tourn;
		JLabel place;
		JLabel city;
		JLabel datao;
		JLabel datae;

		JTextField t1;// Тут белые полоски
		JTextField t2;
		JTextField t3;
		JTextField t4;
		JTextField t5;

		t1 = new JTextField(10);
		t2 = new JTextField(10);
		t3 = new JTextField(10);
		t4 = new JTextField(10);
		t5 = new JTextField(10);
		
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
			String chars = " .1234567890";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 10) {
						super.insertString(offs, str, a);
					}
				}
			}
		});
		
		t5.setDocument(new PlainDocument() {
			String chars = " .1234567890";

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < 10) {
						super.insertString(offs, str, a);
					}
				}
			}
		});

		info.dispose(); // отключает кнопки
		info.setVisible(true); // Делает таблицу видимым
		//
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = t1.getText();
				String place = t2.getText();
				String city = t3.getText();
				String datao = t4.getText();
				String datae = t5.getText();

				if (t1.getText().trim().length() > 0 && t2.getText().trim().length() > 0
						&& t3.getText().trim().length() > 0 && t4.getText().trim().length() > 0
						&& t5.getText().trim().length() > 0) {

					try {

						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
						ptr = con.prepareStatement(
								"insert into Tourment(Name_tourment,Place_tourment,City_tourment,Data_open,Data_end) values (?,?,?,?,?)");

						ptr.setString(1, name);
						ptr.setString(2, place);
						ptr.setString(3, city);
						ptr.setString(4, datao);
						ptr.setString(5, datae);
						ptr.executeUpdate();

						JOptionPane.showMessageDialog(null, "Турнир добавлена");

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
		tourn = new JLabel("Название");
		place = new JLabel("Место");
		city = new JLabel("Город");
		datao = new JLabel("Дата начала");
		datae = new JLabel("дата Закрытия");

		tourn.setFont(word);
		place.setFont(word);
		city.setFont(word);
		datao.setFont(word);
		datae.setFont(word);

		tourn.setBounds(100, 130, 200, 25);
		t1.setBounds(100, 150, 200, 25);
		place.setBounds(100, 230, 200, 25);
		t2.setBounds(100, 250, 200, 25);
		city.setBounds(100, 330, 200, 25);
		t3.setBounds(100, 350, 200, 25);
		datao.setBounds(100, 430, 200, 25);
		t4.setBounds(100, 450, 200, 25);
		datae.setBounds(100, 530, 200, 25);
		t5.setBounds(100, 550, 200, 25);

		panel.add(add);

		panel.add(t2);
		panel.add(t3);
		panel.add(t4);
		panel.add(t5);
		panel.add(t1);

		panel.add(tourn);
		panel.add(place);
		panel.add(city);
		panel.add(datao);
		panel.add(datae);

		panel.add(back); // добавление кнопок на панель
		panel.add(background);
		info.setVisible(true); // Делает таблицу видимым
		info.add(panel);

	}

}
