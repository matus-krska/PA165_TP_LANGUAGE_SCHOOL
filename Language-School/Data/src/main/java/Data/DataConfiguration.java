package Data;

import ConfigMapper.BeanMappingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(BeanMappingConfiguration.class)
@ComponentScan(basePackages = {"Data"})
public class DataConfiguration {

    final static Logger log = LoggerFactory.getLogger(DataConfiguration.class);

    @Autowired
    DataFacadeInterface sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
