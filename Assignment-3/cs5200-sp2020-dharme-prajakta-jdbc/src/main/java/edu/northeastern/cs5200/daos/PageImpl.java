package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

public class PageImpl implements PageDao {

    Connection connection = new Connection();
    java.sql.Connection conn;
    PreparedStatement pstatement;
    Statement statement;
    private static PageImpl instance = null;
    WebsiteImpl wimpl = new WebsiteImpl();
    public Collection<Page> pageList = new ArrayList<Page>();
    public Collection<Page> pageList2 = new ArrayList<Page>();
    ResultSet rs;

    public static String INSERT_INTO_PAGE = "INSERT INTO page(id,websiteid,title,description,created,updated,views) VALUES (?,?,?,?,?,?,?)";
    public static String FIND_ALL_PAGES= "SELECT * FROM page";
    public static String FIND_PAGE_BY_ID = "SELECT * FROM page WHERE id=?";
    public static String FIND_PAGE_BY_WEBSITE = "SELECT * FROM page WHERE websiteid=?";
    public static String UPDATE_PAGE = "UPDATE page SET websiteid=?, title=?, description=?,created=?,updated=?,views=? WHERE id=?";
    public static String DELETE_PAGE_BY_ID = "DELETE FROM page WHERE id=?";
    public static String DELETE_LAST_UPDATED_PAGE_IN_WEBSITE = "DELETE FROM page WHERE websiteid=? ORDER BY updated DESC LIMIT 1;";

    int query_status;

    public static PageImpl getInstance(){
        if(instance == null) {
            instance = new PageImpl() {
            };
        }
        return instance;
    }

    @Override
    public void createPageForWebsite(int websiteId, Page page) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(INSERT_INTO_PAGE);
            pstatement.setInt(1,page.getId());
            pstatement.setInt(2,websiteId);
            pstatement.setString(3,page.getTitle());
            pstatement.setString(4,page.getDescription());
            pstatement.setDate(5,page.getCreated());
            pstatement.setDate(6,page.getUpdated());
            pstatement.setInt(7,page.getViews());
            pstatement.executeUpdate();
            System.out.println("Inserted new page "+page.getTitle()+ "for website id "+websiteId );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Page> findAllPages() {
        try {
            conn = Connection.getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL_PAGES);
            while (rs.next()){
                Page page = new Page(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("created"),
                        rs.getDate("updated"),
                        rs.getInt("views"),
                        wimpl.findWebsiteById(rs.getInt("websiteid"))
                        );
                pageList.add(page);
            }
            return pageList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page findPageById(int pageId) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(FIND_PAGE_BY_ID);
            pstatement.setInt(1,pageId);
            ResultSet rs = pstatement.executeQuery();
            while (rs.next()){
                Page page = new Page(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("created"),
                        rs.getDate("updated"),
                        rs.getInt("views"),
                        wimpl.findWebsiteById(rs.getInt("websiteid"))
                );
                return page;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Page> findPagesForWebsite(int websiteId) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(FIND_PAGE_BY_WEBSITE);
            pstatement.setInt(1,websiteId);
            rs = pstatement.executeQuery();
            while (rs.next()){
                Page page = new Page(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("created"),
                        rs.getDate("updated"),
                        rs.getInt("views"),
                        wimpl.findWebsiteById(rs.getInt("websiteid"))
                );
                pageList2.add(page);
            }
            return  pageList2;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updatePage(int pageId, Page page) {
        try {
            conn=Connection.getConnection();
            pstatement = conn.prepareStatement(UPDATE_PAGE);
            pstatement.setInt(1,page.getWebsite().getId());
            pstatement.setString(2,page.getTitle());
            pstatement.setString(3,page.getDescription());
            pstatement.setDate(4,page.getCreated());
            pstatement.setDate(5,page.getUpdated());
            pstatement.setInt(6,page.getViews());
            pstatement.setInt(7,pageId);
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
    public int deletePage(int pageId) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_PAGE_BY_ID);
            pstatement.setInt(1,pageId);
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Page id"+pageId+ " deleted successfully");
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
    public int deleteLastUpdatePageInWebsite(Website website) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_LAST_UPDATED_PAGE_IN_WEBSITE);
            pstatement.setInt(1,website.getId());
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Page with last updated date deleted successfully");
                return  query_status;}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Page delete unsuccessful");
            e.printStackTrace();
        }
        return 0;
    }
}
