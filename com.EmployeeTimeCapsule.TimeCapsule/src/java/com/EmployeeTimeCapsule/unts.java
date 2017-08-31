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

                r = SQLConnection.GetOnePara("procGetWeekDays", "0");
                r.next();
                session.setAttribute("weekDays", r.getString("WeekDays"));

                r = SQLConnection.GetOnePara("procGetProjects", "--Select project--");
                r.next();
                request.setAttribute("prj", r.getString("Projects"));

                if (request.getParameter("id") == null) {

                } else {
                    String sun = "";
                    String mon = "";
                    String tue = "";
                    String wed = "";
                    String thur = "";
                    String fri = "";
                    String sat = "";
                    r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timeshetID);
                    while (r.next()) {
                        switch (r.getInt("WeekDayNameID")) {
                            case 1:
                                sun = sun + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskSun", sun);
                                break;
                            case 2:
                                mon = mon + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskMon", mon);
                                break;
                            case 3:
                                tue = tue + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskTue", tue);
                                break;
                            case 4:
                                wed = wed + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskWed", wed);
                                break;
                            case 5:
                                thur = thur + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskThur", thur);
                                break;
                            case 6:
                                fri = fri + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskFri", fri);
                                break;
                            case 7:
                                sat = sat + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                                request.setAttribute("prjtskSat", sat);
                                break;
                            default:
                                break;
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
        int runningColTot = 0;
        String runningWkDay =  "";
        ResultSet r = null;
        String[] pos = new String[7];
        String[] prj = new String[7];
        String[] tsks = new String[7];
        String timesheetid;
        String username;
        boolean cont = false;

        int cnt = 1;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null) {
            try {
                r = SQLConnection.GetTwoPara("procAutoCreateTimesheet ", session.getAttribute("username").toString(), request.getParameter("wk"));
                r.next();
                session.setAttribute("timesheetid", r.getString("TimeSheetID"));

                username = session.getAttribute("username").toString();
                timesheetid = session.getAttribute("timesheetid").toString();
                String tsk;

                r = SQLConnection.GetOnePara("procGetWeekPeriods ", timesheetid);
                r.next();
                request.setAttribute("Periods", r.getString("Periods"));

                r = SQLConnection.GetOnePara("procGetTimesheetWeek", timesheetid);
                r.next();
                session.setAttribute("selectedwk", r.getString("WeekNumber"));

                r = SQLConnection.GetOnePara("procGetWeekDays", request.getParameter("weekday"));
                r.next();
                session.setAttribute("weekDays", r.getString("WeekDays"));

                r = SQLConnection.GetOnePara("procGetProjects", request.getParameter("prj"));
                r.next();
                request.setAttribute("prj", r.getString("Projects"));

                tsk = request.getParameter("tskSun") != null ? (request.getParameter("tskSun").contains("No task(s) exist") ? "" : request.getParameter("tskSun")) : "";
                r = SQLConnection.GetTwoPara("procGetProjectTasks", request.getParameter("prj"), tsk);
                r.next();
                request.setAttribute("tsk", r.getString("ProjectTasks"));

                while (rw <= 7) {
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

                r = SQLConnection.GetEightColTwoPara("procGetTimeshetProjectTotalDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", session.getAttribute("timesheetid").toString(), username);
                cnt = 8;
                while (r.next()) {
                    if (Integer.parseInt(r.getString("Order")) >= 8) {
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
                String sun = "";
                String mon = "";
                String tue = "";
                String wed = "";
                String thur = "";
                String fri = "";
                String sat = "";
                TimesheetProjectsTasks(timesheetid, sun, request, mon, tue, wed, thur, fri, sat);

                //Check if daily hours exceed 16hrs
                col = 1;
                rw = 1;
                while (col <= 7) {
                    colTot = 0;
                    while (rw <= 7) {
                        colTot = colTot + Integer.parseInt(request.getParameter(("pos" + rw) + col));
                        rw++;
                    }
                    try {
                        r = SQLConnection.GetEightColTwoPara("procGetTimeshetProjectTotalDetails ", "Order", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat", session.getAttribute("timesheetid").toString(), username);
                        cnt = 8;
                        while (r.next()) {
                            if (Integer.parseInt(r.getString("Order")) == 8) {
                                switch (col) {
                                    case 1:
                                        colTot = colTot + Integer.parseInt(r.getString("Sun"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 1) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                    case 2:
                                        colTot = colTot + Integer.parseInt(r.getString("Mon"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 2) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                    case 3:
                                        colTot = colTot + Integer.parseInt(r.getString("Tue"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 3) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                    case 4:
                                        colTot = colTot + Integer.parseInt(r.getString("Wed"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 4) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                    case 5:
                                        colTot = colTot + Integer.parseInt(r.getString("Thur"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 5) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                    case 6:
                                        colTot = colTot + Integer.parseInt(r.getString("Fri"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 6) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                    default:
                                        colTot = colTot + Integer.parseInt(r.getString("Sat"));
                                        if (Integer.parseInt(request.getParameter("weekday")) == 7) {
                                            colTot = colTot + Integer.parseInt(request.getParameter("prjtskhrs"));
                                        }
                                        break;
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (colTot > 16) {
                        runningColTot = colTot;
                        runningWkDay =  runningWkDay + unts.getWeekDayName(col)+ ":";
                    } 
                    rw = 1;
                    col++;
                    colTot = 0;
                }
                //Update timesheet details
                if (runningColTot > 16) {
                    session.setAttribute("ResultMessage", " <button type=\"button\" id =\"result\" class=\"btn btn-danger btn-block\">No values have been updated. Total values captured for " + runningWkDay + " exceed the available hours</button>");                       
                }
                else {
                    rw = 1;
                    col = 1;
                    if (request.getParameter("save") != null) {
                        try {
                            while (rw <= 7) {
                                while (col <= 7) {
                                    pos[col - 1] = request.getParameter(("pos" + rw) + col);
                                    col++;
                                }
                                if (rw != 8) {
                                    r = SQLConnection.UpdateEightPara("procUpdTimesheetDetails", username, request.getParameter("wk"), Integer.parseInt(session.getAttribute("timesheetid").toString()), rw, 0, 0, pos);
                                    while (r.next()) {
                                        session.setAttribute("timesheetid", r.getString("TimesheetID"));
                                        session.setAttribute("ResultMessage", r.getString("ResultMessage"));
                                    }
                                }
                                col = 1;
                                rw++;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (request.getParameter("sub") != null) {
                        try {
                            r = SQLConnection.UpdateTwoPara("procUpdateTisheetStatus", timesheetid, "Pending");
                            r.next();
                            session.setAttribute("ResultMessage", r.getString("ResultMessage"));
                        } catch (SQLException ex) {
                            Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (request.getParameter("prjsave") != null) {
                        try {
                            r = SQLConnection.UpdateFivePara("procInsertTimeSheet", timesheetid, request.getParameter("weekday"), request.getParameter("prj"), request.getParameter("tsk"), request.getParameter("prjtskhrs"));
                            r.next();
                            session.setAttribute("ResultMessage", r.getString("ResultMessage"));
                            r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timesheetid);
                        } catch (SQLException ex) {
                            Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (request.getParameter("prjremove") != null) {
                        try {
                            r = SQLConnection.UpdateFivePara("procDeleteTimeSheet", timesheetid, request.getParameter("weekday"), request.getParameter("prj"), request.getParameter("tsk"), request.getParameter("prjtskhrs"));
                            r.next();
                            session.setAttribute("ResultMessage", r.getString("ResultMessage"));
                            r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timesheetid);
                        } catch (SQLException ex) {
                            Logger.getLogger(unts.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if ((Integer.parseInt(session.getAttribute("selectedwk").toString()) == Integer.parseInt(request.getParameter("wk")) && request.getParameter("prjremove") == null && request.getParameter("prjsave") == null && request.getParameter("sub") == null && request.getParameter("save") == null) || session.getAttribute("selectedwk").toString() == "0") {

                    } else {
                        cnt = 1;
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

                        sun = "";
                        mon = "";
                        tue = "";
                        wed = "";
                        thur = "";
                        fri = "";
                        sat = "";
                        TimesheetProjectsTasks(timesheetid, sun, request, mon, tue, wed, thur, fri, sat);
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
                }
                sun = "";
                mon = "";
                tue = "";
                wed = "";
                thur = "";
                fri = "";
                sat = "";
                TimesheetProjectsTasks(timesheetid, sun, request, mon, tue, wed, thur, fri, sat);
                request.getRequestDispatcher("/WEB-INF/views/unts.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            request.setAttribute("url", "Sessin has expired.Please login.");
            response.sendRedirect(request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf("com.EmployeeTimeCapsule.TimeCapsule")) + "com.EmployeeTimeCapsule.TimeCapsule/" + "login");
        }
    }

    private void TimesheetProjectsTasks(String timesheetid, String sun, HttpServletRequest request, String mon, String tue, String wed, String thur, String fri, String sat) throws SQLException {
        ResultSet r;
        r = SQLConnection.GetOnePara("procGetCapturedProjectsAndTasks ", timesheetid);
        while (r.next()) {
            switch (r.getInt("WeekDayNameID")) {
                case 1:
                    sun = sun + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskSun", sun);
                    break;
                case 2:
                    mon = mon + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskMon", mon);
                    break;
                case 3:
                    tue = tue + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskTue", tue);
                    break;
                case 4:
                    wed = wed + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskWed", wed);
                    break;
                case 5:
                    thur = thur + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskThur", thur);
                    break;
                case 6:
                    fri = fri + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskFri", fri);
                    break;
                case 7:
                    sat = sat + r.getString("ProjectTask") + "(" + r.getString("UsedHours") + "/" + r.getString("TaskHours") + ")";
                    request.setAttribute("prjtskSat", sat);
                    break;
                default:
                    break;
            }
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
            wkDay = "Saturday";
        }

        return wkDay;
    }

    public static String getWeekDayShortName(int x) {
        String wkDay;
        if (x == 1) {
            wkDay = "Sun";
        } else if (x == 2) {
            wkDay = "Mon";
        } else if (x == 3) {
            wkDay = "Tue";
        } else if (x == 4) {
            wkDay = "Wed";
        } else if (x == 5) {
            wkDay = "Thur";
        } else if (x == 6) {
            wkDay = "Fri";
        } else {
            wkDay = "Sat";
        }

        return wkDay;
    }

    public static int getWeekDayNumber(String x) {
        int wkDay;
        if (x == "Sunday") {
            wkDay = 1;
        } else if (x == "Monday") {
            wkDay = 2;
        } else if (x == "Tuesday") {
            wkDay = 3;
        } else if (x == "Wednesday") {
            wkDay = 4;
        } else if (x == "Thursday") {
            wkDay = 5;
        } else if (x == "Friday") {
            wkDay = 6;
        } else if (x == "Saturday") {
            wkDay = 7;
        } else {
            wkDay = 0;
        }

        return wkDay;
    }
}
