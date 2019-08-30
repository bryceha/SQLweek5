package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Type;

public class TypeDao {
	
	private Connection connection;
	private PokemonDao pokemonDao;
	private final String GET_TYPES_QUERY = "SELECT * FROM types";
	private final String GET_TYPE_BY_ID_QUERY = "SELECT * FROM types WHERE type_id = ?";
	private final String CREATE_NEW_TYPE_QUERY = "INSERT INTO types(type_name) VALUES(?)";
	private final String DELETE_TYPE_BY_ID_QUERY = "DELETE FROM types WHERE type_id = ?";
	
	public TypeDao() {
		connection = DBConnection.getConnection();
		pokemonDao = new PokemonDao();
	}
	
	public List<Type> getTypes() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TYPES_QUERY).executeQuery();
		List<Type> types = new ArrayList<Type>();
		
		while (rs.next()) {
			types.add(populateType(rs.getInt(1), rs.getString(2)));
		}
		
		return types;
	}
	
	public Type getTypeById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TYPE_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateType(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewType(String typeName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TYPE_QUERY);
		ps.setString(1, typeName);
		ps.executeUpdate();
	}
	
	public void deleteTypeById(int id) throws SQLException {
		pokemonDao.deletePokemonByTypeId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_TYPE_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	private Type populateType(int id, String name) throws SQLException {
		return new Type(id, name, pokemonDao.getPokemonByTypeId(id));
	}
}
