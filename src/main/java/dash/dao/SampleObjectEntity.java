package dash.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

import dash.pojo.SampleObject;

/**
 * This is an example implementation of an entity for a simple object (non-user)
 *
 * @author Tyler.swensen@gmail.com
 *
 */
@Entity
@Table(name="sample_object")
public class SampleObjectEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the user
	 * Be aware that every object/entity MUST have an id */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	/** basic_field_sample of the user */
	@Column(name = "basic_field_sample")
	private String basic_field_sample;

	/** insertion date in the database */
	@Column(name = "time_stamp_sample")
	private Date time_stamp_sample;

	public SampleObjectEntity(){}


	public SampleObjectEntity( String basic_field_sample) {
		super();
		this.basic_field_sample = basic_field_sample;
	}



	public SampleObjectEntity(SampleObject sampleObject) {
		try {
			BeanUtils.copyProperties(this, sampleObject);
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBasic_field_sample() {
		return basic_field_sample;
	}

	public void setBasic_field_sample(String basic_field_sample) {
		this.basic_field_sample = basic_field_sample;
	}


	public Long getId() {
		return id;
	}

	public void setId( Long id) {
		this.id = id;
	}

	public Date getTime_stamp_sample() {
		return time_stamp_sample;
	}

	public void setTime_stamp_sample(Date time_stamp_sample) {
		this.time_stamp_sample = time_stamp_sample;
	}

}
