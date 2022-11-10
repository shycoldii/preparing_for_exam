package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация JPA.
 * <p>
 * LocalEntityManagerFactoryBean
 * <p>
 * is the simplest and the most limited.
 * You cannot refer to an existing JDBC DataSource bean definition
 * and no support for global transactions exists.
 * <p>
 * LocalContainerEntityManagerFactoryBean
 * <p>
 * is the most powerful JPA setup option, allowing for flexible local configuration
 * within the application. It supports links to an existing JDBC DataSource,
 * supports both local and global transactions
 *
 * @author Darya Alexandrova
 * @since 2022.10.28
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"pure_jpa"})
//Properties files registered via @PropertySource are automatically added
// to the Environment in
// org.springframework.context.annotation.ConfigurationClassParser.
// doProcessConfigurationClass(ConfigurationClass, SourceClass) while processing
// @Configuration classes.
@ComponentScan(basePackages = {"pure_jpa"})
@PropertySource({"classpath:db/db.properties"})
public class TestJpaConfig {

    @Value("${db.dialect}")
    private String dialect;
    @Value("${db.hbm2ddl}")
    private String hbm2ddl;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("pure_jpa.model");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(new
                HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        //In the preceding method, LocalContainerEntityManager FactoryBean is created
        // by explicit instantiation, and is not created by Spring.
        // So afterPropertiesSet(), which initializes the factory object must be
        // called explicitly.
        factoryBean.afterPropertiesSet();
        //The LocalContainerEntityManagerFactoryBean object creates an EntityManagerFactory bean as returned by
        //the PersistenceProvider implementation; in this case, org.hibernate.jpa.HibernatePersistenceProvider.
        //The EntityManagerFactory instance created by the
        //factory bean is retrieved by calling factoryBean. getNativeEntityManagerFactory().
        // The HibernatePersistenceProvider is not visible in the preceding configuration.
        // The only link to the persistence technology used under the hood is
        // HibernateJpaVendorAdapter. The HibernateJpaVendorAdapter exposes Hibernate’s
        // persistence provider and EntityManager extension interface.
        // The locations where the persistence metadata can be found are set by the
        // setPackagesToScan(...) method, and the datasource bean is required
        // as well to properly create an EntityManagerFactory.
        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).generateUniqueName(true).addScript("db/init_script.sql").build();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", dialect);
        hibernateProp.put("hibernate.hbm2ddl.auto", hbm2ddl);
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", false);
        return hibernateProp;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


}
