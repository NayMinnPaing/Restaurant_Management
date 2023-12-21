package RestaurantManagement.example.restaurantmanagement.controller;






import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class StartController {
	
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	
	public ModelAndView start() {
		
		// List<RestaurantModel> model=restaurantServicImp.selectIs_checkout();
		
		// System.out.println(i.getTableId());
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("availible", model);
		modelAndView.setViewName("index");
		return modelAndView;
		
	}
	
	
}
