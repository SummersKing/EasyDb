package org.summer.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.summer.service.CompanyService;
@Controller
@RequestMapping("/easyDb")
public class IndexController {
@Autowired
    private  CompanyService cs;
    @GetMapping("/log")
    public  String login() {
        return "login";
    }

    @GetMapping("/testEq")
    public @ResponseBody  String testEq() {
        System.out.println("testEq()");
        return cs.testEq();
    }
        @GetMapping("/testWriteBatch")
    public @ResponseBody  String testWriteBatch() {
            System.out.println("testPostBatch()");
        return cs.testPutBatch();
    }

    @GetMapping("/testWrite")
    public @ResponseBody  String testWrite() {
        System.out.println("testPost()");
        return cs.testPut();

    }

    @GetMapping("/testLike")
    public @ResponseBody  String testLike() {
        System.out.println("testLike()");

        return   cs.testLike();
    }

    @GetMapping("/testPostBatch")
    public @ResponseBody  String testPostBatch() {
        System.out.println("testPostBatch()");

        return   cs.testPostBatch();
    }

    @GetMapping("/testPost")
    public @ResponseBody  String testPost() {
        System.out.println("testPost()");

        return   cs.testPost();
    }
    @GetMapping("/testDel")
    public @ResponseBody  String testDel() {
        System.out.println("testDel()");

        return   cs.testDel();
    }

    @GetMapping("/testDelBatch")
    public @ResponseBody  String testDelBatch() {
        System.out.println("testDelBatch()");

        return   cs.testDelBatch();
    }
    @GetMapping("/testMultipe")
    public @ResponseBody  String testMultipe() {
        System.out.println("testMultipe()");

        return   cs.testMultipe();
    }

    @GetMapping("/testQuickEq")
    public @ResponseBody  String testQuickEq() {
        System.out.println("testQuickEq()");

        return   cs.testEq1();
    }

    @GetMapping("/testGetWrapper")
    public @ResponseBody  String testGetWrapper() {
        System.out.println("testGetWrapper()");

        return   cs.testGetWrapper();
    }







}
