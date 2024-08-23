package com.example.phongtro.controllers;

import com.example.phongtro.entity.PaymentMethod;
import com.example.phongtro.entity.RoomRentals;
import com.example.phongtro.services.RoomService;
import com.example.phongtro.services.PaymentMethodService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RoomController", urlPatterns = {"/rooms/*"})
public class RoomController extends HttpServlet {
    private RoomService roomService;
    private PaymentMethodService paymentMethodService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            this.roomService = new RoomService();
        } catch (SQLException e) {
            throw new ServletException("SQLException in RoomService initialization", e);
        }

        try {
            this.paymentMethodService = new PaymentMethodService();
        } catch (SQLException e) {
            throw new ServletException("SQLException in PaymentMethodService initialization", e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderListRooms(req, resp);
            } else {
                switch (url) {
                    case "/delete":
                        this.roomService.deleteRooms(req, resp);
                        resp.sendRedirect("/rooms");
                        break;
                    case "/create":
                        this.renderPageCreate(req, resp);
                        break;
                    case "/search":
                        this.renderSearchPage(req, resp);
                        break;
                    case "/update":
                        this.renderPageUpdate(req, resp);
                        break;
                    default:
                        // Handle 404 or default case
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            switch (url) {
                case "/create":
                    this.roomService.createRoom(req, resp);
                    resp.sendRedirect("/rooms");
                    break;
                case "/update":
                    this.roomService.updateRoom(req, resp);
                    resp.sendRedirect("/rooms");
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderListRooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<RoomRentals> rooms = this.roomService.getAllRooms();
        request.setAttribute("rooms", rooms);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void renderPageCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<PaymentMethod> paymentMethods = this.paymentMethodService.getAllPaymentMethods();
        request.setAttribute("paymentMethods", paymentMethods);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void renderSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<RoomRentals> rooms = this.roomService.searchRooms(request);
        request.setAttribute("rooms", rooms);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void renderPageUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RoomRentals room = this.roomService.findRoomById(request);
        List<PaymentMethod> paymentMethods = this.paymentMethodService.getAllPaymentMethods();
        request.setAttribute("room", room);
        request.setAttribute("paymentMethods", paymentMethods);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/update.jsp");
        requestDispatcher.forward(request, response);
    }
}
