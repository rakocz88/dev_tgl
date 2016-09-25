package pl.pilaf.tgl;

import static com.jayway.restassured.RestAssured.when;
import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.Assert;
import pl.pilaf.inz.Application;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.tgl.constants.BandConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:9994")
@ActiveProfiles("test")
public class BandIntegrationTest {

	@Value("${local.server.port}")
	int port;

	@Autowired
	private BandRepository bandRepository;

	@Before
	public void init() {
		bandRepository.save(new Band(23, BandConstants.BAND_NAME, BandConstants.BAND_DESC, null, null, null, null));
	}

	@Test
	public void isStatusCodeOk() {
		String url = "http://localhost:9994/band/all";
		when().get(url).then().statusCode(200);
	}

	@Test
	public void is1Band() {
		String url = "http://localhost:9994/band/all";
		@SuppressWarnings("unchecked")
		Band[] bandArray = when().get(url).getBody().as(Band[].class);
		Assert.assertEquals(1, bandArray.length);
		Band band = (Band) bandArray[0];
		assertBandValues(band);

	}

	private void assertBandValues(Band band) {
		assertEquals(BandConstants.BAND_NAME, band.getName());
		assertEquals(BandConstants.BAND_DESC, band.getDescription());
	}

}