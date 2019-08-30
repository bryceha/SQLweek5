package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Pokemon;

public class PokemonDao {
	
	private Connection connection;
	private final String GET_POKEMON_BY_TYPE_ID_QUERY = "SELECT * FROM pokemon WHERE type_id = ?";
	private final String DELETE_POKEMON_BY_TYPE_ID_QUERY = "DELETE FROM pokemon WHERE type_id = ?";
	private final String CREATE_NEW_POKEMON_QUERY = "INSERT INTO pokemon(pokemon_name, type_id) VALUES(?, ?)";
	private final String DELETE_POKEMON_BY_ID_QUERY = "DELETE FROM pokemon WHERE pokemon_id = ?";
	private final String GET_POKEMON_QUERY = "SELECT * FROM pokemon";
	private final String GET_POKEMON_BY_ID_QUERY = "SELECT * FROM pokemon WHERE pokemon_id = ?";
	
	public PokemonDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Pokemon> getPokemon() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_POKEMON_QUERY).executeQuery();
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		while (rs.next()) {
			pokemon.add(populatePokemon(rs.getInt(1), rs.getString(2)));
		}
		
		return pokemon;
	}
	
	public Pokemon getPokemonById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_POKEMON_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populatePokemon(rs.getInt(1), rs.getString(2));
	}

	public List<Pokemon> getPokemonByTypeId(int typeId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_POKEMON_BY_TYPE_ID_QUERY);
		ps.setInt(1, typeId);
		ResultSet rs = ps.executeQuery();
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		while (rs.next()) {
			pokemon.add(new Pokemon(rs.getInt(1), rs.getString(2)));
		}
		
		return pokemon;
	}
	
	public void createNewPokemon(String pokemonName, int typeId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_POKEMON_QUERY);
		ps.setString(1, pokemonName);
		ps.setInt(2, typeId);
		ps.executeUpdate();
	}
	
	public void deletePokemonByTypeId(int typeId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_POKEMON_BY_TYPE_ID_QUERY);
		ps.setInt(1, typeId);
		ps.executeUpdate();
	}
	
	public void deletePokemonById(int pokemonId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_POKEMON_BY_ID_QUERY);
		ps.setInt(1, pokemonId);
		ps.executeUpdate();
	}
	
	private Pokemon populatePokemon(int id, String name) {
		return new Pokemon(id, name);
	}

}
