package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {// �����ͺ��̽��� ������� ����
		try {
			String dbURL = "jdbc:mysql://localhost:3306/study?useSSL=false&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "fpem3309";
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
