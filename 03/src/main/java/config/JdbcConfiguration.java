package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Главная конфигурация данного модуля.
 *
 * @author Darya Alexandrova
 * @since 2022.09.18
 */
@Configuration
@ComponentScan(basePackages = {"repo", "service","entity"})
@EnableTransactionManagement(proxyTargetClass = true)
public class JdbcConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate());
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).generateUniqueName(true).addScript("db/init_script.sql").build();
    }

    @Bean(destroyMethod = "destroy")
    public CleanUp cleanUp() {
        System.out.println("УНИЧТОЖАЮ!");
        return new CleanUp(jdbcTemplate());
    }

    //транзакции
    @Bean
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(transactionManager());
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}

// necessary to gracefully shutdown the database
class CleanUp {
    private JdbcTemplate jdbcTemplate;

    CleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void destroy() {
        jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
    }


    /*
    Способ с реальной бд
    Еще можно с
    DataSourceInitializer initializer = new DataSourceInitializer();

	initializer.setDataSource(this.dataSource);
	ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
	databasePopulator.addScript(
			new ClassPathResource("/org/springframework/cloud/task/schema-h2.sql"));
	initializer.setDatabasePopulator(databasePopulator);

	initializer.afterPPlatformTransactionManagerropertiesSet();
     */
    /*@Bean
    public DataSource dataSource() {
        try {
            var dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver =
                    (Class<? extends Driver>) Class.
                            forName(driverClassName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            DatabasePopulatorUtils.execute(databasePopulator(),
                    dataSource);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }
    @Value("classpath:db/schema.sql")
    private Resource schemaScript;

    @Value("classpath:db/test-data.sql")
    private Resource dataScript;

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator =
                 new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
    return populator;
}
    */


}
