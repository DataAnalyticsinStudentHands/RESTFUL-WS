package dash.dao;

import java.util.List;





import dash.pojo.FormResponse;


/**
 * An Example DAO interface for a simple object.
 * @Author tyler.swensen@gmail.com
 */
public interface FormResponseDao {
	
	public List<FormResponseEntity> getFormResponses(int numberOfFormResponses, Long startIndex);
	
	public int getNumberOfFormResponses();

	/**
	 * Returns a response given its id
	 *
	 * @param id
	 * @return
	 */
	public FormResponseEntity getFormResponseById(Long id);
	
	public void deleteFormResponseById(FormResponse formResponse);

	public Long createFormResponse(FormResponseEntity formResponse);

	public void updateFormResponse(FormResponseEntity formResponse);

	/** removes all responses */
	public void deleteFormResponses();

}
