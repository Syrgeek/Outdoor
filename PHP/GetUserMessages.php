<?php
session_start() ;
include("Connection.php") ;
$email = $_SESSION['user_email'] ;
$query = "SELECT * FROM user_has_message WHERE user_email = '$email' ORDER BY date DESC" ; // file(takes nothing) gets the checkins of session+username
$result = mysqli_query($link, $query) ; // file takes email gets checkins+isFriend with session+response['username']

$response = array() ;
$idx = 0 ;
while($row = mysqli_fetch_array($result))
{
    $senderEmail = $row['sender_user_email'] ;
    $query2 = "SELECT username FROM user WHERE user_email = '$senderEmail' " ;
    $result2 = mysqli_query($link, $query2) ;
    $row2 = mysqli_fetch_array($result2) ;
    $row['username'] = $row2['username'] ;
    $response[$idx] = $row ;
    $idx++ ;
}
$finalResponse["array"] = $response ;
echo json_encode($finalResponse) ;
?>