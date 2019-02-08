/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.digitalfrontiers.autoconfigure.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Flyway Pro and Enterprise Edition.
 */
@Configuration
@ConditionalOnClass(Flyway.class)
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "spring.flyway", name = "license-key")
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@AutoConfigureBefore(FlywayAutoConfiguration.class)
@EnableConfigurationProperties(FlywayLicenseProperties.class)
public class FlywayLicenseAutoConfiguration {

	@Bean
	public FlywayConfigurationCustomizer flywayLicenseKeyConfigurationCustomizer(FlywayLicenseProperties flywayLicenseProperties) {
		return new FlywayLicenseKeyConfigurationCustomizer(flywayLicenseProperties);
	}

}
