package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	@Override
	String search(String text) throws Exception {
		//Write your code here
		String result = "null";
		String error = null;
		try{
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT response FROM keywordlist WHERE keyword = ?");
			log.info(stmt.toString());
			stmt.setString(1, text);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				result = rs.getString("response");
			rs.close();
			stmt.close();
			connection.close();
		}catch(Exception e){
			// log.info(e);
			error = e.toString();
			result = "null exception";
		}
		if(error != null)
			log.info("sql error::" + error);
		return result;
	}


	private Connection getConnection() throws URISyntaxException, SQLException {
		Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);

		connection = DriverManager.getConnection(dbUrl, username, password);

		return connection;
	}

}
