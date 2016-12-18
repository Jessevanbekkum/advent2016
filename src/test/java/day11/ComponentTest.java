package day11;

import java.util.Set;
import org.junit.Test;

import com.google.common.collect.Sets;

import static org.junit.Assert.*;

public class ComponentTest {


    @Test
    public void shouldFailOnOtherGenerator() {

        Component LM = new Component("LI", Component.Type.Microchip);
        Component HG = new Component("H", Component.Type.Generator);
        Set<Component> floor = Sets.newHashSet(LM, HG);

        assertFalse(LM.isSafe(floor));

    }

    @Test
    public void shouldBeProtectedByOwnGenerator() {

        Component LM = new Component("LI", Component.Type.Microchip);
        Component LG = new Component("LI", Component.Type.Generator);
        Component HG = new Component("H", Component.Type.Generator);
        Set<Component> floor = Sets.newHashSet(LM, LG, HG);

        assertTrue(LM.isSafe(floor));

    }
}
