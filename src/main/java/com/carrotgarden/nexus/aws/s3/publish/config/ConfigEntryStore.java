/**
 * Copyright (C) 2010-2012 Andrei Pozolotin <Andrei.Pozolotin@gmail.com>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.carrotgarden.nexus.aws.s3.publish.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * [ repo-id : config-entry-map ]
 */
@SuppressWarnings("serial")
public class ConfigEntryStore extends ConcurrentHashMap<String, ConfigEntryMap> {

}
