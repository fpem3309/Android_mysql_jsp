package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.DatabaseUtil;

public class UserDAO {
	Connection conn = DatabaseUtil.getConnection();
	private ResultSet rs;
	String login_return = "success";

	public int join(String userEmail, String userPassword) {
		String SQL = "INSERT INTO USER VALUES (?,?)";
		try { // ������ �����͸� ������ �־��ش�.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �������� ?�ȿ� ������ �����͸� �־��ش�.
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword); // ��ɾ ������ ��� ��ȯ, ��ȯ��: insert�� �� �������� ����
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String login(String userEmail, String userPassword) {
		String SQL = "SELECT userEmail, userPassword FROM USER WHERE userEmail=? AND userPassword=?";
		try { // ������ �����͸� ������ �־��ش�.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �������� ?�ȿ� ������ �����͸� �־��ش�.
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword); // ��ɾ ������ ��� ��ȯ, ��ȯ��: insert�� �� �������� ����
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("userEmail").equals(userEmail) && rs.getString("userPassword").equals(userPassword)) {
					login_return = "success";
				}else {
					login_return = "false";
				}
			} else {
				login_return = "NO_ID";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login_return;
	}
	
}