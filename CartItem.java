import javafx.scene.image.Image;

public class CartItem extends Product{
	//extends the product class to save on typing out the information as it requires a lot of the same values
	//cart item constructer to pass in 4 perameters and set them as super 
	CartItem(String name, String description, double price, String image) {
		super(name, description, price, image);
		// TODO Auto-generated constructor stub
	}


}
