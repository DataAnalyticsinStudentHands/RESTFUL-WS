package dash.dao;

import java.util.List;





import dash.pojo.Response;


/**
 * An Example DAO interface for a simple object.
 * @Author tyler.swensen@gmail.com
 */
public interface ResponseDao {
	
	public List<ResponseEntity> getResponses(int numberOfResponses, Long startIndex);
	
	public int getNumberOfResponses();

	/**
	 * Returns a response given its id
	 *
	 * @param id
	 * @return
	 */
	public ResponseEntity getResponseById(Long id);
	
	public void deleteResponseById(Response response);

	public Long createResponse(ResponseEntity response);

	public void updateResponse(ResponseEntity response);

	/** removes all responses */
	public void deleteResponses();

}
