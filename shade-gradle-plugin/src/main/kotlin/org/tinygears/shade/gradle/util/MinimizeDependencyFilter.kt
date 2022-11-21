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

package org.tinygears.shade.gradle.util

import org.gradle.api.Project
import org.gradle.api.artifacts.ResolvedDependency

internal class MinimizeDependencyFilter constructor(project: Project): AbstractDependencyFilter(project) {

    override fun resolve(dependencies:         Set<ResolvedDependency>,
                         includedDependencies: MutableSet<ResolvedDependency>,
                         excludedDependencies: MutableSet<ResolvedDependency>) {

        dependencies.forEach {
            if (isIncluded(it) && !isParentExcluded(excludedDependencies, it)) {
                includedDependencies.add(it)
                resolve(it.children, includedDependencies, excludedDependencies)
            } else {
                excludedDependencies.add(it)
            }
        }
    }

    private fun isParentExcluded(excludedDependencies: Set<ResolvedDependency>, dependency: ResolvedDependency): Boolean {
        return excludedDependencies.any { dependency.parents.contains(it) }
    }
}