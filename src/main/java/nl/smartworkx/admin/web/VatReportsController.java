package nl.smartworkx.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.application.VatReport;
import nl.smartworkx.admin.application.VatReportService;
import nl.smartworkx.admin.model.Quarter;

/**
 * @version 1.0
 * @autror Joris Wijlens
 * @since 1.0
 */
@RestController
@RequestMapping("/vatReports")
public class VatReportsController {

	private VatReportService vatReportService;

	@Autowired
	public VatReportsController(final VatReportService vatReportService) {

		this.vatReportService = vatReportService;
	}

	@RequestMapping
	public VatReport findById(@RequestParam Integer year, @RequestParam Integer quarter) {

		return vatReportService.findVatReport(new Quarter(year, quarter));
	}
}
