package com.javaunit3.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BestMovieService {
    //define a private Movie property and use Spring annotation, so that it's autowired.
    @Autowired
    private Movie movie;

    //define a method getBestMovie() that returns the movie.
    public Movie getBestMovie(){
        return movie;
    }
}
