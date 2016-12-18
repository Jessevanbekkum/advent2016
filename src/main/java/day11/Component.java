package day11;

import java.util.Objects;
import java.util.Set;

public class Component implements Comparable<Component> {

    private String element;
    private Type type;

    public Component(final String element, final Type type) {
        this.element = element;
        this.type = type;
    }

    @Override
    public int compareTo(final Component o) {
        return toString().compareTo(o.toString());
    }

    enum Type {
        Generator, Microchip
    }

    boolean isSafe(Set<Component> floor) {
        if (this.type == Type.Generator) {
            return true;
        }
        boolean myGenerator =
                floor.stream().anyMatch(comp -> comp.type == Type.Generator && comp.element.equals(this.element));

        boolean otherGenerator =
                floor.stream().anyMatch(comp -> comp.type == Type.Generator && !comp.element.equals(this.element));
        return !otherGenerator || myGenerator;
    }

    public String getElement() {
        return element;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return element + type.name().charAt(0);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Component component = (Component) o;
        return Objects.equals(element, component.element) &&
                type == component.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, type);
    }
}
