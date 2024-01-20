package gmx.fwd.utils;

import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class GmxResult {

	HashMap<String, Object> response = new HashMap<>();

	public HashMap<String, Object> resultError(String msg) {
		return result(false, msg, null);
	}

	public HashMap<String, Object> result(Object data) {
		return result(true, null, data);
	}

	public HashMap<String, Object> result(Boolean chk, String msg, Object data) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("result", chk);
		map.put("msg", msg);
		map.put("data", data);

		return map;

	}
}
