package nl.smartworkx.admin.mt940;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class Mt940ImporterServiceTest {

	private Mt940ImporterService mt940ImporterService;

	@Before
	public void before(){
mt940ImporterService = new Mt940ImporterService(new PrintCreateFinancialFactService());
	}

	@Test
	public void parseMt940(){
		mt940ImporterService.importMt940(Mt940ImporterService.class.getResourceAsStream("/201604.940"));
	}

	@Test
	public void testGetName(){

		String counterParty = mt940ImporterService
				.getCounterParty("32//CSID/NL03ZZZ301771260000//CNTP/NL12INGB0677515995/INGBNL2A/TL"
						+ "S BV inzake OV-Chipkaart///REMI/USTD//Automatisch opladen OV-chip"
						+ "kaart Kaartnummer:3528020024626470 Oplaaddatum/tijd:29-10-14 09:3"
						+ "0/");
		Assert.assertThat(counterParty, Matchers.equalTo("TLS BV inzake OV-Chipkaart"));
	}

}