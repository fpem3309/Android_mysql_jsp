package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import test.DatabaseUtil;

public class BoardDAO {
	Connection conn = DatabaseUtil.getConnection();
	private ResultSet rs;

	public String board() {
		String SQL = "SELECT * FROM BOARD";
		try { // 각각의 데이터를 실제로 넣어준다.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 쿼리문의 ?안에 각각의 데이터를 넣어준다.
			rs =  pstmt.executeQuery();
			rs.next();
			
			JsonArray arr = new JsonArray();
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_content =rs.getString("board_content");
				String board_hit =rs.getString("board_hit");
				String board_writer =rs.getString("board_writer");
				
				JSONObject obj = new JSONObject();
				obj.put("board_no", board_no);
				obj.put("board_title", board_title);
				obj.put("board_content", board_content);
				obj.put("board_hit", board_hit);
				obj.put("board_writer", board_writer);
				return obj.toJSONString();
			};	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dd";
	}
	
}