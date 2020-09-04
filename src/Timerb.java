import java.util.TimerTask;
import java.util.Timer;

 public class Timerb extends TimerTask{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Backup.BACKUP_ON){
			Backup.backupCreat();
		}		
	}
}
