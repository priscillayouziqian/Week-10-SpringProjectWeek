package com.javaunit3.springmvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//add annotation to MovieApp class, so that the project package is scanned for spring components.
@ComponentScan
public class MovieApp {
    public static void main(String[] args){
        //get application context
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MovieApp.class);

        //use application context, get the best movie service using the default bean id (since we didn't spedify a bean id, the id will simply be the class name of the bean starting with a lover case).
        BestMovieService bestMovieService = applicationContext.getBean("bestMovieService", BestMovieService.class);

        //Using the best movie service, get the best movie
        Movie bestMovie = bestMovieService.getBestMovie();

        //Print out the title, maturity rating, and genre of the best movie
        System.out.println("Title: " + bestMovie.getTitle());
        System.out.println("Maturity Rating: " + bestMovie.getMaturityRating());
        System.out.println("Genre: " + bestMovie.getGenre());
    }
}


