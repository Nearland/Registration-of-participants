package copy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.healthmarketscience.jackcess.DatabaseBuilder;

public class Menu1 {

	public JFrame window;

	public Menu1() {

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
		exit.setBounds(725, 740, 150, 50); // кординаты кнопки
		exit.setBorderPainted(false); // убирает обводку кнопки
		exit.setFocusPainted(false); // убирает рамку текста кнопки
		exit.setForeground(Color.cyan);
		exit.setBackground(Color.DARK_GRAY);
		exit.setFont(BigFontTR);

		JButton team1 = new JButton("Команда");
		team1.setBounds(725, 640, 150, 50); // кординаты кнопки
		team1.setBorderPainted(false); // убирает обводку кнопки
		team1.setFocusPainted(false); // убирает рамку текста кнопки
		team1.setForeground(Color.cyan);
		team1.setBackground(Color.DARK_GRAY);
		team1.setFont(BigFontTR);

		JButton Sportsman = new JButton("Спортсмен");
		Sportsman.setBounds(725, 540, 150, 50); // кординаты кнопки
		Sportsman.setBorderPainted(false); // убирает обводку кнопки
		Sportsman.setFocusPainted(false); // убирает рамку текста кнопки
		Sportsman.setForeground(Color.cyan);
		Sportsman.setBackground(Color.DARK_GRAY);
		Sportsman.setFont(BigFontTR);

		JButton Document = new JButton("Документы");
		Document.setBounds(725, 440, 150, 50); // кординаты кнопки
		Document.setBorderPainted(false); // убирает обводку кнопки
		Document.setFocusPainted(false); // убирает рамку текста кнопки
		Document.setForeground(Color.cyan);
		Document.setBackground(Color.DARK_GRAY);
		Document.setFont(BigFontTR);

		JButton reg = new JButton("Регистратура");
		reg.setBounds(720, 340, 160, 50); // кординаты кнопки
		reg.setBorderPainted(false); // убирает обводку кнопки
		reg.setFocusPainted(false); // убирает рамку текста кнопки
		reg.setForeground(Color.cyan);
		reg.setBackground(Color.DARK_GRAY);
		reg.setFont(BigFontTR);

		JButton tourment = new JButton("Турниры");
		tourment.setBounds(720, 240, 160, 50); // кординаты кнопки
		tourment.setBorderPainted(false); // убирает обводку кнопки
		tourment.setFocusPainted(false); // убирает рамку текста кнопки
		tourment.setForeground(Color.cyan);
		tourment.setBackground(Color.DARK_GRAY);
		tourment.setFont(BigFontTR);

		JButton Email1 = new JButton("Почта");
		Email1.setBounds(720, 140, 160, 50); // кординаты кнопки
		Email1.setBorderPainted(false); // убирает обводку кнопки
		Email1.setFocusPainted(false); // убирает рамку текста кнопки
		Email1.setForeground(Color.cyan);
		Email1.setBackground(Color.DARK_GRAY);
		Email1.setFont(BigFontTR);

		panel.add(Email1);
		panel.add(tourment);
		panel.add(reg);
		panel.add(Document);
		panel.add(exit);
		panel.add(Sportsman);
		panel.add(team1);
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

		Email1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Emailapp1();
				window.dispose();

			}

		});

		Sportsman.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Sportsman1();
				window.dispose();

			}

		});

		team1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Team1();
				window.dispose();

			}

		});

		Document.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Document1();
				window.dispose();

			}

		});

		reg.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new registratura1();
				window.dispose();

			}

		});

		tourment.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Tournament1();
				window.dispose();

			}

		});

		window.setVisible(true);

	}

}
