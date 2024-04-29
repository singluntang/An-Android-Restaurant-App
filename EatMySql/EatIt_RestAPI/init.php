<?php

$host="localhost";
$database="eatit_server_client";
$username="root";
$password="";

// $host="localhost";
// $database="eatit_server_client";
// $username="root";
// $password="00573975t";

$con=mysqli_connect($host,$username,$password,$database);

if(!$con){

    //echo "Connection unsuccessful";
}
else
{
    //echo "Connection successful";

}

?>
