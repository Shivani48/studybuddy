<?php
   include("db.php");
      
	  $myname = mysqli_real_escape_string($conn,$_POST['name']);
	  $myuni = mysqli_real_escape_string($conn,$_POST['university']);
	  $mymjr = mysqli_real_escape_string($conn,$_POST['major']);
	  $mylvl = mysqli_real_escape_string($conn,$_POST['level']);
	  $myphn = mysqli_real_escape_string($conn,$_POST['phone']);
      $myzip = mysqli_real_escape_string($conn,$_POST['zipcode']);
	  $myemail = mysqli_real_escape_string($conn,$_POST['email']);
	  $mypasswd = mysqli_real_escape_string($conn,$_POST['password']); 
      
	  if (empty($myname) || empty($myuni) || empty($mymjr) || empty($mylvl) || empty($myphn) || empty($myzip) || empty($myemail) || empty($mypasswd)) {
		  echo "Any value cannot be empty";
	  }
	  else{
		  
	  $sql = "INSERT INTO usertable (name,university,major,level,phone,zipcode,email,password)
			VALUES ('$myname','$myuni','$mymjr','$mylvl','$myphn','$myzip','$myemail','$mypasswd')";
	 	
		if (mysqli_query($conn, $sql)) {
			echo "success";
			} else {
			echo "Error: " . $sql . "<br>" . mysqli_error($conn);
			}
	  }
			
?>