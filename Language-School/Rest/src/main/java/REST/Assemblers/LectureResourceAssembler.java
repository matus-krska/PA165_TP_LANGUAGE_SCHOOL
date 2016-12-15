/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST.Assemblers;

import DTO.LectureDTO;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 * @author zanri
 */
public class LectureResourceAssembler implements ResourceAssembler<LectureDTO, Resource<LectureDTO>> {
    @Override
    public Resource<LectureDTO> toResource(LectureDTO studentDTO) {
//        Resource<LectureDTO> studentResource = new Resource<StudentDTO>(studentDTO);
//
//        try {
//            studentResource.add(linkTo(CoursesControllerHateoas.class).slash(studentDTO.getId()).withSelfRel());
//            studentResource.add(linkTo(CoursesControllerHateoas.class).slash(studentDTO.getId()).withRel("DELETE"));
//
//        } catch (Exception ex) {
//            Logger.getLogger(LecturerResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturersControllerHateoas", ex);
//        }
//
//        return studentResource;
        return null;
    }
    
}
