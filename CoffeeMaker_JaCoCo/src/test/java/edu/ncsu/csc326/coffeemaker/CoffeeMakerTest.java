package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Javier Aguerri
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}
	
	@Test
	public void addRecipe2() {
		coffeeMaker.addRecipe(recipe1);
		Recipe[] recipeArr = coffeeMaker.getRecipes();
		assertTrue(recipeArr[0].equals(recipe1));
	}
	
	@Test
	public void addRecipe3() {
		coffeeMaker.addRecipe(recipe1);
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}
	
	@Test
	public void addRecipe4() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertTrue(coffeeMaker.addRecipe(recipe3));		
	}
	
	@Test
	public void addRecipe5() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}
	
	@Test
	public void deleteRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.deleteRecipe(-1));
	}
	
	@Test
	public void deleteRecipe2() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.deleteRecipe(2));
	}
	
	@Test
	public void deleteRecipe3() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe4);
		assertEquals(coffeeMaker.deleteRecipe(2),recipe3.getName());
	}
	
	@Test
	public void editRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertNull(coffeeMaker.editRecipe(-1, recipe3));
	}
	
	@Test
	public void editRecipe2() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertNull(coffeeMaker.editRecipe(3, recipe3));
	}
	
	@Test
	public void editRecipe3() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertNull(coffeeMaker.editRecipe(5, recipe3));
	}
	
	@Test
	public void editRecipe5() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals(coffeeMaker.editRecipe(1, recipe3),recipe3.getName());
	}
	/**
	 * @throws InventoryException  if there was an error parsing the quantity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventory1a() throws InventoryException {
		coffeeMaker.addInventory(null, "1", "1", "1");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory1b() throws InventoryException {
		coffeeMaker.addInventory("-3", "1", "1", "1");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory1c() throws InventoryException {
		coffeeMaker.addInventory("asdf", "1", "1", "1");
	}
	
	@Test
	public void testAddInventory1d(){
		try {
			coffeeMaker.addInventory("2", "1", "1", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory1e(){
		try {
			coffeeMaker.addInventory("0", "1", "1", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory1f(){
		try {
			coffeeMaker.addInventory("-0", "1", "1", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = InventoryException.class)
	public void testAddInventory2a() throws InventoryException {
		coffeeMaker.addInventory("1", null, "1", "1");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory2b() throws InventoryException {
		coffeeMaker.addInventory("1", "-3", "1", "1");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory2c() throws InventoryException {
		coffeeMaker.addInventory("1", "asdf", "1", "1");
	}
	
	@Test
	public void testAddInventory2d(){
		try {
			coffeeMaker.addInventory("1", "2", "1", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory2e(){
		try {
			coffeeMaker.addInventory("1", "0", "1", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory2f(){
		try {
			coffeeMaker.addInventory("1", "-0", "1", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory3a() throws InventoryException {
		coffeeMaker.addInventory("1", "1", null, "1");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory3b() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "-3", "1");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory3c() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "asdf", "1");
	}
	
	@Test
	public void testAddInventory3d(){
		try {
			coffeeMaker.addInventory("1", "1", "2", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory3e(){
		try {
			coffeeMaker.addInventory("1", "1", "0", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory3f(){
		try {
			coffeeMaker.addInventory("1", "1", "-0", "1");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory4a() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "1", null);
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory4b() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "1", "-3");
	}
	
	@Test(expected = InventoryException.class)
	public void testAddInventory4c() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "1", "asdf");
	}
	
	@Test
	public void testAddInventory4d(){
		try {
			coffeeMaker.addInventory("1", "1", "1", "2");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory4e(){
		try {
			coffeeMaker.addInventory("1", "1", "1", "0");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddInventory4f(){
		try {
			coffeeMaker.addInventory("1", "1", "1", "-0");
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee1() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(100, coffeeMaker.makeCoffee(-1, 100));
	}
	
	@Test
	public void testMakeCoffee2() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(100, coffeeMaker.makeCoffee(6, 100));
	}
	
	@Test
	public void testMakeCoffee3() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(100, coffeeMaker.makeCoffee(1, 100));
	}
	
	@Test
	public void testMakeCoffee4() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}
	
	@Test
	public void testMakeCoffee5() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(10, coffeeMaker.makeCoffee(0, 10));
	}
	
	@Test
	public void testMakeCoffee6() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

}
