<?php
session_start() ;
include("Connection.php") ;
$email = $_SESSION['user_email'] ;
$query = "SELECT * FROM checkin WHERE checkin_user_email IN ( SELECT friend_user_email FROM user_has_friend WHERE user_email = '$email')  ORDER BY date DESC" ;
$result = mysqli_query($link, $query) ;

$response = array() ;
$idx = 0 ;
while($row = mysqli_fetch_array($result))
{
    $friendEmail = $row['checkin_user_email'] ;
    $query2 = "SELECT username FROM user WHERE user_email = '$friendEmail' " ;
    $result2 = mysqli_query($link, $query2) ;
    $row2 = mysqli_fetch_array($result2) ;
    $row['username'] = $row2['username'] ;

    $checkinID = $row['checkin_id'] ;
    $query3 = "SELECT * FROM user_likes_checkin WHERE checkin_checkin_id = '$checkinID' " ;
    $result3 = mysqli_query($link, $query3) ;
    $row['likes'] = $result3->num_rows ;

    $query4 = "SELECT * FROM user_likes_checkin WHERE user_email = '$email' AND checkin_checkin_id = '$checkinID' " ;
    $result4 = mysqli_query($link, $query4) ;
    $row4 = mysqli_fetch_array($result4) ;
    if($row4)
    {
        $row['if_liked'] = 1 ;
    }else
    {
        $row['if_liked'] = 0 ;
    }
     
    $response[$idx] = $row ;
    $idx++ ;
}
$finalResponse["array"] = $response ;
echo json_encode($finalResponse) ;
?>	