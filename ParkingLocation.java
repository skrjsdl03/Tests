package ppstatement;

import java.sql.*;

public class ParkingLocation {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "DEUDB";
        String password = "1234";

        String memberId = "GUN001"; // 사용자 ID

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT p.공간번호, p.주차장ID " +
                    "FROM 주차 p " +
                    "JOIN 차량 c ON p.차량번호 = c.차량번호 " +
                    "WHERE c.회원ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("공간 번호: " + rs.getString("공간번호"));
                System.out.println("주차장 ID: " + rs.getString("주차장ID"));
                System.out.println("------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
