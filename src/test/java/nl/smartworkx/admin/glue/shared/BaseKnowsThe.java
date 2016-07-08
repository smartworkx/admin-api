package nl.smartworkx.admin.glue.shared;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nl.smartworkx.admin.model.DddEntity;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class BaseKnowsThe<T extends DddEntity> {
	public static final String CUCUMBER_GLUE_SCOPE_NAME = "cucumber-glue";

	private LinkedHashMap<Long, T> currents = new LinkedHashMap<>();

	public T getCurrent() {

		List<Map.Entry<Long, T>> entryList =
				new ArrayList<>(currents.entrySet());
		Map.Entry<Long, T> lastEntry =
				entryList.get(entryList.size() - 1);
		return lastEntry.getValue();
	}

	public void setCurrent(final T financialFact) {

		currents.put(financialFact.getId(), financialFact);

	}
}
