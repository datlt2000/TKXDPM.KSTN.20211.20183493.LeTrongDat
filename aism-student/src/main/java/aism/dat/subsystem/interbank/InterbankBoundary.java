package aism.dat.subsystem.interbank;

import aism.dat.common.exception.UnrecognizedException;
import aism.dat.utils.API;

public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
