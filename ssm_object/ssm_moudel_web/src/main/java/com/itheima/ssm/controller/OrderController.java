package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(required = true,name = "page",defaultValue = "1") int page,
                                @RequestParam(required = true,defaultValue = "4") int size) throws Exception {
        //这里实际返回的是List<Page>,源码
        List<Orders> orders = ordersService.findAll(page,size);
        //PageInfo就是一个PageBean，有一个结果集属性
        PageInfo pageInfo = new PageInfo(orders);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView orderById(String id){
        ModelAndView mv = new ModelAndView();
        Orders order = ordersService.OrderById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }


}
