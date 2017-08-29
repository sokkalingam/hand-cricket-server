package com.project.handcricket.mongodb.repo;

import com.project.handcricket.mongodb.model.DataRefresh;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface DataRefreshRepo extends MongoRepository<DataRefresh, String>  {



}
