package myapp.sudarshan.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import myapp.sudarshan.annotations.UniqueUserName;
import myapp.sudarshan.repository.UserRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
	
	@Autowired
	private UserRepository userRepository;	

	@Override
	public void initialize(UniqueUserName constraintAnnotation) {	
	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		
		if(userRepository == null)
			return true;
		
		return userRepository.findByName(userName) == null;
	}


}
