package com.crowleyworks.basicweb.config;

import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConf {

	@Bean
	public DriverManagerDataSource dataSource() {		
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		Map<String,String> allEnv = System.getenv();
		
		ObjectMapper om = new ObjectMapper();
		try {
			String VCS = allEnv.get("VCAP_SERVICES");
			JsonNode jsonEnv = om.readTree(VCS);
			JsonNode jsonDb = jsonEnv.get("cleardb");
			JsonNode firstCred = jsonDb.get(0);
			JsonNode credentials = firstCred.get("credentials");
			String jdbcUrl = credentials.get("jdbcUrl").getTextValue();
			dmds.setUrl(jdbcUrl);
			dmds.setDriverClassName("com.mysql.jdbc.Driver");
			String userName = credentials.get("username").getTextValue();
			dmds.setUsername(userName);
			String pw = credentials.get("password").getTextValue();
			dmds.setPassword(pw);
			return dmds;
		} catch (Exception e) {
			System.err.println("VCAP_SERVICES not detected: " + e.getLocalizedMessage());
		}
		
		return null;
	}
}
