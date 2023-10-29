import java.sql.*;
import java.util.Scanner;

public class UserInsertEx {

    public static void main(String[] args) {

        // ==================== JDBC 관련 변수 =======================
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String url = "jdbc:mariadb://127.0.0.1:3307/1019chunjae";
        String user = "root";
        String pass = "12345";
        // ==========================================================

        try {
            // 1. 드라이버 세팅
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Connection 획득
            conn = DriverManager.getConnection(url, user, pass);

        } catch(Exception e) {
            System.out.println("DB 작업중 문제 발생");
            System.out.println(e.getMessage());

        }

        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();

            if(cmd.equals("exit")) {
                break;

            } else if(cmd.equals("add")) {
                System.out.println("========= 주소록 등록 =========");
                System.out.print("이름 : ");
                String name = scan.nextLine();
                System.out.print("주소 : ");
                String address = scan.nextLine();
                System.out.print("전화번호 : ");
                String phone = scan.nextLine();

                // DB 작업
                //====================================================
                //3. Statement 생성
                try {
                    stmt = conn.createStatement();

                    //4. SQL 처리
                    String sql = "INSERT INTO t_address\r\n"
                            + "SET `name` = '" + name + "',\r\n"
                            + "address = '" + address + "',\r\n"
                            + "phone = '" + phone + "'";

                    // 조회결과가 없는 경우에는 ResultSet으로 받아올게 없기 때문에
                    // sql만 반영해주는 executeUpdate(sql) 사용
                    stmt.executeUpdate(sql);

                } catch(Exception e) {
                    System.out.println("ADD DB작업중 문제 발생!!");
                    System.out.println(e.getMessage());
                }
                //====================================================

                System.out.println("주소록 등록 완료.");
                System.out.println("============================");

            } else if(cmd.equals("list")) {
                System.out.println("========= 모든 데이터 리스트 출력 =========");
                // DB 작업
                //====================================================
                try {
                    stmt = conn.createStatement();

                    //4. SQL 처리
                    String sql = "SELECT * FROM t_address\r\n";

                    // 조회결과가 없는 경우에는 ResultSet으로 받아올게 없기 때문에
                    // sql만 반영해주는 executeUpdate(sql) 사용
                    rs = stmt.executeQuery(sql);

                    while(rs.next()) {
                        System.out.println("id: " + rs.getString("id"));
                        System.out.println("이름: " + rs.getString("name"));
                        System.out.println("주소: " + rs.getString("address"));
                        System.out.println("전화번호: " + rs.getString("phone"));
                        System.out.println();
                    }

                } catch(Exception e) {
                    System.out.println("SELECT DB작업중 문제 발생!!");

                }
                //====================================================

                System.out.println("리스트 출력 완료.");
                System.out.println("============================");


            } else if(cmd.equals("update")) {
                System.out.println("========= 데이터 수정 =========");

                System.out.print("수정할 데이터의 id를 입력하세요: ");
                String findId = scan.nextLine();
                System.out.print("새로운 이름을 입력하세요 : ");
                String newName = scan.nextLine();
                System.out.print("새로운 주소를 입력하세요 : ");
                String newAddress = scan.nextLine();
                System.out.print("새로운 전화번호를 입력하세요 : ");
                String newPhone = scan.nextLine();


                // DB 작업
                //====================================================
                //3. Statement 생성
                try {
                    stmt = conn.createStatement();

                    //4. SQL 처리
                    String sql = "UPDATE t_address\r\n"
                            + "SET name = '" + newName + "',\r\n"
                            + "address = '" + newAddress + "',\r\n"
                            + "phone = '" + newPhone + "'\r\n"
                            + "WHERE id = '" + findId + "'";

                    rs = stmt.executeQuery(sql);

                    System.out.println(rs.getString("id"));
                    System.out.println(rs.getString("name"));
                    System.out.println(rs.getString("phone"));

                } catch(Exception e) {
                    System.out.println("UPDATE DB작업중 문제 발생!!");
                    System.out.println(e.getMessage());
                }
                //====================================================

                System.out.println("데이터 수정 완료.");
                System.out.println("============================");


            } else if(cmd.equals("delete")) {
                System.out.println("========= 데이터 삭제 =========");

                System.out.print("삭제할 데이터의 id를 입력하세요: ");
                String findId = scan.nextLine();

                // DB 작업
                //====================================================
                //3. Statement 생성
                try {
                    stmt = conn.createStatement();

                    //4. SQL 처리
                    String sql = "DELETE FROM t_address\r\n"
                            + "WHERE id = '" + findId + "'";


                    // 조회결과가 없는 경우에는 ResultSet으로 받아올게 없기 때문에
                    // sql만 반영해주는 executeUpdate(sql) 사용
                    stmt.executeUpdate(sql);


                } catch(Exception e) {
                    System.out.println("DELETE DB작업중 문제 발생!!");
                    System.out.println(e.getMessage());
                }
                //====================================================

                System.out.println("데이터 삭제 완료.");
                System.out.println("============================");


            }
        }
    }
}
