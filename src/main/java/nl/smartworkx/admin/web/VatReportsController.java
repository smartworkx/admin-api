package nl.smartworkx.admin.web;

import nl.smartworkx.admin.model.VatReport;
import nl.smartworkx.admin.model.Quarter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @autror Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/vatReports")
public class VatReportsController {




	@RequestMapping
	public VatReport findById(Quarter period){
		return new VatReport(period,"123","345");
	}
}
