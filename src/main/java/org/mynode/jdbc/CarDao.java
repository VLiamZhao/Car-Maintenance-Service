//package org.mynode.jdbc;
//
//import org.mynode.model.Car;
//import java.math.BigDecimal;
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CarDao {
//    static final String DBURL = "jdbc:postgresql://localhost:5430/garage";
//    static final String USER = "admin";
//    static final String PASS = "password";
//    public List<Car> cars() {
//        List<Car> cars = new ArrayList<>();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            //STEP 2: Open a connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DBURL, USER, PASS);
//            //STEP 3: Execute a query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM car";
//            rs = stmt.executeQuery(sql);
//            //STEP 4: Extract data from result set
//            while(rs.next()) {
//                //Retrieve by column name
//                Long id  = rs.getLong("id");
//                String type = rs.getString("type");
//                BigDecimal price = rs.getBigDecimal("price");
//                LocalDate regi_date_date = rs.getDate("regi_date").toLocalDate();
//                long owner_id = rs.getLong("owner_id");
//                //Fill the object
//                Car car = new Car();
//                car.setId(id);
//                car.setType(type);
//                car.setPrice(price);
////                car.setOwnerId(owner_id);
//                cars.add(car);
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
//        return cars;
//    }
//
//}
