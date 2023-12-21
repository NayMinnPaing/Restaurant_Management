package RestaurantManagement.example.restaurantmanagement.model;

import lombok.Getter;

import lombok.Setter;

public class RestaurantModel {
	
	@Getter
	@Setter
	private boolean isCheckout;
	@Getter
	@Setter
	private int totalPrice;
	
	@Getter
	@Setter
	private int item_id;
	
	@Getter
	@Setter
	private int item_price;

	@Getter
	@Setter
	private String item_Name;
	
	@Getter
	@Setter
	private int tableId;
	
	@Getter
	@Setter
	private int quantity;
}
