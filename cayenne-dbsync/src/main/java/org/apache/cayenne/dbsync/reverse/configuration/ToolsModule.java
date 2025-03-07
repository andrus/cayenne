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

package org.apache.cayenne.dbsync.reverse.configuration;

import org.apache.cayenne.access.flush.DataDomainFlushActionFactory;
import org.apache.cayenne.access.flush.DefaultDataDomainFlushActionFactory;
import org.apache.cayenne.access.flush.operation.DefaultDbRowOpSorter;
import org.apache.cayenne.access.flush.operation.DbRowOpSorter;
import org.apache.cayenne.access.translator.batch.BatchTranslatorFactory;
import org.apache.cayenne.access.translator.batch.DefaultBatchTranslatorFactory;
import org.apache.cayenne.access.types.DefaultValueObjectTypeRegistry;
import org.apache.cayenne.access.types.ValueObjectTypeRegistry;
import org.apache.cayenne.configuration.Constants;
import org.apache.cayenne.configuration.DataChannelDescriptorLoader;
import org.apache.cayenne.configuration.DataMapLoader;
import org.apache.cayenne.configuration.DefaultRuntimeProperties;
import org.apache.cayenne.configuration.RuntimeProperties;
import org.apache.cayenne.configuration.server.DataSourceFactory;
import org.apache.cayenne.configuration.server.DbAdapterFactory;
import org.apache.cayenne.configuration.server.DefaultDbAdapterFactory;
import org.apache.cayenne.configuration.server.PkGeneratorFactoryProvider;
import org.apache.cayenne.configuration.server.ServerModule;
import org.apache.cayenne.configuration.xml.DataChannelMetaData;
import org.apache.cayenne.configuration.xml.DefaultDataChannelMetaData;
import org.apache.cayenne.configuration.xml.HandlerFactory;
import org.apache.cayenne.configuration.xml.XMLDataChannelDescriptorLoader;
import org.apache.cayenne.configuration.xml.XMLDataMapLoader;
import org.apache.cayenne.configuration.xml.XMLReaderProvider;
import org.apache.cayenne.dba.JdbcPkGenerator;
import org.apache.cayenne.dba.PkGenerator;
import org.apache.cayenne.dba.db2.DB2Adapter;
import org.apache.cayenne.dba.db2.DB2PkGenerator;
import org.apache.cayenne.dba.db2.DB2Sniffer;
import org.apache.cayenne.dba.derby.DerbyAdapter;
import org.apache.cayenne.dba.derby.DerbyPkGenerator;
import org.apache.cayenne.dba.derby.DerbySniffer;
import org.apache.cayenne.dba.firebird.FirebirdSniffer;
import org.apache.cayenne.dba.frontbase.FrontBaseAdapter;
import org.apache.cayenne.dba.frontbase.FrontBasePkGenerator;
import org.apache.cayenne.dba.frontbase.FrontBaseSniffer;
import org.apache.cayenne.dba.h2.H2Adapter;
import org.apache.cayenne.dba.h2.H2PkGenerator;
import org.apache.cayenne.dba.h2.H2Sniffer;
import org.apache.cayenne.dba.hsqldb.HSQLDBSniffer;
import org.apache.cayenne.dba.ingres.IngresAdapter;
import org.apache.cayenne.dba.ingres.IngresPkGenerator;
import org.apache.cayenne.dba.ingres.IngresSniffer;
import org.apache.cayenne.dba.mariadb.MariaDBSniffer;
import org.apache.cayenne.dba.mysql.MySQLAdapter;
import org.apache.cayenne.dba.mysql.MySQLPkGenerator;
import org.apache.cayenne.dba.mysql.MySQLSniffer;
import org.apache.cayenne.dba.oracle.Oracle8Adapter;
import org.apache.cayenne.dba.oracle.OracleAdapter;
import org.apache.cayenne.dba.oracle.OraclePkGenerator;
import org.apache.cayenne.dba.oracle.OracleSniffer;
import org.apache.cayenne.dba.postgres.PostgresAdapter;
import org.apache.cayenne.dba.postgres.PostgresPkGenerator;
import org.apache.cayenne.dba.postgres.PostgresSniffer;
import org.apache.cayenne.dba.sqlite.SQLiteSniffer;
import org.apache.cayenne.dba.sqlserver.SQLServerAdapter;
import org.apache.cayenne.dba.sqlserver.SQLServerSniffer;
import org.apache.cayenne.dba.sybase.SybaseAdapter;
import org.apache.cayenne.dba.sybase.SybasePkGenerator;
import org.apache.cayenne.dba.sybase.SybaseSniffer;
import org.apache.cayenne.di.AdhocObjectFactory;
import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.ClassLoaderManager;
import org.apache.cayenne.di.Key;
import org.apache.cayenne.di.Module;
import org.apache.cayenne.di.spi.DefaultAdhocObjectFactory;
import org.apache.cayenne.di.spi.DefaultClassLoaderManager;
import org.apache.cayenne.log.JdbcEventLogger;
import org.apache.cayenne.log.Slf4jJdbcEventLogger;
import org.apache.cayenne.project.ProjectModule;
import org.apache.cayenne.project.extension.ExtensionAwareHandlerFactory;
import org.apache.cayenne.reflect.generic.ValueComparisonStrategyFactory;
import org.apache.cayenne.reflect.generic.DefaultValueComparisonStrategyFactory;
import org.apache.cayenne.resource.ClassLoaderResourceLocator;
import org.apache.cayenne.resource.ResourceLocator;
import org.slf4j.Logger;
import org.xml.sax.XMLReader;

import java.util.Objects;

/**
 * A DI module to bootstrap DI container for Cayenne Ant tasks and Maven
 * plugins.
 * 
 * @since 4.0
 */
public class ToolsModule implements Module {

    private Logger logger;

    public ToolsModule(Logger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    public void configure(Binder binder) {

        binder.bind(Logger.class).toInstance(logger);

        // configure empty global stack properties
        ServerModule.contributeProperties(binder);
        ServerModule.contributeDefaultTypes(binder);
        ServerModule.contributeUserTypes(binder);
        ServerModule.contributeTypeFactories(binder);
        ServerModule.contributeValueObjectTypes(binder);

        binder.bind(ValueObjectTypeRegistry.class).to(DefaultValueObjectTypeRegistry.class);
        binder.bind(ValueComparisonStrategyFactory.class).to(DefaultValueComparisonStrategyFactory.class);

        binder.bind(ClassLoaderManager.class).to(DefaultClassLoaderManager.class);
        binder.bind(AdhocObjectFactory.class).to(DefaultAdhocObjectFactory.class);
        binder.bind(ResourceLocator.class).to(ClassLoaderResourceLocator.class);
        binder.bind(Key.get(ResourceLocator.class, Constants.SERVER_RESOURCE_LOCATOR)).to(ClassLoaderResourceLocator.class);

        binder.bind(RuntimeProperties.class).to(DefaultRuntimeProperties.class);
        binder.bind(BatchTranslatorFactory.class).to(DefaultBatchTranslatorFactory.class);
        binder.bind(JdbcEventLogger.class).to(Slf4jJdbcEventLogger.class);

        ServerModule.contributeAdapterDetectors(binder)
                .add(FirebirdSniffer.class)
                .add(FrontBaseSniffer.class)
                .add(IngresSniffer.class)
                .add(SQLiteSniffer.class)
                .add(DB2Sniffer.class)
                .add(H2Sniffer.class)
                .add(HSQLDBSniffer.class)
                .add(SybaseSniffer.class)
                .add(DerbySniffer.class)
                .add(SQLServerSniffer.class)
                .add(OracleSniffer.class)
                .add(PostgresSniffer.class)
                .add(MySQLSniffer.class)
                .add(MariaDBSniffer.class);

        binder.bind(PkGeneratorFactoryProvider.class).to(PkGeneratorFactoryProvider.class);
        binder.bind(PkGenerator.class).to(JdbcPkGenerator.class);
        ServerModule.contributePkGenerators(binder)
                .put(DB2Adapter.class.getName(), DB2PkGenerator.class)
                .put(DerbyAdapter.class.getName(), DerbyPkGenerator.class)
                .put(FrontBaseAdapter.class.getName(), FrontBasePkGenerator.class)
                .put(H2Adapter.class.getName(), H2PkGenerator.class)
                .put(IngresAdapter.class.getName(), IngresPkGenerator.class)
                .put(MySQLAdapter.class.getName(), MySQLPkGenerator.class)
                .put(OracleAdapter.class.getName(), OraclePkGenerator.class)
                .put(Oracle8Adapter.class.getName(), OraclePkGenerator.class)
                .put(PostgresAdapter.class.getName(), PostgresPkGenerator.class)
                .put(SQLServerAdapter.class.getName(), SybasePkGenerator.class)
                .put(SybaseAdapter.class.getName(), SybasePkGenerator.class);

        binder.bind(DbAdapterFactory.class).to(DefaultDbAdapterFactory.class);
        binder.bind(DataSourceFactory.class).to(DriverDataSourceFactory.class);

        binder.bind(DataMapLoader.class).to(XMLDataMapLoader.class);
        binder.bind(DataChannelDescriptorLoader.class).to(XMLDataChannelDescriptorLoader.class);
        binder.bind(HandlerFactory.class).to(ExtensionAwareHandlerFactory.class);
        binder.bind(DataChannelMetaData.class).to(DefaultDataChannelMetaData.class);
        binder.bind(XMLReader.class).toProviderInstance(new XMLReaderProvider(true)).withoutScope();
        binder.bind(DataDomainFlushActionFactory.class).to(DefaultDataDomainFlushActionFactory.class);
        binder.bind(DbRowOpSorter.class).to(DefaultDbRowOpSorter.class);

        ProjectModule.contributeExtensions(binder);
    }

}
