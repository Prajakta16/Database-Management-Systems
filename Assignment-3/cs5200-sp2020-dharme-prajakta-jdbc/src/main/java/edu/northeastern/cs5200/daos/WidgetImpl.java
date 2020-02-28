package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Type;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WidgetImpl implements WidgetDao {

    Connection connection = new Connection();
    java.sql.Connection conn;
    PreparedStatement pstatement;
    Statement statement;
    private static WidgetImpl instance = null;
    PageImpl pageImpl = new PageImpl();
    public Collection<Widget> widgetList = new ArrayList<Widget>();


    ResultSet rs;
    int query_status;

    public static String INSERT_INTO_WIDGET = "INSERT INTO widget(id,name,text,type,pageid,url,widget_order,width,height) VALUES (?,?,?,?,?,?,?,?,?)";
    public static String INSERT_QUESTION_INTO_WIDGET = "INSERT INTO widget(name,text,type,posted_on,asked_by,length,views,endorsed_by_instructor,module,pageid,id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static String INSERT_ANSWER_INTO_WIDGET = "INSERT INTO widget(name,text,type,posted_on,posted_by,correct_answer,up_votes,down_votes,question_widget_id) VALUES (?,?,?,?,?,?,?,?,?)";
    public static String FIND_ALL_WIDGETS= "SELECT * FROM widget";
    public static String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id=?";
    public static String FIND_WIDGET_BY_PAGE = "SELECT * FROM widget WHERE pageid=?";
    public static String UPDATE_WIDGET = "UPDATE widget SET name=?,text=?,type=?,pageid=?,url=?,widget_order=?,width=?,height=? WHERE id=?";
    public static String UPDATE_WIDGET_ORDER = "UPDATE widget SET widget_order=? WHERE id=?";
    public static String DELETE_WIDGET_BY_ID = "DELETE FROM widget WHERE id=?";
    public static String DELETE_LAST_WIDGET_IN_PAGE = "DELETE FROM widget WHERE widget_order=? AND pageid=?";

    public static WidgetImpl getInstance(){
        if(instance == null) {
            instance = new WidgetImpl() {
            };
        }
        return instance;
    }

    @Override
    public void createWidgetForPage(int pageId, Widget widget) {
        try {
            conn = Connection.getConnection();
            if(widget.getType() == Type.question){
                pstatement = conn.prepareStatement(INSERT_QUESTION_INTO_WIDGET);
                pstatement.setString(1, widget.getName());
                pstatement.setString(2, widget.getText());
                pstatement.setString(3, widget.getType().name());
                pstatement.setDate(4, widget.getPosted_on());
                pstatement.setInt(5, widget.getAsked_by());
                pstatement.setInt(6, widget.getLength());
                pstatement.setInt(7, widget.getViews());
                pstatement.setBoolean(8, widget.isEndorsed_by_instructor());
                pstatement.setString(9, widget.getModule().name());
                pstatement.setInt(10, pageId);
                pstatement.setInt(11,widget.getId());
                query_status = pstatement.executeUpdate();
            }
            else if (widget.getType() == Type.answer){
                    pstatement=conn.prepareStatement(INSERT_ANSWER_INTO_WIDGET);
                    pstatement.setString(1, widget.getName());
                    pstatement.setString(2, widget.getText());
                    pstatement.setString(3, widget.getType().name());
                    pstatement.setDate(4, widget.getPosted_on());
                    pstatement.setInt(5, widget.getPosted_by());
                    pstatement.setBoolean(6, widget.isCorrect_answer());
                    pstatement.setInt(7, widget.getUp_votes());
                    pstatement.setInt(8, widget.getDown_votes());
                    pstatement.setInt(9, widget.getQuestion_widget_id());
                    query_status = pstatement.executeUpdate();
            }
            else {
                pstatement = conn.prepareStatement(INSERT_INTO_WIDGET);
                String type = widget.getType().name();
                pstatement.setInt(1, widget.getId());
                pstatement.setString(2, widget.getName());
                pstatement.setString(3, widget.getText());
                pstatement.setString(4, type);
                pstatement.setInt(5, pageId);
                pstatement.setString(6, widget.getUrl());
                pstatement.setInt(7, widget.getWidget_order());
                pstatement.setInt(8, widget.getWidth());
                pstatement.setInt(9, widget.getHeight());
                query_status = pstatement.executeUpdate();
            }
            if (query_status == 1)
                System.out.println("Widget " + widget.getName() + " inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Widget> findAllWidgets() {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(FIND_ALL_WIDGETS);
            rs = pstatement.executeQuery();
            while (rs.next()){
                Widget widget = new Widget(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("width"),
                        rs.getInt("height"),
                        rs.getString("text"),
                        rs.getInt("widget_order"),
                        Type.getByName(rs.getString("type")),
                        pageImpl.findPageById(rs.getInt("pageid")),
                        rs.getString("url"));
                widgetList.add(widget);
            }
            return  widgetList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }

    @Override
    public Widget findWidgetById(int widgetId) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(FIND_WIDGET_BY_ID);
            pstatement.setInt(1,widgetId);
            rs = pstatement.executeQuery();
            Widget widget = null;
            while (rs.next()){
                widget = new Widget(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("width"),
                        rs.getInt("height"),
                        rs.getString("text"),
                        rs.getInt("widget_order"),
                        Type.getByName(rs.getString("type")),
                        pageImpl.findPageById(rs.getInt("pageid")),
                        rs.getString("url"));
            }
            return  widget;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Widget> findWidgetsForPage(int pageId) {
        try {
            Collection<Widget> widgetList2 = new ArrayList<Widget>();
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(FIND_WIDGET_BY_PAGE);
            pstatement.setInt(1,pageId);
            rs = pstatement.executeQuery();
            while (rs.next()){
                Widget widget = new Widget(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("width"),
                        rs.getInt("height"),
                        rs.getString("text"),
                        rs.getInt("widget_order"),
                        Type.getByName(rs.getString("type")),
                        pageImpl.findPageById(pageId),
                        rs.getString("url"));
                widgetList2.add(widget);
            }
            return  widgetList2;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }

    @Override
    public int updateWidget(int widgetId, Widget widget) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(UPDATE_WIDGET);
            pstatement.setString(1,widget.getName());
            pstatement.setString(2,widget.getText());
            pstatement.setString(3,widget.getType().name());
            pstatement.setInt(4,widget.getPage().getId());
            pstatement.setString(5,widget.getUrl());
            pstatement.setInt(6,widget.getWidget_order());
            pstatement.setInt(7,widget.getWidth());
            pstatement.setInt(8,widget.getHeight());
            pstatement.setInt(9,widgetId);
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Widget "+widgetId+" updated successfully");
                return  query_status;}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Widget "+widgetId+" update unsuccessful");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateWidgetOrder(int newOrder, Widget widget) {
        try {
            int total_widgets=0, order_diff;
            conn = Connection.getConnection();
            Collection<Widget> widgetListToOrder;

            widgetListToOrder = findWidgetsForPage(widget.getPage().getId());
            for(Widget w:widgetListToOrder)
                total_widgets = total_widgets+1; //Gives total widgets in a page
            System.out.println("Total widgets "+total_widgets);
            if(newOrder>widget.getWidget_order())
                order_diff = newOrder - widget.getWidget_order();
            else
                order_diff = newOrder + total_widgets - widget.getWidget_order();
            System.out.println(order_diff);

            pstatement = conn.prepareStatement(UPDATE_WIDGET_ORDER);
            pstatement.setInt(1,newOrder);
            pstatement.setInt(2,widget.getId());
            pstatement.executeUpdate();
            query_status = pstatement.executeUpdate();
            if(query_status==1)
                System.out.println("Widget order for "+widget.getName()+" updated to "+newOrder);

            for(Widget w:widgetListToOrder){
                if(w.getId()!=widget.getId()){
                    pstatement = conn.prepareStatement(UPDATE_WIDGET_ORDER);
                    pstatement.setInt(1,(w.getWidget_order()+order_diff)% total_widgets);
                    pstatement.setInt(2,w.getId());
                    pstatement.executeUpdate();
                    query_status = pstatement.executeUpdate();
                    if(query_status==1)
                        System.out.println("Widget order for "+w.getName()+" updated to "+(w.getWidget_order()+order_diff)% total_widgets);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Widget "+widget.getName()+" order update unsuccessful");
            e.printStackTrace();
        }
    }

    @Override
    public int deleteWidget(int widgetId) {
        try {
            conn = Connection.getConnection();
            pstatement = conn.prepareStatement(DELETE_WIDGET_BY_ID);
            pstatement.setInt(1,widgetId);
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Widget "+widgetId+" deleted successfully");
                return  query_status;}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Widget "+widgetId+" delete unsuccessful");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteLastWidgetInPage(Page page) {
        try {
            int max_order=0;
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful for creating developer");
            }

            Collection<Widget> widgetListToDetermineMaxOrder = findWidgetsForPage(page.getId());
            for(Widget w: widgetListToDetermineMaxOrder)
                if(w.getWidget_order()>max_order)
                    max_order=w.getWidget_order();

            pstatement = conn.prepareStatement(DELETE_LAST_WIDGET_IN_PAGE);
            pstatement.setInt(1,max_order);
            pstatement.setInt(2,page.getId());
            query_status = pstatement.executeUpdate();
            if(query_status==1){
                System.out.println("Widget with order"+max_order+" deleted successfully");
                return  query_status;}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Widget delete unsuccessful");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void getUnansweredQuestions() {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful for creating developer");
            }
            CallableStatement callableStatement = conn.prepareCall("CALL getUnansweredQuestions();");
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Question id "+rs.getInt("question_id")+
                        " module "+rs.getString("module")+"" +
                        " number of answers "+rs.getString("MAX(num_of_answers)"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endorsedUsersForWeek(Date date1, Date date2) {
        try {
            if(conn==null){
                conn = connection.getConnection();
                System.out.println("Connection successful for creating developer");
            }
            pstatement = conn.prepareCall("CALL endorsedUsersForWeek(?,?);");
            pstatement.setDate(1,date1);
            pstatement.setDate(2,date2);
            rs = pstatement.executeQuery();
            while (rs.next()) {
                System.out.println("First name "+rs.getString("first_name")+
                        " number of answers "+rs.getInt("num_of_answers"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
