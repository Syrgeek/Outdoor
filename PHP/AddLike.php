<?php
session_start() ;
include("Connection.php") ;
$checkinID = $_GET['checkin_id'] ;
$email = $_SESSION['user_email'] ;

$response = array() ;
$query = "SELECT * FROM user_likes_checkin WHERE checkin_checkin_id = '$checkinID' AND user_email = '$email' " ;
$result = mysqli_query($link, $query) ;
if($result->num_rows===0)
{
    $query2 = "INSERT INTO user_likes_checkin (user_email, checkin_checkin_id) VALUES ('$email', '$checkinID')" ;
    mysqli_query($link, $query2) ;
    
    $response["success"] = 1 ;
    echo json_encode($response) ;
}else
{
    $response["success"] = 0 ;
    echo json_encode($response) ;
}
?>