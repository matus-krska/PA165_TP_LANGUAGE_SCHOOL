/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeImp;

import ConfigMapper.BeanMapper;
import ConfigMapper.BeanMappingConfiguration;
import DTO.StudentDTO;
import ServiceImp.LecturerServiceImpl;
import ServiceImp.StudentServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;

import org.dozer.Mapper;
import org.dozer.classmap.DozerClass;
import org.dozer.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.muni.fi.pa165.lang_school.entities.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author zanri
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StudentFacadeImplTest {
    
    @Mock
    private BeanMapper mapper;

    @Mock
    private StudentServiceImpl studentService;

    private StudentFacadeImpl studentFacade;

    private StudentDTO student;
   
    
    @BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Student())).when(mapper).mapTo(any(StudentDTO.class), eq(Student.class));
        doReturn(Optional.of(new StudentDTO())).when(mapper).mapTo(any(Student.class), eq(StudentDTO.class));
        
        studentFacade = new StudentFacadeImpl(studentService, mapper);
    }

    /**
     * Initialize instance of Lecturer and Lecture for test purposes
     */
    @BeforeMethod
    public void init() {
        student = new StudentDTO();
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(studentService);
    }

    /**
     * Test of registerStudent method, of class StudentFacadeImpl.
     */
    @Test
    public void testRegisterStudent() {
        studentFacade.registerStudent(student);
        verify(studentService, times(1)).addStudent(any(Student.class));
    }

    /**
     * Test of updateStudent method, of class StudentFacadeImpl.
     */
    @Test
    public void testUpdateStudent() {
        studentFacade.updateStudent(student);
        verify(studentService, times(1)).updateStudent(any(Student.class));
    }

    /**
     * Test of findById method, of class StudentFacadeImpl.
     */
    @Test
    public void testFindById() {
        studentFacade.findById(Long.MAX_VALUE);
        verify(studentService, times(1)).findById(any(Long.class));
    }

    /**
     * Test of filterByNameSurname method, of class StudentFacadeImpl.
     */
    @Test
    public void testFilterByNameSurname() {
        studentFacade.filterByNameSurname("Simon", "Hyben");
        verify(studentService, times(1)).findByNameSurname(any(String.class), any(String.class));
    }

    /**
     * Test of findByIdNameAndSurname method, of class StudentFacadeImpl.
     */
    @Test
    public void testFindByIdNameAndSurname() {
        studentFacade.findByIdNameAndSurname(1l, "Simon", "Hyben");
        verify(studentService, times(1)).findByIdNameAndSurname(any(Long.class),any(String.class), any(String.class));
    }
    
}
