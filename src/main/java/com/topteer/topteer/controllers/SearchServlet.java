package com.topteer.topteer.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/search")
public class SearchServlet {
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String query = req.getParameter("searchTitle");
//            List<Ad> results = DaoFactory.getAdsDao().searchByTitle(query);
            req.setAttribute("query", query);
//            req.setAttribute("ads", results);
            req.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(req, resp);
        }
    }
