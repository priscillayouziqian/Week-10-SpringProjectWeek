package com.javaunit3.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("bestMovieService")
public class BestMovieService {
    //define a private Movie property and use Spring annotation, so that it's autowired.
//    WAY 1: @Autowired
    private Movie movie;

//   WAY 2: @Autowired (Create a setter method, the result is the same as above autowired)
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }

    //WAY 3: create a constructor, annotate the method, so that Spring will use the constructor to inject a Movie object.
    @Autowired
    public BestMovieService(@Qualifier("titanicMovie") Movie movie) {
        //use the Qualifier annotation and the default bean id for the Titanic movie to inject the Titanic movie object
        this.movie = movie;
    }

    //define a method getBestMovie() that returns the movie.
    public Movie getBestMovie(){
        return movie;
    }
}
