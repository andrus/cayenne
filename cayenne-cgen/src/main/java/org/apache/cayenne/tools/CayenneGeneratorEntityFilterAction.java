/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.tools;

import org.apache.cayenne.dbsync.filter.NameFilter;
import org.apache.cayenne.map.DataMap;
import org.apache.cayenne.map.Embeddable;
import org.apache.cayenne.map.ObjEntity;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Performs entity filtering to build a collection of entities that should be used in
 * class generation.
 * 
 * @since 3.0
 */
class CayenneGeneratorEntityFilterAction {

    private NameFilter nameFilter;
    private boolean client;

    Collection<Embeddable> getFilteredEmbeddables(DataMap mainDataMap) {
        Collection<Embeddable> embeddables = new ArrayList<>(mainDataMap.getEmbeddables());

        // filter out excluded entities...

        // note that unlike entity, embeddable is matched by class name as it doesn't
        // have a symbolic name...
        embeddables.removeIf(e -> !nameFilter.isIncluded(e.getClassName()));

        return embeddables;
    }

    Collection<ObjEntity> getFilteredEntities(DataMap mainDataMap)
            throws MalformedURLException {

        Collection<ObjEntity> entities = new ArrayList<>(mainDataMap.getObjEntities());

        // filter out excluded entities...
        entities.removeIf(e -> e.isGeneric() || client && !e.isClientAllowed() || !nameFilter.isIncluded(e.getName()));

        return entities;
    }

    void setClient(boolean client) {
        this.client = client;
    }

    public void setNameFilter(NameFilter nameFilter) {
        this.nameFilter = nameFilter;
    }
}
