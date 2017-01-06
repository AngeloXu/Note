package cn.tedu.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notebook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private String typeId;
	private String name;
	private String desc;
	private Timestamp createTime;
	public Notebook(){
		
	}
	public Notebook(String id, String userId, String typeId, String name, String desc, Timestamp createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.typeId = typeId;
		this.name = name;
		this.desc = desc;
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notebook other = (Notebook) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Notebook [id=" + id + ", userId=" + userId + ", typeId=" + typeId + ", name=" + name + ", desc=" + desc
				+ ", createTime=" + createTime + "]";
	}
	
	
}
