import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ListDoc {

	public JFrame sport;
	JTable sportsmanTable = new JTable();

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ptr;

	public ListDoc() { 

		sport = new JFrame("ListDocument");
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

		JLabel Name = new JLabel("Все документы");
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

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sport.dispose(); // кнопка назад
				new Document();

			}

		});

		sport.dispose(); // отключает кнопки
		sport.setVisible(true); // Делает таблицу видимым

		try {

			JScrollPane pane = new JScrollPane(sportsmanTable);
			pane.getViewport().add(sportsmanTable);
			pane.setBounds(170, 140, 1300, 400); // создание таблицы и прокрутки
			sportsmanTable.setBackground(c1);
			sportsmanTable.setFont(table);
			pane.setBackground(c1);
			Vector<Vector<String>> values = getDataFromDB();

			Vector<String> hat = new Vector<String>();

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
			search.setBounds(355, 545, 200, 25);
			JLabel words = new JLabel("Введите слово для поиска:");
			words.setBounds(170, 545, 200, 25);
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
			for (int i = 3; i <= 4; i++) {
				rowSorter.setComparator(i, new Comparator<String>() { // копипаст наше все! лучше способа не нашел

					@Override
					public int compare(String o1, String o2) {
						return Integer.parseInt(o1) - Integer.parseInt(o2);
					}

				});
			}
			rowSorter.setComparator(9, new Comparator<String>() { // копипаст наше все! лучше способа не нашел

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
			
			sportsmanTable.getTableHeader().setReorderingAllowed(false); // запрет на перетаскивание столбцов

			panel.add(words);
			panel.add(search);
			panel.add(pane);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		panel.add(back); // добавление кнопок на панель
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
		rs = st.executeQuery("Select * from Document"); // SQL// Запрос

		String p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;

		while (rs.next()) {
			Vector<String> element = new Vector<String>();

			p1 = rs.getString(9);
			p2 = rs.getString(1);
			p3 = rs.getString(4);
			p4 = rs.getString(5);
			p5 = rs.getString(2);
			p6 = rs.getString(10);
			p7 = rs.getString(6);
			p8 = rs.getString(7);
			p9 = rs.getString(8);
			p10 = rs.getString(3);

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