<?php
session_start() ;
include("Connection.php") ;
if($_GET['loginEmail']&&$_GET['securityAnswer'])
{
    $query = "SELECT * FROM user WHERE user_email = '".mysqli_real_escape_string($link,$_GET['loginEmail'])."' " ;
    $result = mysqli_query($link, $query) ;
    $row = mysqli_fetch_array($result) ;
    if($row)
    {
        if($row["security_answer"]==$_GET['securityAnswer'])
        {
            $_SESSION['user_email'] = $_GET['loginEmail'] ;
            $response["success"] = 1;
            $response["message"] = $row["username"];
            // echoing JSON response
            echo json_encode($response);
        }else
        {
            $response["success"] = 0 ;
            $response["message"] = "error, Wrong security answer." ;
            // echoing JSON response
            echo json_encode($response) ;
        }
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
    $response["message"] = "You didn't enter your email or answer. Please try again." ;
    // echoing JSON response
    echo json_encode($response) ;
}
?>