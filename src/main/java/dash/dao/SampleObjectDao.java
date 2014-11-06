package dash.dao;

import java.util.List;


import dash.pojo.SampleObject;


/**
 * An Example DAO interface for a simple object.
 * @Author tyler.swensen@gmail.com
 */
public interface SampleObjectDao {
	
	public List<SampleObjectEntity> getSampleObjects(int numberOfSampleObjects, Long startIndex);
	
	public int getNumberOfSampleObjects();

	/**
	 * Returns a sampleObject given its id
	 *
	 * @param id
	 * @return
	 */
	public SampleObjectEntity getSampleObjectById(Long id);
	
	public void deleteSampleObjectById(SampleObject sampleObject);

	public Long createSampleObject(SampleObjectEntity sampleObject);

	public void updateSampleObject(SampleObjectEntity sampleObject);

	/** removes all sampleObjects */
	public void deleteSampleObjects();

}
