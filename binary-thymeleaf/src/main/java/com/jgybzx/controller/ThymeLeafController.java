package com.jgybzx.controller;

import com.jgybzx.entity.Toy;
import com.jgybzx.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/14 10:36
 */
@Controller
@RequestMapping("thymeleaf")
public class ThymeLeafController {
    @RequestMapping("test")
    public String test(Model model) {
        model.addAttribute("name", "Jack,Tom");
        model.addAttribute("name2", "<font color=\"red\">Jack</font>");
        Toy toy = new Toy("迪迦奥特曼", 189.3D);
        User user = new User("大古", 10, toy);
        model.addAttribute("user", user);
        model.addAttribute("today", new Date());
        model.addAttribute("flag", true);
        List<Toy> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new Toy("玩具" + i, i));
        }
        model.addAttribute("toyList",list);
        return "user";
    }
}
