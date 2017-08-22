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
        
            <link href="css/jquery-ui.theme.css" rel="stylesheet">
        <script src="js/jquery-3.2.1.js"></script>  
        <script src="js/jquery-ui.js"></script>

        <script type="text/javascript">
            $(function ()
            {
                $('#date-pick').datepicker();
            });
        </script>
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
                            ${ResultMessage}
                            <form role="form"  method="post" action="mup">
                                <select name="prjts" class="form-control"  onchange="this.form.submit()">
                                    ${Projects}
                                </select>
                               <br />
                                <table class="table">
                                    <tr>
                                        <td>Project Name</td>
                                        <td>
                                            <input name="prjtnm" class="form-control" value="${ProjectName}"/></td>

                                    </tr>
                                    <tr>
                                        <td>Project Owner</td>
                                        <td>
                                            <input name="prjtown" class="form-control" value="${ProjectOwner}"/></td>

                                    </tr>
                                    <tr>
                                        <td>Project Cost Center</td>
                                        <td>
                                            <input name="prjtcc" class="form-control" value="${ProjectCostCenter}"/></td>

                                    </tr>
                                    <tr>
                                        <td>Status</td>
                                        <td>
                                            <div name="prjtsts" class="form-group">
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
                                                <select name="prjtsks" class="form-control"  onchange="this.form.submit()">
                                    ${ProjectTasks}
                                </select>
                               <br />
                                     <table class="table">
                                    <tr>
                                        <td>Task Name</td>
                                        <td>
                                            <input name="tskn" class="form-control" value="${ProjectTaskName}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Task Hours</td>
                                        <td>
                                            <input name="tskhrs" class="form-control"  value="${ProjectTaskHours}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Planned Start Date</td>
                                        <td>
                                             <input name="stdt" type="text" id="date-pick" class="form-control" value="${ProjectTaskStartDate}"/>
                                             
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="submit" name="remove" value="Remove task" class="btn btn-primary btn-sm"/>                                            
                                    </tr>
                                     <tr><td></td><td><input type="submit" name="save" value="Save" class="btn btn-primary btn-bg"/></td></tr>
                                </table>
                                           
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
