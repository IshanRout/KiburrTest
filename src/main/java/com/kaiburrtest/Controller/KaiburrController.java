package com.kaiburrtest.Controller;



import com.kaiburrtest.Model.KaiburrModel;
import com.kaiburrtest.Repository.KaiburrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/kaiburrTest")
public class KaiburrController extends Exception {

    @Autowired
    private KaiburrRepository kaiburrRepository;


    @PostMapping("/add")
    public String addObject(@RequestBody KaiburrModel kaiburrModel) {
        kaiburrRepository.save(kaiburrModel);
        return "New Object added";
    }

    @GetMapping("/findAll")
    public List<KaiburrModel> getAllObjects() {
        return kaiburrRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity search(@PathVariable int id) {

        Optional<KaiburrModel> data = kaiburrRepository.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Status 404 , No Objects found with that id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{name}")
    @Query("{field: ObjectId('?0'}")
    public @ResponseBody
    List<KaiburrModel> findByName(@PathVariable("name") String name) {
        try {
            return kaiburrRepository.findByName(name);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP status 404 no objects found\n");
        }

    }

    @DeleteMapping("/delete/{id}")
    public String deleteObjects(@PathVariable int id) {
        kaiburrRepository.deleteById(id);
        return "Object deleted";
    }
}
