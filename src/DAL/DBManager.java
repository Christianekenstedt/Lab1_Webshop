package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.microsoft.sqlserver.jdbc.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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


    private static ArrayList<Connection> connPool = new ArrayList<>();
    static{
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            for(int i = 0; i < 10; i++)
                connPool.add(DriverManager.getConnection(connectionurl));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        while(connPool.size() < 1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return takeFromPool();
    }

    private synchronized static Connection takeFromPool(){
        return connPool.remove(0);
    }

    public synchronized static void returnConnection(Connection conn){
        connPool.add(conn);
    }
}
