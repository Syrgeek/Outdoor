<?php
session_start() ;
include("Connection.php") ;
$email = $_GET['email'] ;
$message = $_GET['message'] ;
$senderEmail = $_SESSION['user_email'] ;
date_default_timezone_set('Africa/Cairo');
$dt = new DateTime();
$date = $dt->format('Y-m-d H:i:s');
$query = "INSERT INTO user_has_message (user_email, sender_user_email, message, date) VALUES ('$email', '$senderEmail', '$message', '$date')" ;
mysqli_query($link, $query) ;
?>