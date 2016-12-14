package Config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Initalizer of MVC
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        return new Filter[]{encodingFilter};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
}
