package edu.northeastern.cs5200;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.models.Module;

import java.text.ParseException;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;

class main {

    public static void main(String args[]) throws ParseException {

        DeveloperImpl developerImpl = new DeveloperImpl().getInstance();
        UserImpl userImpl = new UserImpl().getInstance();
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
        java.sql.Date date3 = Date.valueOf("2020-02-25");
        java.sql.Date date4 = Date.valueOf("2020-02-28");


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

        Person personDan = new Person (45,"dan","Martin","dan","dan","dan@martin.com");
        User userDan = new User(45,null,personDan);
        Person personEd = new Person (56,"Ed","Karaz","ed","ed","ed@kar.com");
        User userEd = new User(56,null,personEd);
        Person personFeb = new Person (67,"Feb","Brown","feb","feb","feb@brown.com");
        User userFeb = new User(67,null,personFeb);
        Person personTom = new Person (78,"Tom","Harry","tom","tom","tom@harry.com");
        User userTom = new User(78,null,personTom);
        Person personTim = new Person (89,"Tim","Jack","tim","tim","tim@jack.com");
        User userTim = new User(6897,null,personTim);

        System.out.println("---------Creating all users-------------");
        userImpl.createUser(userDan);
        userImpl.createUser(userEd);
        userImpl.createUser(userFeb);
        userImpl.createUser(userTom);
        userImpl.createUser(userTim);


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


        Widget widgetHead123 = new Widget(1, "head123",0,0,"Welcome",0,Type.heading,pageHome,null);
        Widget widgetpost234 = new Widget(2, "post234",0,0,"<p>Lorem</p>",0,Type.html,pageAbout,null);
        Widget widgetHead345 = new Widget(3, "head345",0,0,"Hi",1,Type.heading,pageContact,null);
        Widget widgetIntro456 = new Widget(4, "intro456",0,0,"<h1>Hi</h1>",2,Type.html,pageContact,null);
        Widget widgetImage345 = new Widget(5, "image345",0,0,null,3,Type.image,pageContact,"/img/567.png");
        Widget widgetVideo456 = new Widget(6, "video456",0,0,null,0,Type.youTube,pagePreferences,"https://youtu.be/h67VX51QXiQ");

        Widget widgetQ1 = new Widget(101,"Q1","How are you?",Type.question,pageProfile,date3,Module.Exam,userDan.getId(),10,100,true);
        Widget widgetQ2 = new Widget(102,"Q2","Doubt in assignmnet?",Type.question,pageProfile,date3,Module.Exam,userDan.getId(),10,100,false);
        Widget widgetQ3 = new Widget(103,"Q3","zys",Type.question,pageProfile,date3,Module.Assignment2,userTim.getId(),10,100,true);
        Widget widgetQ4 = new Widget(104,"Q4","ggg",Type.question,pageProfile,date3,Module.Assignment2,userDan.getId(),10,100,true);
        Widget widgetQ5 = new Widget(105,"Q5","hhh",Type.question,pageProfile,date3,Module.Assignment2,userTim.getId(),10,100,true);

        System.out.println("---------------------------Inserting widgets--------------------------------");
        widgetImpl.createWidgetForPage(pageHome.getId(),widgetHead123);
        widgetImpl.createWidgetForPage(pageAbout.getId(),widgetpost234);
        widgetImpl.createWidgetForPage(pageContact.getId(),widgetHead345);
        widgetImpl.createWidgetForPage(pageContact.getId(),widgetIntro456);
        widgetImpl.createWidgetForPage(pageContact.getId(),widgetImage345);
        widgetImpl.createWidgetForPage(pagePreferences.getId(),widgetVideo456);

        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetQ1);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetQ2);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetQ3);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetQ4);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetQ5);

        Widget widgetA1 = new Widget("A1","amswer1",Type.answer,userEd.getId(),date4,false,4,1,widgetQ1.getId());
        Widget widgetA2 = new Widget("A2","amswer2",Type.answer,userEd.getId(),date4,true,4,1,widgetQ1.getId());
        Widget widgetA3 = new Widget("A3","amswer3",Type.answer,userFeb.getId(),date4,true,4,1,widgetQ1.getId());
        Widget widgetA4 = new Widget("A4","amswer4",Type.answer,userFeb.getId(),date4,true,4,1,widgetQ2.getId());
        Widget widgetA5 = new Widget("A5","amswer5",Type.answer,userFeb.getId(),date4,true,4,1,widgetQ2.getId());
        Widget widgetA6 = new Widget("A6","amswer6",Type.answer,userTom.getId(),date4,false,4,1,widgetQ3.getId());
        Widget widgetA7 = new Widget("A7","amswer7",Type.answer,userTom.getId(),date4,false,4,1,widgetQ3.getId());
        Widget widgetA8 = new Widget("A8","amswer8",Type.answer,userTim.getId(),date,true,4,1,widgetQ4.getId());



        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA1);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA2);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA3);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA4);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA5);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA6);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA7);
        widgetImpl.createWidgetForPage(pageProfile.getId(),widgetA8);



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

/*
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

*/
        System.out.println("-----------------------------Execute procedures----------------------------------");
        widgetImpl.getUnansweredQuestions();
        widgetImpl.endorsedUsersForWeek(date3,date4);

        if(conn!=null){
            conn=null;
            System.out.println("Connection closed");
            connection.closeConnection();
        }
    }

}
