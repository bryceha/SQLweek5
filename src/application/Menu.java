package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PokemonDao;
import dao.TypeDao;
import entity.Pokemon;
import entity.Type;

public class Menu {
	
	private TypeDao typeDao = new TypeDao();
	private PokemonDao pokemonDao = new PokemonDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display All Types", 
			"Display a Type", 
			"Create Type", 
			"Delete Type",
			"Display All Pokemon",
			"Display a Pokemon",
			"Create Pokemon",
			"Delete Pokemon");

	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
		try {
			
			if (selection.equals("1")) {
				displayAllTypes();
			} else if (selection.equals("2")) {
				displayAType();
			} else if (selection.equals("3")) {
				createType();
			} else if (selection.equals("4")) {
				deleteType();
			} else if (selection.equals("5")) {
				displayAllPokemon();
			} else if (selection.equals("6")) {
				displayAPokemon();
			} else if (selection.equals("7")) {
				createPokemon();
			} else if (selection.equals("8")) {
				deletePokemon();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			System.out.println("Press enter to continue...");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n---------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayAllTypes() throws SQLException {
		List<Type> types = typeDao.getTypes();
		for (Type type : types) {
			System.out.println(type.getTypeId() + ": " + type.getName());
		}
	}
	
	private void displayAType() throws SQLException {
		System.out.print("Enter type id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Type type = typeDao.getTypeById(id);
		System.out.println(type.getTypeId() + ": " + type.getName());
		for (Pokemon pokemon : type.getPokemon()) {
			System.out.println("\tPokemonId: " + pokemon.getPokemonId() + " Name: " + pokemon.getName());
		}
	}
	
	private void createType() throws SQLException {
		System.out.println("Enter new type name: ");
		String typeName = scanner.nextLine();
		typeDao.createNewType(typeName);
	}
	
	private void deleteType() throws SQLException {
		System.out.print("Enter Type id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		typeDao.deleteTypeById(id);
	}
	
	private void displayAllPokemon() throws SQLException {
		List<Pokemon> pokemons = pokemonDao.getPokemon();
		for (Pokemon pokemon : pokemons) {
			System.out.println(pokemon.getPokemonId() + " : " +  pokemon.getName());
		}
	}
	
	private void displayAPokemon() throws SQLException {
		System.out.print("Enter pokemon id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Pokemon pokemon = pokemonDao.getPokemonById(id);
		System.out.println(pokemon.getPokemonId() + " : " + pokemon.getName());
	}
	
	private void createPokemon() throws SQLException {
		System.out.print("Enter name of new pokemon: ");
		String pokemonName = scanner.nextLine();
		System.out.print("Enter type id of new pokemon: ");
		int typeId = Integer.parseInt(scanner.nextLine());
		pokemonDao.createNewPokemon(pokemonName, typeId);
		
	}
	
	private void deletePokemon() throws SQLException {
		System.out.print("Enter pokemon id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		pokemonDao.deletePokemonById(id);
	}
}
