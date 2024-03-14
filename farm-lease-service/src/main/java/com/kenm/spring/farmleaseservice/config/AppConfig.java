/**
 * 
 */
package com.kenm.spring.farmleaseservice.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * @author User
 *
 */
@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.kenm.spring.farmleaseservice.repository")
@ComponentScan(basePackages = "com.kenm.spring.farmleaseservice")
public class AppConfig {
	@Bean
	public DataSource dataSource() {
	     // Configure your data source here
	     // For example, using a connection pool like HikariCP
	     HikariConfig config = new HikariConfig();
	     config.setJdbcUrl("jdbc:mysql://localhost:3306/farm_db");
	     config.setUsername("root");
	     config.setPassword("");
	     config.setDriverClassName("com.mysql.cj.jdbc.Driver");
	     return new HikariDataSource(config);
	 }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.kenm.spring.farmleaseservice.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}