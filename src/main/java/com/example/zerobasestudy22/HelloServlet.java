package com.example.zerobasestudy22;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.http.*;
import javax.servlet.annotation.*;



@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(message);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}