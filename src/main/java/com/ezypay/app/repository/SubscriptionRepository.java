package com.ezypay.app.repository;

import com.ezypay.app.model.SubscriptionModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionRepository extends JpaRepository<SubscriptionModel, Integer>{
	List<SubscriptionModel> findAll();
	SubscriptionModel findById(int id);
}