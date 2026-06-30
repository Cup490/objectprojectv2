public class InvalidActionException extends Exception {
    public static final String LECTURER_ALREADY_EXISTS = "A lecturer with this name or ID already exists.";
    public static final String DEPARTMENT_ALREADY_EXISTS = "A department with this name already exists.";
    public static final String COMMITTEE_ALREADY_EXISTS = "A committee with this name already exists.";
    public static final String INVALID_CHAIRPERSON = "The chairperson must be at least a Doctor or Professor.";
    public static final String LECTURER_NOT_FOUND = "The specified Lecturer ID does not exist.";
    public static final String DEPARTMENT_NOT_FOUND = "The specified Department does not exist.";
    public static final String COMMITTEE_NOT_FOUND = "The specified Committee does not exist.";
    public static final String ALREADY_IN_DEPARTMENT = "This lecturer is already assigned to a department.";
    public static final String CHAIR_CANNOT_BE_MEMBER = "The chairman cannot be added as a regular member.";
    public static final String ALREADY_IN_COMMITTEE = "This lecturer is already a member of the committee.";
    public static final String NOT_IN_COMMITTEE = "This lecturer is not a member of the committee.";
    public static final String ARTICLE_ALREADY_EXISTS = "This article is already in the list.";

    public InvalidActionException(String message) {
        super(message);
    }
}