package com.kh.jdbc.day01.student.view;

import java.util.List;

import com.kh.jdbc.day01.student.model.Student;

public interface ViewInterface {
	/**
	 * 메뉴 출력 메소드
	 * @return 입력받은 메뉴번호
	 */
	int printMenu();
	/**
	 * 메시지 출력 메소드
	 * @param msg 입력받은 메시지
	 */
	void displayMsg(String msg);
	/**
	 * 학생 정보 입력 메소드
	 * @return 입력받은 정보가 저장된 Student객체
	 */
	Student inputStudent();
	/**
	 * 학생 정보 전체 출력 메소드
	 * @param sList
	 */
	void printStudents(List<Student> sList);
	/**
	 * 학생 1명의 정보 출력 메소드
	 * @param student 출력할 Student 객체
	 */
	void printStudent(Student student);
	/**
	 * 수정할 정보 입력
	 * @param name 학생이름
	 * @return Student 객체
	 */
	public Student modifyStudent(String name);
	/**
	 * 이름 입력받는 메소드
	 * @return
	 */
	String inputName();
}
