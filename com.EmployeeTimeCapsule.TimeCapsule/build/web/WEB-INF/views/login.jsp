<!DOCTYPE html>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>   
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form"  method="post" action="login" id="in">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control required email" placeholder="Username" id="username" name="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">                                     
                                </div>                                
                                <input type="submit" id="login" name="Login" value="login" class="btn btn-lg btn-success btn-block"/><br><i><small>${error}</small></i>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>   
    </div>


</body>





