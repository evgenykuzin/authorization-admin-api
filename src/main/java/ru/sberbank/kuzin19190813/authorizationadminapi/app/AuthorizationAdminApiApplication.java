package ru.sberbank.kuzin19190813.authorizationadminapi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//@ComponentScan("ru.sberbank.kuzin19190813.authorizationadminapi")
@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = {"ru.sberbank.kuzin19190813.authorizationadminapi"})
@EnableTransactionManagement
public class AuthorizationAdminApiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationAdminApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuthorizationAdminApiApplication.class);
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory() throws PropertyVetoException {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        LocalContainerEntityManagerFactoryBean factory;
//        factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setPackagesToScan("ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model");
//        return factory.getObject();
//    }

//    @Bean
//    public DataSource dataSource() {
//        ;
//    }
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.pro100denysko.app.resttest.model");
//        factory.setDataSource(dataSource());
//        return factory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());
//        return txManager;
//    }

//    @Bean
//    public UserService userService(UserRepository userRepository) {
//        return new UserService(userRepository);
//    }

}
