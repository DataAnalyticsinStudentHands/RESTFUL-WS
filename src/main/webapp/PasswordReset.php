<html>
<head>
<title>Password Reset</title>
</head>
<body>
<h1>Password Reset Request</h1>
<form action="users/<?php echo $_GET['user_id']; ?>/tokenPasswordReset?token=<?php echo $_GET['token']; ?>" method="POST">
Enter new password: <input type="password" name="password">
<br />
Re-enter password: <input type="password" name="reenter_password" />
<br />
<input type="submit" value="Submit" />
</form>
</body>
</html>