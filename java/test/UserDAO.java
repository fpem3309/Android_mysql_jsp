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
		try { // 각각의 데이터를 실제로 넣어준다.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword); // 명령어를 수행한 결과 반환, 반환값: insert가 된 데이터의 개수
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String login(String userEmail, String userPassword) {
		String SQL = "SELECT userEmail, userPassword FROM USER WHERE userEmail=? AND userPassword=?";
		try { // 각각의 데이터를 실제로 넣어준다.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword); // 명령어를 수행한 결과 반환, 반환값: insert가 된 데이터의 개수
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