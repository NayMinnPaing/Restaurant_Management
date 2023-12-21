package RestaurantManagement.example.restaurantmanagement.service.serviceImplement;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import RestaurantManagement.example.restaurantmanagement.model.RestaurantModel;
import RestaurantManagement.example.restaurantmanagement.service.RestaurantService;

@Service

public class RestaurantServicImp implements RestaurantService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String insertQuery="INSERT INTO orderitem (itemname ,table_id ,quantity, is_checkout) VALUES ( ?, ?, ?, ?);";
	private static final String selectAllQuery="SELECT itemname, table_id, quantity FROM orderitem WHERE table_id=?;";
	private static final String selectByIdQuery="SELECT orderitem.itemname, orderitem.quantity,listitem.item_price FROM orderitem JOIN listitem on orderitem.itemname = listitem.item_name WHERE table_id=?;";
	private static final String selectAllFromItemQuery="SELECT item_id, item_name, item_price FROM listitem;";
	private static final String selectTotalQuery ="SELECT sum(quantity * item_price) FROM orderitem o inner JOIN listitem l on o.itemname = l.item_name WHERE table_id=?;";
	private static final String deleteDataQuery="DELETE FROM orderitem WHERE table_id=?;";
	private static final String updateCheckoutQuery="UPDATE orderitem SET is_checkout= true WHERE table_id=?";
	// private static final String selectIs_checkoutQuery="SELECT table_id, is_checkout FROM orderitem;";
	@Override
	public int insertData(RestaurantModel model) {
		
		int result=jdbcTemplate.update(insertQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, model.getItem_Name());
				ps.setInt(2, model.getTableId());
				ps.setInt(3, model.getQuantity());
				ps.setBoolean(4, model.isCheckout());
			
			};
		});
		 
	
		return result;
	}
	
	@Override
	public List<RestaurantModel> selectAllFromItem() {
		List<RestaurantModel> model=jdbcTemplate.query(selectAllFromItemQuery, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantModel model=new RestaurantModel();
				model.setItem_id(rs.getInt("item_id"));
				model.setItem_Name(rs.getString("item_name"));	
				model.setItem_price(rs.getInt("item_price"));
				return model;
			}
			
		});
		return model;
	}

	@Override
	public List<RestaurantModel> selectDataOrderById(int tableId) {
		List<RestaurantModel> model=jdbcTemplate.query(selectAllQuery,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, tableId);
				
			}
		}, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantModel model=new RestaurantModel();
				model.setItem_Name(rs.getString("itemname"));
				model.setQuantity(rs.getInt("table_id"));	
				model.setTableId(rs.getInt("quantity"));
				return model;
			}
			
		});
		return model;
	}

 
	@Override
	public List<RestaurantModel> selectById(int tableId) {
		List<RestaurantModel> modellist=jdbcTemplate.query(selectByIdQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, tableId);
				
			}
		},new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantModel model= new RestaurantModel();
				model.setItem_Name(rs.getString("itemname"));
				model.setQuantity(rs.getInt("quantity"));
				model.setItem_price(rs.getInt("item_price"));
				return model;
			}
		} );
		
		return modellist;
	}

		/*
			 * jdbcTemplate.query(selectByIdQuery,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, tableId);
					
				}
			} ,new ResultSetExtractor<RestaurantModel>() {
	
				@Override
				public RestaurantModel extractData(ResultSet rs) throws SQLException, DataAccessException {
					RestaurantModel model=new RestaurantModel();
					while (rs.next()) {
						model.setItem_Name(rs.getString("itemname"));
						
						model.setTableId(rs.getInt("quantity"));
						
					}
					
					return model;
				}
				});
		 * 
		 * 
		 * */
	
	@Override
	public RestaurantModel selectTotalPrice(int tableId) {
		RestaurantModel model=jdbcTemplate.query(selectTotalQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, tableId);
				
			}
		}, new ResultSetExtractor<RestaurantModel>() {

			@Override
			public RestaurantModel extractData(ResultSet rs) throws SQLException, DataAccessException {
				RestaurantModel model=new RestaurantModel();
				while (rs.next()) {
					model.setTotalPrice(rs.getInt(1));
				}
				
				return model;
			}
			
		}); 
		
		return model;
	}


	@Override
	public int deleteData(int tableId) {
		int result=jdbcTemplate.update(deleteDataQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, tableId);
				
			}
		});
		return result;
	}

	@Override
	public int setcheckoutDone(int id) {
		 int result=jdbcTemplate.update(updateCheckoutQuery, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				
			}
		});
		return result;
	}

	/*@Override
	public List<RestaurantModel> selectIs_checkout() {
		List<RestaurantModel> model=jdbcTemplate.query(selectIs_checkoutQuery, new RowMapper<RestaurantModel>() {

			@Override
			public RestaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				RestaurantModel model= new RestaurantModel();
				model.setTableId(rs.getInt("table_id"));
				model.setCheckout(rs.getBoolean("is_checkout"));
				return model;
			}
		});
		return model;
	}*/

}
