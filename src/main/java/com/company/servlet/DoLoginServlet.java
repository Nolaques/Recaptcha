package com.company.servlet;

import com.company.recaptcha.VerifyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/doLogin")
public class DoLoginServlet extends HttpServlet{

    private static final long serialVersionUID = 958900029856081978L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean valid = true;
        String errorString = null;

        //check userName & password
        if (!"tom".equals(userName) || !"tom001".equals(password)){
            valid = false;
            errorString = "UserName or Password is invalid";
        }

        if (valid){
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            System.out.println("gRecaptchaResponse="+ gRecaptchaResponse);

            //verify CAPTCHA
            valid = VerifyUtils.verify(gRecaptchaResponse);
            if (!valid){
                errorString = "Captcha is invalid!";
            }

        }

        if (!valid){
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher =
                    request.getServletContext().getRequestDispatcher("/views/loginView.jsp");
            dispatcher.forward(request,response);
            return;
        }else {
            request.getSession().setAttribute("loginedUser", userName);
            //redirect to /userInfo
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
