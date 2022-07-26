package com.lawencon.config;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.context.annotation.Bean;

import com.lawencon.base.ConnHandler;

//@Configuration
public class HibernateSearchConfiguration {
	
	@Bean
    public SearchSession getFullTextEntityManager() throws InterruptedException {
		SearchSession searchSession = Search.session(ConnHandler.getManager());
        searchSession.massIndexer().startAndWait();
        return searchSession;
    }
	
}