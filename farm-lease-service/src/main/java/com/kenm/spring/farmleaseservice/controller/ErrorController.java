package com.kenm.spring.farmleaseservice.controller;

//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//public class ErrorController implements ErrorController {
//
//    @GetMapping("/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//                model.addAttribute("errorMessage", "Page not found");
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                model.addAttribute("errorMessage", "Internal server error");
//            } else {
//                model.addAttribute("errorMessage", "Unexpected error");
//}
//        } else {
//            model.addAttribute("errorMessage", "Unexpected error");
//        }
//        return "error";
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//}