import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ FileMakerTester.class, NSABackdoorTester.class, ICSFormatTester.class })
public class AllTests {

}
