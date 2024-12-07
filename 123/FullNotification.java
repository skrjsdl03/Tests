package cbstatement;

import java.sql.*;

public class FullNotification {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "DEUDB";
        String password = "1234";


        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = """
                    SELECT COUNT(*)
                    FROM 주차공간
                    WHERE 주차장ID = ? AND 이용가능여부 = '이용불가'
                    """;

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "P011"); // 특정 주차장 ID
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        System.out.println("알림: 주차장이 만차 상태입니다!");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
