/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST.Assemblers;


import DTO.StudentDTO;
import REST.Controllers.CoursesControllerHateoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 * @author zanri
 */
@Component
public class StudentResourceAssembler implements ResourceAssembler<StudentDTO, Resource<StudentDTO>>{
    @Override
    public Resource<StudentDTO> toResource(StudentDTO studentDTO) {
//        Resource<StudentDTO> studentResource = new Resource<StudentDTO>(studentDTO);
//
//        try {
//            studentResource.add(linkTo(StudentsControllerHateoas.class).slash(studentDTO.getId()).withSelfRel());
//            studentResource.add(linkTo(StudentsControllerHateoas.class).slash(studentDTO.getId()).withRel("DELETE"));
//
//        } catch (Exception ex) {
//            Logger.getLogger(LecturerResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturersControllerHateoas", ex);
//        }
//
//        return studentResource;
        return null;
    }
    
}
