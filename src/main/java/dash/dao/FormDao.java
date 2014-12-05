package dash.dao;

import java.util.List;


import dash.pojo.Form;


/**
 * An Example DAO interface for a simple object.
 * @Author tyler.swensen@gmail.com
 */
public interface FormDao {
	
	public List<FormEntity> getForms(int numberOfForms, Long startIndex);
	
	public int getNumberOfForms();

	/**
	 * Returns a form given its id
	 *
	 * @param id
	 * @return
	 */
	public FormEntity getFormById(Long id);
	
	public void deleteFormById(Form form);

	public Long createForm(FormEntity form);

	public void updateForm(FormEntity form);

	/** removes all forms */
	public void deleteForms();

}
