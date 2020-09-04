package copy;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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

public class reginfo1 {

	public JFrame info;

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public reginfo1() {

		info = new JFrame("RegistraturaInfo");
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

		JLabel Name = new JLabel("Регистратурная");
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
		add.setBounds(100, 450, 150, 50);
		add.setBorderPainted(false); // убирает обводку кнопки
		add.setFocusPainted(false); // убирает рамку текста кнопки
		add.setForeground(Color.cyan);
		add.setBackground(Color.DARK_GRAY);
		add.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				info.dispose(); // кнопка назад
				new registratura1();
			}

		});

		JLabel tourn;
		JLabel mail;
		JLabel phone;

		JTextField t1;
		JTextField t2;// Тут белые полоски
		JTextField t3;

		t1 = new JTextField(10);
		t2 = new JTextField(10);
		t3 = new JTextField(10);

		info.dispose(); // отключает кнопки
		info.setVisible(true); // Делает таблицу видимым
		//
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tourn = t1.getText();
				String mail = t2.getText();
				String phone = t3.getText();

				if (t1.getText().trim().length() > 0 && t2.getText().trim().length() > 0
						&& t3.getText().trim().length() > 0) {

					try {

						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						con = DriverManager.getConnection("jdbc:ucanaccess://Sport.accdb");
						ptr = con.prepareStatement(
								"insert into Registratura(Tourmaent,Email_registratura,Phone_registratura) values (?,?,?)");

						ptr.setString(1, tourn);
						ptr.setString(2, mail);
						ptr.setString(3, phone);
						ptr.executeUpdate();

						JOptionPane.showMessageDialog(null, "Регистратура добавлена");

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
				t1.setText(st); // джейти это текстфилд

			}
		});
//////////////////////////

		t3.setDocument(new PlainDocument() {

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

				if (getLength() < 9) {
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

		//
		tourn = new JLabel("№ турнира");
		mail = new JLabel("Почта");
		phone = new JLabel("Телефон");

		tourn.setFont(word);
		mail.setFont(word);
		phone.setFont(word);

		t1.setEditable(false);
		regi.setBounds(300, 150, 50, 25);//
		tourn.setBounds(100, 130, 200, 25);
		t1.setBounds(100, 150, 200, 25);
		mail.setBounds(100, 230, 200, 25);
		t2.setBounds(100, 250, 200, 25);
		phone.setBounds(100, 330, 200, 25);
		t3.setBounds(100, 350, 200, 25);

		panel.add(add);

		panel.add(t1);
		panel.add(t2);
		panel.add(t3);
		panel.add(regi);

		panel.add(tourn);
		panel.add(mail);
		panel.add(phone);

		panel.add(back); // добавление кнопок на панель
		panel.add(background);
		info.setVisible(true); // Делает таблицу видимым
		info.add(panel);

	}

}

