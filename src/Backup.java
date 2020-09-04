import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Backup {

	/// ���������� ��� ������
	static boolean BACKUP_ON = false;
	/// ������� ����������
	static int Backup_frequency = 30;
	static String Backup_place = "";

	/// ����������� ��� ��������������
	private static boolean copy(String pathBackupDB, boolean BACKUP_EVENT) {
		String pathDB = "Sport.accdb";

		pathBackupDB += "backup_" + pathDB;
		File fileDB = new File(pathDB);
		File backupFileDB = new File(pathBackupDB);
		try {
			/// ������� �����
			if (BACKUP_EVENT) {
				Files.copy(fileDB.toPath(), backupFileDB.toPath(), StandardCopyOption.REPLACE_EXISTING);
				RecordBackup.writeLog("��������� ����������� ������ �������");

			}
			/// ��������������� �� �� ������
			else {

				Files.copy(backupFileDB.toPath(), fileDB.toPath(), StandardCopyOption.REPLACE_EXISTING);
				RecordBackup.writeLog("�������������� ������� ���������");

			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private static String[] parsString(String stringConfig) {
		String[] parsString = { "", "" };
		String parsStringToWord[] = stringConfig.split(" ", 2);
		parsString[0] = parsStringToWord[0];
		if (parsStringToWord.length == 2)
			parsString[1] = parsStringToWord[1];

		return parsString;
	}

	// ������ ����� �������, ������������ ���������� ��������
	private static void parsConfig() {
		try (BufferedReader br = new BufferedReader(new FileReader("configuration.txt"))) {

			/// ������ ���������
			String stringConfig;
			while ((stringConfig = br.readLine()) != null) {

				String parsStringToWord[] = parsString(stringConfig);

				switch (parsStringToWord[0]) {

				/// ���/���� �����
				case "backup_on":
					if (Integer.parseInt(parsStringToWord[1]) == 1) {
						BACKUP_ON = true;
					} else {
						BACKUP_ON = false;
					}
					break;

				/// ������� ����������
				case "backup_schedule":
					if (parsStringToWord[1] != null) {
						Backup_frequency = Integer.parseInt(parsStringToWord[1]);
					}
					break;

				/// ���� � ��������
				case "backup_location":
					if (parsStringToWord[1] != null) {
						Backup_place = parsStringToWord[1];

					} else {
						Backup_place = "";
					}
					break;
				}

			}
		}

		catch (IOException ex) {
			System.out.println(ex.getMessage());
			BACKUP_ON = true;
			Backup_frequency = 60;
			Backup_place = "";

		}
	}

	public static void getParsConfig() {
		parsConfig();
	}

	// �������� ������
	public static void backupCreat() {
		parsConfig();
		if (BACKUP_ON == true) {
			/// �����������
			copy(Backup_place, true);

		}
	}

	// �������������� �� ������
	public static void recoveryDB() {
		parsConfig();

		copy(Backup_place, false);
	}
}
