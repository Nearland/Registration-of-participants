import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

public class RecordBackup {
	// �����������
	public static void writeLog(String eventName) {
		try {
			FileWriter LogWriter = new FileWriter("log.txt", true);
			BufferedWriter bufferWriter = new BufferedWriter(LogWriter);

			Date date = new Date();
			bufferWriter.write(date.toString() + " " + eventName);
			bufferWriter.newLine();
			bufferWriter.close();
		}

		catch (IOException ex) {
			System.out.println(ex.getMessage());
			JOptionPane pane = new JOptionPane();
			JOptionPane.showMessageDialog(pane, "����������� �� �������");
			pane.setSize(100, 100);
			pane.setVisible(false);
		}
	}

	

}
