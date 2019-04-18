<?php
$servername = "localhost";
$username = "students";
$password = "students";
$dbname="studdybuddy";

// Create connection
$conn = mysqli_connect($servername, $username, $password,$dbname);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
else{
	$con["connection"]="Successfully";
//echo json_encode($con);
}
?>