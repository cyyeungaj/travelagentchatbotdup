/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;


// import com.example.bot.spring.scheduler.ThreadPoolTaskSchedulerConfig;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
// @Import(ThreadPoolTaskSchedulerConfig.class)
public class KitchenSinkApplication {
    static Path downloadedContentDir;

    public static void main(String[] args) throws IOException {
        downloadedContentDir = Files.createTempDirectory("line-bot");
        // check();
        
        SpringApplication.run(KitchenSinkApplication.class, args);
        // autowiredtesterImp a = (autowiredtesterImp) SpringApplication.run(KitchenSinkApplication.class, args).getBean("autowiredtesterImp");
    }
    // public void check(){
    //   if(threadPoolTaskScheduler == null)
    //     log.info("threadPoolTaskScheduler in main is null");
    // }
}
