/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfigMapper;


import FacadeImp.LecturerFacadeImpl;
import ServiceImp.LecturerServiceImpl;
import org.muni.fi.pa165.lang_school.DAO.LecturerDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.muni.fi.pa165.lang_school.ApplicationContext;
import org.modelmapper.ModelMapper;
/**
 *
 * @author Zan Richard
 */
@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackages = { "ConfigMapper", "Enums", "Exceptions", "FacadeImp", "ServiceImp","Security"})
public class BeanMappingConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
