import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.healthmarketscience.jackcess.DatabaseBuilder;

public class Menu {

	public JFrame window;
	JLabel hour;

	public Menu() {

		window = new JFrame("Sport");
		window.setLocationRelativeTo(null);// в центре экрана
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		window.dispose(); // отключает кнопки
		window.setUndecorated(true); // делает не активными кнопки

		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // Добавление картинки на задний фон
		background.setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17);
		Font name = new Font("TimesRoman", Font.BOLD, 30); // шрифт У меню

		JLabel Name = new JLabel("Меню");
		Name.setBounds(755, 50, 400, 60);
		Name.setForeground(Color.YELLOW);
		Name.setFont(name);
		panel.add(Name);

		JButton exit = new JButton("Выход");
		exit.setBounds(725, 820, 150, 50); // кординаты кнопки
		exit.setBorderPainted(false); // убирает обводку кнопки
		exit.setFocusPainted(false); // убирает рамку текста кнопки
		exit.setForeground(Color.cyan);
		exit.setBackground(Color.DARK_GRAY);
		exit.setFont(BigFontTR);

		JButton team = new JButton("Команда");
		team.setBounds(725, 720, 150, 50); // кординаты кнопки
		team.setBorderPainted(false); // убирает обводку кнопки
		team.setFocusPainted(false); // убирает рамку текста кнопки
		team.setForeground(Color.cyan);
		team.setBackground(Color.DARK_GRAY);
		team.setFont(BigFontTR);

		JButton Sportsman = new JButton("Спортсмен");
		Sportsman.setBounds(725, 620, 150, 50); // кординаты кнопки
		Sportsman.setBorderPainted(false); // убирает обводку кнопки
		Sportsman.setFocusPainted(false); // убирает рамку текста кнопки
		Sportsman.setForeground(Color.cyan);
		Sportsman.setBackground(Color.DARK_GRAY);
		Sportsman.setFont(BigFontTR);

		JButton Document = new JButton("Документы");
		Document.setBounds(725, 520, 150, 50); // кординаты кнопки
		Document.setBorderPainted(false); // убирает обводку кнопки
		Document.setFocusPainted(false); // убирает рамку текста кнопки
		Document.setForeground(Color.cyan);
		Document.setBackground(Color.DARK_GRAY);
		Document.setFont(BigFontTR);

		JButton reg = new JButton("Регистратура");
		reg.setBounds(720, 420, 160, 50); // кординаты кнопки
		reg.setBorderPainted(false); // убирает обводку кнопки
		reg.setFocusPainted(false); // убирает рамку текста кнопки
		reg.setForeground(Color.cyan);
		reg.setBackground(Color.DARK_GRAY);
		reg.setFont(BigFontTR);

		JButton tourment = new JButton("Турниры");
		tourment.setBounds(720, 320, 160, 50); // кординаты кнопки
		tourment.setBorderPainted(false); // убирает обводку кнопки
		tourment.setFocusPainted(false); // убирает рамку текста кнопки
		tourment.setForeground(Color.cyan);
		tourment.setBackground(Color.DARK_GRAY);
		tourment.setFont(BigFontTR);

		JButton admin = new JButton("Админ");
		admin.setBounds(720, 120, 160, 50); // кординаты кнопки
		admin.setBorderPainted(false); // убирает обводку кнопки
		admin.setFocusPainted(false); // убирает рамку текста кнопки
		admin.setForeground(Color.cyan);
		admin.setBackground(Color.DARK_GRAY);
		admin.setFont(BigFontTR);

		JButton post = new JButton("Почта");
		post.setBounds(720, 220, 160, 50); // кординаты кнопки
		post.setBorderPainted(false); // убирает обводку кнопки
		post.setFocusPainted(false); // убирает рамку текста кнопки
		post.setForeground(Color.cyan);
		post.setBackground(Color.DARK_GRAY);
		post.setFont(BigFontTR);

		panel.add(post);
		panel.add(admin);
		panel.add(tourment);
		panel.add(reg);
		panel.add(Document);
		panel.add(exit);
		panel.add(Sportsman);
		panel.add(team);
		panel.add(background);
		window.add(panel);

		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null, "Вы уверены что хотите выйти?", "Выход",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // диалоговое окно
					System.exit(0);
				} else {

				}

			}

		});

		Sportsman.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Sportsman();
				window.dispose();

			}

		});

		post.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Emailapp();
				window.dispose();

			}

		});

		team.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Team();
				window.dispose();

			}

		});

		admin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Admin();
				window.dispose();

			}

		});

		Document.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Document();
				window.dispose();

			}

		});

		reg.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new registratura();
				window.dispose();

			}

		});

		tourment.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Tournament();
				window.dispose();

			}

		});

		/*hour = new JLabel("");
		hour.setForeground(new Color(0, 165, 255));

		hour.setFont(new Font("Verdana", Font.PLAIN, 40)); // это время но из-за того что оно не прерывна
 														   // то лагает весь проект
		hour.setBounds(10, 0, 400, 100);        
		panel.add(hour);
		panel.add(background);

		int timeRun = 0;

		new Thread() {

			public void run() {

				while (timeRun == 0) {

					Calendar cal = new GregorianCalendar();

					int hourr = cal.get(Calendar.HOUR_OF_DAY);
					int minutes = cal.get(Calendar.MINUTE);
					int sec = cal.get(Calendar.SECOND);
					int day_month = cal.get(Calendar.DATE);

					String time = hourr + ":" + minutes + ":" + sec + " " + "Число " + day_month + "";

					hour.setText(time);
				}

			}

		}.start();*/

		window.setVisible(true);

	}

	public static void main(String[] args) {

		// new Menu();
		new Autentification();
	}

}
