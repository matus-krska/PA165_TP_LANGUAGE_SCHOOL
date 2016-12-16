package Config;

import ConfigMapper.BeanMappingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(BeanMappingConfiguration.class)
@ComponentScan(basePackages = {"Config"})
public class DataLoaderConfig {

    @Autowired
    DataLoader dataLoader;

    @PostConstruct
    public void dataLoading() throws IOException {
        dataLoader.loadData();
    }
}

