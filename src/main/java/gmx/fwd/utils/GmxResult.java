package gmx.fwd.utils;

import org.springframework.stereotype.Component;

@Component
public class GmxResult {

	private boolean chk;
	private String msg;
	private Object data;

	public GmxResult resultError(String msg) {
		this.chk = false;
		this.msg = msg;
		this.data = null;
		return this;
	}
	public GmxResult resultErrorEntity(String msg, Object data) {
		this.chk = false;
		this.msg = msg;
		this.data = data;
		return this;
	}

	public GmxResult result(Object data) {
		this.chk = true;
		this.msg = null;
		this.data = data;
		return this;
	}

	public boolean chkFlag() {
		return chk;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}
}
