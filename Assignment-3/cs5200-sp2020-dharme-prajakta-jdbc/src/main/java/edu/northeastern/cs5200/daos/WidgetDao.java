package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Widget;

import java.sql.Date;
import java.util.Collection;

public interface WidgetDao {
    void createWidgetForPage(int pageId, Widget widget);

    Collection<Widget> findAllWidgets();

    Widget findWidgetById(int widgetId);

    Collection<Widget> findWidgetsForPage(int pageId);

    int updateWidget(int widgetId, Widget widget);

    void updateWidgetOrder(int newOrder, Widget widget);

    int deleteWidget(int widgetId);

    int deleteLastWidgetInPage(Page page);

    void getUnansweredQuestions();

    void endorsedUsersForWeek(Date date1, Date date2);
}
