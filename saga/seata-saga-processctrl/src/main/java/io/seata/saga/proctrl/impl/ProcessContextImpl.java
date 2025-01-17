/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.seata.saga.proctrl.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.seata.saga.proctrl.HierarchicalProcessContext;
import io.seata.saga.proctrl.Instruction;
import io.seata.saga.proctrl.ProcessContext;

/**
 * The default process context implementation
 *
 */
public class ProcessContextImpl implements HierarchicalProcessContext, ProcessContext {

    private Map<String, Object> variables = new ConcurrentHashMap<>();
    private Instruction instruction;
    private ProcessContext parent;

    @Override
    public Object getVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }

        if (parent != null) {
            return parent.getVariable(name);
        }

        return null;
    }

    @Override
    public void setVariable(String name, Object value) {
        if (variables.containsKey(name)) {
            setVariableLocally(name, value);
        } else {
            if (parent != null) {
                parent.setVariable(name, value);
            } else {
                setVariableLocally(name, value);
            }
        }
    }

    @Override
    public Map<String, Object> getVariables() {
        final Map<String, Object> collectedVariables = new HashMap<>();

        if (parent != null) {
            collectedVariables.putAll(parent.getVariables());
        }
        variables.forEach(collectedVariables::put);
        return collectedVariables;
    }

    @Override
    public void setVariables(final Map<String, Object> variables) {
        if (variables != null) {
            variables.forEach(this::setVariable);
        }
    }

    @Override
    public Object getVariableLocally(String name) {
        return variables.get(name);
    }

    @Override
    public void setVariableLocally(String name, Object value) {
        variables.put(name, value);
    }

    @Override
    public Map<String, Object> getVariablesLocally() {
        return Collections.unmodifiableMap(variables);
    }

    @Override
    public void setVariablesLocally(Map<String, Object> variables) {
        this.variables.putAll(variables);
    }

    @Override
    public boolean hasVariable(String name) {
        if (variables.containsKey(name)) {
            return true;
        }
        if (parent != null) {
            return parent.hasVariable(name);
        }
        return false;
    }

    @Override
    public Instruction getInstruction() {
        return instruction;
    }

    @Override
    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public <T extends Instruction> T getInstruction(Class<T> clazz) {
        return (T)instruction;
    }

    @Override
    public boolean hasVariableLocal(String name) {
        return variables.containsKey(name);
    }

    @Override
    public Object removeVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.remove(name);
        }

        if (parent != null) {
            return parent.removeVariable(name);
        }

        return null;
    }

    @Override
    public Object removeVariableLocally(String name) {
        return variables.remove(name);
    }

    @Override
    public void clearLocally() {
        variables.clear();
    }

    public ProcessContext getParent() {
        return parent;
    }

    public void setParent(ProcessContext parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "{" + "variables=" + variables + ", instruction=" + instruction + ", parent=" + parent + '}';
    }
}
