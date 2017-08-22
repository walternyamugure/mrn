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

        <%--Graphs%--%>
        <link href="css/morris.css" rel="stylesheet">
        <script src="js/jquery-3.2.1.min.js" ></script>
        <script src="js/morris.min.js" ></script>
        <script src="js/raphael.min.js" ></script>

    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${url}dashmng">Employee Time Capsule System</a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li><a href="${login}login"><i class="fa fa-sign-out fa-fw"></i>Logout</a>
                    </li>
                </ul>
                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <i class="fa fa-table fa-fw"></i>Manage Project(s)
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a href="${url}mnp">Create Projects</a>
                                    </li>
                                    <li>
                                        <a href="${url}mnt">Create Tasks</a>
                                    </li>
                                    <li>
                                        <a href="${url}mup">Update Project</a>
                                    </li>

                                    <li>
                                        <a href="${url}map">Assign MyProjects</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <i class="fa fa-edit fa-fw"></i>Reports
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a href="${url}mrpp">Projects Progress</a>
                                    </li>
                                    <li>
                                        <a href=${url}mrps>Project status</a>
                                    </li>
                                    <li>
                                        <a href="${url}mrcp">Completed Project(s)</a>
                                    </li>
                                    <li>
                                        <a href="${url}mrpvr">Projects vs Resources</a>
                                    </li>
                                    <li>
                                        <a href="${url}mrrb">Monthly Resources Budget</a>
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
                            <form role="form"  method="post" action="mrps">
                                <br/><br/>
                                <select name="pjtn" class="form-control" onchange="this.form.submit()">
                                    ${Projects}
                                </select>
                                <div id="mytasks" style="height: 100%;"></div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Task Name</th>
                                            <th>Budget Hours</th>
                                            <th>Hours Used</th>
                                            <th>% Completion</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        ${ReportTasksProgress}
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        Morris.Donut({
            element: 'mytasks',
            data: [
                ${Tasks}
            ],
            formatter: function (x) {
                return x + "%"
            }
        }).on('click', function (i, row) {
            console.log(i, row);
        });
    </script>
</html>
