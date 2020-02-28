package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class RoleImpl implements RoleDao {
    Connection connection = new Connection();
    java.sql.Connection conn;
    PreparedStatement pstatement;
    Statement statement;
    private static RoleImpl instance = null;
    Role role;
    WebsiteImpl wimpl = new WebsiteImpl();
    ResultSet rs;

    public static String ASSIGN_WEBSITE_ROLE = "INSERT INTO website_role(developerid,websiteid,role) VALUES (?,?,?)";
    public static String ASSIGN_PAGE_ROLE= "INSERT INTO page_role(developerid,pageid,role) VALUES (?,?,?)";
    public static String DELETE_WEBSITE_ROLE = "DELETE FROM website_role where developerid=? AND websiteid=? AND role=?";
    public static String DELETE_PAGE_ROLE = "DELETE FROM page_role where developerid=? AND pageid=? AND role=?";
    public static String GET_ROLE_FOR_DEVELOPER = "SELECT role FROM page_role WHERE developerid=? AND pageid=?";
    public static String UPDATE_ROLE_FOR_DEVELOPER = "UPDATE page_role SET role=? WHERE developerid=? AND pageid=?";

    int query_status;

    public static RoleImpl getInstance(){
        if(instance == null) {
            instance = new RoleImpl() {
            };
        }
        return instance;
    }

    @Override
    public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(ASSIGN_WEBSITE_ROLE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,websiteId);
            pstatement.setString(3,role.getById(roleId).name());
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Website developer role assigned successfully for website "+websiteId);
            else
                System.out.println("Could not assign role");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignPageRole(int developerId, int pageId, int roleId) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(ASSIGN_PAGE_ROLE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,pageId);
            pstatement.setString(3,role.getById(roleId).name());
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Page developer role assigned successfully for page "+pageId);
            else
                System.out.println("Could not assign role");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void swapPageRole(Developer developer1, Developer developer2, Page page) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(GET_ROLE_FOR_DEVELOPER);
            pstatement.setInt(1,developer1.getId());
            pstatement.setInt(2,page.getId());
            rs = pstatement.executeQuery();
            String role1=null,role2=null;
            while (rs.next()){
                role1 = rs.getString("role");
            }

            pstatement = conn.prepareStatement(GET_ROLE_FOR_DEVELOPER);
            pstatement.setInt(1,developer2.getId());
            pstatement.setInt(2,page.getId());
            rs = pstatement.executeQuery();
            while (rs.next()){
                role2 = rs.getString("role");
            }
            String x=role1;
            role1 = role2;
            role2 = x;

            pstatement = conn.prepareStatement(UPDATE_ROLE_FOR_DEVELOPER);
            pstatement.setString(1,role1);
            pstatement.setInt(2,developer1.getId());
            pstatement.setInt(3,page.getId());
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                pstatement = conn.prepareStatement(UPDATE_ROLE_FOR_DEVELOPER);
                pstatement.setString(1,role2);
                pstatement.setInt(2,developer2.getId());
                pstatement.setInt(3,page.getId());
                query_status = pstatement.executeUpdate();
                if(query_status==1)
                    System.out.println("Page developer role swapped successfully for page "+page.getTitle());
                else
                    System.out.println("Could not swap role");}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Cannot swap roles");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_WEBSITE_ROLE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,websiteId);
            pstatement.setString(3,role.getById(roleId).name());
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Website developer role deleted successfully");
            else
                System.out.println("Could not delete role");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePageRole(int developerId, int pageId, int roleId) {
        try {
            conn= Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_PAGE_ROLE);
            pstatement.setInt(1,developerId);
            pstatement.setInt(2,pageId);
            pstatement.setString(3,role.getById(roleId).name());
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Page developer role deleted successfully");
            else
                System.out.println("Could not delete role");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
