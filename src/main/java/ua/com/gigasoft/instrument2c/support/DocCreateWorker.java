package ua.com.gigasoft.instrument2c.support;

import java.util.List;

import ua.com.gigasoft.instrument2c.secondModel.ExDocTempStore;


public class DocCreateWorker {

	
	private static float calcTotalAmount  (List<ExDocTempStore> docTempList){
		float tAmount=0;
		for (ExDocTempStore exDocTempStore : docTempList) {
			tAmount+=exDocTempStore.getDoc().getAmount();
		}
		return tAmount;
		
	}
}
