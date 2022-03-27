package com.ezypay.app;

import java.util.Calendar;
import java.util.Date;

import static  java.util.Calendar.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ezypay.app.model.SubscriptionModel;
import com.ezypay.app.model.SubscriptionModel.SubscriptionAddModel;
import com.ezypay.app.repository.SubscriptionRepository;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	
	@Autowired
    private SubscriptionRepository repo;
	
	// Get all
	@GetMapping("")
	public List<SubscriptionModel> getAllSubscription() {
		return repo.findAll();
	}
	
	// Get by id
    @GetMapping("/{id}")
    public SubscriptionModel getSubscriptionById(@PathVariable(value = "id") int id)
    {
        return repo.findById(id);
    }
	
    @PostMapping("")
    public SubscriptionAddModel addSubscription(@RequestBody SubscriptionModel SubscriptionInput)
    {

    	SubscriptionModel resultAddSubsciption = repo.save(SubscriptionInput);
    	
    	
    	List<String> listInvoiceDates = new ArrayList<String>();
    	
    	Calendar calendar = getInstance();  
        calendar.setTime(resultAddSubsciption.getStart_date()); 
        

    	if(resultAddSubsciption.getType().equalsIgnoreCase("WEEKLY")) {
    		
    		DayOfWeek dow = DayOfWeek.valueOf( resultAddSubsciption.getDay_of_week_month() ); 

    		while(calendar.getTime().before(resultAddSubsciption.getEnd_date())) { 
            	
                if( changeDayNumber(calendar.get( DAY_OF_WEEK )) == dow.getValue() ) {
                	listInvoiceDates.add(formatDate(calendar.getTime()));
                }

                calendar.add(DATE, 1 ); 
    		}
           //System.out.println(listInvoiceDates);
    		
    	}
    	else if (resultAddSubsciption.getType().equalsIgnoreCase("MONTHLY")) {
    		
    		
    		while(calendar.getTime().before(resultAddSubsciption.getEnd_date())) { 
            	

            	//System.out.println(calendar.getTime());
                if( calendar.get( DAY_OF_MONTH ) == Integer.parseInt(resultAddSubsciption.getDay_of_week_month()) ) {
                	listInvoiceDates.add(formatDate(calendar.getTime()));
                }

                calendar.add(DATE, 1 ); 
    		}
    		
    		
    	}
    	else if (resultAddSubsciption.getType().equalsIgnoreCase("DAILY")) {
    		
    		
    		while(calendar.getTime().before(resultAddSubsciption.getEnd_date())) { 
            	
                listInvoiceDates.add(formatDate(calendar.getTime()));

                calendar.add(DATE, 1 ); 
    		}
    		
    		
    	}
    	

    	SubscriptionAddModel outputAddSubsciption = resultAddSubsciption.new SubscriptionAddModel(resultAddSubsciption.getAmount(),resultAddSubsciption.getType(), listInvoiceDates);
    	
        return outputAddSubsciption;
    }
    
    
	public int changeDayNumber(int inputDay)
	{	
		if (inputDay == 1)
			return 7;
		else
			return (inputDay -1);
		
	}
	
	public String formatDate(Date inputDate)
	{	
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		String inputDateTemp = formatDate.format(inputDate);
		
		return inputDateTemp;
		
	}
    
}
