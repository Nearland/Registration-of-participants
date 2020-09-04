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

public class Dopusk {

	public JFrame sport;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public Dopusk() {

		sport = new JFrame("Dopusc");
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

		JLabel Name = new JLabel("Допуски");
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
		update.setBounds(100, 550, 150, 50);
		update.setBorderPainted(false); // убирает обводку кнопки
		update.setFocusPainted(false); // убирает рамку текста кнопки
		update.setForeground(Color.cyan);
		update.setBackground(Color.DARK_GRAY);
		update.setFont(BigFontTR);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // кнопка назад
				new registratura();

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

			hat.add("№ регистратуры"); // Заголовки таблиц
			hat.add("№ документа");
			hat.add("Допуск");
			hat.add("Причина");
			hat.add("№ решения");

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

			
			for (int i = 0; i <= 1; i++) {
				rowSorter.setComparator(i, new Comparator<String>() { // копипаст наше все! лучше способа не нашел

					@Override
					public int compare(String o1, String o2) {
						return Integer.parseInt(o1) - Integer.parseInt(o2);
					}

				});
			}

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

			JLabel iddoc;
			JLabel idreg;
			JLabel dopu;
			JLabel reason;

			JTextField ID;
			JTextField t4;
			JTextField t1;
			JTextField t2;// Тут белые полоски
			JTextField t3;

			ID = new JTextField(10);
			t1 = new JTextField(10);
			t2 = new JTextField(10);
			t3 = new JTextField(10);

			iddoc = new JLabel("№ документа");
			idreg = new JLabel("№ регистратуры");
			dopu = new JLabel("Допуск");

			iddoc.setFont(word);
			idreg.setFont(word);
			dopu.setFont(word);

//////////////////////////

			JComboBox regi;
			regi = new JComboBox();

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

			regi.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {

					String st = regi.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t1.setText(st); // джейти это текстфилд

				}
			});
//////////////////////////

			t2.setDocument(new PlainDocument() {

				@Override
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

					if (getLength() < 20) {
						super.insertString(offs, str, a);

					}
				}
			});

//////////////////////////
			JComboBox dop;
			String[] dopusk = { "ДА", "Нет заявки на участие", "Нет согласия родителей", "Нет паспорта",
					"Нет объязательства родителей" };
			dop = new JComboBox(dopusk);
 
			dop.addActionListener(new ActionListener() { // Джейбокс это комбобокс
				public void actionPerformed(ActionEvent event) {

					String st = dop.getSelectedItem().toString(); // Сдесь я сую итемы из ДЖбок в Джтексбок
					t3.setText(st); // джейти это текстфилд

					if (t3.getText().equals("ДА")) {

						t3.setText("1");

					} else if (t3.getText().equals("Нет заявки на участие"))
						t3.setText("2");
					else if (t3.getText().equals("Нет согласия родителей"))
						t3.setText("3");
					else if (t3.getText().equals("Нет паспорта"))
						t3.setText("4");
					else if (t3.getText().equals("Нет объязательства родителей"))
						t3.setText("5");

				}

			});

//////////////////////////

			t1.setEnabled(false);
			t2.setEnabled(false);
			t3.setEnabled(false);

			ID.setVisible(false);
			ID.setBounds(100, 100, 200, 25);
			// iddoc.setBounds(300, 150, 50, 25);//
			// t1.setEditable(false);
			regi.setBounds(300, 150, 50, 25);//
			idreg.setBounds(100, 130, 200, 25);
			t1.setBounds(100, 150, 200, 25);
			iddoc.setBounds(100, 230, 200, 25);
			t2.setBounds(100, 250, 200, 25);
			dopu.setBounds(100, 330, 200, 25);
			dop.setBounds(300, 350, 150, 25);
			t3.setBounds(100, 350, 200, 25);

			sportsmanTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable sportsmanTable = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2 && sportsmanTable.getSelectedRow() != -1) { // обработка
																									// двойного клика

						confordoc cont = new confordoc();

						con = cont.returnConnection();

						String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 1).toString();

						try {

							String sql = "SELECT Registratura.ID_registrature, Document.ID_document, reason.[yes/no]\r\n" + 
									"FROM Registratura INNER JOIN (reason INNER JOIN Document ON reason.ID_reas = Document.ID_reas)"
									+ " ON Registratura.ID_registrature = Document.Registratura where ID_document=?" + 
									" ";
							ptr = con.prepareStatement(sql);

							ptr.setString(1, sno);
							rs = ptr.executeQuery();

							if (rs.next()) {

								t1.setText(rs.getString("ID_registrature"));
								t2.setText(rs.getString("ID_document"));
								t3.setText(rs.getString("yes/no"));

							}

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex);
						}

					}
				}

			});

			update.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")) {

						JOptionPane.showMessageDialog(null,
								"Пожалуйста выберите строку из таблицы, поля не должны быть пустыми!");

					} else {

						try {

							String uppdate;
							confordoc cont = new confordoc();

							con = cont.returnConnection();

							String sno = sportsmanTable.getValueAt(sportsmanTable.getSelectedRow(), 1).toString();

							uppdate = sno;

							String sql = "Update Document set ID_reas=? where ID_document=" + uppdate;

							ptr = con.prepareStatement(sql);
							ptr.setString(1, t3.getText());
							ptr.executeUpdate();

							JOptionPane.showMessageDialog(null, "Запись обновлена");

						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, ex);
						}
					}
				}
			});
			
			sportsmanTable.getTableHeader().setReorderingAllowed(false); // запрет на перетаскивание столбцов

			panel.add(ID);
			panel.add(t1);
			panel.add(t2);
			panel.add(t3);

			panel.add(regi);

			panel.add(iddoc);
			panel.add(idreg);
			panel.add(dopu);
			panel.add(dop);

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		panel.add(back); // добавление кнопок на панель
		panel.add(update);
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
		rs = st.executeQuery(
				"SELECT Registratura.ID_registrature, Document.ID_document, reason.[yes/no], reason.reasonn, reason.ID_reas\r\n"
						+ "FROM Registratura INNER JOIN (reason INNER JOIN Document ON reason.ID_reas = Document.ID_reas) ON Registratura.ID_registrature = Document.Registratura;"); // SQL//
																																														// Запрос

		String p1, p2, p3, p4, p5;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(1);
			p2 = rs.getString(2);
			p3 = rs.getString(3);
			p4 = rs.getString(4);
			p5 = rs.getString(5);

			element.add(p1);
			element.add(p2);
			element.add(p3);
			element.add(p4);
			element.add(p5);

			result.add(element);
		}

		rs.close();
		st.close(); // закрытие шлюзов
		con.close();
		return result;

	}

}