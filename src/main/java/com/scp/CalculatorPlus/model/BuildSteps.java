package com.scp.CalculatorPlus.model;

import org.apache.commons.math3.fraction.BigFraction;

import java.util.LinkedHashMap;
import java.util.Map;

public class BuildSteps {

    Map<Recipe, BuildStep> buildStepMap;

    Map<Item, BigFraction> baseResourceInputQuantity;

    public BuildSteps() {
        this.buildStepMap = new LinkedHashMap<>();
        this.baseResourceInputQuantity = new LinkedHashMap<>();
    }

    public BuildSteps(Map<Recipe, BuildStep> buildStepMap, Map<Item, BigFraction> baseResourceInputQuantity) {
        this.buildStepMap = buildStepMap;
        this.baseResourceInputQuantity = baseResourceInputQuantity;
    }

    public Map<Recipe, BuildStep> getBuildStepList() {
        return buildStepMap;
    }

    public void setBuildStepList(Map<Recipe, BuildStep> buildStepMap) {
        this.buildStepMap = buildStepMap;
    }

    public void addToBuildStepMap(Map<Recipe, BuildStep> buildStepMap) {
        for (Map.Entry<Recipe, BuildStep> entry : buildStepMap.entrySet()) {
            if (this.buildStepMap.containsKey(entry.getKey())) {
                buildStepMap.get(entry.getKey()).add(entry.getValue());
                continue;
            }
            this.buildStepMap.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<Item, BigFraction> getBaseResourceInputQuantity() {
        return baseResourceInputQuantity;
    }

    public void setBaseResourceInputQuantity(Map<Item, BigFraction> baseResourceInputQuantity) {
        this.baseResourceInputQuantity = baseResourceInputQuantity;
    }

    public void addToBaseResourceInputQuantity(Map<Item, BigFraction> baseResourceInputQuantity) {
        for (Map.Entry<Item, BigFraction> entry : baseResourceInputQuantity.entrySet()) {
            if (this.baseResourceInputQuantity.containsKey(entry.getKey())) {
                baseResourceInputQuantity.get(entry.getKey()).add(entry.getValue());
                continue;
            }
            this.baseResourceInputQuantity.put(entry.getKey(), entry.getValue());
        }
    }
}
