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

public class Document {

	public JFrame Doc;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	JButton update = new JButton("Обновить");
	JButton delete = new JButton("Удалить");

	public Document() {

		Doc = new JFrame("Document");
		Doc.setLocationRelativeTo(null);// в центре экрана
		Doc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Doc.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		Doc.setUndecorated(true); // делает не активными кнопки

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

		JLabel Name = new JLabel("Документы");
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

		// JButton update = new JButton("Обновить");
		update.setBounds(100, 650, 150, 50);
		update.setBorderPainted(false); // убирает обводку кнопки
		update.setFocusPainted(false); // убирает рамку текста кнопки
		update.setForeground(Color.cyan);
		update.setBackground(Color.DARK_GRAY);
		update.setFont(BigFontTR);

		// JButton delete = new JButton("Удалить");
		delete.setBounds(100, 750, 150, 50);
		delete.setBorderPainted(false); // убирает обводку кнопки
		delete.setFocusPainted(false); // убирает рамку текста кнопки
		delete.setForeground(Color.cyan);
		delete.setBackground(Color.DARK_GRAY);
		delete.setFont(BigFontTR);

		JButton create = new JButton("Создать документ");
		create.setBounds(1270, 540, 200, 50);
		create.setBorderPainted(false); // убирает обводку кнопки
		create.setFocusPainted(false); // убирает рамку текста кнопки
		create.setForeground(Color.cyan);
		create.setBackground(Color.DARK_GRAY);
		create.setFont(BigFontTR);

		JButton doci = new JButton("Список документов");
		doci.setBounds(1270, 600, 200, 50);
		doci.setBorderPainted(false); // убирает обводку кнопки
		doci.setFocusPainted(false); // убирает рамку текста кнопки
		doci.setForeground(Color.cyan);
		doci.setBackground(Color.DARK_GRAY);
		doci.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Doc.dispose(); // кнопка назад
				new Menu();

			}

		});

		doci.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Doc.dispose(); // кнопка назад
				new ListDoc();

			}

		});

		create.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Doc.dispose(); // кнопка назад
				new docinfo();

			}

		});

		Doc.dispose(); // отключает кнопки
		Doc.setVisible(true); // Делает frame видимым

		try {

			JScrollPane pane = new JScrollPane(sportsmanTable);
			pane.getViewport().add(sportsmanTable);
			pane.setBounds(370, 140, 1100, 400); // создание таблицы и прокрутки
			sportsmanTable.setBackground(c1);
			sportsmanTable.setFont(table);
			pane.setBackground(c1);
			Vector<Vector<String>> values = getDataFromDB();

			Vector<String> hat = new Vector<String>();

			hat.add("Фамилия спортсмена");
			hat.add("№ Документа");
			hat.add("Наличие заявки на участие");
			hat.add("Паспорт");
			hat.add("Серия");
			hat.add("Номер");
			hat.add("Мед. Полис");
			hat.add("Согласие родителей"); // Заголовки таблиц
			hat.add("Объязательство");
			hat.add("Книжка спротсмена");
			hat.add("№ Регистратуры");

			DefaultTableModel dtm = (DefaultTableModel) sportsmanTable.getModel();

			dtm.setDataVector(values, hat);

			JTextField search = new JTextField(); // добавляем поиск ввода
			search.setBounds(655, 545, 200, 25);
			JLabel words = new JLabel("Введите слово для поиска:");
			words.setBounds(470, 545, 200, 25);
			words.setForeground(Color.DARK_GRAY);
			words.setFont(word);

			sportsmanTable.setAutoCreateRowSorter(true);// сортировка по стрингу

			@SuppressWarnings("unchecked") // что это но чтобы warningов небыло
			TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) sportsmanTable
					.getRowSorter();

			rowSorter.setComparator(1, new Comparator<String>() { // тут сортировка по интеджер

				@Override
				public int compare(String o1, String o2) {
					return Integer.parseInt(o1) - Integer.parseInt(o2);
				}

			});
			for (int i = 4; i <= 5; i++) {
				rowSorter.setComparator(i, new Comparator<String>() { // копипаст наше все! лучше способа не нашел

					@Override
					public int compare(String o1, String o2) {
						return Integer.parseInt(o1) - Integer.parseInt(o2);
					}

				});
			}
			rowSorter.setComparator(10, new Comparator<String>() { // копипаст наше все! лучше способа не нашел

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

			panel.add(words);
			panel.add(search);
			panel.add(pane);

			//////////////////////

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

			JLabel id;
			JLabel zaayava;
			JLabel pasport;
			JLabel seriya;
			JLabel nomer;
			JLabel police;
			JLabel soglasie;
			JLabel obyazatelstvo;
			JLabel sport_book;
			JLabel regis;

			id = new JLabel("№ Документа");
			zaayava = new JLabel("Наличие заявки на участие");
			pasport = new JLabel("Паспорт");
			seriya = new JLabel("Серия");
			nomer = new JLabel("Номер");
			police = new JLabel("Мед. Полис");
			soglasie = new JLabel("Согласие родителей");
			obyazatelstvo = new JLabel("Объязательство");
			sport_book = new JLabel("Книжка спротсмена");
			regis = new JLabel("№ Регистратуры");

			id.setFont(word);
			zaayava.setFont(word);
			pasport.setFont(word);
			seriya.setFont(word);
			nomer.setFont(word);
			police.setFont(word);
			soglasie.setFont(word);
			obyazatelstvo.setFont(word);
			sport_book.setFont(word);
			regis.setFont(word);

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

			JComboBox yes;
			JComboBox yes1;
			JComboBox yes2;
			JComboBox yes3;
			JComboBox yes4;
			JComboBox regi;

			String[] items = { "", "ДА", "НЕТ", };

			yes = new JComboBox(items);
			yes1 = new JComboBox(items);
			yes2 = new JComboBox(items);
			yes3 = new JComboBox(items);
			yes4 = new JComboBox(items);
			regi = new JComboBox();

			yes.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {
					String st = yes.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t1.setText(st); // джейти это текстфилд
				}
			});

			yes1.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {
					String st = yes1.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t2.setText(st); // джейти это текстфилд
				}
			});

			yes2.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {
					String st = yes2.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t6.setText(st); // джейти это текстфилд
				}
			});

			yes3.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {
					String st = yes3.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t7.setText(st); // джейти это текстфилд
				}
			});

			yes4.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {
					String st = yes4.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t8.setText(st); // джейти это текстфилд
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

					String abc = rs.getString(1); // тут индексы из базы данных сунуты в Джейбокс
					regi.addItem(abc);
				}
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
				ex.printStackTrace();

			}
			//////////////////////////

			regi.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {

					String st = regi.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t9.setText(st); // джейти это текстфилд

				}
			});

			t1.setDocument(new PlainDocument() {
				String chars = "ДАНЕТ";

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
				String chars = "ДАНЕТ";

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
					if (chars.indexOf(str) != -1) {
						if (getLength() < 3) {
							super.insertString(offs, str, a);
						}
						//
						if (t2.getText().equals("НЕТ")) {
							String input;
							input = t2.getText();

							t3.setText("0");
							t4.setText("0");
							t3.setEditable(false);
							t4.setEditable(false);
						} else {
							t3.addKeyListener(new KeyAdapter() {
								public void keyTyped(KeyEvent e) {
									char c = e.getKeyChar();
									if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
										e.consume(); // ignore event
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

							t3.setDocument(new PlainDocument() {

								@Override
								public void insertString(int offs, String str, AttributeSet a)
										throws BadLocationException {

									if (getLength() < 4) {
										super.insertString(offs, str, a);

									}
								}
							});
							t4.setDocument(new PlainDocument() {

								@Override
								public void insertString(int offs, String str, AttributeSet a)
										throws BadLocationException {

									if (getLength() < 6) {
										super.insertString(offs, str, a);

									}
								}
							});
							t3.setEditable(true);
							t4.setEditable(true);
						}
					}
				}
			});

			t9.setDocument(new PlainDocument() {
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

			sportsmanTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable sportsmanTable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && sportsmanTable.getSelectedRow() != -1) { // обработка
																									// двойного клика

						confordoc cont = new confordoc();

						con = cont.returnConnection();

						String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 1).toString();

						try {

							String sql = "Select * from Document where ID_document=?";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								t.setText(rs.getString("ID_document"));
								t1.setText(rs.getString("Zayavka_na_uchastie"));
								t2.setText(rs.getString("Passport_sportsmana"));
								t3.setText(rs.getString("Passport_seriya"));
								t4.setText(rs.getString("Passport_number"));
								t5.setText(rs.getString("Medical_policy"));
								t6.setText(rs.getString("Parental_consent"));
								t7.setText(rs.getString("Commitment"));
								t8.setText(rs.getString("Credit_book"));
								t9.setText(rs.getString("Registratura"));

							}

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					}
				}

			});

			panel.add(yes);
			panel.add(yes1);
			panel.add(yes2);
			panel.add(yes3);
			panel.add(yes4);
			panel.add(regi);

			t.setEditable(false);
			t1.setEditable(false);
			t2.setEditable(false);
			t6.setEditable(false);
			t7.setEditable(false);
			t8.setEditable(false);
			t9.setEditable(false);

			panel.add(t);
			panel.add(t1);
			panel.add(t2);
			panel.add(t3);
			panel.add(t4); // добавление джей текс филдов
			panel.add(t5);
			panel.add(t6);
			panel.add(t7);
			panel.add(t8);
			panel.add(t9);

			panel.add(id);
			panel.add(zaayava);
			panel.add(pasport);
			panel.add(seriya);
			panel.add(nomer);
			panel.add(police);
			panel.add(soglasie);
			panel.add(obyazatelstvo);
			panel.add(sport_book);
			panel.add(regis);

			id.setBounds(100, 125, 200, 25);
			t.setBounds(100, 150, 200, 25);
			zaayava.setBounds(100, 175, 200, 25);
			yes.setBounds(300, 200, 50, 25);//
			t1.setBounds(100, 200, 200, 25);
			pasport.setBounds(100, 225, 200, 25);
			yes1.setBounds(300, 250, 50, 25);//
			t2.setBounds(100, 250, 200, 25);
			seriya.setBounds(100, 275, 200, 25);
			t3.setBounds(100, 300, 200, 25);
			nomer.setBounds(100, 325, 200, 25);
			t4.setBounds(100, 350, 200, 25);
			police.setBounds(100, 375, 200, 25);
			t5.setBounds(100, 400, 200, 25);
			soglasie.setBounds(100, 425, 200, 25);
			yes2.setBounds(300, 450, 50, 25);//
			t6.setBounds(100, 450, 200, 25);
			obyazatelstvo.setBounds(100, 475, 200, 25);
			yes3.setBounds(300, 500, 50, 25);//
			t7.setBounds(100, 500, 200, 25);
			sport_book.setBounds(100, 525, 200, 25);
			yes4.setBounds(300, 550, 50, 25);//
			t8.setBounds(100, 550, 200, 25);
			regis.setBounds(100, 575, 200, 25);
			regi.setBounds(300, 600, 50, 25);//
			t9.setBounds(100, 600, 200, 25);

			update.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (t.getText().equals("") || t1.getText().equals("") || t2.getText().equals("")
							|| t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("")
							|| t6.getText().equals("") || t7.getText().equals("") || t8.getText().equals("")
							|| t9.getText().equals("")) {

						JOptionPane.showMessageDialog(null,
								"Пожалуйста выберите строку из таблицы, поля не должны быть пустыми!");

					} else {

						try {

							String uppdate;
							confordoc cont = new confordoc();

							con = cont.returnConnection();

							String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 1).toString();

							uppdate = sno;

							String sql = "Update Document set ID_document=?,Zayavka_na_uchastie=?,Passport_sportsmana=?,"
									+ "Passport_seriya=?,Passport_number=?,Medical_policy=?,Parental_consent=?,Commitment=?,"
									+ "Credit_book=?,Registratura=? where ID_document=" + uppdate;

							ptr = con.prepareStatement(sql);

							ptr.setString(1, t.getText());
							ptr.setString(2, t1.getText());
							ptr.setString(3, t2.getText());
							ptr.setString(4, t3.getText());
							ptr.setString(5, t4.getText());
							ptr.setString(6, t5.getText());
							ptr.setString(7, t6.getText());
							ptr.setString(8, t7.getText());
							ptr.setString(9, t8.getText());
							ptr.setString(10, t9.getText());
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

							String id = t.getText();

							String sql = "Delete from Document where ID_document=" + id;

							st.executeUpdate(sql);

							JOptionPane.showMessageDialog(null, "Запись удалена");

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null,
									"Пожалуйста выберите строку из таблицы, поля не должны быть пустыми!");
							// JOptionPane.showMessageDialog(null, ex);
						}

					} else {

					}

				}
			});

			sportsmanTable.getTableHeader().setReorderingAllowed(false); // запрет на перетаскивание столбцов

///////////////////////////

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		panel.add(back); // добавление кнопок на панель
		panel.add(update);
		panel.add(create);
		panel.add(delete);
		panel.add(doci);

		// crmBackup1.bak;

		panel.add(background);
		Doc.add(panel);
		Doc.setVisible(true);

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
		rs = st.executeQuery(
				"SELECT Sportsman.Surname_sportsman, Document.ID_document, Document.Zayavka_na_uchastie, Document.Passport_sportsmana, Document.Passport_seriya, Document.Passport_number, Document.Medical_policy, Document.Parental_consent, Document.Commitment, Document.Credit_book, Document.Registratura\r\n"
						+ "FROM Document INNER JOIN Sportsman ON Document.ID_document = Sportsman.Document_sportsman;\r\n"
						+ ""); // SQL// Запрос

		String p, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p = rs.getString(1);
			p1 = rs.getString(2);
			p2 = rs.getString(3);
			p3 = rs.getString(4);
			p4 = rs.getString(5);
			p5 = rs.getString(6);
			p6 = rs.getString(7);
			p7 = rs.getString(8);
			p8 = rs.getString(9);
			p9 = rs.getString(10);
			p10 = rs.getString(11);

			element.add(p);
			element.add(p1);
			element.add(p2);
			element.add(p3);
			element.add(p4);
			element.add(p5);
			element.add(p6);
			element.add(p7);
			element.add(p8);
			element.add(p9);
			element.add(p10);

			result.add(element);
		}

		rs.close();
		st.close(); // закрытие шлюзов
		con.close();
		return result;

	}

}