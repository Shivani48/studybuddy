<?php
   include("db.php");
    
      $myemail = mysqli_real_escape_string($conn,$_POST['email']);
	  $sql1 = "SELECT id FROM usertable where email = '$myemail'";
	  $result0 = mysqli_query($conn,$sql1);
      $myid = mysqli_fetch_row($result0);
	  $sql = "SELECT * FROM requestdetails where emailfor = '$myid[0]'";
	  $result1 = mysqli_query($conn,$sql);
	  $rows = array();
	 	while($r = mysqli_fetch_assoc($result1)) {
			$myemail=$r["emailfrom"];
			$sql = "SELECT * FROM usertable where email = '$myemail'";
			$result = mysqli_query($conn,$sql);
			$row = mysqli_fetch_array($result,MYSQLI_ASSOC);
			$rows[] = $row;
		}
	echo json_encode($rows);
		
?>