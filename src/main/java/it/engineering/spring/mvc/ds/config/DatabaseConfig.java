package it.engineering.spring.mvc.ds.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@PropertySource("classpath:db.config")
@EnableTransactionManagement
public class DatabaseConfig {
		@Autowired
		private Environment environment;
		
		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setUrl(environment.getProperty("url"));
			dataSource.setUsername(environment.getProperty("user"));
			dataSource.setPassword(environment.getProperty("password"));
			dataSource.setDriverClassName(environment.getProperty("driver"));
			return dataSource;
		}
		
		@Bean (name = "entityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerfactory(DataSource dataSource) {
			LocalContainerEntityManagerFactoryBean emfb = 
					new LocalContainerEntityManagerFactoryBean();
			//datasource
			emfb.setDataSource(dataSource);
			//postavi koje su klase entity klase
			emfb.setPackagesToScan(new String[] {"it.engineering.spring.mvc.ds.entity"});
			
			//koji persistence provider koristis
			JpaVendorAdapter hibernateVendorAdapter  = new HibernateJpaVendorAdapter();
			emfb.setJpaVendorAdapter(hibernateVendorAdapter);
			emfb.setJpaProperties(additionalProperties());
			return emfb;
		}
		
		private Properties additionalProperties() {
			Properties properties = new Properties();
			properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.format_sql", "true");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			return properties;		
		}
		
		@Bean
		public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(emf);
			return transactionManager;
		}
		
}
