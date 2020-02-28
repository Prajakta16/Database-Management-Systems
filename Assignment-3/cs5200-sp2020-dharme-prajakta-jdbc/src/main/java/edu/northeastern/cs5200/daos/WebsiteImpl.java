package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Website;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.List;

public class WebsiteImpl implements WebsiteDao {

    Connection connection = new Connection();
    java.sql.Connection conn;
    PreparedStatement pstatement;
    Statement statement;
    private static WebsiteImpl instance = null;
    DeveloperImpl d = new DeveloperImpl();
    int query_status;

    public static WebsiteImpl getInstance(){
        if(instance == null) {
            instance = new WebsiteImpl() {
            };
        }
        return instance;
    }

    public static  String INSERT_WEBSITE = "INSERT INTO website(id,developerid,name,description,created,updated,visits) VALUES (?,?,?,?,?,?,?)";
    public static String FIND_WEBSITE_BY_DEVELOPER = "SELECT * FROM website WHERE developerid=?";
    public static String FIND_ALL_WEBSITES= "SELECT * FROM website";
    public static String FIND_WEBSITE_BY_ID = "SELECT * FROM website WHERE id=?";
    public static String DELETE_WEBSITE_BY_ID = "DELETE FROM website WHERE id=?";
    public static String UPDATE_WEBSITE = "UPDATE website SET developerid=?, name=?, description=?,created=?,updated=?,visits=? WHERE id=?";
    public static String UPDATE_PAGE_TITLE = "UPDATE page p JOIN website w ON p.websiteid=w.id SET title=concat(?, title) WHERE w.id=?";


    @Override
    public void createWebsiteForDeveloper(int developerId, Website website) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement = conn.prepareStatement(INSERT_WEBSITE);
            pstatement.setInt(1, website.getId());
            pstatement.setInt(2, developerId);
            pstatement.setString(3, website.getName());
            pstatement.setString(4, website.getDescription());
            pstatement.setDate(5,website.getCreated());
            pstatement.setDate(6,website.getUpdated());
            pstatement.setInt(7, website.getVisits());
            pstatement.executeUpdate();
            System.out.println(website.getName()+" inserted successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Website> findAllWebsites() {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL_WEBSITES);
            Collection<Website> websiteList = new ArrayList<Website>();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date created = rs.getDate("created");
                Date updated = rs.getDate("updated");
                int visits = rs.getInt("visits");
                int developerid = rs.getInt("developerid");
                Website website = new Website(id,name,description,created,updated,visits,d.findDeveloperById(developerid));
                websiteList.add(website);
            }
            return websiteList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement = conn.prepareStatement(FIND_WEBSITE_BY_DEVELOPER);
            pstatement.setInt(1,developerId);
            ResultSet rs2 = pstatement.executeQuery();
            Collection<Website> websiteList = new ArrayList<Website>();
            while (rs2.next()){
                int id = rs2.getInt("id");
                String name = rs2.getString("name");
                String description = rs2.getString("description");
                Date created = rs2.getDate("created");
                Date updated = rs2.getDate("updated");
                int visits = rs2.getInt("visits");
                int developerid = rs2.getInt("developerid");
                Website website = new Website(id,name,description,created,updated,visits,d.findDeveloperById(developerid));
                websiteList.add(website);
            }
            return websiteList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Website findWebsiteById(int websiteId) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement = conn.prepareStatement(FIND_WEBSITE_BY_ID);
            pstatement.setInt(1,websiteId);
            ResultSet rs = pstatement.executeQuery();
            while (rs.next()){
                int developerid = rs.getInt("developerid");
                Website website = new Website(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getDate("created"),rs.getDate("updated"),rs.getInt("visits"),d.findDeveloperById(rs.getInt("developerid")));
            return website;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateWebsite(int websiteId, Website website) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement = conn.prepareStatement(UPDATE_WEBSITE);
            pstatement.setInt(1,website.getDeveloper().getId());
            pstatement.setString(2,website.getName());
            pstatement.setString(3,website.getDescription());
            pstatement.setDate(4,website.getCreated());
            pstatement.setDate(5,website.getUpdated());
            pstatement.setInt(6,website.getVisits());
            pstatement.setInt(7,websiteId);
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                return query_status;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteWebsite(int websiteId) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement = conn.prepareStatement(DELETE_WEBSITE_BY_ID);
            pstatement.setInt(1,websiteId);
            query_status = pstatement.executeUpdate();
            if(query_status==1) {
                System.out.println("Website "+websiteId+" deleted successfully");
                return query_status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updatePageTitleForWebsite(String prefixString, Website website) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful");
            }
            pstatement = conn.prepareStatement(UPDATE_PAGE_TITLE);
            pstatement.setString(1,prefixString);
            pstatement.setInt(2,website.getId());
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Updated page title");
                return query_status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
