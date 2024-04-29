<?php

 require "init.php";

 $method=$_POST["method"];


 if($method == "signup") {
     $phone=$_POST["phone"];
     $name=$_POST["name"];
     $password=$_POST["password"];
     $staff=$_POST["staff"];
     $securecode=$_POST["securecode"];
     $sql= "insert into tbl_users values('$phone','$name','$password','$staff','$securecode');";
 }
 elseif($method == "updatepwd") {
   $phone=$_POST["phone"];
   $password=$_POST["password"];
   $sql= "Update tbl_users set password = '$password' where phone like '$phone';";
 }
 elseif($method == "Rating") {
   $phone=$_POST["phone"];
   $foodId=$_POST["foodId"];
   $value=$_POST["value"];
   $comments=$_POST["comments"];

   $sql= "insert into tbl_rating values('$phone','$value','$foodId','$comments');";

   $sql2= "delete from tbl_rating where phone like '$phone' and foodid like '$foodId';";

 }
 elseif($method == "Order") {
   $orderid=$_POST["orderid"];
   $phone=$_POST["phone"];
   $name=$_POST["name"];
   $address=$_POST["address"];
   $total=$_POST["total"];
   $status=$_POST["status"];
   $comment=$_POST["comment"];
   $paymentState=$_POST["paymentState"];
   $foods=$_POST["foods"];

   $sql= "insert into tbl_request values('$orderid','$phone','$name','$address','$total','$status','$comment','$paymentState','$foods');";
 }
elseif ($method == "Category") {
  $categoryid=$_POST["categoryid"];
  $name=$_POST["name"];
  $image=$_POST["image"];

  $sql= "insert into tbl_category values('$categoryid','$name','$image');";
}
elseif ($method == "CategoryUpdate") {
  $categoryid=$_POST["categoryid"];
  $name=$_POST["name"];
  $image=$_POST["image"];

  $sql= "Update tbl_category set name = '$name' , image = '$image' where id like '$categoryid';";
}
elseif ($method == "DeleteCategory") {
  $categoryid=$_POST["categoryid"];

  $sql= "delete from tbl_category where id like '$categoryid';";

  $sql2= "delete from tbl_food where menuid like '$categoryid';";
}
elseif ($method == "Food") {
  $foodid=$_POST["foodid"];
  $name=$_POST["name"];
  $image=$_POST["image"];
  $description=$_POST["description"];
  $price=$_POST["price"];
  $discount=$_POST["discount"];
  $menuId=$_POST["menuId"];

  $sql= "insert into tbl_food (id,name, image, description, price, discount, menuid)  values('$foodid','$name','$image','$description','$price','$discount','$menuId');";
}
elseif ($method == "DeleteFood") {
  $foodid=$_POST["foodid"];

  $sql= "delete from tbl_food where id like '$foodid';";
}
elseif ($method == "FoodUpdate") {
  $id=$_POST["foodid"];
  $name=$_POST["name"];
  $image=$_POST["image"];
  $description=$_POST["description"];
  $price=$_POST["price"];
  $discount=$_POST["discount"];
  $menuId=$_POST["menuId"];

  $sql= "UPDATE tbl_food SET name='$name',image='$image',description='$description',price='$price',discount='$discount' where id like '$id';";
}
elseif ($method == "DeleteOrder") {
  $Orderid=$_POST["Orderid"];

  $sql= "delete from tbl_request where order_id like '$Orderid';";
}
elseif($method == "OrderStaus") {
  $status=$_POST["status"];
  $orderid=$_POST["orderid"];
  $sql= "Update tbl_request set status = '$status' where order_id like '$orderid';";
}

echo $sql;

if($method == "Rating" || $method == "DeleteCategory") {
  if(mysqli_query($con,$sql2))
  {
      echo "deleted";

  }
}

 if(mysqli_query($con,$sql))
 {
     echo "values added";

 }
 else{

      echo "values not added";
 }


?>
