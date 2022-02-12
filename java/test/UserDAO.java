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
		List list = new ArrayList();
		JSONObject obj = new JSONObject();
		
		String SQL = "SELECT userEmail, userPassword FROM USER WHERE userEmail=? AND userPassword=?";
		try { // ������ �����͸� ������ �־��ش�.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �������� ?�ȿ� ������ �����͸� �־��ش�.
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword); // ��ɾ ������ ��� ��ȯ, ��ȯ��: insert�� �� �������� ����
			
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