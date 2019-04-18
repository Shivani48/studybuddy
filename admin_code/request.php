<?php
   include("db.php");
      
	  $myemail = mysqli_real_escape_string($conn,$_POST['email']);
	  $emailto = mysqli_real_escape_string($conn,$_POST['emailto']);
	  
	  if (empty($myemail) || empty($emailto)) {
		  echo "Any value cannot be empty";
	  }
	  else{
		  
	  $sql = "INSERT INTO requestdetails (emailfrom,emailfor)
			VALUES ('$myemail','$emailto')";
	 	
		if (mysqli_query($conn, $sql)) {
			echo "success";
			} else {
			echo "Error: " . $sql . "<br>" . mysqli_error($conn);
			}
	  }
			
?>