package Config;

import ConfigMapper.BeanMappingConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Configuration of MVC
 * @author Matus Krska, 410073
 * @since 1.0
 */
@EnableWebMvc
@Configuration
@Import({BeanMappingConfiguration.class})
@ComponentScan(basePackages = {"Config","Enums"/*,"Security"*/})
public class SpringMVCConfig extends WebMvcConfigurerAdapter
{
    @Inject
    DataLoader dataLoader;

    @PostConstruct
    public void loadData()
    {
        dataLoader.loadData();
    }

    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     */
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
