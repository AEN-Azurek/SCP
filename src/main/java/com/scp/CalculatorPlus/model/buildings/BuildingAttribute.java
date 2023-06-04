package com.scp.CalculatorPlus.model.buildings;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "building_attribute", schema = "dbo")
public class BuildingAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @Column(name = "attribute_value")
    private String attributeValue;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;

    public BuildingAttribute() {
    }

    public BuildingAttribute(long id, Building building, Attribute attribute, String attributeValue) {
        this.id = id;
        this.building = building;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }
}
