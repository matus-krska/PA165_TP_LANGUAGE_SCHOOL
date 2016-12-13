/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfigMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author zanri
 */
@Named
public class BeanMapper {
private ModelMapper modelMapper;

    @Inject
    public BeanMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
    }

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
            List<T> mappedCollection = new ArrayList<>();
            for (Object object : objects) {
                    mappedCollection.add(modelMapper.map(object, mapToClass));
            }
            return mappedCollection;
    }

    public <T> Optional<T> mapTo(Object u, Class<T> mapToClass) {
            return Optional.ofNullable(modelMapper.map(u, mapToClass));
    }

    public boolean isCollection(Object obj) {
            return (obj instanceof Collection) || (obj instanceof Map);
    }
}
