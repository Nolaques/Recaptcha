package com.company.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userInfo")
public class UserInfoServlet extends HttpServlet{

    private static final long serialVersionUID = 958900029856081978L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginedUser") == null){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }

        RequestDispatcher dispatcher =
                req.getServletContext().getRequestDispatcher("/views/userInfoView.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
