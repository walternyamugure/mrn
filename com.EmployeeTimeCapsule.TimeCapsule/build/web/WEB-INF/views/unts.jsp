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
        <script>
            $(document).ready(function () {
                $('#save').on('click', function (data) {

                    var parent = document.getElementById("wrapper");
                    var child = document.getElementById("result");
                    if (typeof (parent) !== 'undefined' && parent !== null)
                    {
                        if (typeof (child) !== 'undefined' && child !== null)
                        {
                            parent.removeChild(child);
                        }
                    }
                    var rw = 1;
                    var col = 1;
                    var ele;
                    var val;
                    var uh;
                    var th;
                    var totUsedHours;
                    while (rw <= 8) {
                        col = 1;
                        while (col <= 7) {
                            ele = "pos" + rw + col;
                            val = document.getElementById(ele).value;
                            if (!isNaN(val) && val >= 0 && val < 17) {
                                document.getElementById(ele).style.removeProperty("color");
                                document.getElementById(ele).style.removeProperty("borderColor");
                                if (rw == 8) {
                                    if (col == 1) {
                                        uh = document.getElementById('suh').innerText;
                                        th = document.getElementById('sth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    } else if (col == 2) {
                                        uh = document.getElementById('muh').innerText;
                                        th = document.getElementById('mth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    } else if (col == 3) {
                                        uh = document.getElementById('tuh').innerText;
                                        th = document.getElementById('tth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    } else if (col == 4) {
                                        uh = document.getElementById('wuh').innerText;
                                        th = document.getElementById('wth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    } else if (col == 5) {
                                        uh = document.getElementById('thuh').innerText;
                                        th = document.getElementById('thth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    } else if (col == 6) {
                                        uh = document.getElementById('fuh').innerText;
                                        th = document.getElementById('fth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    } else if (col == 7) {
                                        uh = document.getElementById('satuh').innerText;
                                        th = document.getElementById('satth').innerText;
                                        uh = uh.split('(').join('');
                                        th = th.split('/').join('');
                                        th = th.split(')').join('');
                                        uh = Number(uh);
                                        th = Number(th);
                                        if ((uh + Number(val)) > th) {
                                            document.getElementById(ele).style.color = "red";
                                            document.getElementById(ele).style.borderColor = "red";
                                            data.preventDefault();
                                        }
                                    }
                                }
                            } else {
                                document.getElementById(ele).style.color = "red";
                                document.getElementById(ele).style.borderColor = "red";
                                data.preventDefault();
                            }
                            col++;
                        }
                        rw++;
                    }
                });
            });
        </script>
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
            ${ResultMessage}</br>
            <form role="form"  method="post" action="unts">
                <div id="page-wrapper">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <select class="form-control" name="wk" onchange="this.form.submit()">${Periods}</select>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Sunday</th>
                                            <th>Monday</th>
                                            <th>Tuesday</th>
                                            <th>Wednesday</th>
                                            <th>Thursday</th>
                                            <th>Friday</th>
                                            <th>Saturday</th>
                                        </tr>
                                    </thead>
                                    <tbody>          
                                        <tr>
                                            <td>Regular hours</td>
                                            <td>
                                                <input class="form-control" id="pos11" name="pos11" type="text" value=${Sun1}></td>
                                            <td>
                                                <input class="form-control" id="pos12" name="pos12" type="text" value=${Mon1}></td>
                                            <td>
                                                <input class="form-control" id="pos13" name="pos13" type="text" value=${Tue1}></td>
                                            <td>
                                                <input class="form-control" id="pos14" name="pos14" type="text" value=${Wed1}></td>
                                            <td>
                                                <input class="form-control" id="pos15" name="pos15" type="text" value=${Thur1}></td>
                                            <td>
                                                <input class="form-control" id="pos16" name="pos16" type="text" value=${Fri1}></td>
                                            <td>
                                                <input class="form-control" id="pos17" name="pos17" type="text" value=${Sat1}></td>
                                        </tr>
                                        <tr>
                                            <td>Overtime</td>
                                            <td>
                                                <input class="form-control" id="pos21" name="pos21" type="text" value=${Sun2}></td>
                                            <td>
                                                <input class="form-control" id="pos22" name="pos22" type="text" value=${Mon2}></td>
                                            <td>
                                                <input class="form-control" id="pos23" name="pos23" type="text" value=${Tue2}></td>
                                            <td>
                                                <input class="form-control" id="pos24"  name="pos24" type="text" value=${Wed2}></td>
                                            <td>
                                                <input class="form-control" id="pos25"  name="pos25" type="text" value=${Thur2}></td>
                                            <td>
                                                <input class="form-control" id="pos26"  name="pos26" type="text" value=${Fri2}></td>
                                            <td>
                                                <input class="form-control" id="pos27"  name="pos27" type="text" value=${Sat2}></td>
                                        </tr>            
                                        <tr>
                                            <td>Sick</td>
                                            <td>
                                                <input class="form-control"  id="pos31" name="pos31" type="text" value=${Sun3}></td>
                                            <td>
                                                <input class="form-control"  id="pos32"  name="pos32" type="text" value=${Mon3}></td>
                                            <td>
                                                <input class="form-control"  id="pos33"  name="pos33" type="text" value=${Tue3}></td>
                                            <td>
                                                <input class="form-control"  id="pos34"  name="pos34" type="text" value=${Wed3}></td>
                                            <td>
                                                <input class="form-control"  id="pos35"  name="pos35" type="text" value=${Thur3}></td>
                                            <td>
                                                <input class="form-control"  id="pos36"  name="pos36" type="text" value=${Fri3}></td>
                                            <td>
                                                <input class="form-control"  id="pos37"  name="pos37" type="text" value=${Sat3}></td>
                                        </tr>
                                        <tr>
                                            <td>Vacation</td>
                                            <td>
                                                <input class="form-control"  id="pos41"  name="pos41" type="text" value=${Sun4}></td>
                                            <td>
                                                <input class="form-control"  id="pos42"  name="pos42" type="text" value=${Mon4}></td>
                                            <td>
                                                <input class="form-control"  id="pos43"  name="pos43" type="text" value=${Tue4}></td>
                                            <td>
                                                <input class="form-control"  id="pos44"  name="pos44" type="text" value=${Wed4}></td>
                                            <td>
                                                <input class="form-control"  id="pos45"  name="pos45" type="text" value=${Thur4}></td>
                                            <td>
                                                <input class="form-control"  id="pos46"  name="pos46" type="text" value=${Fri4}></td>
                                            <td>
                                                <input class="form-control"  id="pos47"  name="pos47" type="text" value=${Sat4}></td>
                                        </tr>            
                                        <tr>
                                            <td>Public holiday</td>
                                            <td>
                                                <input class="form-control"  id="pos51"   name="pos51" type="text" value=${Sun5}></td>
                                            <td>
                                                <input class="form-control"  id="pos52"   name="pos52" type="text" value=${Mon5}></td>
                                            <td>
                                                <input class="form-control"  id="pos53"   name="pos53" type="text" value=${Tue5}></td>
                                            <td>
                                                <input class="form-control"  id="pos54"   name="pos54" type="text" value=${Wed5}></td>
                                            <td>
                                                <input class="form-control"  id="pos55"   name="pos55" type="text" value=${Thur5}></td>
                                            <td>
                                                <input class="form-control"  id="pos56"   name="pos56" type="text" value=${Fri5}></td>
                                            <td>
                                                <input class="form-control"  id="pos57"   name="pos57" type="text" value=${Sat5}></td>
                                        </tr>
                                        <tr>
                                            <td>Unpaid leave</td>
                                            <td>
                                                <input class="form-control"  id="pos61" name="pos61" type="text" value=${Sun6}></td>
                                            <td>
                                                <input class="form-control"  id="pos62" name="pos62" type="text" value=${Mon6}></td>
                                            <td>
                                                <input class="form-control"  id="pos63" name="pos63" type="text" value=${Tue6}></td>
                                            <td>
                                                <input class="form-control"  id="pos64" name="pos64" type="text" value=${Wed6}></td>
                                            <td>
                                                <input class="form-control"  id="pos65" name="pos65" type="text" value=${Thur6}></td>
                                            <td>
                                                <input class="form-control"  id="pos66" name="pos66" type="text" value=${Fri6}></td>
                                            <td>
                                                <input class="form-control"  id="pos67" name="pos67" type="text" value=${Sat6}></td>
                                        </tr>
                                        <tr>
                                            <td>Other leave</td>
                                            <td>
                                                <input class="form-control"  id="pos71" name="pos71" type="text" value=${Sun7}></td>
                                            <td>
                                                <input class="form-control"  id="pos72" name="pos72" type="text" value=${Mon7}></td>
                                            <td>
                                                <input class="form-control"  id="pos73" name="pos73" type="text" value=${Tue7}></td>
                                            <td>
                                                <input class="form-control"  id="pos74" name="pos74" type="text" value=${Wed7}></td>
                                            <td>
                                                <input class="form-control"  id="pos75" name="pos75" type="text" value=${Thur7}></td>
                                            <td>
                                                <input class="form-control"  id="pos76" name="pos76" type="text" value=${Fri7}></td>
                                            <td>
                                                <input class="form-control"  id="pos77" name="pos77" type="text" value=${Sat7}></td>
                                        </tr>
                                        <tr>
                                            <td>Project</td>
                                            <td>
                                                <select name="prjSun" class="form-control" onchange="this.form.submit()">
                                                    ${prjSun}
                                                </select><br /><select name="tskSun" class="form-control" onchange="this.form.submit()">
                                                    ${tskSun}
                                                </select> <span style="color:grey; font-size: small;">
                                                    <label id="suh">${suh}</label>
                                                    <label id="sth">${sth}</label>
                                                </span><br /><input class="form-control" id="pos81" name="pos81" type="text" value=${Sun8} /></td>
                                            <td>
                                                <select  name="prjMon" class="form-control" onchange="this.form.submit()">
                                                    ${prjMon}
                                                </select><br /><select name="tskMon" class="form-control" onchange="this.form.submit()">
                                                    ${tskMon}
                                                </select><span style="color:grey; font-size: small;">
                                                    <label id="muh"> ${muh}</label>
                                                    <label id="mth"> ${mth}</label>
                                                </span><br /><input class="form-control" id="pos82" name="pos82" type="text" value=${Mon8} /></td>
                                            <td>
                                                <select  name="prjTue" class="form-control" onchange="this.form.submit()">
                                                    ${prjTue}
                                                </select><br /><select name="tskTue" class="form-control" onchange="this.form.submit()">
                                                    ${tskTue}
                                                </select><span style="color:grey; font-size: small;">
                                                    <label id="tuh">${tuh}</label>
                                                    <label id="tth">${tth}</label>
                                                </span><br /> <input class="form-control"  id="pos83" name="pos83" type="text" value=${Tue8} /></td>
                                            <td>
                                                <select  name="prjWed" class="form-control" onchange="this.form.submit()">
                                                    ${prjWed}
                                                </select><br /><select name="tskWed" class="form-control" onchange="this.form.submit()">
                                                    ${tskWed}
                                                </select> <span style="color:grey; font-size: small;">
                                                    <label id="wuh">${wuh}</label>
                                                    <label id="wth">${wth}</label>
                                                </span><br /><input class="form-control"  id="pos84" name="pos84" type="text" value=${Wed8} /></td>
                                            <td>
                                                <select  name="prjThur" class="form-control" onchange="this.form.submit()">
                                                    ${prjThur}
                                                </select><br /><select name="tskThur" class="form-control" onchange="this.form.submit()">
                                                    ${tskThur}
                                                </select> <span style="color:grey; font-size: small;">
                                                    <label id="thuh">${thuh}</label>
                                                    <label id="thth">${thth}</label>
                                                </span><br /><input class="form-control"  id="pos85" name="pos85" type="text" value=${Thur8} /></td>
                                            <td>
                                                <select  name="prjFri" class="form-control" onchange="this.form.submit()" >
                                                    ${prjFri}
                                                </select><br /><select name="tskFri" class="form-control" onchange="this.form.submit()">
                                                    ${tskFri}
                                                </select> <span style="color:grey; font-size: small;">
                                                    <label id="fuh">${fuh}</label>
                                                    <label id="fth">${fth}</label>
                                                </span><br /><input class="form-control" id="pos86" name="pos86" type="text" value=${Fri8} /></td>
                                            <td>
                                                <select  name="prjSat" class="form-control" onchange="this.form.submit()" onchange="this.form.submit()">
                                                    ${prjSat}
                                                </select><br /><select name="tskSat" class="form-control" onchange="this.form.submit()">
                                                    ${tskSat}
                                                </select> <span style="color:grey; font-size: small;">
                                                    <label id="satuh">${satuh}</label>
                                                    <label id="satth">${satth}</label>
                                                </span><br /><input class="form-control" id="pos87" name="pos87" type="text" value=${Sat8} /></td>
                                        </tr>                                           
                                        <tr>
                                            <td>Total</td>
                                            <td>
                                                <input class="form-control" name="totsun" type="text" disabled value=${Sun9} ></td>
                                            <td>
                                                <input class="form-control" name="totmon" type="text" disabled value=${Mon9} ></td>
                                            <td>
                                                <input class="form-control" name="tottue" type="text" disabled value=${Tue9} ></td>
                                            <td>
                                                <input class="form-control" name="totwed" type="text" disabled value=${Wed9} ></td>
                                            <td>
                                                <input class="form-control" name="totthur" type="text" disabled value=${Thur9} ></td>
                                            <td>
                                                <input class="form-control" name="totfri" type="text" disabled value=${Fri9} ></td>
                                            <td>
                                                <input class="form-control" name="totsat" type="text" disabled value=${Sat9} ></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <table class="table">
                                    <tr>
                                        <td><b>Timesheet status</b></td>
                                        <td>${tsStatus}</td>
                                        <td><b>Week</b></td>
                                        <td>${wkNum}</td>
                                        <td><b>Weekly Total</b></td>
                                        <td>${wkTotal}</td>
                                        <td><b>Cost Center</b></td>
                                        <td>${costCenter}</td>
                                    </tr>
                                </table>
                                <table class="table">
                                    <tr>
                                        <td>
                                            <input type="submit" id="save" name="save" value="Save" class="btn btn-primary btn-lg"/>  
                                        </td>
                                        <td>
                                            <input type="submit" id="sub" name="sub" value="Submit" class="btn btn-primary btn-lg"/>  
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>           
    </body>
</html>

