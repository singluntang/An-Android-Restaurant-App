<?php
 $host="localhost";
 $database="project";
 $username="root";
 $password="";

 $con=mysqli_connect($host,$username,$password,$database);

$sql = "select * from users;";
$result = mysqli_query($con,$sql);
$response = array();

while ($row=mysqli_fetch_array($result))
{
    array_push($response,array("name"=>$row[0],"email"=>$row[1],"contact"=>$row[2],"password"=>$row[3]));
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);

?>
