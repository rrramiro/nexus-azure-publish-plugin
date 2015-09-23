/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.nexus.aws.s3.publish.config;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.carrotgarden.nexus.aws.s3.publish.amazon.AmazonService;
import com.carrotgarden.nexus.aws.s3.publish.mailer.Report;
import com.carrotgarden.nexus.aws.s3.publish.util.ConfigHelp;
import org.sonatype.nexus.proxy.maven.gav.Gav;
import org.sonatype.nexus.proxy.maven.gav.GavCalculator;

/** public view of plug-in capabilities */
public class ConfigEntry {

	private GavCalculator calculator;
	private ConfigState configState;
	private AmazonService amazonManager;
	private volatile ConfigBean configBean;


	private Pattern includePattern;
	private Pattern excludePattern;

	private void includePattern(final ConfigBean configBean) {

		includePattern = ConfigHelp.defaultInclude();

	}

	private void excludePattern(final ConfigBean configBean) {

		if (configBean.enableExclude()) {
			final String defaultPattern = ConfigHelp.defaultExclude().pattern();
			final String customPattern = configBean.excludePattern();
			final String resultPattern = defaultPattern + "|" + customPattern;
			excludePattern = excludeCustom(resultPattern);
		} else {
			excludePattern = ConfigHelp.defaultExclude();
		}

	}

	private Pattern excludeCustom(final String pattern) {
		try {
			return Pattern.compile(pattern);
		} catch (final Exception e) {
			return ConfigHelp.defaultExclude();
		}
	}

	private Set<Report> reportSubscribeSet;


	private void reportSubscribeSet(final String reportText) {

		reportSubscribeSet = Report.reportSet(reportText);

	}

	/** UUID of capability */
	String configId();

	/** current capability life cycle state */
	public ConfigState configState() {
		return configState;
	}

	/** compare against state */
	public boolean isConfigState(final ConfigState state) {
		return configState == state;
	}

	/** amazon provider serving this capability */
	public AmazonService amazonService() {
		return amazonManager;
	}

	/** repository id : '*' or group-id or repo-id */
	public String comboId() {
		return configBean.comboId();
	}

	/** should exclude repo path from publication? */
	public boolean isExcluded(final String path) {

		// if (!configBean.publishSnapshots() && isSnapshot(path)) {
		// return true;
		// }

		// if (!configBean.publishReleases() && isRelease(path)) {
		// return true;
		// }

		/** force exclude */
		if (excludePattern.matcher(path).matches()) {
			return true;
		}

		/** force include */
		if (includePattern.matcher(path).matches()) {
			return false;
		}

		/** permit only valid artifact */
		final Gav gav = calculator.pathToGav(path);
		if (gav == null) {
			return true;
		} else {
			return false;
		}

	}

	/** is given report included in subscriptions? */
	public boolean isSubscribed(final Report report) {

		if (!configBean.enableEmail()) {
			return false;
		}

		return reportSubscribeSet.contains(report);

	}


	/** report recipient list */
	List<String> reportEmailList();

	/** report subscription list */
	Set<Report> reportSubscribeSet();



}
