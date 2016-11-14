package com.RESTJerseyExample.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.RESTJerseyExample.model.Student;
public class StudentResource {
	static Statement stmt=null;
	static Connection conn = null;
	static ResultSet rs=null;
	static List<Student> list=new ArrayList<Student>();
	public static void main(String[] args) throws Exception
	{	
		conn = getDBConnection();
		//System.out.println(getStudent(15618));
		//Student student=new Student();
		//student.setId(15618);
		//student.setAddress("suneel1111");
		//student.setPhone(81060);
		//System.out.println(insertQuery(student));
		StudentResource studentResource = new StudentResource();
		//System.out.println(studentResource.updateStudent(student));
		System.out.println(studentResource.getAllStudents());
		System.out.println(studentResource.deleteStudent(1));
	}
	public  Student updateStudent(Student student) throws Exception{

		Student currentStudent = getStudent(student.getId());
		int studentId = currentStudent.getId();
		String studentName = (student.getName()==null)?currentStudent.getName():student.getName();
		String studentAddress=(student.getAddress()==null)?currentStudent.getAddress():student.getAddress();
		int studentPhone=(student.getPhone()== 0)?currentStudent.getPhone():student.getPhone();
		String insertQuery = "Update student SET studentName=?,studentPhone=?,studentAddress=? WHERE studentId=?";
		java.sql.PreparedStatement ps = conn.prepareStatement(insertQuery);		
		ps.setString(1, studentName);
		ps.setInt(2, studentPhone);
		ps.setString(3, studentAddress);
		ps.setInt(4, studentId);
		ps.execute();
		Student updatedStudentRecord = getStudent(studentId);
		System.out.println(updatedStudentRecord);
		return updatedStudentRecord;
	}
	public  List<Student> getAllStudents()throws Exception
	{

		conn = getDBConnection();
		Statement stmt = conn.createStatement();
		try {

			rs = stmt.executeQuery("select * from student");
			while(rs.next()){
				Student student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setPhone(rs.getInt(3));
				student.setAddress(rs.getString(4));
				//list.add("\n");
				list.add(student);		
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeDBConnection();
		}
		//System.out.println(list);

		return list;
	}

	public static Connection getDBConnection()throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static void closeDBConnection()throws Exception{
		try {
			if(conn!=null)
				conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This method returns a student record
	 * corresponding to the studentId
	 * @param studentId
	 * @param stmt 
	 * @return 
	 * @return Student Object
	 * @throws SQLException 
	 */
	public  Student  getStudent(int studentId)throws Exception
	{
		try {
			conn= getDBConnection();
			Statement stmt=conn.createStatement();
			Student student=new Student();

			String sql=("select * from student where studentId="+studentId);
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				student.setId(rs.getInt("studentId"));
				student.setPhone(rs.getInt("studentPhone"));
				student.setAddress(rs.getString("studentAddress"));
				student.setName(rs.getString("studentName"));
				return student;
			}
			else 
			{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This method deletes a student record from 
	 * the table for a given studentId
	 * @param studentId
	 * @return
	 */
	public  boolean deleteStudent(int studentId)
	{
		boolean value=true;
		try {
			conn= getDBConnection();
			Statement stmt=conn.createStatement();
			String sql=("DELETE  from student where studentId="+studentId);

			int done=stmt.executeUpdate(sql);
			if(done==1){
				value=true;
			}
			else{
				value=false;
			}

		}catch(Exception e){
			e.printStackTrace();
			value=false;
		}
		return value;
	}
	/**
	 * This method deletes all student records from DB
	 * @return
	 */
	public boolean deleteAllStudents()
	{
		return true;
	}
	/**
	 * 
	 * @param student
	 * @return
	 */
	public  boolean insertStudent(Student student){
		try{
			String insertQuery = "insert into student(studentId,studentName,studentPhone,studentAddress) values (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getPhone());
			pstmt.setString(4, student.getAddress());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

}
