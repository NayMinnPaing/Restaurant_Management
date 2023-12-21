package RestaurantManagement.example.restaurantmanagement.service;

import java.util.List;

import RestaurantManagement.example.restaurantmanagement.model.RestaurantModel;

public interface RestaurantService {
	
	public int insertData(RestaurantModel model);
	public List<RestaurantModel> selectDataOrderById(int tableId);
	public List<RestaurantModel> selectById(int tableId);
	public List<RestaurantModel> selectAllFromItem();
	public RestaurantModel selectTotalPrice(int tableId);
	public int setcheckoutDone(int id);
	public int deleteData(int tableId);
	// public List<RestaurantModel> selectIs_checkout();
}
