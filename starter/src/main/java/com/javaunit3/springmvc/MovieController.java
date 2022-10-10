package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

//                String movieTitle = request.getParameter("movieTitle");
//
//                model.addAttribute("BestMovieVote", movieTitle);
//
//                return "voteForBestMovie";
                String movieId = request.getParameter("movieId");
                String voterName = request.getParameter("voterName");

                Session session = sessionFactory.getCurrentSession();

                session.beginTransaction();

                MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class, Integer.parseInt(movieId));
                VoteEntity newVote = new VoteEntity();
                newVote.setVoterName(voterName);
                movieEntity.addVote(newVote);

                session.update(movieEntity);

                session.getTransaction().commit();

                return "voteForBestMovie";
        }

        @RequestMapping("/addMovieForm")
        public String addMovieForm(){
                return "addMovie";
        }

        @RequestMapping("/addMovie")
        public String addMovie(HttpServletRequest request) {
                String movieTitle = request.getParameter("movieTitle");
                String maturityRating = request.getParameter("maturityRating");
                String genre = request.getParameter("genre");

                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setTitle(movieTitle);
                movieEntity.setMaturityRating(maturityRating);
                movieEntity.setGenre(genre);

                Session session = sessionFactory.getCurrentSession();

                session.beginTransaction();

                session.save(movieEntity);

                session.getTransaction().commit();

                return "addMovie";
        }

        @RequestMapping("/voteForBestMovieForm")
        public String voteForBestMovieFormPage(Model model){


                Session session = sessionFactory.getCurrentSession();
                session.beginTransaction();

                List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();

                session.getTransaction().commit();
                model.addAttribute("movies", movieEntityList);
                return "voteForBestMovie";
        }

}
