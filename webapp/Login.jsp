<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="test.UserDTO"%>
<%@ page import="test.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
//����ڰ� ���� �����͸� �ѱ��� ����� �� �ִ� �������� ��ȯ
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
	script.println("alert('�Է��� �� �� ������ �ֽ��ϴ�.')");
	script.println("</script>");
	script.close();
	return;
}
UserDAO userDAO = new UserDAO();
String result = userDAO.login(userEmail, userPassword);
%>

