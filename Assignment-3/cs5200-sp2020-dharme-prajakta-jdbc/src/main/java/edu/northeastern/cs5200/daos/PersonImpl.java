package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonImpl implements PersonDao {

    Connection connection = new Connection();
    java.sql.Connection conn;
    PreparedStatement pstatement;
    public static String INSERT_PERSON= "INSERT INTO person(id,first_name,last_name,username,password,email) values (?,?,?,?,?,?)";
    public static String FIND_PERSON_BY_ID= "SELECT * FROM person WHERE id=?";
    private static PersonImpl instance = null;

    public static PersonImpl getInstance(){
        if(instance == null) {
            instance = new PersonImpl() {
            };
        }
        return instance;
    }

    @Override
    public void createPerson(Person person) {
        try {

            conn=connection.getConnection();
            pstatement=conn.prepareStatement(INSERT_PERSON);

            pstatement.setInt(1,person.getId());
            pstatement.setString(2,person.getFirst_name());
            pstatement.setString(3,person.getLast_name());
            pstatement.setString(4,person.getUsername());
            pstatement.setString(5,person.getPassword());
            pstatement.setString(6,person.getEmail());

            int result_status = pstatement.executeUpdate();
            if(result_status==1)
                System.out.println("Inserted the new Pperson in db");
        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                connection.closeConnection();
            }
        }
    }


    @Override
    public Person findPersonById(int personId) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(FIND_PERSON_BY_ID);
            pstatement.setInt(1,personId);
            ResultSet rs = pstatement.executeQuery();
            Person person=null;
            if (rs.next()){
                 person = new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"));
            }
            return person;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot find person by id");
        }
        return null;
    }


}
