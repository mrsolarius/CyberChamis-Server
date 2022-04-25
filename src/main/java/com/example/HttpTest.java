package com.example;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class HttpTest {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/ping")
    String db() {
      return "pong";
    }

    @GetMapping("/db")
    String db(Map<String, Object> model, HttpServletResponse response) {
      try (Connection connection = dataSource.getConnection()) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
  
        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add("Read from DB: " + rs.getTimestamp("tick"));
        }
  
        model.put("records", output);
        stmt.close();
        connection.close();
        return "db";
      } catch (Exception e) {
        response.setStatus(500);
        try {
          response.getOutputStream().print( e.getMessage() );
        } catch (Exception e2) {
          System.err.println(e2.getMessage());
        }
        return "error";
      }
    }
  
}
