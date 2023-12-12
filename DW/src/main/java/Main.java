import Modules.Staging;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
	private static boolean setUTF8Output() {
		try {
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		if (!setUTF8Output()) {
			return;
		}
		Staging.process();
	}
}
