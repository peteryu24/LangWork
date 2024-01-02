package gmx.fwd.vo.contentvo;

import java.util.Date;

public class ContentVo {	
	private int mgrSeq;
	
	private String reqName;
	private String resName;
	
	private String reqLang;
	private String resLang;
	
	private Date reqTime;
	private Date resTime;

	private String etcNote;
	private int ctyCode;
	private String prjCode;
	
	public int getMgrSeq() {
		return mgrSeq;
	}
	public void setMgrSeq(int mgrSeq) {
		this.mgrSeq = mgrSeq;
	}	
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getReqLang() {
		return reqLang;
	}
	public void setReqLang(String reqLang) {
		this.reqLang = reqLang;
	}
	public String getResLang() {
		return resLang;
	}
	public void setResLang(String resLang) {
		this.resLang = resLang;
	}
	public Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	public Date getResTime() {
		return resTime;
	}
	public void setResTime(Date resTime) {
		this.resTime = resTime;
	}
	public String getEtcNote() {
		return etcNote;
	}
	public void setEtcNote(String etcNote) {
		this.etcNote = etcNote;
	}
	public int getCtyCode() {
		return ctyCode;
	}
	public void setCtyCode(int ctyCode) {
		this.ctyCode = ctyCode;
	}
	public String getPrjCode() {
		return prjCode;
	}
	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}
}