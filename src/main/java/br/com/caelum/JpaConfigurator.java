package br.com.caelum;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfigurator {

    //DataSource sem Pool de Conexoes
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
////	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
////	    dataSource.setUrl("jdbc:mysql://localhost/projeto_jpa");
////	    dataSource.setUsername("root");
////	    dataSource.setPassword("");
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://192.168.56.105:5432/projeto_jpa");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres");
//
//        return dataSource;
//    }

    @Bean
    public DataSource getDataSource() throws PropertyVetoException { //Datasource com Pool de Conexoes
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        
        dataSource.setDriverClass("org.postgresql.Driver");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        dataSource.setJdbcUrl("jdbc:postgresql://192.168.56.105:5432/projeto_jpa");
        
        dataSource.setMinPoolSize(3);
        dataSource.setMaxPoolSize(5);
        dataSource.setNumHelperThreads(15);
        
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setPackagesToScan("br.com.caelum");
        entityManagerFactory.setDataSource(dataSource);

        entityManagerFactory
                .setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props = new Properties();

//		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        entityManagerFactory.setJpaProperties(props);
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager getTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

}
