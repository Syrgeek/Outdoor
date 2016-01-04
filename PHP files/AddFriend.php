<?php
session_start() ;
include("Connection.php") ;
$addedEmail = $_POST['addedEmail'] ;
$email = $_SESSION['user_email'] ;

//echo $email ;
$query = "SELECT * FROM user_has_friend WHERE user_email = '$email' AND friend_user_email = '$addedEmail' " ;
$result = mysqli_query($link, $query) ;
$row = mysqli_fetch_array($result) ;

if($row)
{
    $query2 = "DELETE FROM user_has_friend WHERE user_email = '$email' AND friend_user_email = '$addedEmail'" ;
    mysqli_query($link, $query2) ;
}else
{
    $query2 = "INSERT INTO user_has_friend (user_email, friend_user_email) VALUES ('$email', '$addedEmail')" ;
    mysqli_query($link, $query2) ;
}
?>
