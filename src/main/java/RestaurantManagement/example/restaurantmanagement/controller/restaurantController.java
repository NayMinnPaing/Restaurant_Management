package RestaurantManagement.example.restaurantmanagement.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import RestaurantManagement.example.restaurantmanagement.model.RestaurantModel;
import RestaurantManagement.example.restaurantmanagement.service.serviceImplement.RestaurantServicImp;

@Controller
public class restaurantController {
	
	@Autowired
	private RestaurantServicImp restaurantServicImp;
	
	
	@RequestMapping(value = "/addmenu/{tableId}", method = RequestMethod.GET)
	public ModelAndView menu(@PathVariable String tableId) {
		int id = Integer.parseInt(tableId);
		List<RestaurantModel> model= restaurantServicImp.selectAllFromItem();
		List<RestaurantModel> modellist = restaurantServicImp.selectDataOrderById(id);

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("tableId", id);
		modelview.addObject("modellist", modellist);

		modelview.addObject("menulist", model);
		modelview.setViewName("addmenu");
		return modelview;
		
	}
	
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public ModelAndView addMenu(@ModelAttribute RestaurantModel model) {
		boolean notCheckout=false;
		model.setCheckout(notCheckout);
		// System.out.println(model.getItem_Name());
		// System.out.println(model.getTableId());
		restaurantServicImp.insertData(model);
		 
		List<RestaurantModel> modelForMenu= restaurantServicImp.selectAllFromItem();
		List<RestaurantModel> modellist = restaurantServicImp.selectDataOrderById(model.getTableId());

		
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("modellist", modellist);
		modelAndView.addObject("menulist", modelForMenu);
		modelAndView.addObject("tableId" , model.getTableId());
		modelAndView.setViewName("addmenu");
		return modelAndView;
		
	}
	
		@RequestMapping(value = "/payment", method = RequestMethod.GET)
		public ModelAndView payment(@RequestParam(name="tableId") String tableId) {
			 
			int id=Integer.parseInt(tableId);
			RestaurantModel totalprice= restaurantServicImp.selectTotalPrice(id);
			// System.out.println("TotalPrice: "+totalprice.getTotalPrice());
			
			List<RestaurantModel> modellist=restaurantServicImp.selectById(id);
			
			ModelAndView modelview = new ModelAndView();
			
			modelview.addObject("modellist", modellist);
			modelview.addObject("tableId", id);
			modelview.addObject("totalprice", totalprice);
			modelview.setViewName("payment");
			return modelview;
			
		}
		
	/*	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
		public ModelAndView addIdToCheckout() {
			
			ModelAndView modelview = new ModelAndView();
			
			modelview.setViewName("payment");
			return modelview;
		}*/
		
		
		@RequestMapping(value = "/delete/{tableId}")
		public ModelAndView delete(@PathVariable(name ="tableId") String tableId) {
			int id=Integer.parseInt(tableId);
 			int result=restaurantServicImp.deleteData(id);
 			if (result != 0) {
				 restaurantServicImp.setcheckoutDone(id);
			}
			
			ModelAndView modelview= new ModelAndView();
			modelview.addObject("tableId", id);
			modelview.addObject("result", result);
			modelview.setViewName("payment");
			
			return modelview;
			
		}
		
		
}
