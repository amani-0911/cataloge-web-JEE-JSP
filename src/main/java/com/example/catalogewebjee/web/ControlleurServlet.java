package com.example.catalogewebjee.web;

import com.example.catalogewebjee.metier.Product;
import com.example.catalogewebjee.metier.ProductService;
import com.example.catalogewebjee.metier.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ControlleurServlet extends HttpServlet {
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        productService=new ProductServiceImpl();
        productService.initCataloge();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getServletPath();
        req.setAttribute("mode","All");
        if (path.equals("/index.do") && req.getMethod().equals("GET")) {
            List<Product> products=productService.findAll();
            req.setAttribute("listProducts",products);
            req.getRequestDispatcher("views/products.jsp")
                    .forward(req,resp);

        }
      else  if (path.equals("/search.do") && req.getMethod().equals("GET")) {
            req.setAttribute("mode","search");
          String keyword=req.getParameter("Keyword");
            List<Product> products = productService.findByName(keyword);
            req.setAttribute("listProducts", products);
           req.setAttribute("keyword",keyword);
            req.getRequestDispatcher("views/products.jsp")
                    .forward(req, resp);
        }

      else  if (path.equals("/save.do") && req.getMethod().equals("POST")) {
            req.setAttribute("mode","save");
          String name=req.getParameter("name");
            double price= Double.parseDouble(req.getParameter("price"));

            Product product=new Product(null,name,price);
            productService.save(product);
            List<Product> products=productService.findAll();
              req.setAttribute("listProducts", products);
            req.getRequestDispatcher("views/products.jsp")
                    .forward(req, resp);
        }
        else  if (path.equals("/delete.do") && req.getMethod().equals("GET")) {
            req.setAttribute("mode","delete");
            long id= Long.parseLong(req.getParameter("id"));
            String keyword=req.getParameter("Keyword");
             productService.delete(id);
             resp.sendRedirect("search.do?keyword="+keyword);
               }
        else  if (path.equals("/edit.do") && req.getMethod().equals("GET")) {
            long id= Long.parseLong(req.getParameter("id"));

         Product product=productService.findById(id);
         req.setAttribute("product",product);
         req.setAttribute("mode","edit");
            List<Product> products=productService.findAll();
            req.setAttribute("listProducts", products);
            req.getRequestDispatcher("views/products.jsp")
                    .forward(req, resp);
        }
        else  if (path.equals("/update.do") && req.getMethod().equals("POST")) {
           req.setAttribute("mode","update");
            long id= Long.parseLong(req.getParameter("id"));
            String name=req.getParameter("name");
            double price= Double.parseDouble(req.getParameter("price"));

            Product product=new Product(id,name,price);
            productService.updateProduct(product);
            List<Product> products=productService.findAll();
            req.setAttribute("listProducts", products);
            req.getRequestDispatcher("views/products.jsp")
                    .forward(req, resp);
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
