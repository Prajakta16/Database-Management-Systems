package com.example.cs5200sp2020dharmeprajaktajpa;

import com.example.cs5200sp2020dharmeprajaktajpa.dao.*;
import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {

    @Autowired
    UniversityDao universityDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    FacultyDao facultyDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    SectionDao sectionDao;

    @Autowired
    EnrollmentDao enrollmentDao;

    @BeforeAll
    void setup() {
        List<Faculty> faculties = new ArrayList<>();

        universityDao.truncateDatabase();

        Faculty facultyAlan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
        Faculty facultyAda = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
        facultyDao.createFaculty(facultyAlan);
        facultyDao.createFaculty(facultyAda);

        Student studentAlice = new Student("alice", "password", "Alice", "Wonderland", 2020, 12000);
        Student studentBob = new Student("bob", "password", "Bob", "Hope", 2021, 23000);
        Student studentCharlie = new Student("charlie", "password", "Charlie", "Brown", 2019, 21000);
        Student studentDan = new Student("dan", "password", "Dan", "Craig", 2019, 0);
        Student studentEdward = new Student("edward", "password", "Edward", "Scissorhands", 2022, 11000);
        Student studentFrank = new Student("frank", "password", "Frank", "Herbert", 2018, 0);
        Student studentGregory = new Student("gregory", "password", "Gregory", "Peck", 2023, 10000);
        studentDao.createStudent(studentAlice);
        studentDao.createStudent(studentBob);
        studentDao.createStudent(studentCharlie);
        studentDao.createStudent(studentDan);
        studentDao.createStudent(studentEdward);
        studentDao.createStudent(studentFrank);
        studentDao.createStudent(studentGregory);

        Course course1234 = new Course("CS1234", facultyAlan);
        Course course2345 = new Course("CS2345", facultyAlan);
        Course course3456 = new Course("CS3456", facultyAda);
        Course course4567 = new Course("CS4567", facultyAda);
        courseDao.createCourse(course1234);
        courseDao.createCourse(course2345);
        courseDao.createCourse(course3456);
        courseDao.createCourse(course4567);

        courseDao.setAuthorForCourse(facultyAlan, course1234);
        courseDao.setAuthorForCourse(facultyAlan, course2345);
        courseDao.setAuthorForCourse(facultyAda, course3456);
        courseDao.setAuthorForCourse(facultyAda, course4567);

        Section section4321 = new Section(50, "SEC4321", course1234);
        Section section5432 = new Section(50, "SEC5432", course1234);
        Section section6543 = new Section(50, "SEC6543", course2345);
        Section section7654 = new Section(50, "SEC7654", course3456);
        sectionDao.createSection(section4321);
        sectionDao.createSection(section5432);
        sectionDao.createSection(section6543);
        sectionDao.createSection(section7654);

        courseDao.addSectionToCourse(section4321, course1234);
        courseDao.addSectionToCourse(section5432, course1234);
        courseDao.addSectionToCourse(section6543, course2345);
        courseDao.addSectionToCourse(section7654, course3456);

        enrollmentDao.enrollStudentInSection(studentAlice, section4321);
        enrollmentDao.enrollStudentInSection(studentAlice, section5432);
        enrollmentDao.enrollStudentInSection(studentBob, section5432);
        enrollmentDao.enrollStudentInSection(studentCharlie, section6543);

    }

    @Test
    void countUserTest() {
        assertEquals(9, personDao.findAllUsers().size());
    }

    @Test
    void countFacultiesTest() {
        assertEquals(2, facultyDao.findAllFaculty().size());
    }

    @Test
    void countStudentsTest() {
        assertEquals(7, studentDao.findAllStudents().size());
    }

    @Test
    void countCoursesTest() {
        assertEquals(4, courseDao.findAllCourses().size());
    }

    @Test
    void countSectionsTest() {
        assertEquals(4, sectionDao.findAllSections().size());
    }

    @Test
    void countCoursesAuthoredByFacultyTest() {
        Faculty f1 = facultyDao.findFacultyByUserName("alan");
        Faculty f2 = facultyDao.findFacultyByUserName("ada");
        assertEquals(2, facultyDao.findCoursesForAuthor(f1).size());
        assertEquals(2, facultyDao.findCoursesForAuthor(f2).size());
    }

    @Test
    void countSectionsPerCourseTest() {
        Course course1 = courseDao.findCourseByLabel("CS1234");
        Course course2 = courseDao.findCourseByLabel("CS2345");
        Course course3 = courseDao.findCourseByLabel("CS3456");
        Course course4 = courseDao.findCourseByLabel("CS4567");

        assertEquals(2, courseDao.findSectionForCourse(course1).size());
        assertEquals(1, courseDao.findSectionForCourse(course2).size());
        assertEquals(1, courseDao.findSectionForCourse(course3).size());
        assertEquals(0, courseDao.findSectionForCourse(course4).size());
    }

    @Test
    void countSectionEnrollmentsTest() {
        Section section1 = sectionDao.findSectionByTitle("SEC4321");
        Section section2 = sectionDao.findSectionByTitle("SEC5432");
        Section section3 = sectionDao.findSectionByTitle("SEC6543");
        Section section4 = sectionDao.findSectionByTitle("SEC7654");

        assertEquals(1, sectionDao.findStudentsInSection(section1).size());
        assertEquals(2, sectionDao.findStudentsInSection(section2).size());
        assertEquals(1, sectionDao.findStudentsInSection(section3).size());
        assertEquals(0, sectionDao.findStudentsInSection(section4).size());
    }

    @Test
    void countStudentEnrollmentsTest() {
        Student student1 = studentDao.findStudentByUsername("alice");
        Student student2 = studentDao.findStudentByUsername("bob");
        Student student3 = studentDao.findStudentByUsername("charlie");

        assertEquals(2, studentDao.findSectionsForStudent(student1).size());
        assertEquals(1, studentDao.findSectionsForStudent(student2).size());
        assertEquals(1, studentDao.findSectionsForStudent(student3).size());
    }

    @Test
    void countSectionSeatsTest() {
        Section section1 = sectionDao.findSectionByTitle("SEC4321");
        Section section2 = sectionDao.findSectionByTitle("SEC5432");
        Section section3 = sectionDao.findSectionByTitle("SEC6543");
        Section section4 = sectionDao.findSectionByTitle("SEC7654");

        assertEquals(49, section1.getSeats());
        assertEquals(48, section2.getSeats());
        assertEquals(49, section3.getSeats());
        assertEquals(50, section4.getSeats());
    }


}
