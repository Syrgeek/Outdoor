<?php
session_start() ;
include("Connection.php") ;
$email = $_POST['email'] ;
$myEmail = $_SESSION['user_email'] ;
$query = "SELECT * FROM checkin WHERE checkin_user_email = '$email' " ;
$result = mysqli_query($link, $query) ;

$query2 = "SELECT username FROM user WHERE user_email = '$email' " ;
$result2 = mysqli_query($link, $query2) ;
$row2 = mysqli_fetch_array($result2) ;

$response = array() ;
$idx = 0 ;
while($row = mysqli_fetch_array($result))
{
    $checkinID = $row['checkin_id'] ;

    $query3 = "SELECT * FROM user_likes_checkin WHERE checkin_checkin_id = '$checkinID' " ;
    $result3 = mysqli_query($link, $query3) ;
    $row['likes'] = $result3->num_rows ;

    $row['username'] = $row2['username'] ;
     
    $query4 = "SELECT * FROM user_likes_checkin WHERE user_email = '$myEmail' AND checkin_checkin_id = '$checkinID' " ;
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
$query5 = "SELECT * FROM user_has_friend WHERE user_email = '$myEmail' AND friend_user_email = '$email' " ;
$result5 = mysqli_query($link, $query5) ;
if($result5->num_rows!==0)
{
    $finalResponse['is_friend'] = 1 ;
}else
{
    $finalResponse['is_friend'] = 0 ;
}
$finalResponse["array"] = $response ;
$finalResponse["username"] = $row2['username'] ;
echo json_encode($finalResponse) ;
?>
