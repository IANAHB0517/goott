package weblsj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

	public static void main(String[] args) {
		Date d = new Date();
		
		String pat = "z a hh:MM:ss";
		SimpleDateFormat form = new SimpleDateFormat(pat);

		System.out.println(form.format(d));
	}

}
