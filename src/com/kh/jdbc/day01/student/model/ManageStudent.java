package com.kh.jdbc.day01.student.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ManageStudent implements ManageInterface{
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "JHJDBC";
	private static final String PASSWORD = "JHJDBC";
	private List<Student> sList;
	
	
	
	public ManageStudent() {
		sList = new ArrayList<Student>();
	}
	
	@Override
	public Student searchOneByName(String name) {
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rset = null;

	    String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '" + name + "'";

	    try {
	        Class.forName(DRIVER_NAME);
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        stmt = conn.createStatement();
	        rset = stmt.executeQuery(query);

	        if (rset.next()) {
	        	String studentName = rset.getString("STUDENT_NAME");
	        	int firstScore = rset.getInt("FIRST_SCORE");
	        	int secondScore = rset.getInt("SECOND_SCORE");
	        	
	        	//	        	student = new Student();
//	            student.setName(rset.getString("STUDENT_NAME"));
//	            student.setFirstScore(rset.getInt("FIRST_SCORE"));
//	            student.setSecondScore(rset.getInt("SECOND_SCORE"));
	            Student student = new Student(studentName,firstScore,secondScore);
	            return student;
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
	            rset.close();
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return null;

	}

	@Override
	public int searchIndexByName(String name) {
		for(int i = 0; i < sList.size(); i++) {
			if(sList.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1; // index가 0이면 첫번째 값이기 때문에 -1로 리턴
	}
	public List<Student> getAllStudents() {
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL";
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement(); // 워크시트 열기
			rset = stmt.executeQuery(query);
			// 후처리
			// rset에 있는 필드의 값을 Student의 필드에 하나씩 넣어줌
			// Student 객체는 List에 저장해서 보내줌
			// sList를 생성자에서 한번만 초기화하는 것이 아니라
			// getAllStudent() 메소드가 동작할 때마다 초기화해서 값이 누적되지 않도록 함.
			sList = new ArrayList<Student>();
			while(rset.next()) {
				Student student = new Student();
				student.setName(rset.getString("STUDENT_NAME"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
				sList.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sList;
	}
	@Override
	public int addStudent(Student student) {
		// 정보가 입력된 student객체를 sList에 저장
		
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getName()+"',"
					+ ""+student.getFirstScore()+" , "
					+ ""+student.getSecondScore()+")";
			result = stmt.executeUpdate(query);
//			if(result > 0) {
//				System.out.println("학생정보 추가 완료");
//			} else {
//				System.out.println("학생정보 추가 실패.");
//			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public void setStudent(int index, Student student) {
		sList.set(index, student);
	}
	@Override
	public int setStudent(Student student) {
		
		String query = "UPDATE STUDENT_TBL SET FIRST_SCORE = "+student.getFirstScore()+""
				+ "							, SECOND_SCORE = "+student.getSecondScore()+"  "
											+"WHERE STUDENT_NAME = '"+student.getName() +"'";
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
//			if(result > 0) {
//				System.out.println("데이터 수정이 완료되었습니다.");
//			} else {
//				System.out.println("데이터 수정이 완료되지 않았습니다.");
//			}
		} catch (ClassNotFoundException e) {
			
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void removeStudent(int index) {
		sList.remove(index);
	}
	@Override
	public void removeStudent(String name) {
		Connection conn = null;
		Statement stmt = null;
	    try {
	    	conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        stmt = conn.createStatement();
	    	Class.forName(DRIVER_NAME);
	        String query = "DELETE FROM STUDENT_TBL WHERE STUDENT_NAME = '" + name + "'";
	        int result = stmt.executeUpdate(query);
	        
	        if(result > 0) {
	            System.out.println("학생정보 삭제 완료");
	        } else {
	            System.out.println("학생정보 삭제 실패");
	        }
	        
	        
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}


