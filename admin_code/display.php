<?php
   include("db.php");
    
      $myemail = mysqli_real_escape_string($conn,$_POST['email']);
      $sql = "SELECT * FROM usertable where email != '$myemail'";
	  $result = mysqli_query($conn,$sql);
	  $rows = array();
		while($r = mysqli_fetch_assoc($result)) {
		$rows[] = $r;
		}
	echo json_encode($rows);
		
?>