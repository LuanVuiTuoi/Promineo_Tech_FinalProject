package com.promineotech.budgetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;	
}
