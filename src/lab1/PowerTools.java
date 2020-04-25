package lab1;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PowerTools {
    private ArrayList<Tool> list = new ArrayList<Tool>();

    public ArrayList<Tool> all() {
        return this.list;
    }

    public void addTool(Tool tool) {
        this.list.add(tool);
    }

    @Override
    public String toString() {
        return "PowerTools [\n" +
                all().stream()
                     .sorted(new ToolComparator())
                     .map(x -> "\t" + x.toString() + "\n")
                     .collect(Collectors.joining()) +
                "]";
    }
}
