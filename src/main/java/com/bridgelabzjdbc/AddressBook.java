package com.bridgelabzjdbc;

import java.sql.*;

public class AddressBook {

    // Database connection parameters
    static final String DB_URL = "jdbc:mysql://localhost:3306/addressbook_db";
    static final String USER = "root";
    static final String PASS = "Root@123";
    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {
            System.out.println("Connected to the database successfully!");

            // Insert new contact
            createContact(conn);

            // Read all contacts
            System.out.println("Contacts in the database:");
            readContacts(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Create Operation (Insert)
    public static void createContact(Connection conn) {
        String sql = "INSERT INTO contacts (first_name, last_name, address, city, state, zip, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Nivrutti");
            pstmt.setString(2, "Wagh");
            pstmt.setString(3, "Near Aazad Maidan, Jalna");
            pstmt.setString(4, "Jalna");
            pstmt.setString(5, "MH");
            pstmt.setString(6, "443204");
            pstmt.setString(7, "555-1234");
            pstmt.setString(8, "nivru.Wagh@gmail.com");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new contact was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Operation (Retrieve All Contacts)
    public static void readContacts(Connection conn) {
        String sql = "SELECT * FROM contacts";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");

                // Create a Contact object and display it
                ContactCreation contact = new ContactCreation(id, firstName, lastName, address, city, state, zip, phoneNumber, email);
                contact.displayContact();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}