/*******************************************************************************
* Copyright 2000-2014 JetBrains s.r.o.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*******************************************************************************/
package org.jetbrains.kotlin.core.resolve.lang.java.structure

import org.eclipse.jdt.core.dom.IVariableBinding
import org.jetbrains.kotlin.load.java.structure.JavaClass
import org.jetbrains.kotlin.load.java.structure.JavaField
import org.jetbrains.kotlin.load.java.structure.JavaType

public class EclipseJavaField(private val javaField: IVariableBinding) : EclipseJavaMember<IVariableBinding>(javaField), JavaField {
    override val hasConstantNotNullInitializer: Boolean
        get() = false
    
    override val initializerValue: Any? = binding.constantValue

    override val isEnumEntry: Boolean = binding.isEnumConstant()

    override val isFromSource: Boolean
        get() = binding.declaringClass.isFromSource

    override val type: JavaType
        get() = EclipseJavaType.create(binding.getType())
    
    override val containingClass: JavaClass
        get() = EclipseJavaClass(binding.getDeclaringClass())
}