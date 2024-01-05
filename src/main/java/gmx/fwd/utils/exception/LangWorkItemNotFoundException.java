package gmx.fwd.utils.exception;

public class LangWorkItemNotFoundException extends RuntimeException {

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
