import javax.swing.event.ChangeListener;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.util.Duration;

public class CartScreenController{
	private Cart cart;
	private ControllerScreen controllerScreen;
	private ObservableList<Media> itemsOdered = FXCollections.observableArrayList();
	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private TableColumn<Media, String> colMediacategory;
	
	@FXML
	private TableColumn<Media, String> colMediaCost;
	
	@FXML
	private javafx.scene.control.Button btnPlay;

	@FXML
	private javafx.scene.control.Button btnRemove;
	
    @FXML
    private TextField tfFilter;
    
    @FXML
    private RadioButton radioBtnFilterId;
    
    @FXML
    private Label totalCost;
    
    @FXML
    private Label playingMedia;
    
    @FXML
    private Button btnStop;
    
    @FXML
    private Button btnOrder;
    
    @FXML
    private ToggleGroup filterCategory;
	
	public CartScreenController(Cart cart, ControllerScreen controllerScreen) {
		super();
		this.cart = cart;
		this.controllerScreen = controllerScreen;
	}
	
	@FXML
	private void initialize() {
		colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("Title"));
		colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		tblMedia.setItems(this.cart.getItemsOrdered());
		
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		playingMedia.setVisible(false);
    	btnStop.setVisible(false);
    	
		tfFilter.textProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				showFilterMedia(newValue);
			}
		});
    	tblMedia.getSelectionModel().selectedItemProperty().addListener(
    			new ChangeListener<Media>() {
    				
    				@Override
    				public void changed(ObservableValue<? extends Media> observable, Media oldValue,
    									Media newValue) {
    					if(newValue != null) {
    						updateButtonBar(newValue);
    					}
    					totalCost.setText(cart.totalCost()+"$");
    				}
    			});

	}
	void updateButtonBar(Media media) {
		btnRemove.setVisible(true);
		if(media instanceof Playable) {
			btnPlay.setVisible(true);
		}
		else {
			btnPlay.setVisible(false);
		}
	}
	
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
		totalCost.setText(cart.totalCost()+"$");
	}
	 void showFilterMedia(String searchString) {
	    	if(searchString.isEmpty()) {
	    		tblMedia.setItems(this.cart.getItemsOrdered());
	    	} else {
	        	if(radioBtnFilterId.isSelected()) {
	        		tblMedia.setItems(new FilteredList<Media>(this.cart.getItemsOrdered(),item-> item.getId()==Integer.parseInt(searchString)));
	        	}else
	        	tblMedia.setItems(new FilteredList<Media>(this.cart.getItemsOrdered(),item-> item.getTitle().contains(searchString)));
	    	}
	    }
	 @FXML
	    void btnStopPressed(ActionEvent event) {
	    	playingMedia.setVisible(false);
	    	btnStop.setVisible(false);
	    }
	    
	    @FXML
	    void btnOrderPressed(ActionEvent event) {
			System.out.println("Order");
	    	btnOrder.setText("Success!!!");
	    	btnOrder.setDisable(true);
	    	cart.getItemsOrdered().removeAll(cart.getItemsOrdered());
	    	totalCost.setText("0.0$");	
	    	PauseTransition pt = new PauseTransition(Duration.seconds(1));
	        pt.setOnFinished(e ->{
	        	btnOrder.setDisable(false);
	        	
	        	playingMedia.setVisible(false);
	        	btnPlay.setVisible(false);
	        	btnOrder.setText("Order");
	        } );
	        pt.playFromStart();
	    }
	    
	    @FXML
	    void changeToStoreScreen(ActionEvent event) {
	    	this.controllerScreen.showStoreScreen();
	    }
	    @FXML
	    void changeToAddBookScreen(ActionEvent event) {
	    	this.controllerScreen.showAddBookScreen();
	    }

	    @FXML
	    void changeToAddCDScreen(ActionEvent event) {
	    	this.controllerScreen.showAddCDCreen();
	    }

	    @FXML
	    void changeToAddDVDScreen(ActionEvent event) {
	    	this.controllerScreen.showAddDVDScreen();
	    }

	    @FXML
	    void changeToCartScreen(ActionEvent event) {
	    	this.controllerScreen.showCartScreen();
	    }

	    @FXML
	    void updateFilter(InputMethodEvent event) {
	    	System.out.println(event.toString());
	    }
}