package Data;

import ServiceImp.*;
import org.muni.fi.pa165.lang_school.entities.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Lukas Daubner (410034)
 */
@Component
@Transactional
public class DataFacadeImpl implements DataFacadeInterface {

	final static Logger log = LoggerFactory.getLogger(DataFacadeImpl.class);

	@Autowired
	private StudentServiceImpl studentService;
	@Autowired
	private CourseServiceImpl courseService;
	@Autowired
	private LectureServiceImpl lectureService;
	@Autowired
	private LecturerServiceImpl lecturerService;
	@Autowired
	private UserServiceImpl userService;

	@Override
	public void loadData() {
//            User user1 = new User();
//            user1.setEmail("a@email.cz");
//            user1.setPasswordHash("a");
//
//            userService.registerUser(user1, user1.getPasswordHash());
//
//            User user2 = new User();
//            user2.setEmail("b@email.cz");
//            user2.setPasswordHash("b");
//
//            userService.registerUser(user2, user2.getPasswordHash());
//
//            User user3 = new User();
//            user3.setEmail("c@email.cz");
//            user3.setPasswordHash("c");
//
//            userService.registerUser(user3, user3.getPasswordHash());
//            
//            Student s1 = new Student();
//            s1.setName("A");
//            s1.setSurname("A");
//            
//
//            Student s2 = new Student();
//            s2.setName("B");
//            s2.setSurname("B");
//
//            Course c1 = new Course();
//            c1.setLanguage("Errnglish");
//            c1.setName("Errnglish 4 Aliens");
//            c1.setProficiencyLevel(ProficiencyLevel.B2);
//
//            Course c2 = new Course();
//            c2.setLanguage("Klingon");
//            c2.setName("Klingon 101");
//            c2.setProficiencyLevel(ProficiencyLevel.A1);
//
//            Lecture l11 = new Lecture();
//            l11.setClassroomId("A96");
//            l11.setDayTime(LocalDateTime.of(1979, Month.OCTOBER, 12, 0, 0));
//            l11.setTopic("Guidenance");
//
//            Lecture l12 = new Lecture();
//            l12.setClassroomId("A96");
//            l12.setDayTime(LocalDateTime.of(1980, Month.JANUARY, 1, 0, 0));
//            l12.setTopic("Food and dirnk");
//
//            Lecture l13 = new Lecture();
//            l13.setClassroomId("A96");
//            l13.setDayTime(LocalDateTime.of(1982, Month.JANUARY, 1, 0, 0));
//            l13.setTopic("Answers");
//
//            Lecture l21 = new Lecture();
//            l21.setClassroomId("C56");
//            l21.setDayTime(LocalDateTime.of(1977, Month.MAY, 25, 0, 0));
//            l21.setTopic("Hope");
//
//            Lecture l22 = new Lecture();
//            l22.setClassroomId("C56");
//            l22.setDayTime(LocalDateTime.of(1980, Month.MAY, 17, 0, 0));
//            l22.setTopic("Empire");
//
//            Lecture l23 = new Lecture();
//            l23.setClassroomId("C56");
//            l23.setDayTime(LocalDateTime.of(1983, Month.MAY, 25, 0, 0));
//            l23.setTopic("Return");
//
//            Lecturer lect1 = new Lecturer();
//            lect1.setFirstName("Marvin");
//            lect1.setSurname("Android");
//            lect1.setNickname("Paranoid");
//            lect1.setEmail("marvin.gpp@sirius.cyber.sir");
//            lect1.setPasswordHash("Hash");
//
//            Lecturer lect2 = new Lecturer();
//            lect2.setFirstName("Gandalf");
//            lect2.setSurname("the Gray");
//            lect2.setNickname("Olórin");
//            lect2.setEmail("gandalf@mainar.tol");
//            lect2.setPasswordHash("Hash");
//
//            
//
//            lect1.addLanguage(lan11);
//            lan11.setLecturer(lect1);
//
//            lect2.addLanguage(lan21);
//            lan21.setLecturer(lect2);
//
//            lect2.addLanguage(lan22);
//            lan22.setLecturer(lect2);
//
//            l11.addCourse(c1);
//            l12.addCourse(c1);
//            l13.addCourse(c1);
//            c1.addLectures(Arrays.asList(l11, l12, l13));
//
//            l21.addCourse(c2);
//            l22.addCourse(c2);
//            l23.addCourse(c2);
//            c2.addLectures(Arrays.asList(l21, l22, l23));
//
//            l11.addLecturer(lect1);
//            l12.addLecturer(lect1);
//            l13.addLecturer(lect1);
//            l23.addLecturer(lect1);
//            lect1.setListOfLectures(Arrays.asList(l11, l12, l13, l23));
//
//            l21.addLecturer(lect2);
//            l22.addLecturer(lect2);
//            lect2.setListOfLectures(Arrays.asList(l21, l22));
//
//            l11.addStudent(s1);
//            l12.addStudent(s1);
//            l13.addStudent(s1);
//            l21.addStudent(s1);
//            s1.addListOfLectures(Arrays.asList(l11, l12, l13, l21));
//
//            l11.addStudent(s2);
//            l12.addStudent(s2);
//            l13.addStudent(s2);
//            l21.addStudent(s2);
//            l22.addStudent(s2);
//            l23.addStudent(s2);
//            s2.addListOfLectures(Arrays.asList(l11, l12, l13, l21, l22, l23));
//
//            studentService.create(s1);
//            studentService.create(s2);
//
//            courseService.create(c1);
//            courseService.create(c2);
//
//            lectureService.create(l11);
//            lectureService.create(l12);
//            lectureService.create(l13);
//            lectureService.create(l21);
//            lectureService.create(l22);
//            lectureService.create(l23);
//
//            lecturerService.create(lect1);
//            lecturerService.create(lect2);
//
//            languageService.create(lan11);
//            languageService.create(lan21);
//            languageService.create(lan22);

	}

}
