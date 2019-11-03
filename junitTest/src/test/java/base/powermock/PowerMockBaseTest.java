package base.powermock;

import com.spring.learn.service.DateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateService.class)
public class PowerMockBaseTest {

    @Mock
    DateService dateService;

    @Test
    public void mockStaticMethodTest() {
        PowerMockito.mockStatic(DateService.class);
        Mockito.when(DateService.getStartDate()).thenReturn("2019-01-01");

        Assert.assertEquals("2019-01-01",DateService.getStartDate());
    }
}
