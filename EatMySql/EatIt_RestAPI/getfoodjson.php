<?php
 require "init.php";

 $method=$_REQUEST["method"];


 $result="Nodata";

if ($method == "Food"){
  $categoryid=$_REQUEST["categoryid"];
  $sql = "SELECT * FROM `tbl_food` where menuid like '$categoryid';";
}
elseif ($method == "FoodDetail"){
  $id=$_REQUEST["id"];
  $sql = "SELECT * FROM `tbl_food` where id like '$id';";
}
elseif ($method == "FoodItem"){
  $name=$_REQUEST["name"];
  $sql = "SELECT * FROM `tbl_food` where name like '$name';";
}
elseif ($method == "AllFood"){
  $sql = "SELECT CAST(id AS UNSIGNED) 'id', name, image, description, price, discount, menuid FROM `tbl_food` order by id;";
}


//echo $sql;
$sql_result = mysqli_query($con , $sql);
//$result = mysqli_fetch_assoc($sql_result);
//$result = mysqli_query($con,$sql);
$response = array();


while ($row=mysqli_fetch_assoc($sql_result))
{
    array_push($response,array("id"=>$row['id'],"name"=>$row['name'],"image"=>$row['image'],"description"=>$row['description'],"price"=>$row['price'],"discount"=>$row['discount'],"menuid"=>$row['menuid']));

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
