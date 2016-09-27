package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.microsoft.sqlserver.jdbc.*;

import java.sql.*;

/**
 * TODO: Threadsafe pool with connections available and one pool with busy.
 * Created by Anton on 2016-09-27.
 */
public class DBManager {

    static String connectionurl = "jdbc:sqlserver://lab1webshop.database.windows.net:1433;" +
            "database=Webshop;" +
            "user=lab1admin@lab1webshop;" +
            "password=Antonchristian95;" +
            "encrypt=true;" +
            "trustServerCertificate=false;" +
            "hostNameInCertificate=*.database.windows.net;" +
            "loginTimeout=30;";


    static{
        init();
    }

    public static void init(){
        System.out.println("Connecting to database.");
        getConnection();
    }

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            Connection con = DriverManager.getConnection(connectionurl);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
