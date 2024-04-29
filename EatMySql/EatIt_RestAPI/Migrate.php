<?php

 require "init.php";

 $name=$_POST["name"];
 $image=$_POST["image"];
 $description=$_POST["description"];
 $price=$_POST["price"];
 $discount=$_POST["discount"];
 $menuid=$_POST["menuid"];

$sql= "insert into tbl_food (`name`, `image`, `description`, `price`, `discount`, `menuid`)  values('$name','$image','$description','$price','$discount','$menuid');";

echo $sql;

 if(mysqli_query($con,$sql))
 {
     echo "values added";

 }
 else{

      echo "values not added";
 }


?>
