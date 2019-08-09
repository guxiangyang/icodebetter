package iwb.domain.db;

import java.time.Instant;

// Generated Feb 4, 2007 3:49:13 PM by Hibernate Tools 3.2.0.b9

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import iwb.domain.result.W5GlobalFuncResult;
import iwb.util.GenericUtil;

/**
 * LogUserAction generated by hbm2java
 */
@Entity
@Table(name="log5_db_func_action",schema="iwb")
public class Log5GlobalFuncAction implements java.io.Serializable, Log5Base {
	private static final long serialVersionUID = 1342520916422912873L;

	private int logId;

	private int userId;
	
	private int dbFuncId;

	private String dsc;
	private String projectUuid;  
	private String error;
	private	String transactionId;

	private int processTime;
	private long startTime;

	public String toInfluxDB() {
		StringBuilder s=new StringBuilder();
		s.append("func_action,func_id=").append(getDbFuncId());
		if(projectUuid!=null)s.append(",project_uuid=").append(projectUuid);
		s.append(" user_id=").append(getUserId()).append("i,duration=").append(getProcessTime()).append("i,code=\"").append(GenericUtil.stringToJS2(getDsc())).append("\"");
		if(!GenericUtil.isEmpty(transactionId))s.append(",trid=\"").append(transactionId).append("\"");
		if(!GenericUtil.isEmpty(error))s.append(",error=\"").append(GenericUtil.stringToJS2(error)).append("\"");
		s.append(" ").append(startTime).append("000000");
		return s.toString();
	}

	
	@Column(name="dsc")
	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}



	public Log5GlobalFuncAction() {
	}

	public Log5GlobalFuncAction(W5GlobalFuncResult r) {
		this.dbFuncId = r.getGlobalFuncId();
		if(r.getScd()!=null) {
			if(r.getScd().get("userId")!=null)this.userId = (Integer)r.getScd().get("userId");
			if(r.getScd().get("projectId")!=null)this.projectUuid = (String)r.getScd().get("projectId");
		}
		this.transactionId =r.getRequestParams()!=null ? r.getRequestParams().get("_trid_"):null;
		this.startTime=Instant.now().toEpochMilli();
		this.processTime = -1;
	}

    @SequenceGenerator(name="sex_log_db_func_action",sequenceName="iwb.seq_log_db_func_action",allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sex_log_db_func_action")
	@Column(name="log_id")
	public int getLogId() {
		return this.logId;
	}

	@Column(name="db_func_id")
	public int getDbFuncId() {
		return dbFuncId;
	}

	public void setDbFuncId(int dbFuncId) {
		this.dbFuncId = dbFuncId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="process_time")
	public int getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public void calcProcessTime() {
		this.processTime = (int)(Instant.now().toEpochMilli() - this.startTime);
	}

	@Transient
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	@Transient
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}

}
