package com.kaiburrtest.Repository;

import com.kaiburrtest.Model.KaiburrModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/* This interface is manages the data in a springboot application and is responsible for
    performing all the CRUD based operations */

/* The data are stored in the mongoDB database so the interface extends the MongoRepository
*
*  The interface must extend Repository and be typed to the domain class and an ID type
*   here the id type is integer.*/
public interface KaiburrRepository extends MongoRepository<KaiburrModel,Integer> {
    List<KaiburrModel> findByName(String name);


}
