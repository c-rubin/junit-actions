public class GradeCalculator {
//    static int extraLabPoints;
    public static Grade calculateGrade(int labPoints, float examGrade)throws Exception{
        checkGrades(labPoints,examGrade);
        int lp[]= {labPoints};
        examGrade+=getExtraLabPoints(lp);
        int finalGrade = calcFinalGrade(examGrade,lp[0]);
        return getGrade(finalGrade);
    }

    private static Grade getGrade(int finalGrade) {
        if(finalGrade<18) {
            return new Grade(false, null, false);
        }
        if(finalGrade>30){
            return new Grade(true,30,true);
        }
        return new Grade(true,finalGrade,false);
    }

    private static int calcFinalGrade(Float examGrade, Integer labPoints) {
        if (labPoints<25) return (int)(labPoints*3 + examGrade*15)/10;//round down
        if (labPoints<41) return Math.round((labPoints*3 + examGrade*15)/10);//round normally
        else{
            float temp = (labPoints*3 + examGrade*15)/10;
            int temp2 = (int) temp;
            if (temp % 1 != 0) temp2++;//if it has decimal, round up
            return temp2;
        }
    }

    private static float getExtraLabPoints(int labPoints[]) {
        if (labPoints[0]>50){
            int extraLabPoints = labPoints[0]-50;
            if (extraLabPoints>15) extraLabPoints=15;
            labPoints[0]=50;
            return extraLabPoints/5;
        }
        return 0;
    }

    static void checkGrades(int labPoints, float examGrade) throws Exception{
        if (labPoints<0) {
            throw new Exception("Lab Points out of bounds");
        }

        if (examGrade<0 || examGrade > 10){
            throw new Exception("Exam Points out of bounds");
        }
    }
}
