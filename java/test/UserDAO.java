package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import test.DatabaseUtil;

public class UserDAO {
	Connection conn = DatabaseUtil.getConnection();
	private ResultSet rs;

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
		List list = new ArrayList();
		JSONObject obj = new JSONObject();
		
		String SQL = "SELECT userEmail, userPassword FROM USER WHERE userEmail=? AND userPassword=?";
		try { // 각각의 데이터를 실제로 넣어준다.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword); // 명령어를 수행한 결과 반환, 반환값: insert가 된 데이터의 개수
			
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userEmail) && rs.getString(2).equals(userPassword)) {
					obj.put("login","true");
					list.add(obj);
				}else {
					obj.put("login","false");
					list.add(obj);
				}
			} else {
				obj.put("login","NO_ID");
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""+list;
	}
	
}