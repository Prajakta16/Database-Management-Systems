package edu.northeastern.cs5200;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

class main {

    public static void main(String args[]) throws ParseException {

    DeveloperImpl developerImpl = new DeveloperImpl().getInstance();
    WebsiteImpl websiteImpl = new WebsiteImpl().getInstance();
    PageImpl pageImpl = new PageImpl().getInstance();
    WidgetImpl widgetImpl = new WidgetImpl().getInstance();
    RoleImpl roleImpl = new RoleImpl().getInstance();
    PriviledgeImpl priviledgeImpl = new PriviledgeImpl().getInstance();

    Collection<Widget> widgetList = null;
    Widget widget;
    Role role = null;
    Priviledge priviledge = null;
    Website website2;

    Connection connection = new Connection();
    java.sql.Connection conn=null;
    int query_status;

    Calendar calendar = Calendar.getInstance(); // create a java calendar instance
    java.util.Date currentDate = calendar.getTime(); // this java date will represent the current date, or "now".
    java.sql.Date date = new java.sql.Date(currentDate.getTime());
    java.sql.Date date2 = Date.valueOf("2020-01-06");
    java.sql.Date date3 = Date.valueOf("2020-02-27");


    Person personAlice = new Person(12,"Alice","Wonder","alice","alice","alice@wonder.com");
    Phone phoneAlice = new Phone(1,"123-234-3456",true,personAlice);
    Address addressAlice = new Address(personAlice,"123 Adam St.","Alton",01234,true);
    Developer devAlice = new Developer(12,"4321rewq",personAlice);

    Person personBob = new Person(23,"Bob","Marley","bob","bob","bob@marley.com");
    Phone phoneBob = new Phone(2,"345-456-5677",true,personBob);
    Address addressBob = new Address (personBob,"345 Charles St.","Chelms",03455,true);
    Developer devBob = new Developer(23,"5432trew",personBob);

    Person personCharles = new Person(34,"Charles","Garcia","charlie","charlie","chuch@garcia.com");
    Phone phoneCharles = new Phone(3,"321-432-5435",true,personCharles);
    Address addressCharles = new Address (personCharles,"654 Frank St.","Foulton",04322,true);
    Developer devCharles = new Developer(34,"6543ytre",personCharles);

    System.out.println("---------Creating all developers-------------");
    developerImpl.createDeveloper(devAlice);
    developerImpl.createDeveloper(devBob);
    developerImpl.createDeveloper(devCharles);

    System.out.println("---------Insert phone for developers-------------");
    developerImpl.insertPhoneForDeveloper(devAlice,phoneAlice);
        developerImpl.insertPhoneForDeveloper(devBob,phoneBob);
        developerImpl.insertPhoneForDeveloper(devCharles,phoneCharles);

        System.out.println("---------Insert address for developers-------------");
        developerImpl.insertAddressForDeveloper(devAlice,addressAlice);
        developerImpl.insertAddressForDeveloper(devBob,addressBob);
        developerImpl.insertAddressForDeveloper(devCharles,addressCharles);

    System.out.println("----------Finding all developers-----------------");
    Collection<Developer> developerList = developerImpl.findAllDevelopers();
        for (Developer developer1:developerList){
            System.out.println(developer1.getId());
        }

    System.out.println("----------Finding developers by id-----------------");
    Developer developer = developerImpl.findDeveloperById(devAlice.getId());
    System.out.println("Found "+developer.getId()+","+developer.getPerson().getFirst_name());

    System.out.println("----------Finding developers by username-----------------");
    developer = developerImpl.findDeveloperByUsername(personCharles.getUsername());
    System.out.println("Found "+developer.getId()+","+developer.getPerson().getFirst_name()+" for username"+personCharles.getUsername());

    System.out.println("----------Finding developers by credentials-----------------");
    developer = developerImpl.findDeveloperByCredentials(personBob.getUsername(),personBob.getPassword());
    System.out.println("Found "+developer.getId()+","+developer.getPerson().getFirst_name()+" for username"+personBob.getUsername());

        System.out.println("----------Update developer-----------------");
        Person personUpdate = new Person(12,"Alicee","Wonderr","alicee","alicee","alice@wonder.comm");
        Developer devUpdate= new Developer(12,"4321rewqqq",personUpdate);
        developerImpl.updateDeveloper(12,devUpdate);

        System.out.println("----------Delete developer-----------------");
        //developerImpl.deleteDeveloper(12);
        //developerImpl.createDeveloper(devAlice); //recreating the deleted developer

    Website websiteFacebook = new Website(123,"Facebook","an online social media and social networking service",date,date,1234234,devAlice);
    Website websiteTwitter = new Website(234,"Twitter","an online news and social networking service",date,date,4321543,devBob);
    Website websiteWikipedia = new Website(345,"Wikipedia","a free online encyclopedia",date,date,3456654,devCharles);
    Website websiteCNN = new Website(456,"CNN","an American basic cable and satellite television news channel",date,date,6543345,devAlice);
    Website websiteCNET = new Website(567,"CNET","an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",date,date,5433455,devBob);
    Website websiteGizmodo = new Website(678,"Gizmodo","a design, technology, science and science fiction website that also writes articles on politics",date,date,4322345,devCharles);

    System.out.println("---------------------------Inserting websites--------------------------------");
    websiteImpl.createWebsiteForDeveloper(devAlice.getId(),websiteFacebook);
    websiteImpl.createWebsiteForDeveloper(devBob.getId(),websiteTwitter);
    websiteImpl.createWebsiteForDeveloper(devCharles.getId(),websiteWikipedia);
    websiteImpl.createWebsiteForDeveloper(devAlice.getId(),websiteCNN);
    websiteImpl.createWebsiteForDeveloper(devBob.getId(),websiteCNET);
    websiteImpl.createWebsiteForDeveloper(devCharles.getId(),websiteGizmodo);

    System.out.println("---------Finding all websites for a developer-------------");
    Collection<Website> websites = websiteImpl.findWebsitesForDeveloper(devAlice.getId());
    for (Website website:websites){
        System.out.println(website.getName());
    }
    System.out.println("-----------------Finding all websites----------------------------");
    websites  = websiteImpl.findAllWebsites();
    for (Website website:websites){
        System.out.println(website.getName());
    }

    System.out.println("-------------------Finding website by id---------------------------");
    website2 = websiteImpl.findWebsiteById(websiteFacebook.getId());
    System.out.println(website2.getId()+","+website2.getName());

    System.out.println("-------------------Update website---------------------------");
    Website websiteCNNupdate = new Website(456,"CNNnew","American cable and satellite television news channel",date2,date2,9543345,devBob);
    query_status = websiteImpl.updateWebsite(websiteCNN.getId(),websiteCNNupdate);
    if(query_status == 1)
        System.out.println("Successfully updated"+websiteCNNupdate.getId()+","+websiteCNNupdate.getName());
    else
        System.out.println("Could not update"+websiteCNNupdate.getId()+","+websiteCNNupdate.getName());


    Page pageHome = new Page(123, "Home", "Landing page", date2, date3,123434,websiteCNET);
    Page pageAbout = new Page (234, "About", "Website description", date2, date3,234545,websiteGizmodo);
    Page pageContact = new Page (345, "Contact", "Addresses, phones, and contact info", date2, date3,345656,websiteWikipedia);
    Page pagePreferences = new Page (456, "Preferences", "Where users can configure their preferences", date2, date3,456776,websiteCNN);
    Page pageProfile = new Page(567, "Profile", "Users can configure their personal information", date2, date3,567878,websiteCNET);

    System.out.println("---------------------------Inserting pages--------------------------------");
    pageImpl.createPageForWebsite(websiteCNET.getId(),pageHome);
    pageImpl.createPageForWebsite(websiteGizmodo.getId(),pageAbout);
    pageImpl.createPageForWebsite(websiteWikipedia.getId(),pageContact);
    pageImpl.createPageForWebsite(websiteCNN.getId(),pagePreferences);
    pageImpl.createPageForWebsite(websiteCNET.getId(),pageProfile);

    System.out.println("-----------------Finding all pages----------------------------");
    Collection<Page> pageList  = pageImpl.findAllPages();
    for (Page page:pageList){
        System.out.println(page.getTitle());
    }

    System.out.println("---------Finding all pages for a website-------------");
    pageList = pageImpl.findPagesForWebsite(websiteGizmodo.getId());
        for (Page page:pageList){
            System.out.println("Found "+page.getTitle()+" for website "+page.getWebsite().getName());
        }

    System.out.println("-------------------Finding page by id---------------------------");
    Page page = pageImpl.findPageById(pageHome.getId());
    System.out.println(page.getTitle());

        System.out.println("-------------------Update page---------------------------");
        Page pageHomeUpdate = new Page(123, "Home new", "Landing page new", Date.valueOf("2020-01-07"), Date.valueOf("2020-02-29"),523434,websiteWikipedia);
        query_status = pageImpl.updatePage(pageHome.getId(),pageHomeUpdate);
        if(query_status == 1)
            System.out.println("Successfully updated"+pageHomeUpdate.getId()+","+pageHomeUpdate.getTitle());
        else
            System.out.println("Could not update"+pageHomeUpdate.getId()+","+pageHomeUpdate.getTitle());


        Widget widgetHead123 = new Widget(1, "head123",0,0,"Welcome",0,Type.heading,pageHome,null);
        Widget widgetpost234 = new Widget(2, "post234",0,0,"<p>Lorem</p>",0,Type.html,pageAbout,null);
        Widget widgetHead345 = new Widget(3, "head345",0,0,"Hi",1,Type.heading,pageContact,null);
        Widget widgetIntro456 = new Widget(4, "intro456",0,0,"<h1>Hi</h1>",2,Type.html,pageContact,null);
        Widget widgetImage345 = new Widget(5, "image345",0,0,null,3,Type.image,pageContact,"/img/567.png");
        Widget widgetVideo456 = new Widget(6, "video456",0,0,null,0,Type.youTube,pagePreferences,"https://youtu.be/h67VX51QXiQ");

        System.out.println("---------------------------Inserting widgets--------------------------------");
        widgetImpl.createWidgetForPage(pageHome.getId(),widgetHead123);
        widgetImpl.createWidgetForPage(pageAbout.getId(),widgetpost234);
        widgetImpl.createWidgetForPage(pageContact.getId(),widgetHead345);
        widgetImpl.createWidgetForPage(pageContact.getId(),widgetIntro456);
        widgetImpl.createWidgetForPage(pageContact.getId(),widgetImage345);
        widgetImpl.createWidgetForPage(pagePreferences.getId(),widgetVideo456);

        System.out.println("---------------------------Finding all widgets--------------------------------");
        widgetList =  widgetImpl.findAllWidgets();
        for (Widget w:widgetList){
            System.out.println(w.getName());
        }

        System.out.println("---------------------------Finding widget by id--------------------------------");
        widget =  widgetImpl.findWidgetById(2);
        System.out.println(widget.getName());

        System.out.println("---------------------------Finding all widgets for a page--------------------------------");
        widgetList =  widgetImpl.findWidgetsForPage(pageContact.getId());
        for (Widget w:widgetList){
            System.out.println(w.getName());
        }

        System.out.println("-------------------Update widget---------------------------");
        Widget widgetUpdate = new Widget(5, "image3455",5,5,"image converted to heading",5,Type.heading,pageProfile,"/head/567.png");
        widgetImpl.updateWidget(widgetVideo456.getId(),widgetUpdate);

        System.out.println("-------------------Delete widget---------------------------");
        widgetImpl.deleteWidget(widgetVideo456.getId());
        widgetImpl.createWidgetForPage(pagePreferences.getId(),widgetVideo456); //Reinserting deleted widget

        System.out.println("-------------------Assigning website role--------------------------");
        roleImpl.assignWebsiteRole(devAlice.getId(),websiteFacebook.getId(),role.getIdByRole("owner"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteFacebook.getId(),role.getIdByRole("editor"));
        roleImpl.assignWebsiteRole(devCharles.getId(),websiteFacebook.getId(),role.getIdByRole("admin"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteTwitter.getId(),role.getIdByRole("owner"));
        roleImpl.assignWebsiteRole(devCharles.getId(),websiteTwitter.getId(),role.getIdByRole("editor"));
        roleImpl.assignWebsiteRole(devAlice.getId(),websiteTwitter.getId(),role.getIdByRole("admin"));
        roleImpl.assignWebsiteRole(devCharles.getId(),websiteWikipedia.getId(),role.getIdByRole("owner"));
        roleImpl.assignWebsiteRole(devAlice.getId(),websiteWikipedia.getId(),role.getIdByRole("editor"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteWikipedia.getId(),role.getIdByRole("admin"));
        roleImpl.assignWebsiteRole(devAlice.getId(),websiteCNN.getId(),role.getIdByRole("owner"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteCNN.getId(),role.getIdByRole("editor"));
        roleImpl.assignWebsiteRole(devCharles.getId(),websiteCNN.getId(),role.getIdByRole("admin"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteCNET.getId(),role.getIdByRole("owner"));
        roleImpl.assignWebsiteRole(devCharles.getId(),websiteCNET.getId(),role.getIdByRole("editor"));
        roleImpl.assignWebsiteRole(devAlice.getId(),websiteCNET.getId(),role.getIdByRole("admin"));
        roleImpl.assignWebsiteRole(devCharles.getId(),websiteGizmodo.getId(),role.getIdByRole("owner"));
        roleImpl.assignWebsiteRole(devAlice.getId(),websiteGizmodo.getId(),role.getIdByRole("editor"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteGizmodo.getId(),role.getIdByRole("admin"));


        System.out.println("-------------------Assigning page role--------------------------");
        roleImpl.assignPageRole(devAlice.getId(),pageHome.getId(),role.getIdByRole("editor"));
        roleImpl.assignPageRole(devBob.getId(),pageHome.getId(),role.getIdByRole("reviewer"));
        roleImpl.assignPageRole(devCharles.getId(),pageHome.getId(),role.getIdByRole("writer"));
        roleImpl.assignPageRole(devBob.getId(),pageAbout.getId(),role.getIdByRole("editor"));
        roleImpl.assignPageRole(devCharles.getId(),pageAbout.getId(),role.getIdByRole("reviewer"));
        roleImpl.assignPageRole(devAlice.getId(),pageAbout.getId(),role.getIdByRole("writer"));
        roleImpl.assignPageRole(devCharles.getId(),pageContact.getId(),role.getIdByRole("editor"));
        roleImpl.assignPageRole(devAlice.getId(),pageContact.getId(),role.getIdByRole("reviewer"));
        roleImpl.assignPageRole(devBob.getId(),pageContact.getId(),role.getIdByRole("writer"));
        roleImpl.assignPageRole(devAlice.getId(),pagePreferences.getId(),role.getIdByRole("editor"));
        roleImpl.assignPageRole(devBob.getId(),pagePreferences.getId(),role.getIdByRole("reviewer"));
        roleImpl.assignPageRole(devCharles.getId(),pagePreferences.getId(),role.getIdByRole("writer"));
        roleImpl.assignPageRole(devBob.getId(),pageProfile.getId(),role.getIdByRole("editor"));
        roleImpl.assignPageRole(devCharles.getId(),pageProfile.getId(),role.getIdByRole("reviewer"));
        roleImpl.assignPageRole(devAlice.getId(),pageProfile.getId(),role.getIdByRole("writer"));

        System.out.println("-------------------Deleting website role--------------------------");
        roleImpl.deleteWebsiteRole(devBob.getId(),websiteGizmodo.getId(),role.getIdByRole("admin"));
        roleImpl.assignWebsiteRole(devBob.getId(),websiteGizmodo.getId(),role.getIdByRole("admin")); //Reinserting deleted entry

        System.out.println("-------------------Deleting page role--------------------------");
        roleImpl.deletePageRole(devAlice.getId(),pageProfile.getId(),role.getIdByRole("writer"));
        roleImpl.assignPageRole(devAlice.getId(),pageProfile.getId(),role.getIdByRole("writer"));  //Reinserting deleted entry

        System.out.println("-------------------Assigning priviledge--------------------------");
        priviledgeImpl.deleteWebsitePriviledge(devAlice.getId(),websiteFacebook.getId(),"create");
        priviledgeImpl.assignWebsitePrivilege(devAlice.getId(),websiteFacebook.getId(),"create");
        priviledgeImpl.deletePagePriviledge(devAlice.getId(),pageHome.getId(),"update");
        priviledgeImpl.assignPagePriviledge(devAlice.getId(),pageHome.getId(),"update");

        System.out.println("-----------------------------Update queries----------------------------------");
        //Update developer - Update Charlie's primary phone number to 333-444-5555
        Phone phoneCharlesUpdate = new Phone(3,"333-444-5555",true,personCharles);
        developerImpl.updatePhoneForDeveloper(devCharles,phoneCharlesUpdate);

        //Update widget - Update the relative order of widget head345 on the page so that it's new order is 3. Note that the other widget's order needs to update as well
        widgetImpl.updateWidgetOrder(3,widgetHead345);

        //Update page - Append 'CNET - ' to the beginning of all CNET's page titles
        websiteImpl.updatePageTitleForWebsite("CNET - ",websiteCNET);

        //Update roles - Swap Charlie's and Bob's role in CNET's Home page
        roleImpl.swapPageRole(devCharles,devBob,pageHome);

        System.out.println("-----------------------------Delete queries----------------------------------");

        // Delete developer - Delete Alice's primary address
        developerImpl.deletePrimaryAddressForDeveloper(devAlice);

        // Delete widget - Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field
        widgetImpl.deleteLastWidgetInPage(pageContact);

        // Delete page - Remove the last updated page in Wikipedia
        pageImpl.deleteLastUpdatePageInWebsite(websiteWikipedia);

        // Delete website - Remove the CNET web site, as well as all related roles and privileges relating developers to the Website and Pages
        Collection<Page> pageForWebsiteToBeDeleted = pageImpl.findPagesForWebsite(websiteCNET.getId());
        for(Page p:pageForWebsiteToBeDeleted) {
            pageImpl.deletePage(p.getId());
        }
        websiteImpl.deleteWebsite(websiteCNET.getId());
        //cascade delete during table creation will automatically delete all related roles and privileges of website and page


        if(conn!=null){
        conn=null;
        System.out.println("Connection closed");
        connection.closeConnection();
        }
    }

}
