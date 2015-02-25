<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Password Reset</title>
</head>
<body>
<h1>Password Reset Request</h1>
<form action="users/<%=request.getParameter("user_id")%>/tokenPasswordReset?token=<%=request.getParameter("token") %>" method="POST">
Enter new password: <input type="password" name="password">
<br />
Re-enter password: <input type="password" name="reenter_password" />
<br />
<input type="submit" value="Submit" />
</form>
</body>
</html>