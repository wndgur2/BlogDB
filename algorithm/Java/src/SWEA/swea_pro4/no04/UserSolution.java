package SWEA.swea_pro4.no04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class UserSolution {
	private static final int[] GRADES = {1, 2, 3};
	private static final char[] GENDERS = {'m', 'f'}; 
	private static Map<Character, Integer> GENDER_MAP = new HashMap<>();
	{
		GENDER_MAP.put('m', 0);
		GENDER_MAP.put('f', 1);
	}
	
	private class Student implements Comparable<Student>{
		private int id;
		private int grade;
		private char gender;
		private int score;
		
		public Student(int id, int grade, char gender, int score){
			this.id = id;
			this.grade= grade;
			this.gender = gender;
			this.score = score;
		}
		
		// for temporary student
		public Student(int score) {
			this.id = 0;
			this.score = score;
		}

		@Override
		public int compareTo(Student o) {
			if(score == o.score) {
				return id - o.id; // 2. id 오름차순 정렬
			}
			return score - o.score; // 1. score 오름차순 정렬
		}
	}
	
	private Map<Integer, Student> studentMap;
	private List<List<TreeSet<Student>>> students;
	
	public void init() {
		studentMap = new HashMap();
		students = new ArrayList<>();
		
		for(int i=0; i<GRADES.length; i++) {
			students.add(new ArrayList<>());
			
			for(int j=0; j<GENDERS.length; j++) 
				students.get(i).add(new TreeSet<Student>());
			
		}
		return;
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		TreeSet<Student> tempSet = students.get(mGrade-1).get(GENDER_MAP.get(mGender[0]));
		
		Student newStudent = new Student(mId, mGrade, mGender[0], mScore);
		tempSet.add(newStudent);
		studentMap.put(mId, newStudent);
		return tempSet.last().id;
	}

	public int remove(int mId) {
		if(studentMap.containsKey(mId)) {
			Student toRemove = studentMap.get(mId);
			TreeSet<Student> tempSet = students.get(toRemove.grade-1).get(GENDER_MAP.get(toRemove.gender));
			tempSet.remove(toRemove);
			studentMap.remove(toRemove);
			if(tempSet.isEmpty()) {
//				System.out.println("해당 조건에 남은 학생이 없음");
				return 0;
			}
			Student first = tempSet.first();
			return first.id;
		}
//		System.out.println("해당 학생이 없음");
		return 0;
	}

	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
		Student tempStudent = new Student(mScore);
		TreeSet<Student> tempSet = new TreeSet<>();
		for (int i = 0; i < mGradeCnt; i++) {
			List<TreeSet<Student>> gradeSets = students.get(mGrade[i]-1);
			for (int j = 0; j < mGenderCnt; j++) {
				Student res = gradeSets.get(GENDER_MAP.get(mGender[j][0])).ceiling(tempStudent);
				if(res!=null)
					tempSet.add(res);
			}
		}
		if(tempSet.isEmpty()) return 0;
		return tempSet.first().id;
	}
}