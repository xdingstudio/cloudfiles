package com.bigroad.model.db;

// Generated 2015-4-25 13:01:25 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TDepartmentOperation generated by hbm2java
 */
@Entity
@Table(name = "T_departmentOperation", catalog = "CloudFiles")
public class TDepartmentOperation implements java.io.Serializable {

	private String departmentOperationId;
	private TFile TFile;
	private TDepartment TDepartment;
	private Integer departmentOperationAuthorityId;

	public TDepartmentOperation() {
	}

	public TDepartmentOperation(String departmentOperationId) {
		this.departmentOperationId = departmentOperationId;
	}

	public TDepartmentOperation(String departmentOperationId, TFile TFile,
			TDepartment TDepartment, Integer departmentOperationAuthorityId) {
		this.departmentOperationId = departmentOperationId;
		this.TFile = TFile;
		this.TDepartment = TDepartment;
		this.departmentOperationAuthorityId = departmentOperationAuthorityId;
	}

	@Id
	@Column(name = "departmentOperationID", unique = true, nullable = false, length = 100)
	public String getDepartmentOperationId() {
		return this.departmentOperationId;
	}

	public void setDepartmentOperationId(String departmentOperationId) {
		this.departmentOperationId = departmentOperationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileID")
	public TFile getTFile() {
		return this.TFile;
	}

	public void setTFile(TFile TFile) {
		this.TFile = TFile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentID")
	public TDepartment getTDepartment() {
		return this.TDepartment;
	}

	public void setTDepartment(TDepartment TDepartment) {
		this.TDepartment = TDepartment;
	}

	@Column(name = "departmentOperationAuthorityID")
	public Integer getDepartmentOperationAuthorityId() {
		return this.departmentOperationAuthorityId;
	}

	public void setDepartmentOperationAuthorityId(
			Integer departmentOperationAuthorityId) {
		this.departmentOperationAuthorityId = departmentOperationAuthorityId;
	}

}
