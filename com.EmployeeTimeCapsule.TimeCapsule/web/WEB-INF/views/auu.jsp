<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>EmployeeManager</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/metisMenu.min.css" rel="stylesheet">
        <link href="css/sb-admin-2.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">

    </head>

    <body>

        <div id="wrapper">

            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${url}anu">Employee Time Capsule System</a>
                </div>

                <ul class="nav navbar-top-links navbar-right">

                    <li><a href="${url}login"><i class="fa fa-sign-out fa-fw"></i>Logout</a>
                    </li>                   
                </ul>   

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <i class="fa fa-table fa-fw"></i>System Administration
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a href="${url}anu">Add new user</a>
                                    </li>
                                    <li>
                                        <a href="${url}auu">Update user</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Update User</h1>
                            ${ResultMessage}
                            <form role="form"  method="post" action="auu">
                                <div class="input-group">
                                    <input id="btn-input" type="text" class="form-control input-sm" placeholder="Enter user name..." name="search"/>
                                    <span class="input-group-btn">
                                        <input type="submit" name="search" value="Search" class="btn btn-warning btn-sm" id="btn-chat"/>                                        
                                    </span>
                                </div>
                                <br />
                                <table class="table">
                                    <tr>
                                        <td>User name</td><td> <label for="username" class="form-control">${UserID}</label><input type="hidden" value="${UserID}" name="username"></td>
                                    </tr>
                                    <tr>
                                        <td>Name</td><td> <input name="name" class="form-control" value="${Name}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Surname</td><td> <input name="surname" class="form-control" value="${Surname}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Role</td><td> <select class="form-control" name="role">
                                                ${Role}
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td>Employment type</td><td> <select class="form-control" name="emptyp">
                                                ${EmploymentType}                                               
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td>Email</td><td> <input name="email" class="form-control" value="${Email}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Department</td><td> <input name="department" class="form-control" value="${Department}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Password</td><td> <input name="pass" class="form-control" value="${Password}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Confirm Password</td><td> <input name="confirmpass" class="form-control"  value="${Password}" /></td>
                                    </tr>   
                                    <tr>
                                        <td>Status</td>
                                        <td>
                                            <div class="form-group">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="active" value="Yes" id="optionsRadios1" checked>Active                                               
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="active" value="No" id="optionsRadios2">Inactive

                                                    </label>
                                                </div>

                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <input type="submit" name="save" value="Save" class="btn btn-primary btn-lg"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>