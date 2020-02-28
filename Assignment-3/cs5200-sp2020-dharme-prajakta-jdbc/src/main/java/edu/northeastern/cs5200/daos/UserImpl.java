package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserImpl implements UserDao {
    private static UserImpl instance = null;
    java.sql.Connection conn;
    PreparedStatement pstatement,pstatement2;

    public static String INSERT_USER = ("INSERT INTO user(id,personid) values (?,?)");
    public static String INSERT_USER_IN_PERSON= ("INSERT INTO person(id,first_name,last_name,username,password,email) values (?,?,?,?,?,?)");

    public static UserImpl getInstance(){
        if(instance == null) {
            instance = new UserImpl() {
            };
        }
        return instance;
    }

    @Override
    public void createUser(User user) {
        try {
            if(conn==null){
                conn = Connection.getConnection();
                System.out.println("Connection successful for creating developer");
            }
            pstatement=conn.prepareStatement(INSERT_USER_IN_PERSON);
            pstatement.setInt(1,user.getPerson().getId());
            pstatement.setString(2,user.getPerson().getFirst_name());
            pstatement.setString(3,user.getPerson().getLast_name());
            pstatement.setString(4,user.getPerson().getUsername());
            pstatement.setString(5,user.getPerson().getPassword());
            pstatement.setString(6,user.getPerson().getEmail());

            pstatement2=conn.prepareStatement(INSERT_USER);
            pstatement2.setInt(1,user.getId());
            pstatement2.setInt(2,user.getPerson().getId());

            int result_status = pstatement.executeUpdate();
            if(result_status==1) {
                System.out.println("Inserted new person "+user.getPerson().getFirst_name() +" into the db");
            }
            int result_status2 = pstatement2.executeUpdate();
            if(result_status2==1)
                System.out.println("Inserted new user "+user.getPerson().getFirst_name() +" into the db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        }  catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot insert user");
        }

    }
}
