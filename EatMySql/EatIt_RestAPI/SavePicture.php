<?php
  $method = $_POST["method"];
  $name = $_POST["name"];
  $image = $_POST["image"];

  $decodedImage = base64_decode("$image");

  If ($method == "Category") {
      file_put_contents("pictures/". $name . ".JPG", $decodedImage);
  }
  else {
      file_put_contents("Foods/". $name . ".JPG", $decodedImage);
  }
 ?>
