package com.mathjazz.guihttpapijdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void init(){
//        String sqlDel = "DROP TABLE newses";
//        getJdbcTemplate().update(sqlDel);

//        String sql = "CREATE TABLE newses(news_id int,  text varchar(255), PRIMARY KEY(news_id))";
//        getJdbcTemplate().update(sql);
//        String sql = "CREATE TABLE newses(news_id int AUTO_INCREMENT PRIMARY KEY,  text varchar(255))";
//        getJdbcTemplate().update(sql);
//    }

}
//
//
