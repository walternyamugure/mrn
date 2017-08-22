<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a class="navbar-brand" href="${url}dashuser">Employee Time Capsule System</a>
                </div>


                <ul class="nav navbar-top-links navbar-right">

                    <li><a href=${url}><i class="fa fa-sign-out fa-fw"></i>Logout</a>
                    </li>
                </ul>


                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="${url}unts"><i class="fa fa-table fa-fw"></i>New Timesheet</a>
                            </li>
                            <li>
                                <a href="${url}uts?typ=All"><i class="fa fa-table fa-fw"></i>My Timesheets</a>
                            </li>

                        </ul>
                    </div>

                </div>

            </nav>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">

                            <h1 class="page-header">Dashboard</h1>

                            <div class="row">
                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-comments fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div class="huge">${Approved}</div>
                                                    <div>Approved Timesheets</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="${url}uts?typ=Approved">
                                            <div class="panel-footer">
                                                <span class="pull-left">View</span>
                                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-green">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-tasks fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div class="huge">${Pending}</div>
                                                    <div>Pending Timesheets</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="${url}uts?typ=Pending">
                                            <div class="panel-footer">
                                                <span class="pull-left">View</span>
                                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-yellow">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div class="huge">${Rejected}</div>
                                                    <div>Rejected Timesheets</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="${url}uts?typ=Rejected">
                                            <div class="panel-footer">
                                                <span class="pull-left">View</span>
                                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-red">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="fa fa-support fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div class="huge">${Outstanding}</div>
                                                    <div>Outstanding Timesheets</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="${url}uts?typ=Outstanding">
                                            <div class="panel-footer">
                                                <span class="pull-left">View</span>
                                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

            </div>


        </div>




    </body>

</html>