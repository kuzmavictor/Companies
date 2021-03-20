package ua.kharkiv.catalog.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Allows to config the necessary environment for CatalogCompanies application.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DataConfig {

    private static final String PROP_DATABASE_DRIVER = "db.driver-class-name";
    private static final String PROP_DATABASE_PASSWORD = "db.password";
    private static final String PROP_DATABASE_USERNAME = "db.username";
    private static final String PROP_DATABASE_URL = "db.url";

    private static final String PROP_HIBERNATE_HBM2DLL_AUTO = "db.hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_DIALECT = "db.hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";


    @Resource
    private Environment env;

    /**
     * Configures the datasource.
     */
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        config.setJdbcUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        config.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        config.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));

        return new HikariDataSource(config);
    }

    /**
     * Configures the entity manager.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager =
                new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("ua.kharkiv.catalog.entity");
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setJpaProperties(jpaProperties());

        return entityManager;
    }

    /**
     * Configures the liquibase for application.
     */
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/db.changelog-master.xml");
        liquibase.setDataSource(dataSource());

        return liquibase;
    }

    /**
     * Configures the {@code JpaTransactionManager}.
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager);

        return transactionManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROP_HIBERNATE_HBM2DLL_AUTO,
                env.getRequiredProperty(PROP_HIBERNATE_HBM2DLL_AUTO));
        properties.setProperty(PROP_HIBERNATE_DIALECT,
                env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.setProperty(PROP_HIBERNATE_SHOW_SQL,
                env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));

        return properties;
    }
}
