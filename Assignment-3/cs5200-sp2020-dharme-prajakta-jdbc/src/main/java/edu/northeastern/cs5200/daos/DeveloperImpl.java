package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.Connection;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class DeveloperImpl implements DeveloperDao {

    private static DeveloperImpl instance = null;
    PersonImpl pimpl = new PersonImpl();
    ResultSet rs;
    int query_status;
    public Collection<Developer> developerList = new ArrayList<Developer>();

    private void DeveloperImpl(){
    }

    public static DeveloperImpl getInstance(){
        if(instance == null) {
            instance = new DeveloperImpl() {
            };
        }
        return instance;
    }

    Connection connection = new Connection();
    java.sql.Connection conn;

    Statement statement;
    PreparedStatement pstatement, pstatement2, pstatement3;
    public static String INSERT_DEVELOPER = ("INSERT INTO developer(id,personid,developer_key) values (?,?,?)");
    public static String INSERT_PHONE_FOR_PERSON = ("INSERT INTO phone(phone,primary,personid) values (?,?,?)");
    public static String INSERT_DEVELOPER_IN_PERSON= ("INSERT INTO person(id,first_name,last_name,username,password,email) values (?,?,?,?,?,?)");
    public static String FIND_ALL_DEVELOPERS = ("SELECT * FROM developer");
    public static String FIND_DEVELOPER_BY_ID = ("SELECT * FROM developer JOIN person ON developer.personid=person.id WHERE developer.id=?");
    public static String FIND_DEVELOPER_BY_USERNAME = ("SELECT * FROM developer JOIN person ON developer.personid=person.id WHERE person.username=?");
    public static String FIND_DEVELOPER_BY_CREDENTIALS = ("SELECT * FROM developer JOIN person ON developer.personid=person.id WHERE person.username=? AND person.password=?");
    public static String UPDATE_DEVELOPER = ("UPDATE developer SET personid=?, developer_key=? WHERE id=?");
    public static String UPDATE_PRIMARY_PHONE_FOR_DEVELOPER = "UPDATE phone set phone=? WHERE personid=? AND primary_phone=1";
    public static String INSERT_PHONE_FOR_DEVELOPER = "INSERT INTO phone(phone,primary_phone,personid) VALUES(?,?,?)";
    public static String INSERT_ADDRESS_FOR_DEVELOPER = "INSERT INTO address(personid,street1,city,zip,primary_address) VALUES(?,?,?,?,?) ";
    public static String DELETE_DEVELOPER = ("DELETE FROM developer WHERE id=?");
    public static String DELETE_DEVELOPER_FROM_PERSON = ("DELETE FROM person WHERE id=?");
    public static String DELETE_PRIMARY_ADDRESS_FOR_DEVELOPER = ("DELETE FROM address WHERE personid=? AND primary_address=1");


    @Override
    public void createDeveloper(Developer developer) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful for creating developer");
            }
            pstatement=conn.prepareStatement(INSERT_DEVELOPER_IN_PERSON);
            pstatement.setInt(1,developer.getPerson().getId());
            pstatement.setString(2,developer.getPerson().getFirst_name());
            pstatement.setString(3,developer.getPerson().getLast_name());
            pstatement.setString(4,developer.getPerson().getUsername());
            pstatement.setString(5,developer.getPerson().getPassword());
            pstatement.setString(6,developer.getPerson().getEmail());

            pstatement2=conn.prepareStatement(INSERT_DEVELOPER);
            pstatement2.setInt(1,developer.getId());
            pstatement2.setInt(2,developer.getPerson().getId());
            pstatement2.setString(3,developer.getDeveloper_key());

            int result_status = pstatement.executeUpdate();
            if(result_status==1) {
                System.out.println("Inserted new person "+developer.getPerson().getFirst_name() +" into the db");
            }
            int result_status2 = pstatement2.executeUpdate();
            if(result_status2==1)
                System.out.println("Inserted new developer "+developer.getPerson().getFirst_name() +" into the db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        }  catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot insert developer");
        }

    }

    @Override
    public Collection<Developer> findAllDevelopers() {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(FIND_ALL_DEVELOPERS);
            ResultSet rs = pstatement.executeQuery();
            while (rs.next()){
                //System.out.println(rs.getInt("id"));
                //System.out.println(rs.getString("developer_key"));
                //System.out.println(rs.getInt("personid"));
                Developer developer = new Developer(
                        rs.getInt("id"),
                        rs.getString("developer_key"),
                        pimpl.findPersonById(rs.getInt("personid")));
                developerList.add(developer);
            }
            return developerList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot find  all developers");
        }
        return null;
    }

    @Override
    public Developer findDeveloperById(int developerId) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(FIND_DEVELOPER_BY_ID);
            pstatement.setInt(1,developerId);
            ResultSet rs = pstatement.executeQuery();
            Developer developer=null;
            if (rs.next()){
                Person person = new Person(
                        rs.getInt("personid"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                        );
                developer = new Developer(
                        developerId,
                        rs.getString("developer_key"),
                        person );
            }
            return developer;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot find developer by id");
        }
        return null;
    }

    @Override
    public Developer findDeveloperByUsername(String username) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
            pstatement.setString(1,username);
            rs = pstatement.executeQuery();
            Developer developer=null;
            while (rs.next()){
                Person person = new Person(rs.getInt("personid"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        username,
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("dob"));
                developer = new Developer(rs.getInt("id"),
                        rs.getString("developer_key"),
                        person
                        );
            }
            return developer;
        } catch (ClassNotFoundException e) {
            System.out.println("Connection unsuccessful");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Cannot find developer by username");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Developer findDeveloperByCredentials(String username, String password) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
            pstatement.setString(1,username);
            pstatement.setString(2,password);
            rs = pstatement.executeQuery();
            Developer developer=null;
            while (rs.next()){
                Person person = new Person(rs.getInt("personid"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        username,
                        password,
                        rs.getString("email"),
                        rs.getDate("dob"));
                developer = new Developer(rs.getInt("id"),
                        rs.getString("developer_key"),
                        person
                );
            }
            return developer;
        } catch (ClassNotFoundException e) {
            System.out.println("Connection unsuccessful");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Cannot find developer by credentials");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int updateDeveloper(int developerId, Developer developer) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement2=conn.prepareStatement(UPDATE_DEVELOPER);
            pstatement2.setInt(1,developer.getPerson().getId());;
            pstatement2.setString(2,developer.getDeveloper_key());
            pstatement2.setInt(3,developerId);
            query_status=pstatement2.executeUpdate();
            if(query_status==1){
                    System.out.println("Dev details update successfullyl");
                    return query_status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot update developer");
        }
        return 0;
    }

    @Override
    public int deleteDeveloper(int developerId) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(DELETE_DEVELOPER);
            pstatement.setInt(1,developerId);
            query_status=pstatement.executeUpdate();
            if(query_status==1){
                pstatement=conn.prepareStatement(DELETE_DEVELOPER_FROM_PERSON);
                pstatement.setInt(1,findDeveloperById(developerId).getPerson().getId());
                query_status=pstatement.executeUpdate();
                if(query_status==1)
                    System.out.println("Successfully deleted developer with id "+developerId);
                return query_status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot delete developer by id");
        }
        return 0;
    }

    @Override
    public void insertPhoneForDeveloper(Developer developer, Phone newPhone) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(INSERT_PHONE_FOR_DEVELOPER);
            pstatement.setString(1,newPhone.getPhone());
            pstatement.setBoolean(2,newPhone.isPrimary());
            pstatement.setInt(3,developer.getPerson().getId());
            query_status=pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Successfully added phone for developer "+developer.getPerson().getUsername());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot add phone for developer");
        }
    }

    @Override
    public void insertAddressForDeveloper(Developer developer, Address address) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(INSERT_ADDRESS_FOR_DEVELOPER);
            pstatement.setInt(1,address.getPerson().getId());
            pstatement.setString(2,address.getStreet1());
            pstatement.setString(3,address.getCity());
            pstatement.setInt(4,address.getZip());
            pstatement.setBoolean(5,address.isprimary_address());
            query_status=pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Successfully added address for developer "+developer.getPerson().getUsername());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot add address for developer");
        }
    }

    @Override
    public int deletePrimaryAddressForDeveloper(Developer developer) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(DELETE_PRIMARY_ADDRESS_FOR_DEVELOPER);
            pstatement.setInt(1,developer.getPerson().getId());
            query_status=pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Successfully deleted developer address for  "+developer.getPerson().getUsername());
                return query_status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot delete developer address");
        }
        return 0;
    }


    @Override
    public int updatePhoneForDeveloper(Developer developer, Phone updatedPhone) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement=conn.prepareStatement(UPDATE_PRIMARY_PHONE_FOR_DEVELOPER);
            pstatement.setString(1,updatedPhone.getPhone());
            pstatement.setInt(2,developer.getPerson().getId());
            query_status=pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Successfully updated phone for developer "+developer.getPerson().getUsername());
                return query_status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection unsuccessful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot delete developer by id");
        }
        return 0;
    }



}
