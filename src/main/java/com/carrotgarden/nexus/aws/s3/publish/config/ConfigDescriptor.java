/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.nexus.aws.s3.publish.config;

import static org.sonatype.nexus.plugins.capabilities.CapabilityType.*;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.formfields.FormField;
import org.sonatype.nexus.plugins.capabilities.CapabilityDescriptor;
import org.sonatype.nexus.plugins.capabilities.CapabilityType;
import org.sonatype.nexus.capability.support.CapabilityDescriptorSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * capability configuration form
 */
@Singleton
@Named(ConfigDescriptor.NAME)
public class ConfigDescriptor extends CapabilityDescriptorSupport implements
		CapabilityDescriptor {

	/**
	 * plug-in capability UUID
	 */
	public static final String NAME = "carrot.config.aws.s3.publish";

	public static final CapabilityType TYPE = capabilityType(NAME);

	@Override
	public CapabilityType type() {
		return TYPE;
	}

	@Override
	public String name() {
		return NAME;
	}

	@Override
	public List<FormField> formFields() {
		return Arrays.asList(Form.formFields());
	}
}
