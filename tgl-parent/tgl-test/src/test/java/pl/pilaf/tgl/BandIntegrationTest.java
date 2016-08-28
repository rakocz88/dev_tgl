package pl.pilaf.tgl;

import static com.jayway.restassured.RestAssured.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import pl.pilaf.inz.Application;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.repository.BandRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:9994")
@ActiveProfiles("test")
public class BandIntegrationTest {
	RestTemplate restTemplate = new TestRestTemplate();
	@Value("${local.server.port}")
	int port;

	@Autowired
	private BandRepository bandRepository;

	@Before
	public void init() {
		bandRepository.save(new Band(23, "Marian", "Wilki", null, null, null, null));
	}

	@Test
	public void shouldRegisterUser() {
		// ResponseEntity<BandWrapper[]> response =
		// restTemplate.getForEntity("http://localhost:" + port + "/bands/all",
		// BandWrapper[].class);
		// String url = "http://localhost:" + String.valueOf(port) +
		// "/bands/all";
		String url = "http://localhost:9994/bands";
		when().get(url).then().statusCode(200);

		// System.out.println(response.getBody().getName());
		// System.out.println(response.getStatusCode());
	}

}