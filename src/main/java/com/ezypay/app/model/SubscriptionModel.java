package com.ezypay.app.model;

import java.sql.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "subscription")
public class SubscriptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subscription_id;
    private BigDecimal amount;
    private String type;
    private String day_of_week_month;	    
    private Date start_date;
    private Date end_date;

    public SubscriptionModel() {
    }
    
    public SubscriptionModel(int subscription_id) {
        this.subscription_id = subscription_id;
    }
    
    public int getSubscription_id() {
        return subscription_id;
    }
    
    public BigDecimal getAmount() {
    	return amount;
    }
    
    public String getType() {
        return type;
    }
    
    public String getDay_of_week_month() {
        return day_of_week_month;
    }
    
    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }  
        
    public class SubscriptionAddModel {
    	public BigDecimal amount;
    	public String type;
    	public List<String> invoice_dates;
        
        public SubscriptionAddModel(BigDecimal amount, String type, List<String> invoice_dates) {
            this.amount = amount;
            this.type = type;
            this.invoice_dates = invoice_dates;
        }


        
    }
    
}
