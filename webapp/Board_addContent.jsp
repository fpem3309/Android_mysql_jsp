<%@page import="test.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="test.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
//����ڰ� ���� �����͸� �ѱ��� ����� �� �ִ� �������� ��ȯ
request.setCharacterEncoding("UTF-8");
String add_content = request.getParameter("add_content");
String board_no = request.getParameter("board_no");


//if (request.getParameter("add_content") != null) {
//	add_content = (String) request.getParameter("add_content");
//}
//if (request.getParameter("add_content") != null) {
//	add_content = (String) request.getParameter("add_content");
//}

BoardDAO boardDAO = new BoardDAO();
int result = boardDAO.add_content(add_content, board_no);
%>
<%=result%>