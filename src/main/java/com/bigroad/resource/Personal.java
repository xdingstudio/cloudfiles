package com.bigroad.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigroad.httpclient.CloudFileCon;
import com.bigroad.model.res.PersonFileJson;
import com.bigroad.service.personal.PersonFileServiceI;
import com.bigroad.util.Command;
import com.bigroad.util.MD5Check;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/person")
public class Personal {

	@Autowired
	PersonFileServiceI personFile;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> openFolder(@PathParam("id") String id,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			List<PersonFileJson> f = personFile.getAllPersonFiles(id);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}

	@GET
	@Path("{userid}/{folderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> openMyFolder(
			@PathParam("folderid") String folderId, @Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			List<PersonFileJson> f = personFile.getchidFiles(folderId);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}

	@POST
	@Path("{userid}/{folderid}")
	public Response addNewFolder(@PathParam("userid") String userid,
			@PathParam("folderid") String folderId,
			@FormParam("foldername") String foldername,
			@Context HttpHeaders headers) {

		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {

			System.out.println(userid + folderId + foldername);
			personFile.addNewFolder(userid, folderId, foldername);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@PathParam("id") String id) {

		String uploadedFileLocation = "/home/hadoop/uploaded/"
				+ fileDetail.getFileName();
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		String md5=null;
       File file = new File(uploadedFileLocation);
       try {
		              md5=new MD5Check().getFileMD5String(file);
	   } catch (IOException e) {
		// TODO Auto-generated catch block
	     	e.printStackTrace();
	   }
       CloudFileCon con = new CloudFileCon();
      String[] inf = con.verifyUser();
      String cmd = "curl -X PUT  -T  "+uploadedFileLocation+" -H \"X-Auth-Token:"+inf[1]+ "\" "+inf[0]+"/"+"photos"+"/"+fileDetail.getFileName();
      Command.exeCmd(cmd);
      System.out.println(cmd);
    		return Response.status(200).build();
    


	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@DELETE
	@Path("{userid}/{folderid}/{fileid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFile(@PathParam("fileid") String fileid,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
            personFile.deleteFile(fileid, "file");
			System.out.println(fileid);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}
	

	@DELETE
	@Path("{userid}/{folderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFolder(@PathParam("folderid") String folderID,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
            personFile.deleteFile(folderID, "folder");
			System.out.println(folderID);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}
	
	

@POST
@Path("{userid}/{folderid}/{fileid}")
@Produces(MediaType.APPLICATION_JSON)
public Response changeFileName(@PathParam("fileid") String fileid,
	                           @FormParam("foldername") String foldername,
		                       @Context HttpHeaders headers) {
	if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
		personFile.updateFileName(fileid, "file", foldername);
		System.out.println(fileid+foldername);
		return Response.ok().build();
	} else {
		return Response.status(403).build();
	}
}

@POST
@Path("{userid}/{folderid}")
@Produces(MediaType.APPLICATION_JSON)
public Response changeFolderName(@PathParam("folderid") String folderID,
	                             @FormParam("foldername") String foldername,
		                         @Context HttpHeaders headers) {
	if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
		personFile.updateFileName(folderID, "folder", foldername);
		System.out.println(folderID+foldername);
		return Response.ok().build();
	} else {
		return Response.status(403).build();
	}
}
	
	
	
}
