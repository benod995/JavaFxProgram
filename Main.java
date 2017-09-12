//Author: Ben O'Donnell
//Student ID: R00124453
//Class COM2-B
import javafx.application.Application;
import java.util.Scanner;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Extends application to declare as a javaFx Program
public class Main extends Application {

	//Grid pane used to align contents in position
	GridPane grid = new GridPane();
	//Array list for the products data. Declared as static 
	private static ArrayList<Product> data = 
			new ArrayList<Product>();
	//Array list for the cart items. Declared as static
	private final static ArrayList<Product> cart =
			new ArrayList<Product>();
	//Clss that controls the buttons and their id for unique values
	class MyButton extends Button{
		int id;
		public MyButton(int id, String name){
			super(name);
			this.id = id;
		}
	}
	//Stack pane class to sort content in a stack
	private StackPane createCell(Control c) {

		StackPane cell = new StackPane();


		//get children().add() used to add the contents to the program to be able to display
		cell.getChildren().add(c);
		cell.getStyleClass().add("cell");
		return cell;
	}

	//override tells the program that the following code is to be used rather than the default
	@Override
	public void start(Stage primaryStage) {
		//setting the title of the javaFx window
		primaryStage.setTitle("JavaFX Welcome");
		System.out.println("hello");
		//using the perameters from the constructor in the product class to display new info about the product
		//Adding the Cars(Products) to the array 'data'
		Product car1 = new Product("car 1","good car",110000,"Graphics/car1.jpeg");
		data.add(car1);
		Product car2 = new Product("car 2","great car",125000,"Graphics/car2.jpeg");
		data.add(car2);
		Product car3 = new Product("car 3","Sports car",99000,"Graphics/car3.jpeg");
		data.add(car3);
		Product car4 = new Product("car 4","Super car",135000,"Graphics/car4.jpeg");
		data.add(car4);
		Product car5 = new Product("car 5","Super car",140000,"Graphics/car5.jpeg");
		data.add(car5);
		Product car6 = new Product("car 6","Amazing car",80000,"Graphics/car6.jpeg");
		data.add(car6);
		BorderPane border = new BorderPane();
		//calls the write to file serialization class
		writeToFile();
		//setting the vbox to the left of the scene and the grid into the center
		border.setLeft(addVBox());
		border.setCenter(grid);
		//set the window scene to whatever size you want to initially open
		Scene scene = new Scene(border, 800, 500);
		//get the css class to be used to style the window
		scene.getStylesheets().
		add("grid-with-borders.css");
		grid.getStyleClass().add("grid");
		for (int x = 0 ; x < 4 ; x++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setFillWidth(true);
			cc.setHgrow(Priority.ALWAYS);
			grid.getColumnConstraints().add(cc);
		}

		for (int y = 0 ; y < 10 ; y++) {
			RowConstraints rc = new RowConstraints();
			rc.setFillHeight(true);
			rc.setVgrow(Priority.ALWAYS);
			grid.getRowConstraints().add(rc);
		}
		//this just sets the scene to be able for use 
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//this write to file class serializes the data from the array to a .ser file
	//try catch statement throws any errors
	public static void writeToFile(){
		try {
			FileOutputStream fileOut =
					new FileOutputStream("ArrayStore.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.writeObject(cart);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ArrayStore.ser");
		}catch(IOException i) {
			i.printStackTrace();
		}

	}
	@SuppressWarnings("unchecked")
	//read file used to deserialize the data from the array and read the object
	//try catch statement used to throw any errors
	public static void readFile(){

		try {
			FileInputStream fileIn = new FileInputStream("ArrayStore.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			data = (ArrayList<Product>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee...");


	}

	//main method has launch(args) to start the application as a javaFx program
	public static void main(String[] args) {
		launch(args);
	}
	//Creates a VBox with a list of links for the left region
	private VBox addVBox() {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10)); // Set all sides to 10
		vbox.setSpacing(8);              // Gap between nodes
		//title must be set for the user to understand the options
		Text title = new Text("Data");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vbox.getChildren().add(title); //add the title 
		//set buttons to be displayed in the vbox and give them unique names
		Button products= new Button("Products");
		Button cart = new Button("cart");
		Button newProd = new Button("new Product");
		vbox.getChildren().addAll(products,cart,newProd);   //add buttons
		//event handlers to call classes to declare what the buttons should do
		products.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showProducts();
			}
		});

		cart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showCart();
			}
		});
		newProd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newProduct();
			}
		});
		return vbox;
	}
	//this class creates a new product when the user types one 
	private void newProduct(){
		grid.getChildren().clear();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scanner keyboard = new Scanner(System.in);
		//Name Label - constrains use (child, column, row)
		Label nameLabel = new Label("Car name");
		grid.setConstraints(nameLabel, 0, 0);

		//Name Input
		TextField nameInput = new TextField();
		grid.setConstraints(nameInput, 1, 0);

		//Description Label
		Label desc = new Label("Description");
		grid.setConstraints(desc, 0, 1);

		//Description Input
		TextField descInput = new TextField();
		grid.setConstraints(descInput, 1, 1);

		//Price Label
		Label priceLabel = new Label("Price");
		grid.setConstraints(priceLabel, 0, 2);

		//Price Input
		TextField priceInput = new TextField();
		grid.setConstraints(priceInput, 1, 2);

		//Image Label
		Label imageLabel = new Label("Image");
		grid.setConstraints(imageLabel, 0, 3);

		//Image Input
		TextField imageInput = new TextField();
		grid.setConstraints(imageInput, 1, 3);
		//Add to products
		Button addProductsButton = new Button("Add Product");
		grid.setConstraints(addProductsButton, 1, 4);

		//Add everything to grid
		grid.getChildren().addAll(nameLabel, nameInput, desc, priceLabel, descInput, addProductsButton, priceInput, imageLabel, imageInput);

		addProductsButton.setOnAction(e ->{
			Product addTheCar = new Product(nameInput.getText(), descInput.getText(), Double.valueOf(priceInput.getText()),imageInput.getText());
			data.add(addTheCar); //@ToDo
			writeToFile();
		});
	}


	//this class shows the products available to the customer
	private void showProducts(){

		grid.getChildren().clear();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		//set text accross the screen to let the user know what each perameter in the array means
		Text text1 = new Text("Product");
		Text text2 = new Text("Name ");
		Text text3 = new Text("Description ");
		Text text4 = new Text("Click here ");
		grid.getChildren().addAll(text1,text2,text3,text4);
		//set the positioning of the text
		grid.setConstraints(text1,0,0);
		grid.setConstraints(text2,1,0);
		grid.setConstraints(text3,2,0);
		grid.setConstraints(text4,3,0);

		//for loop to display all of the products under one another when created
		for(int x = 0; x<data.size(); x++){
			Product p2 = data.get(x);
			Text name = new Text(p2.getName());
			grid.setConstraints(name, 0,x+1);
			Text description = new Text(p2.getDescription());
			grid.setConstraints(description, 1,x+1);
			Text price = new Text(String.valueOf(p2.getPrice()));
			grid.setConstraints(price, 2,x+1);
			ImageView image = new ImageView(p2.getImage());
			grid.setConstraints(image, 2,x+1);
			MyButton button = new MyButton(p2.getId(), "buy");
			grid.setConstraints(button, 3,x+1);
			//lambda expression and for loop to handle button in the show products class and give each one a unique ID
			button.setOnAction(e->{
				MyButton but = (MyButton) e.getSource();
				int id = but.id;
				for (int i=0;i<data.size();i++){
					Product p = data.get(i);
					if (p.getId() == id){
						System.out.print("Name: " + p.getName() + "\t");
						System.out.print("Description: " + p.getDescription() + "\t");
						System.out.print("Price: " + p.getPrice() + "\t");
						System.out.println("Image " + p.getImage());
						cart.add(p);
					}
				}
			});
			grid.getChildren().addAll(name,description,price,image,button);
		}
	}
	//show cart method to display everything that the user has selected from the show products class
	private void showCart(){
		grid.getChildren().clear();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		for (int y = 0; y<cart.size(); y++){
			Product k = cart.get(y);

			Text name = new Text(k.getName());
			System.out.print("Name: " + k.getName() + "\t");
			grid.setConstraints(name, 0,y+1);

			Text description = new Text(k.getDescription());
			System.out.print("Description: " + k.getDescription() + "\t");
			grid.setConstraints(description, 1,y+1);

			Text price = new Text(String.valueOf(k.getPrice()));
			System.out.print("Price: " + k.getPrice() + "\t");
			grid.setConstraints(price, 2,y+1);

			ImageView image = new ImageView(k.getImage());
			System.out.println("Image " + k.getImage());
			grid.setConstraints(image, 3,y+1);
			grid.getChildren().addAll(name,description,price,image);

		}


	}

}


