<?php
   include("db.php");
      
      $myemail = mysqli_real_escape_string($conn,$_POST['user_name']);
      $mypasswd = mysqli_real_escape_string($conn,$_POST['password']); 
      
      $sql = "SELECT * FROM usertable WHERE email = '$myemail' and password = '$mypasswd'";
      $result = mysqli_query($conn,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $count = mysqli_num_rows($result);
     	
      if($count == 1) {
		session_start();
		echo "success";
		}
	else {
		echo "Invalid Email & Password";
	}
?>