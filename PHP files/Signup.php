<?php
session_start() ;
include("Connection.php") ; // include connection file
    if(!$_POST['username'])
        $error .= "\nPlease enter a username" ;

    if(!$_POST['email']) // email not entered
        $error .= "\nPlease enter your email" ;
    else if(!filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) // This checks if the given parameter is valid according
        $error .= "\nPlease enter a valid email address" ;           // to the second parameter(validate email)

    if(!$_POST['password'])
        $error .= "\nPlease enter a password" ;
    else // we need to check that it is at least 8 chars. long and contains at least one capital case letter
    {
        if(strlen($_POST['password'])<8)
            $error .= "\nPlease enter a password at least 8 characters long" ;
        if(!preg_match('`[A-Z]`', $_POST['password'])) // First parameter is a regex checking if there exits a letter from
            $error .= "\nPlease include at least one capital letter in your password" ; // A to Z in the second parameter
    }

    if(!$_POST['securityQuestion'])
        $error .= "\nPlease enter a security question" ;
    if(!$_POST['securityAnswer'])
        $error .= "\nPlease enter a security answer" ;

    if(!$_POST['altEmail']) // email not entered
        $error .= "\nPlease enter an alternative email" ;
    else if(!filter_var($_POST['altEmail'], FILTER_VALIDATE_EMAIL))
        $error .= "\nPlease enter a valid alternative email" ; 
              
    if($error)
    {
        $error = "There were error(s) in your signup details:".$error ;
        $response["success"] = 0;
        $response["message"] = $error;
        echo json_encode($response) ;
    }else
    {
        $link = mysqli_connect("mysql1.000webhost.com", "a8139710_mis94", "12345hom", "a8139710_outdoor") ;
        $query = "SELECT * FROM user WHERE user_email = '".mysqli_real_escape_string($link,$_POST['email'])."'" ;
        
        $hashed =  md5(md5($_POST['email']).$_POST['password']) ;
        $result = mysqli_query($link, $query) ; // go execute

        //if(!$result || mysqli_num_rows($result) == 0)
        if($result->num_rows!==0)
        {
            $error = "That email address is already registered. Do you want to log in?" ;
            $response["success"] = 0;
            $response["message"] = $error;
            echo json_encode($response) ;
        }else
        {
            $username = $_POST['username'] ;
            $securityQuestion = $_POST['securityQuestion'] ;
            $securityAnswer = $_POST['securityAnswer'] ;
            $altEmail = $_POST['altEmail'] ;
            $query = "INSERT INTO user (user_email, username, password, security_question, security_answer, alternative_email) VALUES ('".mysqli_real_escape_string($link,$_POST['email'])."', '$username', '.$hashed.', '$securityQuestion', '$securityAnswer', '$altEmail')" ;
            
            mysqli_query($link, $query) ; // go execute
            //echo "You've been signed up" ;
// mysql_query("INSERT INTO user(user_email, username, password) VALUES('$email', '$username', '$password')");
            $response["success"] = 1;
            $response["message"] = $username;
            $_SESSION['user_email'] = $_POST['email'] ;
            // echoing JSON response
            echo json_encode($response);
            //create a session variable
            //echo $_SESSION['id'] ;
            //$_SESSION['id'] = mysqli_insert_id($link) ;
            //echo $_SESSION['id'] ;
        }
    }
?>
