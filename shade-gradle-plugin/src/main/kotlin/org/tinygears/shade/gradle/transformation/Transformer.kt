/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygears.shade.gradle.transformation

import org.apache.tools.zip.ZipOutputStream
import org.gradle.api.Named
import org.gradle.api.file.FileTreeElement
import org.gradle.api.tasks.Internal

/**
 * Modified from org.apache.maven.plugins.shade.resource.ResourceTransformer.java
 *
 * @author Jason van Zyl
 * @author Charlie Knudsen
 * @author John Engelman
 */
interface Transformer: Named {
    fun canTransformResource(element: FileTreeElement): Boolean
    fun transform(context: TransformerContext)
    fun hasTransformedResource(): Boolean
    fun modifyOutputStream(os: ZipOutputStream, preserveFileTimestamps: Boolean)

    @Internal
    override fun getName(): String {
        return this.javaClass.simpleName
    }
}
