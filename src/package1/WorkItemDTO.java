package package1;

/**
 * Created by usegutierrez on 11/19/15.
 */
public abstract class WorkItemDTO {

    private final PersonDTO person = new PersonDTO();
    private final boolean isA;
    private final boolean isB;

    public WorkItemDTO(boolean isA, boolean isB) {
        this.isA = isA;
        this.isB = isB;

        if(isA && isB) {
            throw new RuntimeException("A and B could not be same " +
                    "at the same time");
        }
    }

    public boolean isA() {
        return isA;
    }

    public boolean isB() {
        return isB;
    }

    public void setName(String name) {
        this.person.setName(name);
    }

    public String getName() {
        return this.person.getName();
    }
}
