package com.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


import java.io.IOException;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String timezoneParam = req.getParameter("timezone");

        String formattedTime = formatTimeWithTimeZone(timezoneParam);

        resp.getWriter().write(formattedTime);
        resp.getWriter().close();
    }

    private String formatTimeWithTimeZone(String timezoneParam) {
        OffsetDateTime currentTime = OffsetDateTime.now();
        String timezone = "UTC";
        if (timezoneParam != null) {
            int offsetHours = Integer.parseInt(timezoneParam.substring(3).trim());
            currentTime = OffsetDateTime.now(ZoneOffset.ofHours(offsetHours));
            timezone = timezoneParam.replace(" ","+");
        }
        return currentTime.format(FORMATTER) + timezone;
    }
}
