import org.junit.Test;

import static org.junit.Assert.*;

public class GradeTest {
    @Test
    public void testGrade(){
        boolean appr = true;
        int num = 30;
        boolean cum = true;

        Grade grade = new Grade(appr,num,cum);

        assertEquals(num, (int) grade.getGrade());
        assertEquals(cum,grade.isCum());
        assertEquals(appr,grade.isAppr());
    }

    @Test
    public void testGradeOOBLab(){
        Exception oobLab = assertThrows(Exception.class, () -> {
            GradeCalculator.calculateGrade(-1,-0.1f);
        });
        assertEquals("Lab Points out of bounds", oobLab.getMessage());
    }

    @Test
    public void testGradeOOBExam(){
        Exception oobLab = assertThrows(Exception.class, () -> {
            GradeCalculator.calculateGrade(0,-0.1f);
        });
        assertEquals("Exam Points out of bounds", oobLab.getMessage());

        oobLab = assertThrows(Exception.class, () -> {
            GradeCalculator.calculateGrade(50,10.1f);
        });
        assertEquals("Exam Points out of bounds", oobLab.getMessage());
    }

    @Test
    public void testApproved(){
        try {
            Grade g = GradeCalculator.calculateGrade(0, 0);
            assertFalse(g.isAppr());
            assertNull(g.getGrade());

            g = GradeCalculator.calculateGrade(30,6);
            assertTrue(g.isAppr());
            assertEquals(18, (int) g.getGrade());


        }catch(Exception ex) {
            ex.printStackTrace();
            fail("Exception");
        }
    }

    @Test
    public void testGradeNum() {
        try {
            Grade grade = GradeCalculator.calculateGrade(31, 10);
            assertEquals(24, (int) grade.getGrade());//31*3=93 10*15=150 243/10 = 24.3

            grade = GradeCalculator.calculateGrade(31, 6);
            assertEquals(18, (int) grade.getGrade());//6*15 = 90 183/10

            grade = GradeCalculator.calculateGrade(30, 6.0001f);
            assertEquals(18, (int) grade.getGrade());//180.0015/10

//            grade = GradeCalculator.calculateGrade(45,7.499999f);
//            assertEquals(25, (int) grade.getGrade());// 135 + 112.499985
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception");
        }
    }

    @Test
    public void testCum(){
        try{
            Grade grade = GradeCalculator.calculateGrade(54,10);
            assertEquals(30,(int)grade.getGrade());
            assertFalse(grade.isCum());

            grade = GradeCalculator.calculateGrade(55,10);
            assertEquals(30,(int)grade.getGrade());
            assertTrue(grade.isCum());

            grade = GradeCalculator.calculateGrade(700,1);//50*3 + (1+3)*15  150+60 = 210 = 21
            assertEquals(21,(int)grade.getGrade());
            assertFalse(grade.isCum());
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Exception");
        }
    }

    @Test
    public void testRounds(){
        try{
            Grade grade = GradeCalculator.calculateGrade(24,7.6f);//18.6 rounded down
            assertEquals(18,(int)grade.getGrade());

            grade = GradeCalculator.calculateGrade(700,0);//50*3 + (0+3)*15  150+45 = 195 = 20 (19.5 rounded up)
            assertEquals(20,(int)grade.getGrade());

            grade = GradeCalculator.calculateGrade(25,7.6f);//18.6 rounded normally
            assertEquals(19,(int)grade.getGrade());

            grade = GradeCalculator.calculateGrade(25,7.2f);//18.3 rounded normally
            assertEquals(18,(int)grade.getGrade());

            grade = GradeCalculator.calculateGrade(40,7.6f);//23.4 rounded normally
            assertEquals(23,(int)grade.getGrade());


            grade = GradeCalculator.calculateGrade(40,7.7f);//23.5 rounded normally
            assertEquals(24,(int)grade.getGrade());

            grade = GradeCalculator.calculateGrade(41,7.3f);//23.25 rounded up
            assertEquals(24,(int)grade.getGrade());

            grade = GradeCalculator.calculateGrade(41,5.8f);//21.0 rounded up (must remain 21)
            assertEquals(21,(int)grade.getGrade());
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Exception");
        }
    }
}
