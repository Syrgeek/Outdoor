<?php
session_start() ;
include("Connection.php") ; // include connection file
    if(!$_GET['username'])
        $error .= "\nPlease enter a username" ;

    if(!$_GET['email']) // email not entered
        $error .= "\nPlease enter your email" ;
    else if(!filter_var($_GET['email'], FILTER_VALIDATE_EMAIL)) // This checks if the given parameter is valid according
        $error .= "\nPlease enter a valid email address" ;           // to the second parameter(validate email)

    if(!$_GET['password'])
        $error .= "\nPlease enter a password" ;
    /*else // we need to check that it is at least 8 chars. long and contains at least one capital case letter
    {
        if(strlen($_GET['password'])<8)
            $error .= "\nPlease enter a password at least 8 characters long" ;
        if(!preg_match('`[A-Z]`', $_GET['password'])) // First parameter is a regex checking if there exits a letter from
            $error .= "\nPlease include at least one capital letter in your password" ; // A to Z in the second parameter
    }*/

    if(!$_GET['securityQuestion'])
        $error .= "\nPlease enter a security question" ;
    if(!$_GET['securityAnswer'])
        $error .= "\nPlease enter a security answer" ;

    if(!$_GET['altEmail']) // email not entered
        $error .= "\nPlease enter an alternative email" ;
    else if(!filter_var($_GET['altEmail'], FILTER_VALIDATE_EMAIL))
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
        $query = "SELECT * FROM user WHERE user_email = '".mysqli_real_escape_string($link,$_GET['email'])."'" ;
        /* Without real escape this will work but hackers can use SQL injection by entering characters '); in a text field
         * then type any SQL command it will work and they can retrieve any data from our DB so to avoid this we use
         * the real escape function
         * */
        $hashed =  md5(md5($_GET['email']).$_GET['password']) ;
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
            $username = $_GET['username'] ;
            $securityQuestion = $_GET['securityQuestion'] ;
            $securityAnswer = $_GET['securityAnswer'] ;
            $altEmail = $_GET['altEmail'] ;
            $query = "INSERT INTO user (user_email, username, password, security_question, security_answer, alternative_email) VALUES ('".mysqli_real_escape_string($link,$_GET['email'])."', '$username', '.$hashed.', '$securityQuestion', '$securityAnswer', '$altEmail')" ;
            // Here we used the email of the user as a salt(changes with each user) hashed it -- append our password -- and re-hash again
            mysqli_query($link, $query) ; // go execute
            //echo "You've been signed up" ;
// mysql_query("INSERT INTO user(user_email, username, password) VALUES('$email', '$username', '$password')");
            $response["success"] = 1;
            $response["message"] = "Account successfully created.";
            $_SESSION['user_email'] = $_GET['email'] ;
            // echoing JSON response
            echo json_encode($response);
            //create a session variable
            //echo $_SESSION['id'] ;
            //$_SESSION['id'] = mysqli_insert_id($link) ;
            //echo $_SESSION['id'] ;
            // insert id function gets the id of the element inserted most recently in the specified link(DB)
        }
    }
?>