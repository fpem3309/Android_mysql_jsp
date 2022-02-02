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
		String SQL = "SELECT * FROM BOARD ORDER BY board_no DESC";
		try { // ������ �����͸� ������ �־��ش�.
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �������� ?�ȿ� ������ �����͸� �־��ش�.
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
		//������ ������ null
		return null;
	}
	
	
	
	public int add_content(String add_content, String board_no) {
		String SQL = "Update Board set board_content = ? WHERE board_no = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �������� ?�ȿ� ������ �����͸� �־��ش�.
			pstmt.setString(1, add_content);
			pstmt.setString(2, board_no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
}