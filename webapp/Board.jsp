<%@page import="test.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="test.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
//����ڰ� ���� �����͸� �ѱ��� ����� �� �ִ� �������� ��ȯ
request.setCharacterEncoding("UTF-8");

String userEmail = (String) request.getParameter("userEmail");

BoardDAO boardDAO = new BoardDAO();
String result = boardDAO.board(userEmail);
%>
<%=result%>