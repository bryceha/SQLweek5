package entity;

import java.util.List;

public class Type {

	private int typeId;
	private String name;
	private List<Pokemon> pokemon;
	
	public Type(int typeId, String name, List<Pokemon> pokemon) {
		this.setTypeId(typeId);
		this.setName(name);
		this.setPokemon(pokemon);
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pokemon> getPokemon() {
		return pokemon;
	}

	public void setPokemon(List<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}
	
}
