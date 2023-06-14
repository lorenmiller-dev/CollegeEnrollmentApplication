package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class EnrollmentApplication {
    public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/course_selection";
            String username = "root";
            String password = "Sggfge44!";

            try {
                // Establish the connection
                Connection connection = DriverManager.getConnection(url, username, password);

                // Create a statement
                Statement statement = connection.createStatement();

                // Execute a query
                String query = "SELECT * FROM courses";
                ResultSet resultSet = statement.executeQuery(query);

                // Display the courses
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    String courseName = resultSet.getString("course_name");
                    int courseCredits = resultSet.getInt("credits");

                    System.out.println("Course ID: " + courseId);
                    System.out.println("Course Name: " + courseName);
                    System.out.println("Credits: " + courseCredits);
                    System.out.println("-------------------------------------");
                }

                // Close the resources
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


