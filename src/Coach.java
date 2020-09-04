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

public class Coach {

	public JFrame sport;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public Coach() {

		sport = new JFrame("Tournament");
		sport.setLocationRelativeTo(null);// в центре экрана
		sport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sport.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		sport.setUndecorated(true); // делает не активными кнопки
 
		JLabel background;
		ImageIcon img = new ImageIcon("backcolor.jpg");
		background = new JLabel("", img, JLabel.CENTER); // Добавление картинки на задний фон
		background.setBounds(0, 0, 1920, 1080);

		JPanel panel = new JPanel(); // панель
		panel.setLayout(null);

		Font BigFontTR = new Font("TimesRoman", Font.ITALIC, 17); // шрифт для кнопок
		Font name = new Font("TimesRoman", Font.BOLD, 30); // шрифт У меню
		Color c1 = new Color(0, 214, 255); // color for table
		Font table = new Font("TimesRoman", Font.BOLD, 15); // шрифт У меню
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

		JButton update = new JButton("Обновить");
		update.setBounds(100, 600, 150, 50);
		update.setBorderPainted(false); // убирает обводку кнопки
		update.setFocusPainted(false); // убирает рамку текста кнопки
		update.setForeground(Color.cyan);
		update.setBackground(Color.DARK_GRAY);
		update.setFont(BigFontTR);

		JButton delete = new JButton("Удалить");
		delete.setBounds(100, 660, 150, 50);
		delete.setBorderPainted(false); // убирает обводку кнопки
		delete.setFocusPainted(false); // убирает рамку текста кнопки
		delete.setForeground(Color.cyan);
		delete.setBackground(Color.DARK_GRAY);
		delete.setFont(BigFontTR);

		JButton create = new JButton("Добавить тренера");
		create.setBounds(1210, 540, 260, 50);
		create.setBorderPainted(false); // убирает обводку кнопки
		create.setFocusPainted(false); // убирает рамку текста кнопки
		create.setForeground(Color.cyan);
		create.setBackground(Color.DARK_GRAY);
		create.setFont(BigFontTR);

		JLabel nomer;
		JLabel surname;
		JLabel nameo;
		JLabel patronymic;
		JLabel vid_sporta;
		JLabel Email;
		JLabel phone;

		JTextField ID;
		JTextField t1;// Тут белые полоски
		JTextField t2;
		JTextField t3;
		JTextField t4;
		JTextField t5;
		JTextField t6;

		ID = new JTextField(10);
		t1 = new JTextField(10);
		t2 = new JTextField(10);
		t3 = new JTextField(10);
		t4 = new JTextField(10);
		t5 = new JTextField(10);
		t6 = new JTextField(10);

		nomer = new JLabel("№ тренера");
		nameo = new JLabel("Имя");
		surname = new JLabel("Фамилия");
		patronymic = new JLabel("Отчество");
		vid_sporta = new JLabel("Вид спорта");
		Email = new JLabel("Почта");
		phone = new JLabel("Телефон");

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // кнопка назад
				new Sportsman();

			}

		});

		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // кнопка назад
				new addcoach();

			}

		});

		sport.dispose(); // отключает кнопки
		sport.setVisible(true); // Делает таблицу видимым

		try {

			JScrollPane pane = new JScrollPane(sportsmanTable);
			pane.getViewport().add(sportsmanTable);
			pane.setBounds(470, 140, 1000, 400); // создание таблицы и прокрутки
			sportsmanTable.setBackground(c1);
			sportsmanTable.setFont(table);
			pane.setBackground(c1);
			Vector<Vector<String>> values = getDataFromDB();

			Vector<String> hat = new Vector<String>();

			hat.add("№ тренера");
			hat.add("Фамилия"); // Заголовки таблиц
			hat.add("Имя");
			hat.add("Отчество");
			hat.add("Вид спорта");
			hat.add("Почта");
			hat.add("Телефон");

			DefaultTableModel dtm = (DefaultTableModel) sportsmanTable.getModel();

			dtm.setDataVector(values, hat);

			JTextField search = new JTextField(); // добавляем поиск ввода
			search.setBounds(655, 545, 200, 25);
			JLabel words = new JLabel("Введите слово для поиска:");
			words.setBounds(470, 545, 200, 25);
			words.setForeground(Color.DARK_GRAY);
			words.setFont(word);

			sportsmanTable.setAutoCreateRowSorter(true);// сортировка по стрингу

			@SuppressWarnings("unchecked") //  что это но чтобы warningов небыло
			TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) sportsmanTable
					.getRowSorter();

			rowSorter.setComparator(0, new Comparator<String>() { // тут сортировка по интеджер

				@Override
				public int compare(String o1, String o2) {
					return Integer.parseInt(o1) - Integer.parseInt(o2);
				}

			});

			search.getDocument().addDocumentListener(new DocumentListener() { // поиск отбора

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

			nomer.setFont(word);
			surname.setFont(word);
			nameo.setFont(word);
			patronymic.setFont(word);
			vid_sporta.setFont(word);
			Email.setFont(word);
			phone.setFont(word);

			ID.setVisible(false);
			ID.setBounds(100, 100, 200, 25);

			surname.setBounds(100, 130, 200, 25);
			t1.setBounds(100, 150, 200, 25);
			nameo.setBounds(100, 180, 200, 25);
			t2.setBounds(100, 200, 200, 25);
			patronymic.setBounds(100, 230, 200, 25);
			t3.setBounds(100, 250, 200, 25);
			vid_sporta.setBounds(100, 280, 200, 25);
			t4.setBounds(100, 300, 200, 25);
			Email.setBounds(100, 330, 200, 25);
			t5.setBounds(100, 350, 200, 25);
			phone.setBounds(100, 380, 200, 25);
			t6.setBounds(100, 400, 200, 25);

			sportsmanTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable sportsmanTable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && sportsmanTable.getSelectedRow() != -1) { // обработка
																									// двойного клика

						confordoc cont = new confordoc();

						con = cont.returnConnection();

						String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

						try {

							String sql = "Select * from Coach where ID_coach=?";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								ID.setText(rs.getString("ID_coach"));
								t1.setText(rs.getString("Surname_coach"));
								t2.setText(rs.getString("Name_coach"));
								t3.setText(rs.getString("Patronimic_coach"));
								t4.setText(rs.getString("Vid_sporta"));
								t5.setText(rs.getString("Email_coach"));
								t6.setText(rs.getString("Phone_coach"));
							}

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					}

				}

			});

			t1.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (Character.isDigit(e.getKeyChar()))
						e.consume();
				}
			});

			t1.setDocument(new PlainDocument() {
				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 20) {
						super.insertString(offs, str, a);

					}
				}
			});
			t2.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (Character.isDigit(e.getKeyChar()))
						e.consume();
				}
			});

			t2.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 25) {
						super.insertString(offs, str, a);

					}

				}
			});

			t3.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 20) {
						super.insertString(offs, str, a);

					}
				}
			});

			t3.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (Character.isDigit(e.getKeyChar()))
						e.consume();
				}
			});

			t4.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (Character.isDigit(e.getKeyChar()))
						e.consume();
				}
			});

			t4.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 20) {
						super.insertString(offs, str, a);

					}
				}
			});

			t4.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
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

			t6.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 15) {
						super.insertString(offs, str, a);

					}
				}
			});

			t6.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
				}
			});

			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")
							|| t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Пожалуйста выберите строку из таблицы");

					} else {

						try {

							String uppdate;
							confordoc cont = new confordoc();

							con = cont.returnConnection();

							String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 0).toString();

							uppdate = sno;

							String sql = "Update Coach set Surname_coach=?,Name_coach=?,"
									+ "Patronimic_coach=?,Vid_sporta=?,Email_coach=?,Phone_coach=? where ID_coach="
									+ uppdate;

							ptr = con.prepareStatement(sql);

							ptr.setString(1, t1.getText());
							ptr.setString(2, t2.getText());
							ptr.setString(3, t3.getText());
							ptr.setString(4, t4.getText());
							ptr.setString(5, t5.getText());
							ptr.setString(6, t6.getText());
							ptr.executeUpdate();

							JOptionPane.showMessageDialog(null, "Запись обновлена");

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}
					}
				}
			});

			delete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (JOptionPane.showConfirmDialog(null, "Вы действительно хотите удалить эту запись?", "Удаление",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // диалоговое окно

						try {

							String del;
							confordoc cont = new confordoc();

							con = cont.returnConnection();

							st = con.createStatement();

							String id = ID.getText();

							String sql = "Delete from Coach where ID_coach=" + id;

							st.executeUpdate(sql);

							JOptionPane.showMessageDialog(null, "Запись удалена");

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					} else {

					}

				}
			});
			
			sportsmanTable.getTableHeader().setReorderingAllowed(false); // запрет на перетаскивание столбцов

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		panel.add(ID);
		panel.add(t1);
		panel.add(t2);
		panel.add(t3);
		panel.add(t4);
		panel.add(t5);
		panel.add(t6);

		panel.add(nomer);
		panel.add(surname);
		panel.add(nameo);
		panel.add(patronymic);
		panel.add(vid_sporta);
		panel.add(Email);
		panel.add(phone);

		panel.add(back); // добавление кнопок на панель
		panel.add(update);
		panel.add(create);
		panel.add(delete);
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
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver"; // подключение к БД
		Class.forName(driver);
		con = DriverManager.getConnection(dbURL);
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery("Select * from Coach"); // SQL// Запрос

		String p1, p2, p3, p4, p5, p7, p8;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(1);
			p2 = rs.getString(3);
			p3 = rs.getString(2);
			p4 = rs.getString(4);
			p5 = rs.getString(5);
			p7 = rs.getString(7);
			p8 = rs.getString(6);

			element.add(p1);
			element.add(p3);
			element.add(p2);
			element.add(p4);
			element.add(p8);
			element.add(p5);
			element.add(p7);

			result.add(element);
		}

		rs.close();
		st.close(); // закрытие шлюзов
		con.close();
		return result;

	}

}