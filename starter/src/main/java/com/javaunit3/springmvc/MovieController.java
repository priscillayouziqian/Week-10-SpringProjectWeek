package com.javaunit3.springmvc;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MovieController {

        @Autowired
        private BestMovieService bestMovieService;

//        @Autowired
//        private SessionFactory sessionFactory;

        @RequestMapping("/")
        public String getIndexPage()
        {
            return "index";
        }

        @RequestMapping("/bestMovie")
        public String getBestMoviePage(Model model){ //with a Spring Model parameter
                model.addAttribute("BestMovie", bestMovieService.getBestMovie().getTitle()); //add an attribute to the model named "BestMovie"
                //use the bestMovieService object to set it the best movie's title.
                return "bestMovie"; //the name of corresponding html page we will create.
        }

        @RequestMapping("/voteForBestMovieForm")
        public String voteForBestMovieFormPage() {
                return "voteForBestMovie";
        }

        @RequestMapping("/voteForBestMovie")
        public String voteForBestMovie(HttpServletRequest request, Model model) {

                String movieTitle = request.getParameter("movieTitle");

                model.addAttribute("BestMovieVote", movieTitle);

                return "voteForBestMovie";
        }

}
