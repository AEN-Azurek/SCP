package com.scp.CalculatorPlus.model.buildings;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;

    public Attribute(long id, String attributeName) {
        this.id = id;
        this.attributeName = attributeName;
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public Attribute(long id, String attributeName, Timestamp dateCreated) {
        this.id = id;
        this.attributeName = attributeName;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return attributeName;
    }
}
