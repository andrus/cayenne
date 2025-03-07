/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.configuration;

import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.server.XMLPoolingDataSourceFactory;
import org.apache.cayenne.resource.Resource;
import org.apache.cayenne.util.XMLEncoder;
import org.apache.cayenne.util.XMLSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A descriptor of {@link DataNode} configuration.
 * 
 * @since 3.1
 */
public class DataNodeDescriptor implements ConfigurationNode, XMLSerializable,
        Serializable, Comparable<DataNodeDescriptor> {

    protected String name;
    protected Collection<String> dataMapNames;

    protected String parameters;
    protected String adapterType;
    protected String dataSourceFactoryType;
    protected String schemaUpdateStrategyType;

    protected DataSourceDescriptor dataSourceDescriptor;

    protected transient Resource configurationSource;

    /**
     * @since 3.1
     */
    protected DataChannelDescriptor dataChannelDescriptor;

    public DataNodeDescriptor() {
        this(null);
    }

    public DataNodeDescriptor(String name) {
        this.dataMapNames = new ArrayList<>();
        this.name = name;
    }

    /**
     * @since 3.1
     */
    public DataChannelDescriptor getDataChannelDescriptor() {
        return dataChannelDescriptor;
    }

    /**
     * @since 3.1
     */
    public void setDataChannelDescriptor(DataChannelDescriptor dataChannelDescriptor) {
        this.dataChannelDescriptor = dataChannelDescriptor;
    }

    @Override
    public int compareTo(DataNodeDescriptor o) {
        String o1 = getName();
        String o2 = o.getName();

        if (o1 == null) {
            return (o2 != null) ? -1 : 0;
        } else if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }

    @Override
    public <T> T acceptVisitor(ConfigurationNodeVisitor<T> visitor) {
        return visitor.visitDataNodeDescriptor(this);
    }

    @Override
    public void encodeAsXML(XMLEncoder encoder, ConfigurationNodeVisitor delegate) {
        encoder.start("node")
                .attribute("name", name, false)
                .attribute("adapter", adapterType, true)
                .attribute("factory", dataSourceFactoryType, true);
        
        if (!XMLPoolingDataSourceFactory.class.getName().equals(dataSourceFactoryType)) {
            encoder.attribute("parameters", parameters);
        }
        encoder.attribute("schema-update-strategy", schemaUpdateStrategyType, true);

        if (!dataMapNames.isEmpty()) {
            List<String> names = new ArrayList<>(dataMapNames);
            Collections.sort(names);
            for (String mapName : names) {
                encoder.start("map-ref").attribute("name", mapName).end();
            }
        }

        if (dataSourceDescriptor != null && XMLPoolingDataSourceFactory.class.getName().equals(dataSourceFactoryType)) {
            dataSourceDescriptor.encodeAsXML(encoder, delegate);
        }

        delegate.visitDataNodeDescriptor(this);
        encoder.end();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getDataMapNames() {
        return dataMapNames;
    }

    /**
     * Returns extra DataNodeDescriptor parameters. This property is often used by custom
     * {@link org.apache.cayenne.configuration.server.DataSourceFactory} to configure a DataSource. E.g. JNDIDataSourceFactory may
     * treat parameters String as a JNDI location of the DataSource, etc.
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * Sets extra DataNodeDescriptor parameters. This property is often used by custom
     * {@link org.apache.cayenne.configuration.server.DataSourceFactory} to configure a DataSource. E.g. JNDIDataSourceFactory may
     * treat parameters String as a JNDI location of the DataSource, etc.
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public void setAdapterType(String adapter) {
        this.adapterType = adapter;
    }

    public String getDataSourceFactoryType() {
        return dataSourceFactoryType;
    }

    public void setDataSourceFactoryType(String dataSourceFactory) {
        this.dataSourceFactoryType = dataSourceFactory;
    }

    public String getSchemaUpdateStrategyType() {
        return schemaUpdateStrategyType;
    }

    public void setSchemaUpdateStrategyType(String schemaUpdateStrategyClass) {
        this.schemaUpdateStrategyType = schemaUpdateStrategyClass;
    }

    public DataSourceDescriptor getDataSourceDescriptor() {
        return dataSourceDescriptor;
    }

    public void setDataSourceDescriptor(DataSourceDescriptor dataSourceDescriptor) {
        this.dataSourceDescriptor = dataSourceDescriptor;
    }

    /**
     * Returns configuration resource for this descriptor. Configuration is usually shared
     * with the parent {@link DataChannelDescriptor}.
     */
    public Resource getConfigurationSource() {
        return configurationSource;
    }

    /**
     * Sets configuration resource for this descriptor. Configuration is usually shared
     * with the parent {@link DataChannelDescriptor} and has to be synchronized when it
     * changes in the parent.
     */
    public void setConfigurationSource(Resource configurationResource) {
        this.configurationSource = configurationResource;
    }

}
