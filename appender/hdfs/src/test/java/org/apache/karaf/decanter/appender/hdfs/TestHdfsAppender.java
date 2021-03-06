/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.decanter.appender.hdfs;

import org.apache.karaf.decanter.marshaller.csv.CsvMarshaller;
import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

public class TestHdfsAppender {

    @Test
    public void testCreate() throws Exception {
        HdfsAppender appender = new HdfsAppender();
        appender.marshaller = new CsvMarshaller();
        Hashtable<String, Object> config = new Hashtable<>();
        config.put("hdfs.path", "target/hdfs/create");
        config.put("hdfs.mode", "create");
        appender.activate(config);
        HashMap<String, Object> data = new HashMap<>();
        data.put("foo", "bar");
        data.put("metric", 10);
        Event event = new Event("decanter/collect/create", data);
        appender.handleEvent(event);

        File file = new File("target/hdfs/create");
        Assert.assertTrue(file.exists());
    }

}
