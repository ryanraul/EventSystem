package com.event.system.eventsystem.services;

import java.util.ArrayList;

public class ValidationResult {
   private String message;
   private ArrayList<ValidationResult> errors = new ArrayList<ValidationResult>();

   public Boolean IsValid(){
      return errors.size() == 0;
   }

   public String getMessage() {
      return message;
   }

   public void setErrors(String message){
      this.message = message;
      this.errors.add(this);
   }

   public ValidationResult() {

   }


   
}
