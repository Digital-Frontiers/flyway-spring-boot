package de.digitalfrontiers.autoconfigure.flyway;

import org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;

public class FlywayLicenseAutoConfigurationTests {

	@SpringBootApplication
	interface TestContext {}



	@Test
	public void assertThatAutoConfigIsInvokedWhenLicenseKeyPropertyIsSet() {
		try {
			// Context creation will fail because we do not have a Flyway Pro/Enterprise license for testing
			SpringApplication.run(TestContext.class, "--spring.flyway.licenseKey=testLicenseKey");
			Assert.fail();
		} catch (BeanCreationException e) {
			//assert that FlywayProUpgradeRequiredException is thrown. This implies we tried to perform a
			//Flyway Pro/Enterprise operation, in this case trying to set a license key.
			assertThat(e).hasRootCauseInstanceOf(FlywayProUpgradeRequiredException.class);
		}
	}


	@Test
	public void assertThatAutoConfigIsNotInvokedWhenLicenseKeyPropertyIsMissing() {
		SpringApplication.run(TestContext.class);
	}

}
