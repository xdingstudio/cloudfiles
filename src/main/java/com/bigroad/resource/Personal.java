package com.bigroad.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.bigroad.model.db.TFile;
import com.bigroad.model.res.PersonFileJson;
import com.bigroad.service.personal.PersonFileServiceI;
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
			return Response.ok().entity("success").build();
		} else {
			return Response.status(403).build();
		}
	}

	@POST
	@Path("{userid}/{folderid}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@PathParam("userid") String userID,
			@PathParam("folderid") String folderID) {
		System.out.println(folderID
				+ "-----------------------------------------------------");
		String uploadedFileLocation = "/home/hadoop/uploaded/"
				+ fileDetail.getFileName();
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		String md5 = null;
		File file = new File(uploadedFileLocation);
		try {
			md5 = new MD5Check().getFileMD5String(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fieName = fileDetail.getFileName();
		CloudFileCon con = new CloudFileCon();
		con.uploadFile("photos", fieName, file);
		file.delete();
		TFile tFile = new TFile();

		String fileExt = fieName.substring(fieName.lastIndexOf("."));
		tFile.setFileExtension(fileExt);
		tFile.setFileMd5(md5);
		tFile.setFileName(fieName);
		tFile.setFileNewTime(new Date());
		tFile.setFileType(0);
		personFile.saveFile(tFile, folderID, userID);
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
	// 删除文件,放到回收站
	@Path("{userid}/{folderid}/{fileid}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFile(@PathParam("userid") String userID,
			@PathParam("fileid") String fileID, @Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.moveFileToRecycle(userID, fileID, "file");
			System.out.println(userID + " " + fileID);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}

	@DELETE
	// 删除文件夹,放到回收站
	@Path("{userid}/{folderid}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFolder(@PathParam("userid") String userID,
			@PathParam("folderid") String folderID,
			@FormParam("targetFolderID") String targetFolderID,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.moveFileToRecycle(userID, folderID, "folder");
			System.out.println(userID + " " + folderID);
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
			System.out.println(fileid + foldername);
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
			System.out.println(folderID + foldername);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}

	@PUT
	@Path("{userid}/{folderid}/{fileid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response moveFile(@PathParam("fileid") String fileID,
			@PathParam("folderid") String targetFolderID,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.moveFile(fileID, targetFolderID, "file");
			System.out.println(fileID + " " + targetFolderID);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}

	@PUT
	@Path("{userid}/{folderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response moveFolder(@PathParam("folderid") String folderID,
			@FormParam("targetFolderID") String targetFolderID,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.moveFile(folderID, targetFolderID, "folder");
			System.out.println(folderID + " " + targetFolderID);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}

	@DELETE
	// 删除回收站文件
	@Path("{userid}/recycle/{folderid}/{fileid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRecFile(@PathParam("fileid") String fileid,
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
	// 删除回收站文件夹
	@Path("{userid}/recycle/{folderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRecFolder(@PathParam("folderid") String folderid,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.deleteFile(folderid, "folder");
			System.out.println(folderid);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}

	@GET
	@Path("{userid}/music")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> searchMusic(@PathParam("userid") String userid,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			System.out.println("-------------------------------" + userid);
			List<PersonFileJson> f = personFile.getAllMusic(userid);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}

	@GET
	@Path("{userid}/video")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> searchVideo(@PathParam("userid") String userid,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			System.out.println("-------------------------------" + userid);
			List<PersonFileJson> f = personFile.getAllVedio(userid);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}

	@GET
	@Path("{userid}/picture")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> searchPicture(
			@PathParam("userid") String userid, @Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			System.out.println("-------------------------------" + userid);
			List<PersonFileJson> f = personFile.getAllPicture(userid);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}

	@GET
	@Path("{userid}/document")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> searchDocument(
			@PathParam("userid") String userid, @Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			System.out.println("-------------------------------" + userid);
			List<PersonFileJson> f = personFile.getAllDocument(userid);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}

	@GET
	// 获取回收站所有文件
	@Path("{userid}/recycle")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonFileJson> searchRecycle(
			@PathParam("userid") String userid, @Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			System.out.println("-------------------------------" + userid);
			List<PersonFileJson> f = personFile.getAllMyRecycle(userid);
			return f;
		} else {
			return new ArrayList<PersonFileJson>();
		}
	}
	
	@PUT
	// 从回收站回收 文件                                                              
	@Path("{userid}/recover/{folderid}/{fileid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recoverRecycleFile(@PathParam("fileid") String fileID,
			@PathParam("folderid") String targetFolderID,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.moveFile(fileID, targetFolderID, "file");
			System.out.println(fileID + " " + targetFolderID);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}
	
	@PUT
	// 从回收站回收文件夹                                                               
	@Path("{userid}/recover/{folderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recoverRecycleFolder(@PathParam("folderid") String folderID,
			@FormParam("targetFolderID") String targetFolderID,
			@Context HttpHeaders headers) {
		if (headers.getRequestHeaders().getFirst("X-Auth-Token").equals("sss")) {
			personFile.moveFile(folderID, targetFolderID, "folder");
			System.out.println(folderID + " " + targetFolderID);
			return Response.ok().build();
		} else {
			return Response.status(403).build();
		}
	}
	

}
