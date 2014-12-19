package com.smartad.mesos

/**
 * creates instance of Scheduler and submit tasks to it.
 * author prayagupd 
 * on 14/12/14.
 */

import org.apache.mesos.MesosSchedulerDriver
import org.apache.mesos.Protos.FrameworkInfo

/**
 * Client program which will launch shell commands on cluster
 * Read README.md for example invocation.
 */

object DistributedShellClient {

  def main(args: Array[String]) {

    val framework = FrameworkInfo.newBuilder.
    setName("DistributedShell").
    setUser("").
    setRole("*").
    setCheckpoint(false).
    setFailoverTimeout(0.0d).
    build()

    //create instance of schedule and connect to mesos
    val scheduler = new DistributedShellScheduler
    //submit shell commands
    scheduler.submitTasks(args:_*)
    val mesosURL = "localhost:5050"
    val driver = new MesosSchedulerDriver(scheduler,framework,mesosURL)
    //run the driver
    driver.run()

  }

}
