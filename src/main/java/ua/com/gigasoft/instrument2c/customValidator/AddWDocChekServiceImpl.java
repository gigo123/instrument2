package ua.com.gigasoft.instrument2c.customValidator;

import org.springframework.stereotype.Component;

import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;
@Component
public class AddWDocChekServiceImpl  implements AddWDocChekService{

	@Override
	public boolean checkRow(ExDocWEB docRow) {
		System.out.println("Hello from  roe validaror");
		return false;
	}

}
