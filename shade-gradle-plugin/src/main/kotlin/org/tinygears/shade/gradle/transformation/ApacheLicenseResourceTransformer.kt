/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.tinygears.shade.gradle.transformation

import org.apache.tools.zip.ZipOutputStream
import org.gradle.api.file.FileTreeElement

/**
 * Prevents duplicate copies of the license
 * <p>
 * Modified from org.apache.maven.plugins.shade.resource.ApacheLicenseResourceTransformer.java
 */
class ApacheLicenseResourceTransformer: Transformer {
    override fun canTransformResource(element: FileTreeElement): Boolean {
        val path = element.relativePath.pathString
        return LICENSE_PATH.equals(path, true) ||
               LICENSE_TXT_PATH.regionMatches(0, path, 0, LICENSE_TXT_PATH.length, true)
    }

    override fun transform(context: TransformerContext) {}

    override fun hasTransformedResource(): Boolean {
        return false
    }

    override fun modifyOutputStream(os: ZipOutputStream, preserveFileTimestamps: Boolean) {}

    companion object {
        private const val LICENSE_PATH     = "META-INF/LICENSE"
        private const val LICENSE_TXT_PATH = "META-INF/LICENSE.txt"
    }
}
