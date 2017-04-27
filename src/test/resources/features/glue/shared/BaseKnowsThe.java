package features.glue.shared;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nl.smartworkx.admin.model.Identifiable;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class BaseKnowsThe<T extends Identifiable> {
    public static final String CUCUMBER_GLUE_SCOPE_NAME = "cucumber-glue";

    private LinkedHashMap<Object, T> currents = new LinkedHashMap<>();

    public T getCurrent() {

        List<Map.Entry<Object, T>> entryList =
                new ArrayList<>(currents.entrySet());
        Map.Entry<Object, T> lastEntry =
                entryList.get(entryList.size() - 1);
        return lastEntry.getValue();
    }

    public void setCurrent(final T current) {

        currents.put(current.getId(), current);

    }


}
