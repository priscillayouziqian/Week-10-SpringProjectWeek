package com.javaunit3.springmvc;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

        @Autowired
        private BestMovieService bestMovieService;

        @Autowired
        private SessionFactory sessionFactory;

        @RequestMapping("/")
        public String getIndexPage()
        {
            return "index";
        }

}
