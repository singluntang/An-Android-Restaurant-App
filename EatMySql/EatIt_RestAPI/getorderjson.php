<?php
 require "init.php";

 $method=$_REQUEST["method"];


 $result="Nodata";

if ($method == "OrderStatus"){
  $phone=$_REQUEST["phone"];
  $sql = "SELECT order_id, phone, name, address, total, status, Comment, paymentState FROM tbl_request where phone like '$phone';";
}
elseif ($method == "AllOrderStatus"){
  $sql = "SELECT order_id, phone, name, address, total, status, Comment, paymentState FROM tbl_request;";
}


//echo $sql;
$sql_result = mysqli_query($con , $sql);
//$result = mysqli_fetch_assoc($sql_result);
//$result = mysqli_query($con,$sql);
$response = array();


while ($row=mysqli_fetch_assoc($sql_result))
{

    array_push($response,array("order_id"=>$row['order_id'],"phone"=>$row['phone'],"name"=>$row['name'],"address"=>$row['address'],"total"=>$row['total'],
                    "status"=>$row['status'],"Comment"=>$row['Comment'],
                    "paymentState"=>$row['paymentState']));

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
