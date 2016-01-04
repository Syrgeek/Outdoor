<?php
session_start() ;
include("Connection.php") ;
if($_GET['loginEmail'])
{
$query = "SELECT * FROM user WHERE user_email = '".mysqli_real_escape_string($link,$_GET['loginEmail'])."' " ;
        $result = mysqli_query($link, $query) ;
        $row = mysqli_fetch_array($result) ;
        if($row)
        {
$query = "SELECT security_question FROM user WHERE user_email = '".mysqli_real_escape_string($link,$_GET['loginEmail'])."' " ;
            $result = mysqli_query($link, $query) ;
            $row = mysqli_fetch_array($result) ;
            $response["success"] = 1;
            $response["message"] = $row["security_question"];
            echo json_encode($response);
        }else
        {
            $response["success"] = 0 ;
            $response["message"] = "We couldn't find a user with such an email. Please try again." ;
            // echoing JSON response
            echo json_encode($response) ;
        }
}else
{
    $response["success"] = 0 ;
    $response["message"] = "Please enter an email" ;
    // echoing JSON response
    echo json_encode($response) ;
}

?>