package nl.smartworkx.admin.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.application.VatReport;
import nl.smartworkx.admin.model.Quarter;

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
