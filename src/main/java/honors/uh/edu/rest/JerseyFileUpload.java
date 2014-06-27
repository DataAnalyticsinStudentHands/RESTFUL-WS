package honors.uh.edu.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/files")
public class JerseyFileUpload {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "/Users/plindner/";

	/**
	 * Upload a File
	 */

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(final FormDataMultiPart form) {

		final FormDataBodyPart filePart = form.getField("file");

		final ContentDisposition headerOfFilePart =  filePart.getContentDisposition();

		final InputStream fileInputStream = filePart.getValueAs(InputStream.class);

		final String filePath = SERVER_UPLOAD_LOCATION_FOLDER + headerOfFilePart.getFileName();

		// save the file to the server
		saveFile(fileInputStream, filePath);

		final String output = "File saved to server location using FormDataMultiPart : " + filePath;

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(final InputStream uploadedInputStream, final String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			final byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}

			outpuStream.flush();
			outpuStream.close();

			uploadedInputStream.close();
		} catch (final IOException e) {

			e.printStackTrace();
		}

	}

}