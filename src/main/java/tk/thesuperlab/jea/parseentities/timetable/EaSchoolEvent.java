package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EaSchoolEvent {
	@JsonProperty("time")
	private EaSchoolEventTime time;
	@JsonProperty("subject")
	private EaSchoolEventSubject subject;
	@JsonProperty("hour_special_type")
	private String specialType;
	@JsonProperty("classroom")
	private EaSchoolEventClassroom classroom;
	@JsonProperty("teachers")
	private List<EaTeacher> teachers;

	public EaSchoolEvent() {
	}

	public EaSchoolEventTime getTime() {
		return time;
	}

	public void setTime(EaSchoolEventTime time) {
		this.time = time;
	}

	public EaSchoolEventSubject getSubject() {
		return subject;
	}

	public void setSubject(EaSchoolEventSubject subject) {
		this.subject = subject;
	}

	public String getSpecialType() {
		return specialType;
	}

	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}

	public EaSchoolEventClassroom getClassroom() {
		return classroom;
	}

	public void setClassroom(EaSchoolEventClassroom classroom) {
		this.classroom = classroom;
	}

	public List<EaTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<EaTeacher> teachers) {
		this.teachers = teachers;
	}
}
