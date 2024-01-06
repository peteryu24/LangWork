package gmx.fwd.utils.exception;

public class LangWorkItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private int mgrSeq;

	public LangWorkItemNotFoundException(int mgrSeq) {
		this.mgrSeq = mgrSeq;
	}

	public int getMgrSeq() {
		return mgrSeq;
	}

	public void setMgrSeq(int mgrSeq) {
		this.mgrSeq = mgrSeq;
	}
}
