package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Priviledge;
import edu.northeastern.cs5200.models.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PriviledgeImpl implements PriviledgeDao {

    Connection connection = new Connection();
    java.sql.Connection conn;
    PreparedStatement pstatement;
    private static PriviledgeImpl instance = null;
    Priviledge priviledge=null;
    ResultSet rs;

    public static String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO website_priviledge(developerid,websiteid,priviledge) VALUES (?,?,?)";
    public static String ASSIGN_PAGE_PRIVILEDGE= "INSERT INTO page_priviledge(developerid,pageid,priviledge) VALUES (?,?,?)";
    public static String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM website_priviledge where developerid=? AND websiteid=? AND priviledge=?";
    public static String DELETE_PAGE_PRIVILEDGE = "DELETE FROM page_priviledge where developerid=? AND pageid=? AND priviledge=?";


    int query_status;

    public static PriviledgeImpl getInstance(){
        if(instance == null) {
            instance = new PriviledgeImpl() {
            };
        }
        return instance;
    }


    @Override
    public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,websiteId);
            pstatement.setString(3,priviledge);
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Website developer priviledge assigned successfully for website "+websiteId);
            else
                System.out.println("Could not assign priviledge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,pageId);
            pstatement.setString(3,priviledge);
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Page developer priviledge assigned successfully for page "+pageId);
            else
                System.out.println("Could not assign priviledge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,websiteId);
            pstatement.setString(3,priviledge);
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Website developer priviledge deleted successfully for website "+websiteId);
            else
                System.out.println("Could not delete priviledge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_PAGE_PRIVILEDGE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,pageId);
            pstatement.setString(3,priviledge);
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Page developer priviledge deleted successfully for page "+pageId);
            else
                System.out.println("Could not delete priviledge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
