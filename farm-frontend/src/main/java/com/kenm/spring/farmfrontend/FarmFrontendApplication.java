package com.kenm.spring.farmfrontend;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

import com.kenm.spring.farmfrontend.data.SamplePersonRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

@SpringBootApplication
@Theme(value = "shamba-app")
public class FarmFrontendApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(FarmFrontendApplication.class, args);
	}

    @Bean
    SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
            SqlInitializationProperties properties, SamplePersonRepository repository) {
        // This bean ensures the database is only initialized when empty
        return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
            @Override
            public boolean initializeDatabase() {
                if (repository.count() == 0L) {
                    return super.initializeDatabase();
                }
                return false;
            }
        };
    }
}
