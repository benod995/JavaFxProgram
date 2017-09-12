import javafx.scene.image.Image;
//class must implement Serializable for data to be stored from an array to a file
public class Product implements java.io.Serializable{
	//setting global variables and getters and setters necessary to return the values of each variable
	private static int ID=0;
	private int id;
	public String name;
	public String description;
	public double price;
	public String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//constructor that passes four parameters of different data types and sets them to their corresponding variables
	Product(String name, String description, double price, String image){
		id = ID++;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}

	@Override
	//to string method necessary to display the contents as the values and not the memory locations
	public String toString() {
		return "Product name=" + name + ", description=" + description + ", price=" + price + ", image=" + image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
