package dash.dao;

import java.util.List;


import dash.pojo.FileUpload;


/**
 * An Example DAO interface for a simple object.
 * @Author tyler.swensen@gmail.com
 */
public interface FileUploadDao {
	
	public List<FileUpload> getFileUploads(int numberOfFileUploads, Long startIndex);
	
	public int getNumberOfFileUploads();

	/**
	 * Returns a fileUpload given its id
	 *
	 * @param id
	 * @return
	 */
	public FileUpload getFileUploadById(Long id);
	
	public void deleteFileUploadById(FileUpload fileUpload);

	public Long createFileUpload(FileUpload fileUpload);

	public void updateFileUpload(FileUpload fileUpload);

	/** removes all fileUploads */
	public void deleteFileUploads();

}
