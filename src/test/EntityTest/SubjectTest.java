import Backend.OpenEducation.Model.Subject;
import io.micrometer.core.annotation.TimedSet;

@Test
public class SubjectTest {

    @Test
    void testSubjectWithNullName() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
         }



    @Test
    void testSubjectWithEmptyName(){
    Subject subject = new Subject("");
    assertEqual("", subject.getSubjectName());
    }




     @Test
     void testSubjectNameMinimumLength() {
     Subject subject = new Subject("A");
     assertEquals("A", subject.getName());
        }

    
        @Test
void testSubjectNameMaximumLength() {
    String longName = "ThisIsAVeryLongSubjectNameThatExceedsTheLimit";
    Subject subject = new Subject(longName);

    assertEquals(longName, subject.getSubjectName());
}


         
    }
