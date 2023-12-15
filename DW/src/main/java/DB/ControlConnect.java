package DB;


import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class ControlConnect {
    private static final Properties properties = new Properties();

    /*
    1.Load các biến cục bộ cho ControlConnect từ control.properties
     */
    static {
        try (InputStream input = ControlConnect.class.getResourceAsStream("/control.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getJdbcUrl() {
        String s=(new StringBuilder()).append(properties.getProperty("db.jdbcUrl")).append("://").append(getHost()).append(":").append(getPort()).append("/").append(getName()).toString();
        return s;
    }
    public static String getHost() {
        return properties.getProperty("db.host");
    }
    public static String getPort() {
        return properties.getProperty("db.port");
    }

    public static String getUsername() {
        return properties.getProperty("db.username");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }
    public static String getName() {
        return properties.getProperty("db.name");
    }

    /*
    4. Lấy đường dẫn đến file csv
     */
    public static String getFilePath() {
        Path absolutePath = null;
        /* 2.Kết nối với control.db */
        String jdbcURL = ControlConnect.getJdbcUrl();
        String username = ControlConnect.getUsername();
        String password = ControlConnect.getPassword();

        /*
        2.1. kết nối db Control để lấy file mới
         */
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            /* Thực hiện truy vấn để lấy name từ bảng data_files */
//            String nameDatafile = "SELECT name FROM data_files WHERE id = (SELECT MAX(id) FROM data_files)";
            String selectFilePath = "SELECT CONCAT(fc.location, '/', df.name) AS full_path FROM file_configs fc JOIN data_files df ON fc.id = df.df_config_id ORDER BY df.id DESC LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectFilePath);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                /* Đọc dữ liệu từ ResultSet*/
                if (resultSet.next()) {
                    String full_path = resultSet.getString("full_path");
                    if (full_path != null) {
                        full_path = full_path.replace("//", "/");
                        absolutePath = Paths.get(full_path).toAbsolutePath();
                        System.out.println("Absolute Path file: " + absolutePath);
                    } else {
                        System.out.println("Not found.");
                    }
                }
            }
            System.out.println("Get data file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absolutePath.toString();
    }
    /* Phương thức check Logs xem Get data from file to Staging
    * Dùng để kiểm tra xem trong ngày hôm đó đã chạy dữ liệu hay chưa*/
    public static boolean checkLog(String name, String event_type, String status) {
        boolean rs = false;
        String jdbcURL = getJdbcUrl();
        String username = getUsername();
        String password = getPassword();

        String checkLogQuery = "SELECT COUNT(*) FROM logs WHERE name = ? AND event_type = ? AND status = ? AND DATE(created_at) = DATE(?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(checkLogQuery)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, event_type);
            preparedStatement.setString(3, status);
            preparedStatement.setDate(4, getCurrentDate());
//            preparedStatement.setDate(4, getPreviousDate());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) return rs=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    // Lấy ngày hiện tại
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    /////////////////////////TEST
    public static java.sql.Date getPreviousDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        java.util.Date previousDate = calendar.getTime();
        return new java.sql.Date(previousDate.getTime());
    }

    /*
    Phương thức ghi Logs
     */
    public static void insertLog(String name, String event_type, String status) {
        String jdbcURL = ControlConnect.getJdbcUrl();
        String username = ControlConnect.getUsername();
        String password = ControlConnect.getPassword();

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String insertLogQuery = "INSERT INTO logs (name, event_type,status,created_at) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertLogQuery)) {

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, event_type);
                preparedStatement.setString(3, status);
                preparedStatement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));

                preparedStatement.executeUpdate();

                System.out.println("Log inserted successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//-----Main---------------------------------
    public static void main(String[] args) {
//        System.out.println(getJdbcUrl());
        System.out.println(getFilePath());
//        String nameFile = getCsvNameFile();
//        System.out.println(getCsvFilePath()+nameFile);
//        System.out.println(getFilePath());
//        System.out.println( checkLog("xosohomnay","Get data from source","Success"));
//        // Đường dẫn đến project của bạn
//
//        String dateString = "08/11/2023";
//
//        // Định dạng của chuỗi ngày hiện tại
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        // Chuyển đổi chuỗi ngày thành LocalDate
//        LocalDate localDate = LocalDate.parse(dateString, inputFormatter);
//
//        // Định dạng của LocalDate đầu ra
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        // Chuyển đổi thành định dạng mới
//        LocalDate formattedDate = LocalDate.parse(localDate.format(outputFormatter));
//
//        // In ra kết quả
//        System.out.println("Chuỗi ngày sau khi chuyển đổi: " + formattedDate.getDayOfMonth());
    }
}

