<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="test.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
//사용자가 보낸 데이터를 한글을 사용할 수 있는 형식으로 변환
request.setCharacterEncoding("UTF-8");
String userEmail = null;
String userPassword = null;
String userPassword_chk = null;
int result = 1;

if (request.getParameter("userEmail") != null) {
	userEmail = (String) request.getParameter("userEmail");
}
if (request.getParameter("userPassword") != null) {
	userPassword = (String) request.getParameter("userPassword");
}
if (request.getParameter("userPassword_chk") != null) {
	userPassword_chk = (String) request.getParameter("userPassword_chk");
}
UserDAO userDAO = new UserDAO();
if((userEmail != null && userPassword != null) && (userPassword.equals(userPassword_chk))){

result = userDAO.join(userEmail, userPassword);

}

JSONObject obj = new JSONObject();
obj.put("sign", result);
%>
<%=obj%>
