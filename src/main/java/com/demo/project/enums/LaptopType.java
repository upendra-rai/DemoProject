package com.demo.project.enums;

import javax.annotation.Generated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public enum LaptopType {
	STUDENT("S"), COMMERCIAL("C");
	
	
	 private String value;

	    public String getValue() {
	        return value;
	    }

	    LaptopType(String value){
	        this.value = value;
	    }
	}

