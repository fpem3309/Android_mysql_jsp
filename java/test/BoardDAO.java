package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import test.DatabaseUtil;

public class BoardDAO {
	Connection conn = DatabaseUtil.getConnection();
	private ResultSet rs;

	public String board(String userEmail) {
		List list = new ArrayList();
		String SQL = "SELECT * FROM BOARD WHERE userEmail=? ORDER BY board_no DESC";
		try { // 각각의 데이터를 실제로 넣어준다.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			pstmt.setString(1, userEmail);
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_content =rs.getString("board_content");
				String board_date =rs.getString("board_date");
				String userMood =rs.getString("userMood");
				//String userEmail =rs.getString("userEmail");
				
				JSONObject obj = new JSONObject();
				
				obj.put("board_no", board_no);
				obj.put("board_title", board_title);
				obj.put("board_content", board_content);
				obj.put("board_date", board_date);
				obj.put("userEmail", userEmail);
				obj.put("userMood", userMood);
				list.add(obj.toString());
			};	
			return ""+list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//정보가 없으면 null
		return null;
	}
	
	
	
	public int add_content(String add_content, String add_mood, String board_no) {
		String SQL = "Update Board set board_content = ?, userMood=? WHERE board_no = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			pstmt.setString(1, add_content);
			pstmt.setString(2, add_mood);
			pstmt.setString(3, board_no);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String mood() {
		List list = new ArrayList();
		String SQL = "select userMood from board";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				String userMood = rs.getString("userMood");
				
				JSONObject obj = new JSONObject();
				
				obj.put("userMood", userMood);

				list.add(obj.toString());
			};	
			return ""+list;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}