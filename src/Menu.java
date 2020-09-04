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
		window.setLocationRelativeTo(null);// � ������ ������
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		window.dispose(); // ��������� ������
		window.setUndecorated(true); // ������ �� ��������� ������

		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // ���������� �������� �� ������ ���
		background.setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17);
		Font name = new Font("TimesRoman", Font.BOLD, 30); // ����� � ����

		JLabel Name = new JLabel("����");
		Name.setBounds(755, 50, 400, 60);
		Name.setForeground(Color.YELLOW);
		Name.setFont(name);
		panel.add(Name);

		JButton exit = new JButton("�����");
		exit.setBounds(725, 820, 150, 50); // ��������� ������
		exit.setBorderPainted(false); // ������� ������� ������
		exit.setFocusPainted(false); // ������� ����� ������ ������
		exit.setForeground(Color.cyan);
		exit.setBackground(Color.DARK_GRAY);
		exit.setFont(BigFontTR);

		JButton team = new JButton("�������");
		team.setBounds(725, 720, 150, 50); // ��������� ������
		team.setBorderPainted(false); // ������� ������� ������
		team.setFocusPainted(false); // ������� ����� ������ ������
		team.setForeground(Color.cyan);
		team.setBackground(Color.DARK_GRAY);
		team.setFont(BigFontTR);

		JButton Sportsman = new JButton("���������");
		Sportsman.setBounds(725, 620, 150, 50); // ��������� ������
		Sportsman.setBorderPainted(false); // ������� ������� ������
		Sportsman.setFocusPainted(false); // ������� ����� ������ ������
		Sportsman.setForeground(Color.cyan);
		Sportsman.setBackground(Color.DARK_GRAY);
		Sportsman.setFont(BigFontTR);

		JButton Document = new JButton("���������");
		Document.setBounds(725, 520, 150, 50); // ��������� ������
		Document.setBorderPainted(false); // ������� ������� ������
		Document.setFocusPainted(false); // ������� ����� ������ ������
		Document.setForeground(Color.cyan);
		Document.setBackground(Color.DARK_GRAY);
		Document.setFont(BigFontTR);

		JButton reg = new JButton("������������");
		reg.setBounds(720, 420, 160, 50); // ��������� ������
		reg.setBorderPainted(false); // ������� ������� ������
		reg.setFocusPainted(false); // ������� ����� ������ ������
		reg.setForeground(Color.cyan);
		reg.setBackground(Color.DARK_GRAY);
		reg.setFont(BigFontTR);

		JButton tourment = new JButton("�������");
		tourment.setBounds(720, 320, 160, 50); // ��������� ������
		tourment.setBorderPainted(false); // ������� ������� ������
		tourment.setFocusPainted(false); // ������� ����� ������ ������
		tourment.setForeground(Color.cyan);
		tourment.setBackground(Color.DARK_GRAY);
		tourment.setFont(BigFontTR);

		JButton admin = new JButton("�����");
		admin.setBounds(720, 120, 160, 50); // ��������� ������
		admin.setBorderPainted(false); // ������� ������� ������
		admin.setFocusPainted(false); // ������� ����� ������ ������
		admin.setForeground(Color.cyan);
		admin.setBackground(Color.DARK_GRAY);
		admin.setFont(BigFontTR);

		JButton post = new JButton("�����");
		post.setBounds(720, 220, 160, 50); // ��������� ������
		post.setBorderPainted(false); // ������� ������� ������
		post.setFocusPainted(false); // ������� ����� ������ ������
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

				if (JOptionPane.showConfirmDialog(null, "�� ������� ��� ������ �����?", "�����",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // ���������� ����
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

		hour.setFont(new Font("Verdana", Font.PLAIN, 40)); // ��� ����� �� ��-�� ���� ��� ��� �� ��������
 														   // �� ������ ���� ������
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

					String time = hourr + ":" + minutes + ":" + sec + " " + "����� " + day_month + "";

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
