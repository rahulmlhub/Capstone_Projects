package com.metlife.review.exception;


/**
 * @author Admin
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
//    String fieldName;
    String fieldValue;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s not found with %s : %s", resourceName));
        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
    }

}