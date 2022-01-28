<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="test.UserDTO"%>
<%@ page import="test.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
//사용자가 보낸 데이터를 한글을 사용할 수 있는 형식으로 변환
request.setCharacterEncoding("UTF-8");
String userEmail = null;
String userPassword = null;
if (request.getParameter("userEmail") != null) {
	userEmail = (String) request.getParameter("userEmail");
}
if (request.getParameter("userPassword") != null) {
	userPassword = (String) request.getParameter("userPassword");
}
if (userEmail == null || userPassword == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안 된 사항이 있습니다.')");
	script.println("</script>");
	script.close();
	return;
}
UserDAO userDAO = new UserDAO();
String result = userDAO.login(userEmail, userPassword);
%>

