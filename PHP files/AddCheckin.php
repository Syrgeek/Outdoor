<?php
session_start() ;
include("Connection.php") ;
$placeName = $_POST['placeName'] ;
//echo $placeName ;
$status = $_POST['status'] ;
date_default_timezone_set('Africa/Cairo');
$dt = new DateTime();
$date = $dt->format('Y-m-d H:i:s');
$query = "SELECT * FROM place WHERE placeName = '$placeName' " ;
$result = mysqli_query($link, $query) ;
$row = mysqli_fetch_array($result) ;
$response = array() ;
if($row)
{
$query = "INSERT INTO checkin (checkin_user_email, date, status, checkin_place_name) VALUES ('".mysqli_real_escape_string($link,$_SESSION['user_email'])."', '$date', '$status', '$placeName')" ;
    mysqli_query($link, $query) ;
    $response["success"] = 1 ;
    $response["message"] = "Checkin added successfully" ;
    echo json_encode($response) ;
}else
{
    $response["success"] = 0 ;
    $response["message"] = "Can't find a place with such name" ;
    echo json_encode($response) ;
}

?>	
