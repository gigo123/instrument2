package ua.com.gigasoft.instrument2c.customValidator;

import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;

public class AddWDocChekServiceImpl  implements AddWDocChekService{

	@Override
	public boolean checkRow(ExDocWEB docRow) {
		System.out.println("Hello from  roe validaror");
		return false;
	}

}
