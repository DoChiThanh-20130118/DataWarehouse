package Modules;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.StagingConnect;
import DB.ControlConnect;

import Services.Email;
import Services.EmailUtils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class Staging {
    public static void process() {
        Connection connection = null;
        try {
            /* 3. Lấy đường dẫn đến file csv mới */
            String csvFilePath = ControlConnect.getCsvPath();
            /*	5. Load các biến cục bộ kết nối với StagingConnect */
            String jdbcURL = StagingConnect.getJdbcUrl();
            String username = StagingConnect.getUsername();
            String password = StagingConnect.getPassword();

            /* 6. Kết nối với Staging.db */
            connection = DriverManager.getConnection(jdbcURL, username, password);

            /*4. Xử lý dữ liệu (Đọc file, load nd file,...) */
            try (FileReader fileReader = new FileReader(csvFilePath);
                 CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(fileReader);) {
                /* 7. Cập nhật status cho envent được thực hiện */
                ControlConnect.insertLog("xosohomnay", "Get data from file to Staging", "Start");

                // Lấy ngày, miền, tên tỉnh để kiểm tra sự tồn tại của dữ liệu
                String checkExistenceQuery = "SELECT COUNT(*) FROM lottery_result WHERE date = ? AND regions = ? AND name_province = ?";

                // INSERT dữ liệu vào database staging
                String insertQuery = "INSERT INTO lottery_result (date, regions, name_province, GiaiTam, TienThuong_Tam, GiaiBay, TienThuong_Bay, GiaiSau, TienThuong_Sau, GiaiNam, TienThuong_Nam, GiaiTu, TienThuong_Tu, GiaiBa, TienThuong_Ba, GiaiNhi, TienThuong_Nhi, GiaiNhat, TienThuong_Nhat, GiaiDacBiet, TienThuong_DB) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


                try (PreparedStatement existenceStatement = connection.prepareStatement(checkExistenceQuery);
                     PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    /* 8. Xử lý trùng lắp dữ liệu */
                    for (CSVRecord record : csvParser) {
                        /* Lấy dữ liệu từ CSV Record */
                        String date = record.get("Ngay");
                        String regions = record.get("Mien");
                        String name_province = record.get("TenDai");

                        existenceStatement.setString(1, date);
                        existenceStatement.setString(2, regions);
                        existenceStatement.setString(3, name_province);

                        try (ResultSet resultSet = existenceStatement.executeQuery()) {
                            if (resultSet.next() && resultSet.getInt(1) > 0) {
                                String message = "Data already exists for date: " + date + ", regions: " + regions
                                        + ", name_province: " + name_province;
                                /* 8.1. Gửi Thông báo trùng lắp về Email admin */
                                sendEmail(message);
                                /* 8.2. Ghi log trạng thái trùng lấp và chuyển sang dòng tiếp theo */
                                ControlConnect.insertLog("xosohomnay", "Get data from file to Staging", "Data already exists");
                                System.out.println(message);
                                continue;
                            }
                        }
					/*
						Thiết lập giá trị cho PreparedStatement
					 */
                        String[] columnNames = {"Ngay", "Mien", "TenDai", "GiaiTam", "TienThuong_Tam", "GiaiBay",
                                "TienThuong_Bay", "GiaiSau", "TienThuong_Sau", "GiaiNam", "TienThuong_Nam", "GiaiTu",
                                "TienThuong_Tu", "GiaiBa", "TienThuong_Ba", "GiaiNhi", "TienThuong_Nhi", "GiaiNhat",
                                "TienThuong_Nhat", "giaidacbiet", "TienThuong_DB"};

                        for (int i = 0; i < columnNames.length; i++) {
                            preparedStatement.setString(i + 1, record.get(columnNames[i]));
                        }

                        /* 9. Insert dữ liệu vào Staging.db*/
                        preparedStatement.executeUpdate();
                    }
                }

                System.out.println("Data loaded into Staging successfully.");
                /* 10. Thông báo trạng thái về Email admin */
                sendEmail("Data loaded into Staging database successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
            /* 11. Cập nhật lại status trong logs sau khi đã insert thành công */
            ControlConnect.insertLog("xosohomnay", "Get data from file to Staging", "Success");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /* 12. Đóng kết nối */
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /* Ghi lại logs*/

    /* Gửi email thông báo xự kiện insert đã diễn ra*/
    private static void sendEmail(String messageBody) {
        Email email = new Email();
        email.setFrom("dothanhlike10102002@gmail.com");
        email.setFromPassword("kedm slxz ubvb fkvj");
        email.setTo("phelimhr@gmail.com");
        email.setSubject("Notifications");
        StringBuilder sb = new StringBuilder();
        sb.append(messageBody);

        email.setContent(sb.toString());
        try {
            EmailUtils.send(email);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
