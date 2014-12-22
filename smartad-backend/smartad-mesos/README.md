# smartad-mesos (helloworld in scala scheduler)

This repository contains code to run shell commands in a distributed manner using Apache mesos

#start mesos

```
/usr/local/mesos-0.20.1/build/bin/mesos-master.sh --ip=127.0.0.1 --work_dir=/var/lib/mesos
/usr/local/mesos-0.20.1/build/bin/mesos-slave.sh  --master=127.0.0.1:5050
```

#build app

    mvn clean install

#architecture

```
--------------------------------------------------------------------------------------------------
                                           |
                                           |
       DistributedShellClient              |           DistributedShellScheduler
            (client)                       |                (scheduler)
     submits the tasks to the framework    |  takes tasks from client and runs on mesos cluster
                                           |
--------------------------------------------------------------------------------------------------
```

#run app

`$MESOS_HOME` refers to the mesos installation directory. 

Application needs access `mesos.so` shared library which can be found at `$MESOS_HOME/build/src/.libs`


```bash

$ java -cp target/smartad-mesos-0.0.1-SNAPSHOT.jar -Djava.library.path=$MESOS_HOME/build/src/.libs com.smartad.mesos.DistributedShellClient "/bin/echo hello prayagupd"

I1214 16:35:51.950331 28033 sched.cpp:139] Version: 0.20.1
I1214 16:35:51.957139 28048 sched.cpp:235] New master detected at master@127.0.0.1:5050
I1214 16:35:51.957432 28048 sched.cpp:243] No credentials provided. Attempting to register without authentication
I1214 16:35:51.958973 28048 sched.cpp:409] Framework registered with 20141214-160922-16777343-5050-21268-0001
offer id {
  value: "20141214-160922-16777343-5050-21268-2"
}
framework_id {
  value: "20141214-160922-16777343-5050-21268-0001"
}
slave_id {
  value: "20141214-160922-16777343-5050-21268-0"
}
hostname: "prayagupd"
resources {
  name: "cpus"
  type: SCALAR
  scalar {
    value: 4.0
  }
  role: "*"
}
resources {
  name: "mem"
  type: SCALAR
  scalar {
    value: 2603.0
  }
  role: "*"
}
resources {
  name: "disk"
  type: SCALAR
  scalar {
    value: 82702.0
  }
  role: "*"
}
resources {
  name: "ports"
  type: RANGES
  ranges {
    range {
      begin: 31000
      end: 32000
    }
  }
  role: "*"
}

received status update task_id {
  value: "task1418554252293"
}
state: TASK_RUNNING
slave_id {
  value: "20141214-160922-16777343-5050-21268-0"
}
timestamp: 1.418554252888782E9

received status update task_id {
  value: "task1418554252293"
}
state: TASK_FINISHED
message: "Command exited with status 0"
slave_id {
  value: "20141214-160922-16777343-5050-21268-0"
}
timestamp: 1.418554253889398E9

offer id {
  value: "20141214-160922-16777343-5050-21268-3"
}
framework_id {
  value: "20141214-160922-16777343-5050-21268-0001"
}
slave_id {
  value: "20141214-160922-16777343-5050-21268-0"
}
hostname: "prayagupd"
resources {
  name: "cpus"
  type: SCALAR
  scalar {
    value: 4.0
  }
  role: "*"
}
resources {
  name: "mem"
  type: SCALAR
  scalar {
    value: 2603.0
  }
  role: "*"
}
resources {
  name: "disk"
  type: SCALAR
  scalar {
    value: 82702.0
  }
  role: "*"
}
resources {
  name: "ports"
  type: RANGES
  ranges {
    range {
      begin: 31000
      end: 32000
    }
  }
  role: "*"
}


```

#output

![scheduler](https://raw.githubusercontent.com/prayagupd/smartad/master/smartad-mesos/mesos_scheduler.png)

#References

http://blog.madhukaraphatak.com/mesos-helloworld-scala/

