<?php
 require "init.php";

 $method=$_REQUEST["method"];


 $result="Nodata";


$order_id=$_REQUEST["order_id"];
$sql = "SELECT foods FROM tbl_request where order_id like '$order_id';";


//echo $sql;
$sql_result = mysqli_query($con , $sql);
//$sql_result = mysqli_prepare($connection, $sql);
//$result = mysqli_fetch_assoc($sql_result);
//$result = mysqli_query($con,$sql);
$response = array();


while ($row=mysqli_fetch_assoc($sql_result))
{
    $Value = $row['foods'];
    $result = "data";
}

if($result=="Nodata") {
  echo "Nodata";
}
else {
  //echo json_encode(array("server_response"=>$response))."\nNodata";
  echo $Value."\nNodata";
//http://192.168.86.24/EatIt/getorderDetailjson.php?method=Food&order_id=1522597450482
}
mysqli_close($con);
?>
