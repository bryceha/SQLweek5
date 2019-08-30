package entity;

public class Pokemon {
	
	private int pokemonId;
	private String pokemonName;
	
	public Pokemon(int pokemonId, String pokemonName) {
		this.setPokemonId(pokemonId);
		this.setPokemonName(pokemonName);
	}

	public int getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}

	public String getName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

}
