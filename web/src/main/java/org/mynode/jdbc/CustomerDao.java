//package org.mynode.jdbc;
//
//
//import org.mynode.model.Customer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerDao {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    static final String DBURL = "jdbc:postgresql://localhost:5430/garage";
//    static final String USER = "admin";
//    static final String PASS = "password";
//
//    public List<Customer> getAccounts() {
//        List<Customer> customers = new ArrayList<>();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            //STEP 2: Open a connection
//            logger.debug("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//            //STEP 3: Execute a query
//            logger.debug("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM user";
//            rs = stmt.executeQuery(sql);
//            //STEP 4: Extract data from result set
//            while(rs.next()) {
//                //Retrieve by column name
//                Long id  = rs.getLong("id");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//
//                //Fill the object
//                Customer customer = new Customer();
//                customer.setId(id);
//                customer.setName(name);
//                customer.setDescription(description);
//                customers.add(customer);
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        finally {
//            //STEP 6: finally block used to close resources
//            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(conn != null) conn.close();
//            }
//            catch(SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return customers;
//    }
//
//    public Customer getUserById(long tmpid) {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        Customer result = new Customer();
//        try {
//            //STEP 2: Open a connection
//            logger.debug("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//            //STEP 3: Execute a query
//            logger.debug("Creating statement...");
//            stmt = conn.createStatement();
//            String sql = "SELECT * FROM user WHERE id=" + tmpid + ";";
//            rs = stmt.executeQuery(sql);
//            //STEP 4: Extract data from result
//            if(rs.next()) {
//                Long id  = rs.getLong("id");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                //Fill the object
//
//                result.setId(id);
//                result.setName(name);
//                result.setDescription(description);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            try {
//                if(rs != null)stmt.close();
//                if(stmt != null)stmt.close();
//                if(conn != null)conn.close();
//                logger.debug("Database connection closed");
//            }
//            catch (SQLException se){
//                se.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    public Customer saveUser(Customer input) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            //STEP 2: Open a connection
//            logger.debug("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//            //STEP 3: Execute a query
//            logger.debug("Creating statement...");
//            String sql = "INSERT INTO User (name, description)" + "VALUES (?, ?);";
//            stmt = conn.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
//            stmt.setString(1, input.getName());
//            stmt.setString(2, input.getDescription());
////            stmt.setLong(3, input.getEmployee_id());
//            stmt.execute();
//            rs = stmt.getGeneratedKeys();
//            //STEP 4: Extract data from result set
//            if(rs.next()){
//                logger.debug("ID before generatedKey: " + input.getId());
//                logger.debug("Getting generatedKey: " + rs.getLong("id"));
//                long generatedKey = rs.getLong("id");
//                input.setId(generatedKey);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                if(rs != null)stmt.close();
//                if(stmt != null)stmt.close();
//                if(conn != null)conn.close();
//                logger.debug("Database connection closed");
//            }
//            catch (SQLException se){
//                se.printStackTrace();
//            }
//        }
//        return input;
//    }
//    public boolean deleteUser(long uId) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        boolean result = true;
//        try {
//            //STEP 2: Open a connection
//            logger.debug("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//            //STEP 3: Execute a query
//            logger.debug("Creating statement...");
//            String sql = "DELETE FROM user WHERE id=?;";
//            stmt = conn.prepareStatement(sql);
//            stmt.setLong(1, uId);
//            //STEP 4: Delete data from account
//            result = stmt.execute();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                if(stmt != null)stmt.close();
//                if(conn != null)conn.close();
//                logger.debug("Database connection closed");
//            }
//            catch (SQLException se){
//                se.printStackTrace();
//            }
//        }
//        return result;
//    }
//}
