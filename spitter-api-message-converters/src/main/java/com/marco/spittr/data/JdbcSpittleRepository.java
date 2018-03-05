package com.marco.spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.marco.spittr.Spittle;

public class JdbcSpittleRepository implements SpittleRepository {

	private JdbcOperations jdbc;
		
	@Autowired
	public JdbcSpittleRepository(JdbcOperations jdbc) {		
		this.jdbc = jdbc;
	}

	@Override
	public List<Spittle> findRecentSpittles() {
		return jdbc.query(
		        "select id, message, created_at, latitude, longitude" +
		        " from Spittle" +
		        " order by created_at desc limit 20",
		        new SpittleRowMapper());
	}

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return jdbc.query(
		        "select id, message, created_at, latitude, longitude" +
		        " from Spittle" +
		        " where id < ?" +
		        " order by created_at desc limit 20",
		        new SpittleRowMapper(), max);
	}

	@Override
	public Spittle findOne(long id) {

		try {
			
			return jdbc.queryForObject(
			        "select id, message, created_at, latitude, longitude" +
			        " from Spittle" +
			        " where id = ?",
			        new SpittleRowMapper(), id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new SpittleNotFoundException(id);
		}				
	}

	@Override
	public Spittle save(Spittle spittle) {
		String sql = "insert into Spittle (message, created_at, latitude, longitude)" +
		        " values (?, ?, ?, ?)";
		
		PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(sql,
					Types.VARCHAR, Types.TIMESTAMP, Types.DOUBLE, Types.DOUBLE);		
		statementCreatorFactory.setReturnGeneratedKeys(true);
		PreparedStatementCreator creator = statementCreatorFactory.newPreparedStatementCreator(new Object[] {
			spittle.getMessage(), spittle.getTime(), spittle.getLatitude(), spittle.getLongitude()					
		});
				
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(creator, keyHolder);
		return new Spittle(keyHolder.getKey().longValue(), spittle.getMessage(), spittle.getTime(), spittle.getLongitude(), spittle.getLatitude());		
	}

	private static class SpittleRowMapper implements RowMapper<Spittle> {

		@Override
		public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Spittle(
			          rs.getLong("id"),
			          rs.getString("message"), 
			          rs.getDate("created_at"), 
			          rs.getDouble("longitude"), 
			          rs.getDouble("latitude"));
		}
		
		
		
	}
}
