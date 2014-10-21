package pl.java.scalatech.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Sławomir Borowiec
 *         Module name : spring4WithoutXml
 *         Creating time : 25 lip 2014 12:13:23
 */
@EnableTransactionManagement
@PropertySource("classpath:ds.properties")
@Import(value = { PropertiesLoader.class })
@EnableJpaRepositories("pl.java.scalatech.repository")
public class DsConfig {
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${spring.jpa.show-sql}")
    private boolean sqlShowFlag;
    @Value("${spring.jpa.generate-ddl}")
    private String ddlGenerate;
    @Value("${hibernate.show.sql}")
    private boolean formatSql;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    /*@Bean
    public FullTextEntityManager fullTextEntityManager() {
        return Search.getFullTextEntityManager(entityManagerFactory().createEntityManager());
    }*/

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("pl.java.scalatech.domain");
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        return entityManagerFactoryBean;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddlGenerate);
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", "" + sqlShowFlag);
        properties.setProperty("hibernate.format_sql", "" + formatSql);
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
