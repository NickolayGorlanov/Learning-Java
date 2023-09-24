import java.util.Date;
import java.text.SimpleDateFormat;

public class Voter {

    private String name;
    private Date birthDay; // Вернуто обратно к Date

    public Voter(String name, Date birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Voter voter = (Voter) obj;
        return name.equals(voter.name) && birthDay.equals(voter.birthDay);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + birthDay.hashCode();
        return result;
    }

    public String toString() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + dayFormat.format(birthDay) + ")";
    }

    public String getName() {
        return name;
    }

    public Date getBirthDay() {
        return birthDay;
    }
}
