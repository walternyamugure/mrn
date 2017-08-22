package com.EmployeeTimeCapsule;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class unts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            if (request.getParameter("id") != null) {
                //if (request.getParameter("id").trim() == "") {
                session.setAttribute("timesheetid", request.getParameter("id"));
                //}
            }

            String timeshetID = session.getAttribute("timesheetid").toString();

            ResultSet r = null;
            ResultSet pj = null;
            int cnt = 1;
            String val;

            try {
                r = SQLConnection.GetOnePara("procGetWeekPeriods ", timeshetID);
                r.next();
                request.setAttribute("Periods", r.getString("Periods"));
                
                r = SQLConnection.GetOnePara("procGetTimesheetWeek", timeshetID);
                r.next();
                session.setAttribute("selectedwk", r.getString("WeekNumber"));

                if (request.getParameter("id") == null) {
                    r = SQLConnection.GetOnePara("procGetProjects", "--Select project--");
                    r.next();
                    request.setAttribute("prjSun", r.getString("Projects"));
                    request.setAttribute("prjMon", r.getString("Projects"));
                    request.setAttribute("prjTue", r.getString("Projects"));
                    request.setAttribute("prjWed", r.getString("Projects"));
                    request.setAttribute("prjThur", r.getString("Projects"));
                    request.setAttribute("prjFri", r.getString("Projects"));
                    request.setAttribute("prjSat", r.getString("Projects"));

                } else {

                    r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timeshetID);
                    while (r.next()) {
                        if (r.getInt("WeekDayNameID") == 1) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjSun", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskSun", pj.getString("ProjectTasks"));
                                request.setAttribute("suh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("sth", " / " + pj.getString("TaskHours") + ")");
                            }
                        } else if (r.getInt("WeekDayNameID") == 2) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjMon", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskMon", pj.getString("ProjectTasks"));
                                request.setAttribute("muh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("mth", " / " + pj.getString("TaskHours") + ")");
                            }
                        } else if (r.getInt("WeekDayNameID") == 3) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjTue", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskTue", pj.getString("ProjectTasks"));
                                request.setAttribute("tuh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("tth", " / " + pj.getString("TaskHours") + ")");
                            }
                        } else if (r.getInt("WeekDayNameID") == 4) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjWed", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskWed", pj.getString("ProjectTasks"));
                                request.setAttribute("wuh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("wth", " / " + pj.getString("TaskHours") + ")");
                            }
                        } else if (r.getInt("WeekDayNameID") == 5) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjThur", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskThur", pj.getString("ProjectTasks"));
                                request.setAttribute("thuh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("thth", " / " + pj.getString("TaskHours") + ")");
                            }
                        } else if (r.getInt("WeekDayNameID") == 6) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjFri", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskFri", pj.getString("ProjectTasks"));
                                request.setAttribute("fuh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("fth", " / " + pj.getString("TaskHours") + ")");
                            }
                        } else if (r.getInt("WeekDayNameID") == 7) {
                            val = r.getString("ProjectName") == "" ? "--Select project--" : r.getString("ProjectName");
                            pj = SQLConnection.GetOnePara("procGetProjects", val);
                            pj.next();
                            request.setAttribute("prjSat", pj.getString("Projects"));

                            if (r.getString("ProjectName") != "") {
                                pj = SQLConnection.GetTwoPara("procGetProjectTasks", r.getString("ProjectName"), r.getString("ProjectTaskName"));
                                pj.next();
                                request.setAttribute("tskSat", pj.getString("ProjectTasks"));
                                request.setAttribute("satuh", "(" + pj.getString("UsedHours"));
                                request.setAttribute("satth", " / " + pj.getString("TaskHours") + ")");
                            }
                        }
                    }
                }
                r = SQLConnection.GetEightColTwoPara("procGetTimeshetDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", timeshetID, username);
                while (r.next()) {
                    if ((r.getString("Order")).equals(Integer.toString(cnt))) {
                        request.setAttribute("Sun" + cnt, r.getString("Sun"));
                        request.setAttribute("Mon" + cnt, r.getString("Mon"));
                        request.setAttribute("Tue" + cnt, r.getString("Tue"));
                        request.setAttribute("Wed" + cnt, r.getString("Wed"));
                        request.setAttribute("Thur" + cnt, r.getString("Thur"));
                        request.setAttribute("Fri" + cnt, r.getString("Fri"));
                        request.setAttribute("Sat" + cnt, r.getString("Sat"));
                    }
                    cnt++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                r = SQLConnection.GetFiveColTwoPara("procGetTimesheetHeader ", "Order", "Status", "WeekNumber", "WeekTotal", "CostCenter", timeshetID, username);
                while (r.next()) {
                    if ((r.getString("Order")).equals("1")) {
                        request.setAttribute("tsStatus", r.getString("Status"));
                        request.setAttribute("wkNum", r.getString("WeekNumber"));
                        request.setAttribute("wkTotal", r.getString("WeekTotal"));
                        request.setAttribute("costCenter", r.getString("CostCenter"));
                    }
                    cnt++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            request.setAttribute("url", rooturl);
            session.setAttribute("ResultMessage", "</br>");
            request.getRequestDispatcher("/WEB-INF/views/unts.jsp").forward(request, response);
        } else {
            String rooturl = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/";
            response.sendRedirect(rooturl + "login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int rw = 1;
        int col = 1;
        int colTot = 0;
        int val = 0;
        ResultSet r = null;
        String[] pos = new String[7];
        String[] prj = new String[7];
        String[] tsks = new String[7];
        int[] tot = new int[8];

        prj[0] = request.getParameter("prjSun");
        prj[1] = request.getParameter("prjMon");
        prj[2] = request.getParameter("prjTue");
        prj[3] = request.getParameter("prjWed");
        prj[4] = request.getParameter("prjThur");
        prj[5] = request.getParameter("prjFri");
        prj[6] = request.getParameter("prjSat");

        tsks[0] = request.getParameter("tskSun");
        tsks[1] = request.getParameter("tskMon");
        tsks[2] = request.getParameter("tskTue");
        tsks[3] = request.getParameter("tskWed");
        tsks[4] = request.getParameter("tskThur");
        tsks[5] = request.getParameter("tskFri");
        tsks[6] = request.getParameter("tskSat");

        int cnt = 1;
        HttpSession session = request.getSession(false);

        try {
            r = SQLConnection.GetTwoPara("procAutoCreateTimesheet ", session.getAttribute("username").toString(), request.getParameter("wk"));
            r.next();
            session.setAttribute("timesheetid", r.getString("TimeSheetID"));
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

        String timeshetID = session.getAttribute("timesheetid").toString();

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {

            String username = session.getAttribute("username").toString();
            String timesheetid = session.getAttribute("timesheetid").toString();
            String tsk;

            //Check if hours for week day exceeds 16hrs  
            while (rw <= 8) {
                while (col <= 7) {
                    request.setAttribute("Sun" + rw, request.getParameter("pos" + rw + 1));
                    request.setAttribute("Mon" + rw, request.getParameter("pos" + rw + 2));
                    request.setAttribute("Tue" + rw, request.getParameter("pos" + rw + 3));
                    request.setAttribute("Wed" + rw, request.getParameter("pos" + rw + 4));
                    request.setAttribute("Thur" + rw, request.getParameter("pos" + rw + 5));
                    request.setAttribute("Fri" + rw, request.getParameter("pos" + rw + 6));
                    request.setAttribute("Sat" + rw, request.getParameter("pos" + rw + 7));
                    col++;
                }
                col = 1;
                rw++;
            }
            rw = 1;
            col = 1;
            try {
                r = SQLConnection.GetTwoPara("getTimesheetTotal", username, request.getParameter("wk"));
                while (r.next()) {
                    tot[r.getInt("WeekDayNameID")] = r.getInt("Hours");
                }

                rw = 1;
                col = 1;
                while (col <= 7) {
                    colTot = tot[col];
                    while (rw <= 8) {
                        colTot = colTot + Integer.parseInt(request.getParameter(("pos" + rw) + col));
                        rw++;
                    }
                    if (colTot > 16) {
                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjSun"));
                        r.next();
                        request.setAttribute("prjSun", r.getString("Projects"));
                        tsk = request.getParameter("tskSun") != null ? (request.getParameter("tskSun").contains("No task(s) exist") ? "" : request.getParameter("tskSun")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjSun"), tsk);
                        r.next();
                        request.setAttribute("tskSun", r.getString("ProjectTasks"));
                        request.setAttribute("suh", "(" + r.getString("UsedHours"));
                        request.setAttribute("sth", " / " + r.getString("TaskHours") + ")");

                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjMon"));
                        r.next();
                        request.setAttribute("prjMon", r.getString("Projects"));
                        tsk = request.getParameter("tskMon") != null ? (request.getParameter("tskMon").contains("No task(s) exist") ? "" : request.getParameter("tskMon")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjMon"), tsk);
                        r.next();
                        request.setAttribute("tskMon", r.getString("ProjectTasks"));
                        request.setAttribute("muh", "(" + r.getString("UsedHours"));
                        request.setAttribute("mth", " / " + r.getString("TaskHours") + ")");

                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjTue"));
                        r.next();
                        request.setAttribute("prjTue", r.getString("Projects"));
                        tsk = request.getParameter("tskTue") != null ? (request.getParameter("tskTue").contains("No task(s) exist") ? "" : request.getParameter("tskTue")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjTue"), tsk);
                        r.next();
                        request.setAttribute("tskTue", r.getString("ProjectTasks"));
                        request.setAttribute("tuh", "(" + r.getString("UsedHours"));
                        request.setAttribute("tth", " / " + r.getString("TaskHours") + ")");

                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjWed"));
                        r.next();
                        request.setAttribute("prjWed", r.getString("Projects"));
                        tsk = request.getParameter("tskWed") != null ? (request.getParameter("tskWed").contains("No task(s) exist") ? "" : request.getParameter("tskWed")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjWed"), tsk);
                        r.next();
                        request.setAttribute("tskWed", r.getString("ProjectTasks"));
                        request.setAttribute("wuh", "(" + r.getString("UsedHours"));
                        request.setAttribute("wth", " / " + r.getString("TaskHours") + ")");

                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjThur"));
                        r.next();
                        request.setAttribute("prjThur", r.getString("Projects"));
                        tsk = request.getParameter("tskThur") != null ? (request.getParameter("tskThur").contains("No task(s) exist") ? "" : request.getParameter("tskThur")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjThur"), tsk);
                        r.next();
                        request.setAttribute("tskThur", r.getString("ProjectTasks"));
                        request.setAttribute("thuh", "(" + r.getString("UsedHours"));
                        request.setAttribute("thth", " / " + r.getString("TaskHours") + ")");

                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjFri"));
                        r.next();
                        request.setAttribute("prjFri", r.getString("Projects"));
                        tsk = request.getParameter("tskFri") != null ? (request.getParameter("tskFri").contains("No task(s) exist") ? "" : request.getParameter("tskFri")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjFri"), tsk);
                        r.next();
                        request.setAttribute("tskFri", r.getString("ProjectTasks"));
                        request.setAttribute("fuh", "(" + r.getString("UsedHours"));
                        request.setAttribute("fth", " / " + r.getString("TaskHours") + ")");

                        r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjSat"));
                        r.next();
                        request.setAttribute("prjSat", r.getString("Projects"));
                        tsk = request.getParameter("tskSat") != null ? (request.getParameter("tskSat").contains("No task(s) exist") ? "" : request.getParameter("tskSat")) : "";
                        r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjSat"), tsk);
                        r.next();
                        request.setAttribute("tskSat", r.getString("ProjectTasks"));
                        request.setAttribute("satuh", "(" + r.getString("UsedHours"));
                        request.setAttribute("satth", " / " + r.getString("TaskHours") + ")");

                        session.setAttribute("ResultMessage", " <button type=\"button\" id =\"result\" class=\"btn btn-danger btn-block\">No values have been updated. Total values captured for " + unts.getWeekDayName(col) + " exceed the available hours</button>");
                        r = SQLConnection.GetOnePara("procGetWeekPeriods ", timeshetID);
                        r.next();
                        request.setAttribute("Periods", r.getString("Periods"));
                        request.getRequestDispatcher("/WEB-INF/views/unts.jsp").forward(request, response);
                    }
                    rw = 1;
                    col++;
                    colTot = 0;
                }
                rw = 1;
                col = 1;
            } catch (SQLException ex) {
                Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Original
            rw = 1;
            col = 1;
            if (request.getParameter("save") != null) {
                while (rw <= 8) {
                    while (col <= 7) {
                        pos[col - 1] = request.getParameter(("pos" + rw) + col);
                        col++;
                    }

                    try {
                        if (rw != 8) {
                            r = SQLConnection.UpdateEightPara("procUpdTimesheetDetails", username, request.getParameter("wk"), Integer.parseInt(session.getAttribute("timesheetid").toString()), rw, 0, 0, pos);
                            while (r.next()) {
                                session.setAttribute("timesheetid", r.getString("TimesheetID"));
                            }
                        } else {
                            r = SQLConnection.UpdateTwentyFivePara("procUpdProjectTimesheetDetails", username, request.getParameter("wk"), Integer.parseInt(session.getAttribute("timesheetid").toString()), rw, prj, tsks, pos);
                            while (r.next()) {
                                session.setAttribute("timesheetid", r.getString("TimesheetID"));
                                session.setAttribute("ResultMessage", r.getString("ResultMessage"));
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    col = 1;
                    rw++;
                }
            } else if (request.getParameter("sub") != null) {
                try {
                    r = SQLConnection.UpdateTwoPara("procUpdateTisheetStatus", timesheetid, "Pending");
                    r.next();
                    session.setAttribute("ResultMessage", r.getString("ResultMessage"));
                } catch (SQLException ex) {
                    Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                r = SQLConnection.GetOnePara("procGetWeekPeriods ", timeshetID);
                r.next();
                request.setAttribute("Periods", r.getString("Periods"));

                if (((session.getAttribute("selectedwk").toString() == request.getParameter("wk")) && request.getParameter("sub") == null && request.getParameter("save") == null) || session.getAttribute("selectedwk").toString() == "0") {
                    
                    session.setAttribute("selectedwk", request.getParameter("wk"));
                    rw = 1;
                    col = 1;
                    while (rw <= 8) {
                        while (col <= 7) {
                            request.setAttribute("Sun" + rw, request.getParameter("pos" + rw + 1));
                            request.setAttribute("Mon" + rw, request.getParameter("pos" + rw + 2));
                            request.setAttribute("Tue" + rw, request.getParameter("pos" + rw + 3));
                            request.setAttribute("Wed" + rw, request.getParameter("pos" + rw + 4));
                            request.setAttribute("Thur" + rw, request.getParameter("pos" + rw + 5));
                            request.setAttribute("Fri" + rw, request.getParameter("pos" + rw + 6));
                            request.setAttribute("Sat" + rw, request.getParameter("pos" + rw + 7));
                            col++;
                        }
                        col = 1;
                        rw++;
                    }
                    rw = 1;
                    col = 1;
                } else {
                    session.setAttribute("selectedwk", request.getParameter("wk"));
                    r = SQLConnection.GetEightColTwoPara("procGetTimeshetDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", session.getAttribute("timesheetid").toString(), username);
                    while (r.next()) {
                        if ((r.getString("Order")).equals(Integer.toString(cnt))) {
                            request.setAttribute("Sun" + cnt, r.getString("Sun"));
                            request.setAttribute("Mon" + cnt, r.getString("Mon"));
                            request.setAttribute("Tue" + cnt, r.getString("Tue"));
                            request.setAttribute("Wed" + cnt, r.getString("Wed"));
                            request.setAttribute("Thur" + cnt, r.getString("Thur"));
                            request.setAttribute("Fri" + cnt, r.getString("Fri"));
                            request.setAttribute("Sat" + cnt, r.getString("Sat"));
                        }
                        cnt++;
                    }
                }
                r = SQLConnection.GetFiveColTwoPara("procGetTimesheetHeader ", "Order", "Status", "WeekNumber", "WeekTotal", "CostCenter", session.getAttribute("timesheetid").toString(), username);
                while (r.next()) {
                    if ((r.getString("Order")).equals("1")) {
                        request.setAttribute("tsStatus", r.getString("Status"));
                        request.setAttribute("wkNum", r.getString("WeekNumber"));
                        request.setAttribute("wkTotal", r.getString("WeekTotal"));
                        request.setAttribute("costCenter", r.getString("CostCenter"));
                    }
                    cnt++;
                }
                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjSun"));
                r.next();
                request.setAttribute("prjSun", r.getString("Projects"));
                tsk = request.getParameter("tskSun") != null ? (request.getParameter("tskSun").contains("No task(s) exist") ? "" : request.getParameter("tskSun")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjSun"), tsk);
                r.next();
                request.setAttribute("tskSun", r.getString("ProjectTasks"));
                request.setAttribute("suh", "(" + r.getString("UsedHours"));
                request.setAttribute("sth", " / " + r.getString("TaskHours") + ")");

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjMon"));
                r.next();
                request.setAttribute("prjMon", r.getString("Projects"));
                tsk = request.getParameter("tskMon") != null ? (request.getParameter("tskMon").contains("No task(s) exist") ? "" : request.getParameter("tskMon")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjMon"), tsk);
                r.next();
                request.setAttribute("tskMon", r.getString("ProjectTasks"));
                request.setAttribute("muh", "(" + r.getString("UsedHours"));
                request.setAttribute("mth", " / " + r.getString("TaskHours") + ")");

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjTue"));
                r.next();
                request.setAttribute("prjTue", r.getString("Projects"));
                tsk = request.getParameter("tskTue") != null ? (request.getParameter("tskTue").contains("No task(s) exist") ? "" : request.getParameter("tskTue")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjTue"), tsk);
                r.next();
                request.setAttribute("tskTue", r.getString("ProjectTasks"));
                request.setAttribute("tuh", "(" + r.getString("UsedHours"));
                request.setAttribute("tth", " / " + r.getString("TaskHours") + ")");

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjWed"));
                r.next();
                request.setAttribute("prjWed", r.getString("Projects"));
                tsk = request.getParameter("tskWed") != null ? (request.getParameter("tskWed").contains("No task(s) exist") ? "" : request.getParameter("tskWed")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjWed"), tsk);
                r.next();
                request.setAttribute("tskWed", r.getString("ProjectTasks"));
                request.setAttribute("wuh", "(" + r.getString("UsedHours"));
                request.setAttribute("wth", " / " + r.getString("TaskHours") + ")");

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjThur"));
                r.next();
                request.setAttribute("prjThur", r.getString("Projects"));
                tsk = request.getParameter("tskThur") != null ? (request.getParameter("tskThur").contains("No task(s) exist") ? "" : request.getParameter("tskThur")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjThur"), tsk);
                r.next();
                request.setAttribute("tskThur", r.getString("ProjectTasks"));
                request.setAttribute("thuh", "(" + r.getString("UsedHours"));
                request.setAttribute("thth", " / " + r.getString("TaskHours") + ")");

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjFri"));
                r.next();
                request.setAttribute("prjFri", r.getString("Projects"));
                tsk = request.getParameter("tskFri") != null ? (request.getParameter("tskFri").contains("No task(s) exist") ? "" : request.getParameter("tskFri")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjFri"), tsk);
                r.next();
                request.setAttribute("tskFri", r.getString("ProjectTasks"));
                request.setAttribute("fuh", "(" + r.getString("UsedHours"));
                request.setAttribute("fth", " / " + r.getString("TaskHours") + ")");

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prjSat"));
                r.next();
                request.setAttribute("prjSat", r.getString("Projects"));
                tsk = request.getParameter("tskSat") != null ? (request.getParameter("tskSat").contains("No task(s) exist") ? "" : request.getParameter("tskSat")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prjSat"), tsk);
                r.next();
                request.setAttribute("tskSat", r.getString("ProjectTasks"));
                request.setAttribute("satuh", "(" + r.getString("UsedHours"));
                request.setAttribute("satth", " / " + r.getString("TaskHours") + ")");

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/views/unts.jsp").forward(request, response);

        } else {
            request.setAttribute("url", "Sessin has expired.Please login.");
            response.sendRedirect(request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/" + "login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public static String getWeekDayName(int x) {
        String wkDay;
        if (x == 1) {
            wkDay = "Sunday";
        } else if (x == 2) {
            wkDay = "Monday";
        } else if (x == 3) {
            wkDay = "Tuesday";
        } else if (x == 4) {
            wkDay = "Wednesday";
        } else if (x == 5) {
            wkDay = "Thursday";
        } else if (x == 6) {
            wkDay = "Friday";
        } else {
            wkDay = "Friday";
        }

        return wkDay;
    }
}
