import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Emailapp {

	JFrame frame;

	Emailapp() {
		frame = new JFrame("Post");
		frame.setLocationRelativeTo(null);// � ������ ������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		frame.setUndecorated(true); // ������ �� ��������� ������

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

		JLabel Name = new JLabel("�����");
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

		JButton send = new JButton("���������");
		send.setBounds(150, 650, 150, 50);
		send.setBorderPainted(false); // ������� ������� ������
		send.setFocusPainted(false); // ������� ����� ������ ������
		send.setForeground(Color.cyan);
		send.setBackground(Color.DARK_GRAY);
		send.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.dispose(); // ������ �����
				new Menu();

			}

		});

		JTextField comu;
		JTextField otcogo;
		JTextArea text = new JTextArea();
		JTextField zagolovok;
		JPasswordField pasw;

		comu = new JTextField(10);
		otcogo = new JTextField(10);
		zagolovok = new JTextField(10);
		pasw = new JPasswordField(10);

		JLabel opoveshe;
		JLabel comu1;
		JLabel otcogo1;
		JLabel text1;
		JLabel zagolovok1;
		JLabel pasw1;

		opoveshe = new JLabel("��� �� ������ ������ ����� ������ �������������!");
		comu1 = new JLabel("����:");
		pasw1 = new JLabel("��� ������:");
		otcogo1 = new JLabel("�� ����:");
		text1 = new JLabel("�����");
		zagolovok1 = new JLabel("���������:");

		comu1.setFont(word);
		otcogo1.setFont(word);
		text1.setFont(word);
		zagolovok1.setFont(word);
		pasw1.setFont(word);
		opoveshe.setFont(word);

		opoveshe.setBounds(110, 100, 500, 25);
		comu1.setBounds(110, 125, 200, 25);
		comu.setBounds(150, 125, 200, 25);
		otcogo1.setBounds(85, 175, 200, 25);
		otcogo.setBounds(150, 175, 200, 25);
		pasw1.setBounds(65, 225, 200, 25);
		pasw.setBounds(150, 225, 200, 25);
		zagolovok1.setBounds(70, 275, 200, 25);
		zagolovok.setBounds(150, 275, 200, 25);
		text1.setBounds(150, 325, 200, 25);
		opoveshe.setBounds(110, 305, 500, 25);
		text.setBounds(150, 350, 300, 200);

		send.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String Toemail = comu.getText();
				String FromEmail = otcogo.getText();
				String yourpass = pasw.getText();
				String subject = zagolovok.getText();
				String testt = text.getText();

				String ending = otcogo.getText();

				Properties properties = new Properties();

				properties.put("mail.smtp.auth", "true"); // ���������� �� ��������������
				properties.put("mail.smtp.starttls.enable", "true");

				if (ending.contains("gmail.com")) { // ��� �������� ���� ���������
					// System.out.println("eeeeeeee"); // ��� ��� � ���� �������� ������
					properties.put("mail.smtp.host", "smtp.gmail.com"); // ������ smtp � host
				} else if (ending.contains("mail.ru")) {
					// System.out.println("sdds");
					properties.put("mail.smtp.host", "smtp.mail.ru");
				} else if (ending.contains("yandex.ru")) {
					// System.out.println("trt");
					properties.put("mail.smtp.host", "smtp.yandex.ru");
					properties.put("mail.smtp.port", "465");
				}

				properties.put("mail.smtp.port", "587");

				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(FromEmail, yourpass); // �������� ����� ������

					}

				});

				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(FromEmail));
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(Toemail)); // ���� ��������
																									// ������
					message.setSubject(subject);
					message.setText(testt);

					Transport.send(message);

					JOptionPane.showMessageDialog(null, "������ ������� �����������!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "������ �� ������� ���������!" + ex);
				}

			}
		});

		panel.add(send);
		panel.add(comu);
		panel.add(comu1);
		panel.add(pasw);
		panel.add(pasw1);
		panel.add(otcogo);
		panel.add(otcogo1);
		panel.add(text);
		panel.add(text1);
		panel.add(zagolovok);
		panel.add(zagolovok1);
		panel.add(opoveshe);

		panel.add(back);
		panel.add(background);
		frame.add(panel);
		frame.setVisible(true); // ������ frame �������

		/////////
	}

}
