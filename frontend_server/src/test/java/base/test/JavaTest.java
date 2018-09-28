package base.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JavaTest {
	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void test1() throws Exception {
		System.out.println("test1");
		String password = "password";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String[] list = new String[10];
		for (int i = 0; i < 10; i++) {
			String hashedPassword = passwordEncoder.encode(password);
			System.out.println(String.format("%2d", (i + 1)) + ". hashedPassword=" + hashedPassword);
			list[i] = hashedPassword;
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(String.format("%2d", (i + 1)) + "." + list[i] + " : " + passwordEncoder.matches(password, list[i]));
		}
	}
}
