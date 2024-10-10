
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.time.*;
import com.opencsv.CSVReader;

import People.Student;

import javax.swing.plaf.nimbus.State;


public class Main {

    public static void main (String [] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/java_oop_crud";
        String username = "java_user";
        String password = "eljava";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = DriverManager.getConnection(url, username, password);

        System.out.println("Mysql database Connection established successfully");




        Student st = new Student();



            Scanner input = new Scanner(System.in);

            int option = 0;

            do {

                menu();
                option = input.nextInt();

                switch (option) {

                    case 1:

                        try {
                            System.out.println("What is the firstName name of the student: ");
                            String fName = input.nextLine();
                            input.nextLine();

                            System.out.println("What is the lastName name of the student: ");
                            String lName = input.nextLine();

                            System.out.println("What is the date of birth (YYYY-MM-DD): ");
                            LocalDate dob = LocalDate.parse(input.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);  // Use nextLine() for date

                            int age = LocalDate.now().getYear() - dob.getYear();

                            System.out.println("What is the school name: ");
                            String school = input.nextLine();

                            System.out.println("What is the combination: ");
                            String combination = input.nextLine();

                            System.out.println("What is the level: ");
                            int level = input.nextInt();
                            input.nextLine();

                            Statement stmt = conn.createStatement();

                            int result = stmt.executeUpdate(
                                    "INSERT INTO student (firstName, lastName, age, school, combination, level) VALUES ('" + fName + "', '" + lName + "', " + age + ", '" + school + "', '" + combination + "', " + level + ")"
                            );

                            if (result > 0) {
                                System.out.println("Student inserted successfully");
                            } else {
                                System.out.println("Student not inserted successfully");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        break;

                    case 2:

                        try {
                            String sql = "SELECT * FROM student";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            ResultSet res = statement.executeQuery();

                            // Check if there are any results
                            if (!res.isBeforeFirst()) {
                                System.out.println("No students found.");
                            } else {
                                while (res.next()) {

                                    int id = res.getInt("id");
                                    String firstName = res.getString("firstName");
                                    String lastName = res.getString("lastName");
                                    int age = res.getInt("age");
                                    String school = res.getString("school");
                                    String combination = res.getString("combination");
                                    int level = res.getInt("level");


                                    System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName +
                                            ", Age: " + age + ", School: " + school + ", Combination: " + combination +
                                            ", Level: " + level);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;


                    case 3:
                        try {
                            System.out.println("What is the id of the student: ");
                            int id = input.nextInt();
                            input.nextLine();

                            String sql = "SELECT * FROM student WHERE id = ?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setInt(1, id);

                            ResultSet res = statement.executeQuery();

                            if (res.next()) {

                                String firstName = res.getString("firstName");
                                String lastName = res.getString("lastName");
                                int age = res.getInt("age");
                                String school = res.getString("school");
                                String combination = res.getString("combination");
                                int level = res.getInt("level");


                                System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName +
                                        ", Age: " + age + ", School: " + school + ", Combination: " + combination +
                                        ", Level: " + level);
                            } else {
                                System.out.println("No student found with ID: " + id);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;


                    case 4:
                        try {
                            System.out.println("What is the id of the student you want to update: ");
                            int id = input.nextInt();
                            input.nextLine(); // Consume the leftover newline

                            System.out.println("What is the new first name of the student: ");
                            String newFirstName = input.nextLine();

                            System.out.println("What is the new last name of the student: ");
                            String newLastName = input.nextLine();

                            System.out.println("What is the new date of birth (YYYY-MM-DD): ");
                            String newDobString = input.nextLine();  // Get the date string
                            LocalDate newDob = LocalDate.parse(newDobString, DateTimeFormatter.ISO_LOCAL_DATE);  // Parse the date
                            int newAge = LocalDate.now().getYear() - newDob.getYear();  // Calculate new age

                            System.out.println("What is the new school name: ");
                            String newSchool = input.nextLine();

                            System.out.println("What is the new combination: ");
                            String newCombination = input.nextLine();

                            System.out.println("What is the new level: ");
                            int newLevel = input.nextInt();
                            input.nextLine();  // Consume the leftover newline

                            // SQL query to update the student record
                            String sql = "UPDATE student SET firstName = ?, lastName = ?, age = ?, school = ?, combination = ?, level = ? WHERE id = ?";

                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, newFirstName);
                            statement.setString(2, newLastName);
                            statement.setInt(3, newAge);
                            statement.setString(4, newSchool);
                            statement.setString(5, newCombination);
                            statement.setInt(6, newLevel);
                            statement.setInt(7, id);

                            int rowsUpdated = statement.executeUpdate();

                            if (rowsUpdated > 0) {
                                System.out.println("Student record updated successfully.");
                            } else {
                                System.out.println("Error: No student found with ID " + id);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;


                    case 5:

                        try {
                            System.out.println("What is the id of the student: ");

                            int id = input.nextInt();

                            String sql = "DELETE FROM student WHERE id = ?";

                            PreparedStatement statement = conn.prepareStatement(sql);

                            statement.setInt(1, id);

                            statement.executeUpdate();

                            System.out.println("Student Has Been deleted successfully");


                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        break;

                    case 6:
                        try {
                            String CSVFilePath = "/home/humura/Documents/DB_Interaction--File_handle/src/ReadFile.csv";
                            String insertQuery = "INSERT INTO student(firstName, lastName, age, school, combination, level) VALUES (?, ?, ?, ?, ?, ?)";

                            CSVReader reader = new CSVReader(new FileReader(CSVFilePath));
                            PreparedStatement statement = conn.prepareStatement(insertQuery);
                            String[] nextLine;

                            while ((nextLine = reader.readNext()) != null) {

                                if (nextLine.length < 6) {
                                    System.out.println("Skipping malformed CSV line: " + Arrays.toString(nextLine));
                                    continue;
                                }


                                String firstName = nextLine[0].trim();
                                String lastName = nextLine[1].trim();
                                String dob = nextLine[2].trim();
                                String school = nextLine[3].trim();
                                String combination = nextLine[4].trim();
                                String levelStr = nextLine[5].trim();


                                int age;
                                try {
                                    age = getAge(dob);
                                } catch (Exception e) {
                                    System.out.println("Invalid date format for student: " + firstName + " " + lastName + ", skipping...");
                                    continue;
                                }


                                int level;
                                try {
                                    level = Integer.parseInt(levelStr);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid level for student: " + firstName + " " + lastName + ", skipping...");
                                    continue;
                                }


                                statement.setString(1, firstName);
                                statement.setString(2, lastName);
                                statement.setInt(3, age);
                                statement.setString(4, school);
                                statement.setString(5, combination);
                                statement.setInt(6, level);

                                statement.addBatch();
                            }

                            statement.executeBatch();
                            System.out.println("Data from the CSV file has been added successfully");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;



                    default:

                        System.out.println("\n Invalid input provided \n");


                }


            }

             while (option != 9);



    }

    public static void menu(){
        System.out.println("Welcome to the Terminal Based SDMS !!!!!!!");
        System.out.println("MENU OPTIONS: ");
        System.out.println("1. Add a student");
        System.out.println("2. Retrieve All Students");
        System.out.println("3. Search for a certain student");
        System.out.println("4. Edit a certain students data");
        System.out.println("5. Delete a certain student");
        System.out.println("6. Read from a file (CSV File): ");
        System.out.println("9. Exit the Program");

        System.out.println("Enter the option number basing on what you want to do:   ");

    }

    public static int getAge(String dob) {
        String yearString = dob.split("-")[0].trim();
        int birthYear = Integer.parseInt(yearString);
        int currentYear = java.time.Year.now().getValue();

        return currentYear - birthYear;
    }



}