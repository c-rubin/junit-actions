public class Grade {
    private final Integer grade;
    private final boolean appr;
    private final boolean cum;

    public Grade(boolean appr, Integer grade, boolean cum){
        this.appr = appr;
        this.grade = grade;
        this.cum = cum;
    }


    public boolean isCum() {
        return cum;
    }

    public boolean isAppr() {
        return appr;
    }

    public Integer getGrade() {
        return null;
    }
}
