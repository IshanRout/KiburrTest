package com.kaiburrtest.Controller;



import com.kaiburrtest.Model.KaiburrModel;
import com.kaiburrtest.Repository.KaiburrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/*  The controller class is responsible for processing incoming REST API requests, preparing a model,
    and returning the view to be rendered as a response.*/

import java.util.List;
import java.util.Optional;

/* The @RestController annotation mark controller classes as a request handler to allow Spring to recognize
    it as a RESTful service during runtime*/
@RestController
@RequestMapping("/kaiburrTest")
public class KaiburrController extends Exception {

    /* The @Autowired annotation is used to automatically inject dependencies of the specified type into the current bean. */
    @Autowired
    private KaiburrRepository kaiburrRepository;

    /* Spring boot allows us to see the logs in the console even if we do not provide any specific configuration for it.
    *
    * */
    Logger logger = LoggerFactory.getLogger(KaiburrController.class);

    /* POST API call to add objects to the data in a http client */
    @PostMapping("/add")
    public @ResponseBody String addObject(@RequestBody KaiburrModel kaiburrModel) {
        logger.info("HTTP request for adding objects to the list.");
        kaiburrRepository.save(kaiburrModel);
        return "New Object added";
    }

    /* GET API call to find the list of objects stored in the database in a http client */
    @GetMapping("/findAll")
    public @ResponseBody List<KaiburrModel> getAllObjects() {

        return kaiburrRepository.findAll();
    }

    /* GET API call to find the objects with their unique IDs in a http client */
    @GetMapping("/find/{id}")
    public @ResponseBody ResponseEntity search(@PathVariable int id) {
        logger.info("HTTP request to search the list of objecst present under the id requested");
        Optional<KaiburrModel> data = kaiburrRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            logger.error("HTTP status 404 , no objects found.");
            return new ResponseEntity("Status 404 , No Objects found with that id", HttpStatus.NOT_FOUND);
        }
    }

    /* GET API call to find the objects with the server names IDs in a http client
    *
    * This will get us the list of number of objects present with the server name requestd*/
    @GetMapping("/get/{name}")
    @Query("{field: ObjectId('?0'}")
    public @ResponseBody List<KaiburrModel> findByName(@PathVariable("name") String name) {
        logger.info("HTTP request to search the lists present with the same server name requested ");
        try {
            return kaiburrRepository.findByName(name);
        } catch (Exception e) {
            logger.error("HTTP status 404 , no objects found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP status 404 no objects found\n");
        }

    }

    /* DELETE API call to delete the objects with the requested id in a http client */
    @DeleteMapping("/delete/{id}")
    public String deleteObjects(@PathVariable int id) {
        logger.info("HTTP request to delete the list with the requested id.");
        kaiburrRepository.deleteById(id);
        return "Object deleted";
    }
}
