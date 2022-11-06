package com.kaiburrtest.Model;

/* All the packags imported required for the entity class and the mongodb database */

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


/* The @Document annotation tells the mongodb database in which document the datas will be stored  */
@Document(collection ="Servers" )

/* Annotations for the getter and setter methods  */
@Getter
@Setter
@ToString
/*This is the model class that whee we define all the entities that will be stored in the dataase */
public class KaiburrModel {
    @Id                    /* The annotation id describes that the entity is unique */
    private int id;
    private String name;
    private String language;
    private String framework;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }
}
