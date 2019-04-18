<?php
   include("db.php");
      
      $myemail = mysqli_real_escape_string($conn,$_POST['email']);
      
      $sql = "SELECT * FROM usertable WHERE email = '$myemail'";
      $result = mysqli_query($conn,$sql);
	  $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
    $data["Name"]=$row["name"];
	$data["University"]=$row["university"];
	$data["Major"]=$row["major"];
	$data["Level"]=$row["level"];
	$data["Phone"]=$row["phone"];
	$data["Email"]=$row["email"];
	
	//echo "success";
	header('Content-Type: application/json');
	echo json_encode($data);	
?>