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

	public String board() {
		List list = new ArrayList();
		String SQL = "SELECT * FROM BOARD";
		try { // 각각의 데이터를 실제로 넣어준다.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_content =rs.getString("board_content");
				String board_date =rs.getString("board_date");
				String board_hit =rs.getString("board_hit");
				String userEmail =rs.getString("userEmail");
				
				JSONObject obj = new JSONObject();
				
				obj.put("board_no", board_no);
				obj.put("board_title", board_title);
				obj.put("board_content", board_content);
				obj.put("board_hit", board_hit);
				obj.put("board_date", board_date);
				obj.put("userEmail", userEmail);
				list.add(obj.toString());
			};	
			return ""+list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//정보가 없으면 null
		return null;
	}
	
}