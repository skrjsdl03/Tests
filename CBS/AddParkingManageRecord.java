package cbstatement;

import java.sql.*;

public class AddParkingManageRecord {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "DEUDB";
        String password = "1234";


        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "{CALL AddParkingLotInspection(?, ?, ?)}";

            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                cstmt.setString(1, "P011"); // 주차장 ID
                cstmt.setString(2, "ADMIN001"); // 관리자 ID
                cstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // 점검일시 (현재 시간)

                cstmt.execute();
                System.out.println("점검 기록이 추가되었습니다.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
