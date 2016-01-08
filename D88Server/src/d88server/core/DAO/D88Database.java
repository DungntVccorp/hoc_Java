/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.DAO;

import com.mysql.jdbc.*;
import d88server.core.common.D88Constant;
import d88server.core.common.D88SShare;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class D88Database {

    public interface D88DatabaseDelegate {

        public void didLoadConfig();
    }
    public interface  D88DoneLoadResult{
        public void didLoadResult(ResultSet resultSet);
    }

    private static final D88Database shareIntance = new D88Database();

    Connection connection = null;
    Statement statement = null;
    Statement statement2 = null;

    ResultSet resultSet = null;

    public static D88Database getInstance() {
        return shareIntance;
    }

    public D88Database() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String ConnectionString = "jdbc:mysql://" + D88SShare.getInstance().getConfig(D88Constant.DATABASE_HOST) + ":" + D88SShare.getInstance().getConfig(D88Constant.DATABASE_PORT) + "/" + D88SShare.getInstance().getConfig(D88Constant.DATABASE_NAME);
            connection = (Connection) DriverManager.getConnection(ConnectionString, D88SShare.getInstance().getConfig(D88Constant.DATABASE_USER), D88SShare.getInstance().getConfig(D88Constant.DATABASE_PASWORD));
            if (connection != null) {
                System.out.println("Connect success!");
                statement = (Statement) connection.createStatement();
                //statement2 = (Statement) connection.createStatement();
                if (statement != null) {
                    System.out.println("statement success!");
                    resultSet = statement.executeQuery("SELECT uuid FROM test");
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            System.out.println("Value  " + resultSet.getInt(1));
                        }
                    }
                    //statement.executeUpdate("INSERT INTO test (uuid, name, data) VALUES (8, 8, 8)");
                    //statement2.executeUpdate("INSERT INTO test (uuid, name, data) VALUES (9, 9, 9)");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(D88Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadSettingFormDatabase(D88DatabaseDelegate callBack) {
        // LOAD CONFIG AND SAVE TO SHARE
        callBack.didLoadConfig();
    }

    public void executeQuery(String queryString,D88DoneLoadResult doneLoadResult) {
        doneLoadResult.didLoadResult(null);
    }

    public void executeUpdate(String queryString) {

    }

}
