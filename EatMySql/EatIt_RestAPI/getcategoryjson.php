<?php
 require "init.php";

 $method=$_REQUEST["method"];
 $result="Nodata";

if ($method == "Category"){
  $sql = "SELECT * FROM `tbl_category`;";
}

//echo $sql;
$sql_result = mysqli_query($con , $sql);
//$result = mysqli_fetch_assoc($sql_result);
//$result = mysqli_query($con,$sql);
$response = array();


while ($row=mysqli_fetch_assoc($sql_result))
{
      array_push($response,array("id"=>$row['id'],"name"=>$row['name'],"image"=>$row['image']));

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
