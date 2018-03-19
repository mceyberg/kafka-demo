package com.optum.clm.producer

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 *
 */
@SpringBootTest
class ProducerApplicationIT extends Specification {

	def 'Should start application'() {

		when: 'something'

		then: 'the application should start fine'
			true
	}
}
