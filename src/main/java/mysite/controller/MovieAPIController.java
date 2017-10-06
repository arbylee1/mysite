package mysite.controller;

import mysite.model.api.movie.MovieConnector;
import mysite.model.api.movie.User;
import mysite.model.api.movie.UserExistsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
public class MovieAPIController  {

    @RequestMapping("/api/movie/register")
    public boolean register(@RequestParam(value="username", defaultValue = "") String username,
                      @RequestParam(value="password", defaultValue = "") String password) {
        try {
            MovieConnector.getConnector().registerUser(username, password);
            return true;
        } catch (UserExistsException e) {
            return false;
        }

    }

    @RequestMapping("/api/movie/login")
    public User login(@RequestParam(value="username", defaultValue = "") String username,
                      @RequestParam(value="password", defaultValue = "") String password) {
        return null;
    }
}
