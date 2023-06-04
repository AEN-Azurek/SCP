package com.scp.CalculatorPlus.model.buildings;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building", schema = "dbo")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "footprint_area")
    private Integer footprintArea;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BuildingAttribute> attributes;

    public Building() {
    }

    public Building(long id, String name, Integer footprintArea) {
        this.id = id;
        this.name = name;
        this.footprintArea = footprintArea;
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public Building(long id, String name, Integer footprintArea, Timestamp dateCreated) {
        this.id = id;
        this.name = name;
        this.footprintArea = footprintArea;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFootprintArea() {
        return footprintArea;
    }

    public void setFootprintArea(Integer footprintArea) {
        this.footprintArea = footprintArea;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public boolean extractsResources() {
        return false;
    }

    public void addAttribute(BuildingAttribute attribute) {
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        attributes.add(attribute);
        attribute.setBuilding(this);
    }

    public void addAllAttributes(List<BuildingAttribute> attributes) {
        for (BuildingAttribute attribute : attributes) {
            addAttribute(attribute);
        }
    }

    public void removeAttribute(BuildingAttribute attribute) {
        if (attributes != null) {
            attributes.remove(attribute);
            attribute.setBuilding(null);
        }
    }

    public BuildingAttribute getAttribute(Attribute attribute) {
        if (attributes == null) return null;

        for (BuildingAttribute buildingAttribute : attributes) {
            if (!buildingAttribute.getAttribute().equals(attribute)) continue;
            return buildingAttribute;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
