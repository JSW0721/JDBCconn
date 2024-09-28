import java.sql.*;

public class Main {
    private static String Driver = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/test_db";
    private static String user = "root";
    private static String pw = "통신보안";

    public static Statement stmt;
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static ResultSetMetaData rsmd = null;

    public static void main(String[] args){
        try{
            conn = DriverManager.getConnection(URL, user, pw);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM students WHERE age >= 30 AND age < 40;");

            rsmd = rs.getMetaData();
            int colcnt = rsmd.getColumnCount();

            while(rs.next()){
                for(int i = 1; i <= colcnt; i++){
                    System.out.print(rs.getString(i));
                    if(i<colcnt)System.out.print(" / ");
                }
                System.out.println();
            }
        }catch(Exception e){
            System.out.println("서버 연결 불가");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            if(conn != null){
                try {
                    conn.close();
                    rs.close();
                    stmt.close();

                    System.out.println("DB연결 종료");
                }catch(SQLException e){System.out.println(e.getMessage());}
            }
        }
    }
}
