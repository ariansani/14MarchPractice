package vttp.nusiss.MarchHomePrac;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import vttp.nusiss.MarchHomePrac.controllers.QuoteRestController;
import vttp.nusiss.MarchHomePrac.services.QuoteService;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private QuoteService quoteSvc;

	@Autowired
	private QuoteRestController quoteRestCtrl;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(quoteSvc);

	}

	@Test
	public void quoteRestCtrlShouldNotBeNull(){
		Assertions.assertNotNull(quoteRestCtrl);
	}

	@Test
	public void getQuotesShouldBeEqual(){
		int count = 4;
		Collection<String> result = quoteSvc.getQuotes(count);
		Assertions.assertEquals(count,result.size(),"getQuotes does not return the expected count");
	}

}
