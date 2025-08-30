package CRUDOperation.StudentCrudJsp;

public class Student {
    private int id;
    private String rollNo;
    private String name;
    private String email;
    private String program;

    public Student() {
    }

    public Student(int id, String rollNo, String name, String email, String program) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.program = program;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
