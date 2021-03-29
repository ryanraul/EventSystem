package com.event.system.eventsystem.utils;

import java.util.ArrayList;

public class ValidationResult {
   public ArrayList<ValidationErrors> errors;

   public Boolean IsValid(){
      return errors.size() == 0;
   }

   public void setErrors(String message){
      this.errors.add(new ValidationErrors(message));
   }

   public ValidationResult() {
      this.errors = new ArrayList<ValidationErrors>();
   }


   
}
