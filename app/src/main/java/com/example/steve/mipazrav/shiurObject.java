package com.example.steve.mipazrav;

/**
 * Created by joshuaegoldmeier on 8/29/2016.
 */
public class shiurObject {
  private final String name, description, recID;

  public shiurObject(String name, String description, String recID) {
    this.name = name;
    this.description = description;
    this.recID = recID;

  }

  public String getRecID() {
    return recID;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

}