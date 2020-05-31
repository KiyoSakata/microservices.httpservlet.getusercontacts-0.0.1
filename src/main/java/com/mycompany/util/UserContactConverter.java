package com.mycompany.util;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.mycompany.dto.UserDTO;
import com.mycompany.model.UserContactModel;

@Component
public class UserContactConverter {

	public UserContactModel convert(UserDTO userDTO) {
		UserContactModel contact = null;

		if (Objects.isNull(userDTO)) {
			return null;
		}

		contact = new UserContactModel(userDTO.getId(), userDTO.getEmail(), userDTO.getPhone());
		return contact;
	}
}
