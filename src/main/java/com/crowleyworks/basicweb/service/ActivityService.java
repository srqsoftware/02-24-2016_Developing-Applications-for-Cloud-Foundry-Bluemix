package com.crowleyworks.basicweb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.crowleyworks.basicweb.model.Activity;

public class ActivityService {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public void create(Activity a) throws Exception {
		if (a == null) {
			throw new Exception("Activity is null");
		}
		
		final Activity x = a;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	        	StringBuffer query = new StringBuffer("insert into activities (userid, location, distance, duration, date) VALUES (?, ?, ?, ?, ?)");
	            PreparedStatement ps = connection.prepareStatement(query.toString(), new String[] {"id"});
	            ps.setString(1, x.getUserId());
	            ps.setString(2, x.getLocation());
	            ps.setFloat(3, x.getDistance());
	            ps.setString(4, x.getDuration());
	            ps.setDate(5, new java.sql.Date(x.getDate().getTime()));
	            return ps;
	        }
	    };
		jdbcTemplate.update(psc, keyHolder);
		a.setId(keyHolder.getKey().intValue());
	}
	
	public void update(final Activity a) throws Exception {
			String query = "update activities set location=?, distance=?, duration=? where id=?";
				jdbcTemplate.update(query, new Object[] { a.getLocation(), a.getDistance(), a.getDuration(), a.getId() });
	}
	
	public void delete(final Integer id) throws Exception {
		String query = "delete from activities where id = ?";
		jdbcTemplate.update(query, new Object[] { new Integer(id) });
	}
	
	public Activity get(int id) throws Exception {
		StringBuffer query = new StringBuffer("select id, userid, location, distance, duration, date from activities where id = ?");
		return jdbcTemplate.queryForObject(query.toString(), new Object[] {new Integer(id)} , new ActivityRowMapper());
	}
	
	public List<Activity> getMany() {
		StringBuffer query = new StringBuffer("select id, userid, location, distance, duration, date from activities");
		return jdbcTemplate.query(query.toString(), new ActivityRowMapper());
	}
		

	public static final class ActivityRowMapper implements RowMapper<Activity> {

		@Override
		public Activity mapRow(ResultSet rs, int arg1) throws SQLException {
			Activity a = new Activity();
			a.setId(rs.getInt("id"));
			a.setUserId(rs.getString("userid"));
			a.setLocation(rs.getString("location"));
			a.setDistance(rs.getFloat("distance"));
			a.setDuration(rs.getString("duration"));
			a.setDate(new Date(rs.getDate("date").getTime()));
			return a;
		}
	}


}
