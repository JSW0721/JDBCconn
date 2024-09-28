import java.sql.*;

public class Main {
    private static String Driver = "com.mysql.jdbc.Driver";//MYSQL 8.0부터 주소가 mysql에서 com.mysql로 바뀜
    private static String URL = "jdbc:mysql://localhost:3306/test_db";
    // mysql://뒤 부터는 서버 위치. 지금은 로컬 PC에서 3306port를 이용해서 mysql 서버와 통신하고 있음으로 localhost:3306이 됨.
    //port번호 뒤/ 이후로는 연결하고자 하는 db이름.
    private static String user = "root"; //db연결시 사용할 사용자 계정
    private static String pw = "통신보안";//사용자 계정의 비밀번호

    public static Statement stmt;
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static ResultSetMetaData rsmd = null;

    public static void main(String[] args){
        try{
            //Class.forName(Driver); //MYSQL버전 8.0이상부터는 드라이버 알아서 잡아줌으로 없어도 됨.
            conn = DriverManager.getConnection(URL, user, pw);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM students WHERE age >= 30 AND age < 40");

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
