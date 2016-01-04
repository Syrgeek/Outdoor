<?php
session_start() ;

$response = array() ;
if($_POST["logout"]==1 and $_SESSION['user_email'])
{
    session_destroy() ; // closes all sessions
    $response["success"] = 0 ;
    $response["message"] = "You have been logged out. Have a nice day!" ;
    echo json_encode($response) ;
    session_start() ;
}else
{
    include("Connection.php") ;
    if($_POST['loginEmail'] && $_POST['loginPassword'])
    {
        $hashed =  md5(md5($_POST['loginEmail']).$_POST['loginPassword']) ;
$query = "SELECT * FROM user WHERE user_email = '".mysqli_real_escape_string($link,$_POST['loginEmail'])."' AND password = '.$hashed.' LIMIT 1" ;
        // LIMIT 1 is used to just check for one user
    
        $result = mysqli_query($link, $query) ;
        $row = mysqli_fetch_array($result) ;
        if($row) // if it exists(not null)
        {
            $_SESSION['user_email'] = $_POST['loginEmail'] ;
            $response["success"] = 1 ;
            $response["message"] = $row["username"];
            // echoing JSON response
            echo json_encode($response);
        }else
        {
            $response["success"] = 0 ;
            $response["message"] = "We couldn't find a user with such an email and/or password. Please try again." ;
            // echoing JSON response
            echo json_encode($response) ;   
        }
    }else
    {
        $response["success"] = 0 ;
        $response["message"] = "Please enter your email and password." ;
        // echoing JSON response
        echo json_encode($response) ;   
    }    
}

?>
