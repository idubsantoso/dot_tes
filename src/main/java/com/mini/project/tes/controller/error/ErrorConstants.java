package com.mini.project.tes.controller.error;

import java.net.URI;

/**
 * @author Winner [Alpabit]
 *
 * Oct 8, 2019
 */
public class ErrorConstants {
	 public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
	    public static final String ERR_VALIDATION = "error.validation";
	    public static final String PROBLEM_BASE_URL = "http://www.jhipster.tech/problem";
	    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");

	    private ErrorConstants() {
	    }
}
