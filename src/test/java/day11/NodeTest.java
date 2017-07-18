package day11;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import com.google.common.collect.Sets;

public class NodeTest {
    @Before
    public void init() {
        NodeMemory.INSTANCE.allNodes.clear();
    }
    /**
     * The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.
     * The second floor contains a hydrogen generator.
     * The third floor contains a lithium generator.
     * The fourth floor contains nothing relevant.
     */
    @Test
    public void shouldDoExample() {
        Map<Integer, Set<Component>> inventory = new HashMap<>();
        inventory.put(1, Sets.newHashSet(
                new Component("hydrogen", Component.Type.Microchip),
                new Component("lithium", Component.Type.Microchip)));
        inventory.put(2, Sets.newHashSet(
                new Component("hydrogen", Component.Type.Generator)));
        inventory.put(3, Sets.newHashSet(
                new Component("lithium", Component.Type.Generator)));
        inventory.put(4, Collections.emptySet());
        Node start = new Node(inventory, 1, 0);

        Calculator calculator = new Calculator();
        calculator.calculateDistance(start);


    }

    /**
     * The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
     * The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
     * The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
     * The fourth floor contains nothing relevant.
     */
    @Test
    public void shouldDoInput() {
        Map<Integer, Set<Component>> inventory = new HashMap<>();
        inventory.put(1, Sets.newHashSet(
                new Component("T", Component.Type.Generator),
                new Component("T", Component.Type.Microchip),
                new Component("P", Component.Type.Generator),
                new Component("S", Component.Type.Generator)));
        inventory.put(2, Sets.newHashSet(
                new Component("P", Component.Type.Microchip),
                new Component("S", Component.Type.Microchip)));
        inventory.put(3, Sets.newHashSet(
                new Component("PR", Component.Type.Generator),
                new Component("PR", Component.Type.Microchip),
                new Component("R", Component.Type.Generator),
                new Component("R", Component.Type.Microchip)));
        inventory.put(4, Collections.emptySet());
        Node start = new Node(inventory, 1, 0);

        Calculator calculator = new Calculator();
        calculator.calculateDistance(start);
    }

    /**
     * The first floor contains a polonium generator, a thulium generator, a thulium-compatible microchip, a promethium generator, a ruthenium generator, a ruthenium-compatible microchip, a cobalt generator, and a cobalt-compatible microchip.
     The second floor contains a polonium-compatible microchip and a promethium-compatible microchip.
     The third floor contains nothing relevant.
     The fourth floor contains nothing relevant.
     */
    @Test
    public void shouldDoArnout() {
        Map<Integer, Set<Component>> inventory = new HashMap<>();
        inventory.put(1, Sets.newHashSet(
                new Component("P", Component.Type.Generator),
                new Component("T", Component.Type.Microchip),
                new Component("T", Component.Type.Generator),
                new Component("E", Component.Type.Generator),
                new Component("E", Component.Type.Microchip),
                new Component("D", Component.Type.Generator),
                new Component("D", Component.Type.Microchip),
                new Component("R", Component.Type.Generator)));
        inventory.put(2, Sets.newHashSet(
                new Component("P", Component.Type.Microchip),
                new Component("R", Component.Type.Microchip)));
        inventory.put(3, Collections.emptySet());
        inventory.put(4, Collections.emptySet());
        Node start = new Node(inventory, 1, 0);

        Calculator calculator = new Calculator();
        calculator.calculateDistance(start);
    }
    @Test
    public void shouldDoInput2() {
        Map<Integer, Set<Component>> inventory = new HashMap<>();
        inventory.put(1, Sets.newHashSet(
                new Component("T", Component.Type.Generator),
                new Component("T", Component.Type.Microchip),
                new Component("E", Component.Type.Generator),
                new Component("E", Component.Type.Microchip),
                new Component("D", Component.Type.Generator),
                new Component("D", Component.Type.Microchip),
                new Component("P", Component.Type.Generator),
                new Component("S", Component.Type.Generator)));
        inventory.put(2, Sets.newHashSet(
                new Component("P", Component.Type.Microchip),
                new Component("S", Component.Type.Microchip)));
        inventory.put(3, Sets.newHashSet(
                new Component("PR", Component.Type.Generator),
                new Component("PR", Component.Type.Microchip),
                new Component("R", Component.Type.Generator),
                new Component("R", Component.Type.Microchip)));
        inventory.put(4, Collections.emptySet());
        Node start = new Node(inventory, 1, 0);

        Calculator calculator = new Calculator();
        calculator.calculateDistance(start);


    }
}
