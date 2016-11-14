package com.RESTJerseyExample.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jdk.nashorn.internal.parser.JSONParser;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONObject;

import com.RESTJerseyExample.database.StudentResource;
import com.RESTJerseyExample.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
/**
 * This class has the rest end points
 * for the student resource
 * @author Suneel Reddy
 *
 */
@Path("/student")
public class StudentService  {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public static Response getStudentById(@PathParam("id") int Id ) throws Exception
	{
		StudentResource studentResource = new StudentResource();
		Student student = studentResource.getStudent(Id);
		if(student != null){
			ObjectMapper mapper = new ObjectMapper();
			String jsonstr=mapper.writeValueAsString(student);
			//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
			System.out.println(jsonstr);
			return Response.status(200).entity(jsonstr).build();
		}
		else
		{
			return Response.status(HttpStatus.SC_NOT_FOUND).entity("No record found for student with Id="+Id).build();
		}
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response deleteStudentById(@PathParam("id") int Id)
	{
		StudentResource studentResource=new StudentResource();
		boolean student=studentResource.deleteStudent(Id);
		if(student==true){
			return Response.status(200).entity("Student record with id:"+Id+" Deleted successfully").build();
		}
		else {
			return Response.status(400).entity("Entered id does not match with the records in database").build();
		}	
	}
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudentRecord(@PathParam("student")Student student){
		StudentResource studentResource=new StudentResource();
		boolean output = studentResource.insertStudent(student);
		if(output=true){
			return Response.status(200).entity("Student record updated to database").build();
		}
		else
		{
			return Response.status(400).entity("Student record did not update in database").build();
		}
	}
	@GET
	@Path("/retrive")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudents()
	{
		Student student=new Student();
		String jsonstr = null;
		List<Student> studentlist = null;
		try {
			StudentResource studentResource = new StudentResource();
			studentlist = studentResource.getAllStudents();
			ObjectMapper mapper = new ObjectMapper();
			jsonstr=mapper.writeValueAsString(studentlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(studentlist != null){
			return Response.status(200).entity(jsonstr).build();
		}
		else
			return Response.status(400).entity("Request is of invalid type").build();
	}
	@POST
	@Path("/include")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertAStudentIntoDataBase(Student student) {
		StudentResource studentResource=new StudentResource();
		boolean insertiondone=studentResource.insertStudent(student);
		if(insertiondone == true)
			return Response.status(200).entity("Insertion to the table has been done successfully ").build();
		else 
			return Response.status(400).entity("Insertion to the table was unsuccessful").build();
	}
}

