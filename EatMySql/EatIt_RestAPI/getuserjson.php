<?php
 require "init.php";

 $method=$_REQUEST["method"];
 $result="Nodata";


if($method == "signup") {
  $phone = $_REQUEST['phone'];
  $sql = "SELECT * FROM `tbl_users` where phone like '$phone';";
  //echo $sql;
}
elseif ($method == "signin"){
  $phone = $_REQUEST['phone'];
  $password = $_REQUEST['password'];
  $sql = "SELECT * FROM `tbl_users` where phone like '$phone' and password like '$password';";
}
elseif ($method == "forgetpwd"){
  $phone = $_REQUEST['phone'];
  $securecode = $_REQUEST['securecode'];
  $sql = "SELECT * FROM `tbl_users` where phone like '$phone' and securecode like '$securecode';";
}
elseif ($method == "Category"){
  $sql = "SELECT * FROM `tbl_category`;";
}

//echo $sql;
$sql_result = mysqli_query($con , $sql);
//$result = mysqli_fetch_assoc($sql_result);
//$result = mysqli_query($con,$sql);
$response = array();


while ($row=mysqli_fetch_assoc($sql_result))
{
    array_push($response,array("phone"=>$row['phone'],"name"=>$row['name'],"password"=>$row['password'],"staff"=>$row['staff'],"securecode"=>$row['securecode']));
    $result = "data";
}



if($result=="Nodata") {
  echo "Nodata";
}
else {
  echo json_encode(array("server_response"=>$response))."\nNodata";
//  echo  nl2br ("$Output \n Nodata");
  mysqli_close($con);
}

?>
